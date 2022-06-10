package controller;

import com.sap.cx.boosters.easyrest.controller.EasyRestServiceController;
import service.GreeterService;
import java.util.Map;

class GreeterController implements EasyRestServiceController {

    def greeterService;

    Map execute(Map ctx) {
        def response = [:];
        response.'responseCode' = 200;
        response.'body' = groovy.json.JsonOutput.toJson(greeterService.sayHello(ctx.parameters.firstname));
        return response
    }

}
