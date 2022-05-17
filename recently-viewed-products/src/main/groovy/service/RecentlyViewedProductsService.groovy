package service;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import groovyx.net.http.RESTClient;
import org.apache.commons.collections.CollectionUtils;

class RecentlyViewedProductsService {

    def LOG = org.slf4j.LoggerFactory.getLogger("RecentlyViewedProductsService");
    def configurationService;
    def baseSiteService;
    def userService;
    def enabled = true;

    public List<String> getRecentlyViewedProducts(def baseSiteId){
        def recentlyViewedProducts = new ArrayList<String>();
        def baseSite = baseSiteService.getBaseSiteForUID(baseSiteId);
        if(baseSite != null){
            def endPointHost = configurationService.getConfiguration().getString("easy.recentlyviewedproducts.endpoint.host","http://localhost:3001");
            def restClient = new RESTClient(endPointHost);
            def endPointBasePath = configurationService.getConfiguration().getString("easy.recentlyviewedproducts.endpoint.basePath","/api/recentProducts");
            def path = endPointBasePath + "/"+ baseSiteId +"/" + userService.getCurrentUser().getUid();
		
	    try{
	    	def restResponse = restClient.get(path: path);
	    	if (restResponse.statusLine.statusCode == 200) {
			def recentlyViewedProductCodes = restResponse.data.productCodes;
			// Retrieve the product data for product ids
			recentlyViewedProductCodes.each {
		    		recentlyViewedProducts.add(it.productCode);
			}
	    	}
	    }catch(Exception ex){
		    LOG.debug("Error from the endpoint", ex);
	    }
        }else{
            LOG.error("No base site found with id: {}", baseSiteId);
        }

        return recentlyViewedProducts;
    }

	public void afterPropertiesSet() throws Exception
	{ 
        super.setCurrentTenant(de.hybris.platform.core.Registry.getCurrentTenant());
        super.afterPropertiesSet();
	}

}
