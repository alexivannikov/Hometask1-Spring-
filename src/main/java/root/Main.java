package root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import root.classes.*;
import root.interfaces.*;
import root.interfaces.Process;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String [] args){
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        Flow flow = applicationContext.getBean(Flow.class);

        flow.run(1);
        flow.run(2);
        flow.run(2);
        flow.run(3);
        flow.run(4);

        BeanFactoryPostProcessor b = applicationContext.getBean(BeanFactoryPostProcessorSample.class);
        BeanPostProcessor c = applicationContext.getBean(CacheResultBeanPostProcessor.class);

        b.postProcessBeanFactory(applicationContext.getBeanFactory());

        String [] beanNames = applicationContext.getBeanFactory().getBeanDefinitionNames();

        for(String beanName: beanNames){
            Object bean = applicationContext.getBean(beanName);

            c.postProcessAfterInitialization(bean, beanName);

        }

        //externalService.clearTestMap();
        applicationContext.close();
    }
}
