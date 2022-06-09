import org.slf4j.LoggerFactory
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader

LOG = LoggerFactory.getLogger("easy_helloworld");

def setupSyncJobService =spring.getBean("setupSyncJobService");
setupSyncJobService.executeCatalogSyncJob(String.format("%sContentCatalog", "electronics-spa"));

LOG.info("Init completed");
