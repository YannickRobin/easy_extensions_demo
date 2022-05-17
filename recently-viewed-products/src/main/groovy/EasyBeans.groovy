import org.slf4j.LoggerFactory
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader

LOG = LoggerFactory.getLogger("recently-viewed-products");
LOG.info('Registering Spring beans...');

def reader = new GroovyBeanDefinitionReader(spring.getBeanFactory())

reader.beans {
    recentlyViewedProductsService(service.RecentlyViewedProductsService) {
        configurationService = spring.getBean("configurationService")
        baseSiteService = spring.getBean("baseSiteService")
        userService = spring.getBean("userService")
    }
    recentlyViewedProductsController(controller.RecentlyViewedProductsController) {
        recentlyViewedProductsService = recentlyViewedProductsService
    }
}

LOG.info('Spring beans registered');