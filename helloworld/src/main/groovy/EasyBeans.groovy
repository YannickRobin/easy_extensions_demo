import org.slf4j.LoggerFactory
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader

LOG = LoggerFactory.getLogger("helloworld");
LOG.info('Registering Spring beans...');

def reader = new GroovyBeanDefinitionReader(spring.getBeanFactory())

reader.beans {
    greeterService(service.GreeterService) {
        configurationService = spring.getBean("configurationService")
    }
    greeterController(controller.GreeterController) {
        greeterService = greeterService
    }
    priceService(service.EasyPriceService) {
    }
    priceController(controller.PriceController) {
        priceService = priceService
        catalogVersionService = spring.getBean("catalogVersionService")
        productService = spring.getBean("productService")
    }
}

LOG.info('Spring beans registered');