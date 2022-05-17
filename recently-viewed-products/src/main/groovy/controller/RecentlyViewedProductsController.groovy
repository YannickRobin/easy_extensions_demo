package controller;

import com.sap.cx.boosters.easyrest.controller.EasyRestServiceController;
import java.util.Map;
import org.slf4j.LoggerFactory;

class RecentlyViewedProductsController implements EasyRestServiceController {

    def recentlyViewedProductsService;
    def LOG = LoggerFactory.getLogger("RecentlyViewedProductsController");

    Map execute(Map ctx) {
        def response = [:];
        baseSiteId = ctx.pathParameters.baseSiteId;

        def recentlyViewedProducts = recentlyViewedProductsService.getRecentlyViewedProducts(baseSiteId);
        LOG.info('Prices :' + prices);

        response.'responseCode' = 200;

        def sBody = groovy.json.JsonOutput.toJson(recentlyViewedProducts);

        response.'body' = sBody;
        return response
    }

}