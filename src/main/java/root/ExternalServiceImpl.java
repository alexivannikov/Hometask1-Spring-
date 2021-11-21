package root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import root.interfaces.ExternalService;

import javax.cache.annotation.CacheResult;
import java.util.HashMap;
import java.util.Map;

@Component("externalServiceImplClass")
@Scope("prototype")
@CacheResult
public class ExternalServiceImpl implements ExternalService {
    private static final Logger logger = LoggerFactory.getLogger(ExternalServiceImpl.class);

    private Map<Integer, ExternalInfo> testMap = new HashMap<>();

    public ExternalServiceImpl(){
        this.testMap.put(1, new ExternalInfo(1, null));
        this.testMap.put(2, new ExternalInfo(2, "hashinfo"));
        this.testMap.put(3, new ExternalInfo(3, "info"));
        this.testMap.put(4, new ExternalInfo(4, "information"));
    }

    @CacheResult
    public ExternalInfo getExternalInfo(Integer id){
        logger.info("Method getExternalInfo called");

        return testMap.get(id);
    }

    public Map<Integer, ExternalInfo> getTestMap(){
        return this.testMap;
    }

    public void clearTestMap(){
        this.testMap.clear();

        logger.info("Test map cleared");
    }
}
