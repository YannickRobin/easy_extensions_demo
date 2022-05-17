import org.slf4j.LoggerFactory
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader

LOG = LoggerFactory.getLogger("coin-exchange-rate");
LOG.info('Registering Spring beans...');

def reader = new GroovyBeanDefinitionReader(spring.getBeanFactory())

reader.beans {
    priceService(service.CoinPriceService) {
        configurationService = spring.getBean("configurationService")
    }
    priceController(controller.PriceController) {
        priceService = priceService
        catalogVersionService = spring.getBean("catalogVersionService")
        productService = spring.getBean("productService")
    }
}

LOG.info('Spring beans registered');