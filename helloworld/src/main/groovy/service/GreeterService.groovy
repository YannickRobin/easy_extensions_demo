package service;

class GreeterService {

    def configurationService;

    String sayHello(firstName) {
        def message =  "${getMessage()} ${firstName} for Booster 18th of August";
        return message;
    }

    String getMessage()
    {
        return configurationService.getConfiguration().getString("easy.helloworld.message");
    }

}
