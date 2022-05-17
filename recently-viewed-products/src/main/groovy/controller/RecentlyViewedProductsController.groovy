package controller;

import com.sap.cx.boosters.easyrest.controller.EasyRestServiceController;
import java.util.Map;
import org.slf4j.LoggerFactory;

class RecentlyViewedProductsController implements EasyRestServiceController {

    def recentlyViewedProductsService;
    def LOG = LoggerFactory.getLogger("RecentlyViewedProductsController");

    Map execute(Map ctx) {
        def response = [:];
        def baseSiteId = ctx.pathParameters.baseSiteId;

        def recentlyViewedProducts = recentlyViewedProductsService.getRecentlyViewedProducts(baseSiteId);
        LOG.info('recentlyViewedProducts :' + recentlyViewedProducts);

        response.'responseCode' = 200;

        def sBody = groovy.json.JsonOutput.toJson(recentlyViewedProducts);

        response.'body' = sBody;
        return response
    }

}
