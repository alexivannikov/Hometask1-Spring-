package root.classes;

import lombok.SneakyThrows;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheResult;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

@Component
public class CacheResultBeanPostProcessor implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheResultBeanPostProcessor.class);

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @SneakyThrows
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException{
        Method[] methods = bean.getClass().getDeclaredMethods();

        LOGGER.info(beanName);

        for(Method method: methods){
            if(method.isAnnotationPresent(CacheResult.class)){
                LOGGER.warn("Bean " + beanName + " has annotation @CacheResult on method " + method.getName());

                ProxyFactory proxyFactory = new ProxyFactory(bean);

                /*CacheResultMethodInterceptor - это Advice. В нем содержится логика кеширования*/
                proxyFactory.addAdvice(new CacheResultMethodInterceptor());
            }
        }

        return bean;
    }
}
