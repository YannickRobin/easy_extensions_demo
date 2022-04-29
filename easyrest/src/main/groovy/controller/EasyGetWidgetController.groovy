package controller;

import com.sap.cx.boosters.easyrest.controller.EasyRestServiceController;
import service.EasyRestGetWidgetService;
import java.util.Map;

class EasyGetWidgetController implements EasyRestServiceController {

    def easyGetWidgetService;

    Map execute(Map ctx) {
        def response = [:];
        response.'responseCode' = 200;
        response.'body' = easyGetWidgetService.sayHello("Yannick");
        return response
    }

}