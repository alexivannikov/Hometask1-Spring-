package root;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import root.interfaces.Process;

@Component("externalInfoProcessClass")

public class ExternalInfoProcess implements Process {
    @Value("${id}")
    private Integer id;

    public ExternalInfoProcess(){

    }

    public boolean run(){
        System.out.println(id);

        return false;
    }
}
