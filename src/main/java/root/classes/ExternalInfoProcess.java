package root.classes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import root.interfaces.Process;

import javax.cache.annotation.CacheResult;

@Component("externalInfoProcessClass")
@Lazy
@Scope("prototype")
@CacheResult
public class ExternalInfoProcess implements Process {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExternalInfoProcess.class);
    @Value("${id}")
    private Integer idNotProcess;

    public ExternalInfoProcess() {

    }

    public ExternalInfoProcess(Integer idNotProcess){
        this.idNotProcess = idNotProcess;
    }

    public boolean run(Integer value){
        if(value == this.idNotProcess){
            LOGGER.info("External info id is equal to idNotProcess. Returned false");

            return false;
        }
        else{
            LOGGER.info("External info id is not equal to idNotProcess. Returned true");

            return true;
        }

    }
}
