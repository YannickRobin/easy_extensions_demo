import org.slf4j.LoggerFactory
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader

LOG = LoggerFactory.getLogger("easyrest");
LOG.info('Registering Spring beans...');

def reader = new GroovyBeanDefinitionReader(spring.getBeanFactory())

reader.beans {
    easyGetWidgetService(service.EasyGetWidgetService) {
        configurationService = spring.getBean("configurationService")
    }
    easyGetWidgetController(controller.EasyGetWidgetController) {
        easyGetWidgetService = easyGetWidgetService
    }
}

LOG.info('Spring beans registered');