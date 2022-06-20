package service;

class GreeterService {

    def configurationService;

    String sayHello(firstName) {
        def message =  "${getMessage()} ${firstName} for Booster 20th of June";
        return message;
    }

    String getMessage()
    {
        return configurationService.getConfiguration().getString("easy.helloworld.message");
    }

}
