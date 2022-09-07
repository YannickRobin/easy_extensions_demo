import org.slf4j.LoggerFactory
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException

LOG = LoggerFactory.getLogger("recently-viewed-products");
def contentCatalogId = String.format("%sContentCatalog", "electronics-spa");
def setupSyncJobService = spring.getBean("setupSyncJobService");
def catalogService = spring.getBean("catalogService");
try{
    if(null!=catalogService.getCatalogForId(contentCatalogId)){
        setupSyncJobService.executeCatalogSyncJob(String.format("%sContentCatalog", "electronics-spa"));
    }
}catch(UnknownIdentifierException exception){
    LOG.error("Electronics-SPA catalog is not available in the system...");
}
LOG.info("Init completed...");
