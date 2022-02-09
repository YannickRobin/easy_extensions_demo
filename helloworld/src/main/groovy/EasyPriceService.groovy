import de.hybris.platform.product.impl.DefaultPriceService;
import de.hybris.platform.core.model.product.ProductModel;
import java.util.List;
import de.hybris.platform.jalo.order.price.PriceInformation;
import de.hybris.platform.util.PriceValue;
import org.apache.commons.collections.CollectionUtils;

class EasyPriceService extends DefaultPriceService {

    def LOG = org.slf4j.LoggerFactory.getLogger("EasyPriceService");
    def spring;

    public List<PriceInformation> getPriceInformationsForProduct(ProductModel product) {     
        def prices = super.getPriceInformationsForProduct(product);
        LOG.info("Price before " + prices);

        if (CollectionUtils.isNotEmpty(prices))
        {
            prices.eachWithIndex{price,index->
                def priceValue = price.getPrice();
                def currency = priceValue.getCurrencyIso();
                if (currency.equals('USD'))
                {
                    def priceValueCurrencyTo = convert(priceValue, 'USD', 'EUR');
                    def priceCurrencyTo = new PriceInformation(price.getQualifiers(), priceValueCurrencyTo);
                    prices[index] = priceCurrencyTo;
                }
                else if (currency.equals('EUR'))
                {
                    def priceValueCurrencyTo = convert(priceValue, 'EUR', 'USD');
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
        def priceTo = priceFrom * getRateFromExternalExchangeService(currencyFrom, currencyTo);
        return new PriceValue(currencyTo, priceTo, true);
    }

    BigDecimal getRateFromExternalExchangeService(currencyFrom, currencyTo) {
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