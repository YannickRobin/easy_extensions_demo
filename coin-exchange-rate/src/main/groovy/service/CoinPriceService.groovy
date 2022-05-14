package service;

import de.hybris.platform.product.impl.DefaultPriceService;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.List;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.util.PriceValue;
import org.apache.commons.collections.CollectionUtils;

class CoinPriceService extends DefaultPriceService {

    def LOG = org.slf4j.LoggerFactory.getLogger("CoinPriceService");
    def enabled = true;

    public List<PriceInformation> getPriceInformationsForProduct(ProductModel product) {     
        def prices = super.getPriceInformationsForProduct(product);
        if (!enabled)
            return prices;

        LOG.info("Price before " + prices);

        if (CollectionUtils.isNotEmpty(prices))
        {
            prices.eachWithIndex{price,index->
                def priceValue = price.getPrice();
                def currency = priceValue.getCurrencyIso();
                if (currency.equals('USD'))
                {
                    def priceValueCurrencyTo = convert(priceValue, 'USD', 'BTC');
                    def priceCurrencyTo = new PriceInformation(price.getQualifiers(), priceValueCurrencyTo);
                    prices[index] = priceCurrencyTo;
                }
                else if (currency.equals('EUR'))
                {
                    def priceValueCurrencyTo = convert(priceValue, 'EUR', 'BTC');
                    def priceCurrencyTo = new PriceInformation(price.getQualifiers(), priceValueCurrencyTo);
                    prices[index] = priceCurrencyTo;
                }
            }
        }
        return prices;
    }

    def convert(priceValue, currencyFrom, currencyTo)
    {
        def priceFrom = Math.round(priceValue.getValue()*100)/100;
        def priceTo = priceFrom * getRateFromCoinAPI(currencyFrom, currencyTo);
        return new PriceValue(currencyTo, priceTo, true);
    }

    BigDecimal getRateFromCoinAPI(currencyFrom, currencyTo) {
        def restTemplate = new org.springframework.web.client.RestTemplate();
        restTemplate.execute("https://rest.coinapi.io/v1/exchangerate/" + currencyFrom + '/' + currencyTo, org.springframework.http.HttpMethod.GET, 
                            {  request -> request.headers.add('X-CoinAPI-Key', '70A71F94-76AC-496C-9193-3700E25DD4FB') }, 
                            {
                                def parser = new groovy.json.JsonSlurper()
                                def jsonBody = parser.parseText("$it.body.text")
                                def rate = jsonBody.get("rate")
                                //LOG.info(rate.toString())
                                return rate
                            }
        )
    }

    BigDecimal getRateFromExchangeRatePlatform(currencyFrom, currencyTo) {
        def restTemplate = new org.springframework.web.client.RestTemplate()
        restTemplate.execute("https://api.exchangerate.host/latest?base=" + currencyFrom + "&symbols=" + currencyTo, org.springframework.http.HttpMethod.GET, {}, {
            def parser = new groovy.json.JsonSlurper()
            def jsonBody = parser.parseText("$it.body.text")
            def rate = jsonBody.rates.get(currencyTo)
            LOG.info(rate.toString());
            return rate
        })
    }

	public void afterPropertiesSet() throws Exception
	{ 
        super.setCurrentTenant(de.hybris.platform.core.Registry.getCurrentTenant());
        super.afterPropertiesSet();
	}

}