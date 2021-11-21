package root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

import javax.cache.annotation.CacheResult;
import java.lang.annotation.Annotation;

public class BeanFactoryPostProcessorSample implements BeanFactoryPostProcessor {
    private static Logger logger = LoggerFactory.getLogger(BeanFactoryPostProcessorSample.class);

    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        String [] names = configurableListableBeanFactory.getBeanDefinitionNames();

        for(String name: names){
            BeanDefinition b = configurableListableBeanFactory.getBeanDefinition(name);
            CacheResult annotation = null;
            String beanName = b.getBeanClassName();

            try{
                Class<?> beanClass = Class.forName(beanName);
                annotation = beanClass.getAnnotation(CacheResult.class);
            }
            catch(ClassNotFoundException ex){
                logger.error("Class not found: " + ex.getMessage());
            }

            if(b.isPrototype() && annotation != null){
                logger.debug("Bean " + beanName + " contains annotations @Scope(\"prototype\") and @CacheResult");
            }


        }
    }
}
