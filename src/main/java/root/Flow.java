package root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Flow {
    private static Logger logger = LoggerFactory.getLogger(Flow.class);

    private ExternalServiceImpl externalService;
    private ExternalInfoProcess externalInfoProcess;

    @Autowired
    public Flow(ExternalServiceImpl externalService, ExternalInfoProcess externalInfoProcess){
        this.externalService = externalService;
        this.externalInfoProcess = externalInfoProcess;
    }

    public void run(Integer id){
        ExternalInfo externalInfo = externalService.getExternalInfo(id);

        if(externalInfo.getInfo() != null){
            externalInfoProcess.run(id);
        }
        else{
            logger.info(String.valueOf(externalInfoProcess.getClass()));
        }
    }
    
}
