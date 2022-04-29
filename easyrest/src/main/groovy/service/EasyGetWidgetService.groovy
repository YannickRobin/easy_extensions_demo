package service;

class EasyGetWidgetService {

    def configurationService;

    String sayHello(firstName) {
        def message =  "${getMessage()} ${firstName}";
        return message;
    }

    String getMessage()
    {
        return configurationService.getConfiguration().getString("easy.helloworld.message");
    }

}
