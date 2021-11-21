package root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.stereotype.Component;
import root.interfaces.Process;

@Component("externalInfoProcessClass")
@Lazy
public class ExternalInfoProcess implements Process {
    //@Value("${id}")
    private static final Logger logger = LoggerFactory.getLogger(ExternalInfoProcess.class);
    private Integer idNotProcess;

    public ExternalInfoProcess() {

    }

    public ExternalInfoProcess(Integer idNotProcess){
        this.idNotProcess = idNotProcess;
    }

    public boolean run(Integer value){
        if(value == this.idNotProcess){
            logger.info("External info id is equal to idNotProcess. Returned false");

            return false;
        }
        else{
            logger.info("External info id is not equal to idNotProcess. Returned true");

            return true;
        }

    }
}
