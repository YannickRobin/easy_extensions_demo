import org.slf4j.LoggerFactory
import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader

void registerBeans() {
    LOG.info('Register Spring beans');

    //def reader = new GroovyBeanDefinitionReader(spring.getBeanFactory())
    def reader = new GroovyBeanDefinitionReader(spring.getBeanFactory())


    /*
    reader.beans {
        greeterService(GreeterService)
        easyTestBeanService(MyEasyTestBeanService)
    }
    */
    reader.loadBeanDefinitions("file:EasyBeans.groovy");
}

LOG = LoggerFactory.getLogger("easy_helloworld");
LOG.info("Initializing...");
registerBeans();