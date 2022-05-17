import org.slf4j.LoggerFactory
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader

LOG = LoggerFactory.getLogger("recently-viewed-products");
def setupSyncJobService =spring.getBean("setupSyncJobService");
setupSyncJobService.executeCatalogSyncJob(String.format("%sContentCatalog", "electronics-spa"));
LOG.info("Init completed...");
