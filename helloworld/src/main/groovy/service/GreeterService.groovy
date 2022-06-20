package service;

class GreeterService {

    def configurationService;

    String sayHello(firstName) {
        def message =  "${getMessage()} ${firstName} for Booster";
        return message;
    }

    String getMessage()
    {
        return configurationService.getConfiguration().getString("easy.helloworld.message");
    }

}
