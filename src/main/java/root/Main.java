package root;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import root.classes.*;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String [] args){
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        ExternalServiceImpl externalService = applicationContext.getBean(ExternalServiceImpl.class);
        ExternalInfoProcess externalInfoProcess = applicationContext.getBean(ExternalInfoProcess.class);
        Flow flow = applicationContext.getBean(Flow.class);

        flow.run(1);
        flow.run(2);
        flow.run(2);
        flow.run(3);
        flow.run(4);

        BeanFactoryPostProcessorSample b = new BeanFactoryPostProcessorSample();

        b.postProcessBeanFactory(applicationContext.getBeanFactory());

        CacheResultBeanPostProcessor c = new CacheResultBeanPostProcessor();

        String [] beanNames = applicationContext.getBeanFactory().getBeanDefinitionNames();

        for(String beanName: beanNames){
            Object bean = applicationContext.getBean(beanName);

            c.postProcessAfterInitialization(bean, beanName);

        }

        externalService.clearTestMap();
        applicationContext.close();
    }
}
