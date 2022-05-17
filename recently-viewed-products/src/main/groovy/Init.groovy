import org.slf4j.LoggerFactory
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader

LOG = LoggerFactory.getLogger("recently-viewed-products");
setupSyncJobService.executeCatalogSyncJob(String.format("%sContentCatalog", "electronics-spa"));
LOG.info("Init completed...");
