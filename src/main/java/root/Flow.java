package root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import root.interfaces.*;
import root.interfaces.Process;

@Component
public class Flow {
    private static Logger logger = LoggerFactory.getLogger(Flow.class);

    private ExternalService externalService;
    private Process process;

    @Autowired
    public Flow(ExternalService externalService, Process process){
        this.externalService = externalService;
        this.process = process;
    }

    public void run(Integer id){
        ExternalInfo externalInfo = externalService.getExternalInfo(id);

        if(externalInfo.getInfo() != null){
            process.run(id);
        }
        else{
            logger.info(String.valueOf(externalInfo.getClass()));
        }
    }
    
}
