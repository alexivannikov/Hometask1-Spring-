package root.classes;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import javax.cache.annotation.CacheResult;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component("beanFactoryPostProcessorSample")
public class BeanFactoryPostProcessorSample implements BeanFactoryPostProcessor {
    private static Logger LOGGER = LoggerFactory.getLogger(BeanFactoryPostProcessorSample.class);

    @SneakyThrows
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String [] names = configurableListableBeanFactory.getBeanDefinitionNames();

        for(String name: names){
            BeanDefinition b = configurableListableBeanFactory.getBeanDefinition(name);

            if(!b.isPrototype()){
                String beanName = b.getBeanClassName();
                Method [] methods = Class.forName(beanName).getDeclaredMethods();

                for(Method method: methods){
                    if(method.isAnnotationPresent(CacheResult.class)){
                        LOGGER.warn("BeanFactoryPostProcessor log: bean " + beanName + " has annotation @CacheResult on method " + method.getName());
                    }
                }
            }
        }
    }
}
