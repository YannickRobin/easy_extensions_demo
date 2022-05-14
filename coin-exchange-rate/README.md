# Coin Exchange rate

## CoinAPI.io

For this demo, we use free API from CoinAPI.io with the following API Key: 70A71F94-76AC-496C-9193-3700E25DD4FB

## Demo with price service
In this example, _DefaultPriceService_ is overrided by [CoinPriceService.groovy](/helloworld/src/main/groovy/CoinPriceService.groovy) to convert price from USD to BTC.

Execute the following script in HAC:

```groovy
catVersion = catalogVersionService.getCatalogVersion("Default", "Staged");
product = productService.getProductForCode(catVersion, "Product1");
println priceService.getPriceInformationsForProduct(product);
```
