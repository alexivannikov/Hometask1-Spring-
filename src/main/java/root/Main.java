package root;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {
    public static void main(String [] args){
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(Main.class);

        ExternalServiceImpl bean = applicationContext.getBean(ExternalServiceImpl.class);

        bean.getExternalInfo(1);
        bean.clearTestMap();

        ExternalInfoProcess newBean = applicationContext.getBean(ExternalInfoProcess.class);

        new ExternalInfoProcess().run();
        applicationContext.close();
    }
}
