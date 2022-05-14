package controller;

import com.sap.cx.boosters.easyrest.controller.EasyRestServiceController;
import java.util.Map;
import org.slf4j.LoggerFactory;

class PriceController implements EasyRestServiceController {

    def priceService;
    def catalogVersionService;
    def productService;
    def LOG = LoggerFactory.getLogger("PriceController");

    Map execute(Map ctx) {
        def response = [:];

        def catVersion = catalogVersionService.getCatalogVersion('Default', 'Staged');
        catalogVersionService.setSessionCatalogVersions(Collections.singleton(catVersion));

        def product = productService.getProductForCode(ctx.parameters.code);
        def prices = priceService.getPriceInformationsForProduct(product);
        LOG.info('Prices :' + prices);

        response.'responseCode' = 200;

        def sBody = '[\n';
        prices.each{
            price -> 
            def priceValue = price.getPrice();
            def currency = priceValue.getCurrencyIso();
            def value = priceValue.getValue();
            sBody += '\t{\n\t\t"value": ' + value + ',\n\t\t"currency": "' + currency + '"\n\t}\n';
        }
        sBody += ']'

        response.'body' = sBody;
        return response
    }

}