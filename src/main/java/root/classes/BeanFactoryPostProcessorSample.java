package root.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import javax.cache.annotation.CacheResult;
import java.lang.reflect.Method;

@Component("beanFactoryPostProcessorSample")
public class BeanFactoryPostProcessorSample implements BeanFactoryPostProcessor {
    private static Logger LOGGER = LoggerFactory.getLogger(BeanFactoryPostProcessorSample.class);

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String [] names = configurableListableBeanFactory.getBeanDefinitionNames();

        for(String name: names){
            BeanDefinition b = configurableListableBeanFactory.getBeanDefinition(name);

            if(!b.isPrototype()){
                String beanName = b.getBeanClassName();

                /*try{

                }
                catch(ClassNotFoundException ex){
                    LOGGER.error("Class not found: " + ex.getMessage());
                }*/
            }
        }
    }
}
