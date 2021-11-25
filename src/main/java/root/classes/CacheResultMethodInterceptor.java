package root.classes;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;

import javax.cache.annotation.CacheResult;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CacheResultMethodInterceptor implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheResultMethodInterceptor.class);

    private static final Map <String, Map<MethodArgs, Object>> CACHE = new ConcurrentHashMap<>();


    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Method method = methodInvocation.getMethod();
        boolean isAnnotationMethod = method.isAnnotationPresent(CacheResult.class); //Проверка, что метод помечен аннотацией @CacheResult

        /*Если метод интерфейса не аннотирован, то пробегаемся по имплементациям интерфейса
        и ищем в них аннотированные методы*/
        if(!isAnnotationMethod){
            for(Method declaredMethod: methodInvocation.getThis().getClass().getDeclaredMethods()){
                if(method.getName().equals(declaredMethod.getName()) && AnnotationUtils.findAnnotation(declaredMethod, CacheResult.class) != null){
                    isAnnotationMethod = true;

                    break;
                }
            }
        }

        if(isAnnotationMethod){
            LOGGER.warn("Method " + method.getName() + " has annotation @CacheResult");

            Map <MethodArgs, Object> methodArgsObjectMap = CACHE.get(method.getName());

            if(methodArgsObjectMap != null){
                LOGGER.info("Method " + method.getName() + " has cache " + methodArgsObjectMap);
                LOGGER.info("CACHE size++++++++++++++++++"  + CACHE.size());

                MethodArgs methodArgs = getMethodArgs(methodInvocation.getArguments());

                LOGGER.info("Cache result by method " + method.getName() + " with arguments: " + methodInvocation.getArguments());

                Object result = methodArgsObjectMap.get(methodArgs);

                if(result != null){
                    LOGGER.info("Returned result from cache of method " + method.getName() + " with arguments " + methodInvocation.getArguments() + ": " + result);

                    return result;
                }
                else {
                    LOGGER.info("Call method " + method.getName() + " first with recording result into cache");

                    result = methodInvocation.proceed();

                    methodArgsObjectMap.put(methodArgs, result);

                    return result;
                }
            }
            else{
                LOGGER.info("Method " + method.getName() + " has no cache");

                Object result = methodInvocation.proceed();
                methodArgsObjectMap = new ConcurrentHashMap();

                methodArgsObjectMap.put(getMethodArgs(methodInvocation.getArguments()), result);

                CACHE.put(method.getName(), methodArgsObjectMap);
            }
        }

        return methodInvocation.proceed();
    }

    private MethodArgs getMethodArgs(Object [] args){
        LinkedList<Object> argsList = new LinkedList<>();
        Collections.addAll(argsList, args);

        return new MethodArgs(argsList);
    }
}
