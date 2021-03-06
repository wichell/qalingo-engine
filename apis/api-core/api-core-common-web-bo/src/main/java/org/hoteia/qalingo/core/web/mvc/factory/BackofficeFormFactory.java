/**
 * Most of the code in the Qalingo project is copyrighted Hoteia and licensed
 * under the Apache License Version 2.0 (release version 0.8.0)
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *                   Copyright (c) Hoteia, 2012-2014
 * http://www.hoteia.com - http://twitter.com/hoteia - contact@hoteia.com
 *
 */
package org.hoteia.qalingo.core.web.mvc.factory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hoteia.qalingo.core.domain.AbstractPaymentGateway;
import org.hoteia.qalingo.core.domain.AbstractRuleReferential;
import org.hoteia.qalingo.core.domain.Asset;
import org.hoteia.qalingo.core.domain.CatalogCategoryMaster;
import org.hoteia.qalingo.core.domain.CatalogCategoryVirtual;
import org.hoteia.qalingo.core.domain.Company;
import org.hoteia.qalingo.core.domain.Customer;
import org.hoteia.qalingo.core.domain.DeliveryMethod;
import org.hoteia.qalingo.core.domain.DeliveryMethodPrice;
import org.hoteia.qalingo.core.domain.EngineSetting;
import org.hoteia.qalingo.core.domain.EngineSettingValue;
import org.hoteia.qalingo.core.domain.MarketArea;
import org.hoteia.qalingo.core.domain.OrderPurchase;
import org.hoteia.qalingo.core.domain.ProductBrand;
import org.hoteia.qalingo.core.domain.ProductBrandAttribute;
import org.hoteia.qalingo.core.domain.ProductMarketing;
import org.hoteia.qalingo.core.domain.ProductMarketingAttribute;
import org.hoteia.qalingo.core.domain.ProductSku;
import org.hoteia.qalingo.core.domain.ProductSkuAttribute;
import org.hoteia.qalingo.core.domain.Retailer;
import org.hoteia.qalingo.core.domain.RetailerAddress;
import org.hoteia.qalingo.core.domain.Store;
import org.hoteia.qalingo.core.domain.Tax;
import org.hoteia.qalingo.core.domain.User;
import org.hoteia.qalingo.core.domain.Warehouse;
import org.hoteia.qalingo.core.i18n.message.CoreMessageSource;
import org.hoteia.qalingo.core.pojo.RequestData;
import org.hoteia.qalingo.core.service.BackofficeUrlService;
import org.hoteia.qalingo.core.service.LocalizationService;
import org.hoteia.qalingo.core.service.MarketService;
import org.hoteia.qalingo.core.web.mvc.form.AssetForm;
import org.hoteia.qalingo.core.web.mvc.form.AttributeContextBean;
import org.hoteia.qalingo.core.web.mvc.form.CatalogCategoryForm;
import org.hoteia.qalingo.core.web.mvc.form.CompanyForm;
import org.hoteia.qalingo.core.web.mvc.form.CustomerForm;
import org.hoteia.qalingo.core.web.mvc.form.DeliveryMethodForm;
import org.hoteia.qalingo.core.web.mvc.form.EngineSettingForm;
import org.hoteia.qalingo.core.web.mvc.form.EngineSettingValueForm;
import org.hoteia.qalingo.core.web.mvc.form.OrderForm;
import org.hoteia.qalingo.core.web.mvc.form.PaymentGatewayForm;
import org.hoteia.qalingo.core.web.mvc.form.ProductBrandForm;
import org.hoteia.qalingo.core.web.mvc.form.ProductMarketingForm;
import org.hoteia.qalingo.core.web.mvc.form.ProductSkuForm;
import org.hoteia.qalingo.core.web.mvc.form.QuickSearchForm;
import org.hoteia.qalingo.core.web.mvc.form.RetailerForm;
import org.hoteia.qalingo.core.web.mvc.form.RuleForm;
import org.hoteia.qalingo.core.web.mvc.form.StoreForm;
import org.hoteia.qalingo.core.web.mvc.form.TaxForm;
import org.hoteia.qalingo.core.web.mvc.form.UserForm;
import org.hoteia.qalingo.core.web.mvc.form.WarehouseForm;
import org.hoteia.qalingo.core.web.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 */
@Service("backofficeFormFactory")
public class BackofficeFormFactory {

    @Autowired
    protected MarketService marketService;
    
    @Autowired
    protected LocalizationService localizationService;
    
    @Autowired
    protected CoreMessageSource coreMessageSource;

    @Autowired
    protected RequestUtil requestUtil;
    
    @Autowired
    protected BackofficeUrlService backofficeUrlService;
    
//    @Autowired
//    protected AttributeService attributeService;

    public EngineSettingForm buildEngineSettingForm(final RequestData requestData, final EngineSetting engineSetting) throws Exception {
        final EngineSettingForm engineSettingForm = new EngineSettingForm();
        if(engineSetting != null){
            engineSettingForm.setId(engineSetting.getId().toString());
            engineSettingForm.setCode(engineSetting.getCode());
            engineSettingForm.setVersion(engineSetting.getVersion());
            engineSettingForm.setName(engineSetting.getName());
            engineSettingForm.setDescription(engineSetting.getDescription());
            engineSettingForm.setDefaultValue(engineSetting.getDefaultValue());
        }
        return engineSettingForm;
    }
    
    public EngineSettingValueForm buildEngineSettingValueForm(final RequestData requestData, final EngineSettingValue engineSettingValue) throws Exception {
        final EngineSettingValueForm engineSettingValueForm = new EngineSettingValueForm();
        if(engineSettingValue != null){
            engineSettingValueForm.setId(engineSettingValue.getId().toString());
            engineSettingValueForm.setContext(engineSettingValue.getContext());
            engineSettingValueForm.setValue(engineSettingValue.getValue());
        }
        return engineSettingValueForm;
    }

    public PaymentGatewayForm buildPaymentGatewayForm(final MarketArea marketArea, final AbstractPaymentGateway paymentGateway) throws Exception {
        final PaymentGatewayForm paymentGatewayForm = new PaymentGatewayForm();
        if(paymentGateway != null){
            paymentGatewayForm.setId(paymentGateway.getId().toString());
            paymentGatewayForm.setVersion(paymentGateway.getVersion());
            paymentGatewayForm.setCode(paymentGateway.getCode());
            paymentGatewayForm.setName(paymentGateway.getName());
            paymentGatewayForm.setDescription(paymentGateway.getDescription());
            paymentGatewayForm.setActive(paymentGateway.getMarketAreas().contains(marketArea));
        }
        return paymentGatewayForm;
    }

    public UserForm buildUserForm(final RequestData requestData, final User user) throws Exception {
        final UserForm userForm = new UserForm();
        if(user != null){
            userForm.setId(user.getId().toString());
            userForm.setVersion(user.getVersion());
            userForm.setCode(user.getCode());
            userForm.setLogin(user.getLogin());
            userForm.setTitle(user.getTitle());
            userForm.setFirstname(user.getFirstname());
            userForm.setLastname(user.getLastname());
            userForm.setActive(user.isActive());
            
            userForm.setAddress1(user.getAddress1());
            userForm.setAddress2(user.getAddress2());
            userForm.setPostalCode(user.getPostalCode());
            userForm.setCity(user.getCity());
            userForm.setStateCode(user.getStateCode());
            userForm.setAreaCode(user.getAreaCode());
            userForm.setCountryCode(user.getCountryCode());
            
            userForm.setPhone(user.getPhone());
            userForm.setMobile(user.getMobile());
            userForm.setEmail(user.getEmail());
        }
        return userForm;
    }
    
    public CompanyForm buildCompanyForm(final RequestData requestData, final Company company) throws Exception {
        final CompanyForm companyForm = new CompanyForm();
        if(company != null){
            companyForm.setId(company.getId().toString());
            companyForm.setVersion(company.getVersion());
            companyForm.setCode(company.getCode());
            companyForm.setName(company.getName());
            companyForm.setDescription(company.getDescription());
            companyForm.setActive(company.isActive());
            
            companyForm.setAddress1(company.getAddress1());
            companyForm.setAddress2(company.getAddress2());
            companyForm.setPostalCode(company.getPostalCode());
            companyForm.setCity(company.getCity());
            companyForm.setStateCode(company.getStateCode());
            companyForm.setAreaCode(company.getAreaCode());
            companyForm.setCountryCode(company.getCountryCode());

            companyForm.setPhone(company.getPhone());
            companyForm.setFax(company.getFax());
            companyForm.setEmail(company.getEmail());
            companyForm.setWebsite(company.getWebsite());
        }
        return companyForm;
    }

    public QuickSearchForm buildEngineSettingQuickSearchForm(final RequestData requestData) throws Exception {
        final QuickSearchForm engineSettingQuickSearchForm = new QuickSearchForm();
        return engineSettingQuickSearchForm;
    }

    public QuickSearchForm buildUserQuickSearchForm(final RequestData requestData) throws Exception {
        final QuickSearchForm userQuickSearchForm = new QuickSearchForm();
        return userQuickSearchForm;
    }

    public QuickSearchForm buildBatchQuickSearchForm(final RequestData requestData) throws Exception {
        final QuickSearchForm batchQuickSearchForm = new QuickSearchForm();
        return batchQuickSearchForm;
    }

    public CatalogCategoryForm buildCatalogCategoryForm(final RequestData requestData) throws Exception {
        final CatalogCategoryForm catalogCategoryForm = new CatalogCategoryForm();
//        List<AttributeDefinition> attributeDefinitions = attributeService.findCatalogCategoryGlobalAttributeDefinitions();
//        for (Iterator<AttributeDefinition> iterator = attributeDefinitions.iterator(); iterator.hasNext();) {
//            AttributeDefinition attributeDefinition = (AttributeDefinition) iterator.next();
//            if(attributeDefinition.isGlobal()){
//                catalogCategoryForm.getGlobalAttributes().put(attributeDefinition.getCode(), "");
//            } else {
//                catalogCategoryForm.getMarketAreaAttributes().put(attributeDefinition.getCode(), "");
//            }
//        }
        return catalogCategoryForm;
    }

    public CatalogCategoryForm buildCatalogMasterCategoryForm(final RequestData requestData, final CatalogCategoryMaster parentProductCategory, final CatalogCategoryMaster catalogCategory) throws Exception {
        final CatalogCategoryForm catalogCategoryForm = buildCatalogCategoryForm(requestData);
        if(parentProductCategory != null){
            catalogCategoryForm.setDefaultParentCategoryId("" + catalogCategory.getId());
        } else {
            if(catalogCategory != null
                    && catalogCategory.getParentCatalogCategory() != null){
                catalogCategoryForm.setDefaultParentCategoryId("" + catalogCategory.getParentCatalogCategory().getId());
            }
        }
        
        if(catalogCategory != null){
            catalogCategoryForm.setId(catalogCategory.getId().toString());
            catalogCategoryForm.setCatalogCode(catalogCategory.getName());
            catalogCategoryForm.setName(catalogCategory.getName());
            catalogCategoryForm.setCode(catalogCategory.getCode());
            catalogCategoryForm.setDescription(catalogCategory.getDescription());
            if(catalogCategory.getRanking() != null){
                catalogCategoryForm.setRanking("" + catalogCategory.getRanking());
            }
        }
        return catalogCategoryForm;
    }
    
    public CatalogCategoryForm buildCatalogVirtualCategoryForm(final RequestData requestData, final CatalogCategoryVirtual parentProductCategory, final CatalogCategoryVirtual catalogCategory) throws Exception {
        final CatalogCategoryForm catalogCategoryForm = buildCatalogCategoryForm(requestData);
        if(parentProductCategory != null){
            catalogCategoryForm.setDefaultParentCategoryId("" + parentProductCategory.getId());
        } else {
            if(catalogCategory != null
                    && catalogCategory.getParentCatalogCategory() != null){
                catalogCategoryForm.setDefaultParentCategoryId("" + catalogCategory.getParentCatalogCategory().getId());
            }
        }
        
        if(catalogCategory != null){
            catalogCategoryForm.setId(catalogCategory.getId().toString());
            catalogCategoryForm.setCode(catalogCategory.getCode());
            catalogCategoryForm.setName(catalogCategory.getName());
            catalogCategoryForm.setDescription(catalogCategory.getDescription());
            if(catalogCategory.getRanking() != null){
                catalogCategoryForm.setRanking("" + catalogCategory.getRanking());
            }
            catalogCategoryForm.setCatalogCode(catalogCategory.getName());
            CatalogCategoryMaster catalogCategoryMaster = catalogCategory.getCategoryMaster();
            catalogCategoryForm.setMasterCategoryId("" + catalogCategoryMaster.getId());
        }
        return catalogCategoryForm;
    }
    
    public ProductBrandForm buildProductBrandForm(final RequestData requestData, final ProductBrand productBrand) throws Exception {
        final MarketArea currentMarketArea = requestData.getMarketArea();
        
        final ProductBrandForm productBrandForm = new ProductBrandForm();
        if(productBrand != null){
            productBrandForm.setId(productBrand.getId().toString());
            productBrandForm.setName(productBrand.getName());
            productBrandForm.setCode(productBrand.getCode());
            productBrandForm.setDescription(productBrand.getDescription());

            productBrandForm.setEnabled(productBrand.isEnabled());
            productBrandForm.setEnabledB2B(productBrand.isEnabledB2B());
            productBrandForm.setEnabledB2C(productBrand.isEnabledB2C());

            List<ProductBrandAttribute> globalAttributes = productBrand.getGlobalAttributes();
            for (Iterator<ProductBrandAttribute> iterator = globalAttributes.iterator(); iterator.hasNext();) {
                ProductBrandAttribute productBrandAttribute = (ProductBrandAttribute) iterator.next();
                MarketArea marketArea = null;
                String marketAreaCode = null;
                if(productBrandAttribute.getMarketAreaId() != null){
                    marketArea = marketService.getMarketAreaById(productBrandAttribute.getMarketAreaId());
                    marketAreaCode = marketArea.getCode();
                }
                AttributeContextBean attributeContextBean = new AttributeContextBean(productBrandAttribute.getAttributeDefinition().getCode(), marketAreaCode, productBrandAttribute.getLocalizationCode());
                productBrandForm.getGlobalAttributes().put(attributeContextBean, productBrandAttribute.getValueAsString());
            }
            
            List<ProductBrandAttribute> marketAreaAttributes = productBrand.getMarketAreaAttributes(currentMarketArea.getId());
            for (Iterator<ProductBrandAttribute> iterator = marketAreaAttributes.iterator(); iterator.hasNext();) {
                ProductBrandAttribute productBrandAttribute = (ProductBrandAttribute) iterator.next();
                MarketArea marketAreaAttribute = null;
                if(productBrandAttribute.getMarketAreaId() != null){
                    marketAreaAttribute = marketService.getMarketAreaById(productBrandAttribute.getMarketAreaId());
                }
                AttributeContextBean attributeContextBean = new AttributeContextBean(productBrandAttribute.getAttributeDefinition().getCode(), marketAreaAttribute.getCode(), productBrandAttribute.getLocalizationCode());
                productBrandForm.getMarketAreaAttributes().put(attributeContextBean, productBrandAttribute.getValueAsString());
            }
        }
        return productBrandForm;
    }
    
    public ProductMarketingForm buildProductMarketingForm(final RequestData requestData, final ProductMarketing productMarketing) throws Exception {
        final MarketArea currentMarketArea = requestData.getMarketArea();
        
        final ProductMarketingForm productMarketingForm = new ProductMarketingForm();
        if(productMarketing != null){
            productMarketingForm.setId(productMarketing.getId().toString());
            productMarketingForm.setName(productMarketing.getName());
            productMarketingForm.setCode(productMarketing.getCode());
            productMarketingForm.setDescription(productMarketing.getDescription());
            
            List<ProductMarketingAttribute> globalAttributes = productMarketing.getGlobalAttributes();
            for (Iterator<ProductMarketingAttribute> iterator = globalAttributes.iterator(); iterator.hasNext();) {
                ProductMarketingAttribute productMarketingAttribute = (ProductMarketingAttribute) iterator.next();
                productMarketingForm.getGlobalAttributes().put(productMarketingAttribute.getAttributeDefinition().getCode(), productMarketingAttribute.getValueAsString());
            }
            
            List<ProductMarketingAttribute> marketAreaAttributes = productMarketing.getMarketAreaAttributes(currentMarketArea.getId());
            for (Iterator<ProductMarketingAttribute> iterator = marketAreaAttributes.iterator(); iterator.hasNext();) {
                ProductMarketingAttribute productMarketingAttribute = (ProductMarketingAttribute) iterator.next();
                productMarketingForm.getMarketAreaAttributes().put(productMarketingAttribute.getAttributeDefinition().getCode(), productMarketingAttribute.getValueAsString());
            }
        }
        return productMarketingForm;
    }
    
    public AssetForm buildProductMarketingAssetForm(final RequestData requestData, final Asset asset) throws Exception {
        final AssetForm assetForm = new AssetForm();
        if(asset != null){
            assetForm.setId(asset.getId().toString());
            assetForm.setName(asset.getName());
            assetForm.setDescription(asset.getDescription());
            assetForm.setDefault(asset.isDefault());
            assetForm.setPath(asset.getPath());
            assetForm.setType(asset.getType());
            assetForm.setSize(asset.getSize());
        }
        return assetForm;
    }
    
    public ProductSkuForm buildProductSkuForm(final RequestData requestData, final ProductMarketing productMarketing, final ProductSku productSku) throws Exception {
        final MarketArea currentMarketArea = requestData.getMarketArea();
        
        final ProductSkuForm productSkuForm = new ProductSkuForm();
        if(productSku != null){
            
            productSkuForm.setId(productSku.getId().toString());
            productSkuForm.setName(productSku.getName());
            productSkuForm.setCode(productSku.getCode());
            productSkuForm.setDescription(productSku.getDescription());

            List<ProductSkuAttribute> globalAttributes = productSku.getGlobalAttributes();
            for (Iterator<ProductSkuAttribute> iterator = globalAttributes.iterator(); iterator.hasNext();) {
                ProductSkuAttribute productSkuAttribute = (ProductSkuAttribute) iterator.next();
                productSkuForm.getGlobalAttributes().put(productSkuAttribute.getAttributeDefinition().getCode(), productSkuAttribute.getValueAsString());
            }
            
            List<ProductSkuAttribute> marketAreaAttributes = productSku.getMarketAreaAttributes(currentMarketArea.getId());
            for (Iterator<ProductSkuAttribute> iterator = marketAreaAttributes.iterator(); iterator.hasNext();) {
                ProductSkuAttribute productSkuAttribute = (ProductSkuAttribute) iterator.next();
                productSkuForm.getMarketAreaAttributes().put(productSkuAttribute.getAttributeDefinition().getCode(), productSkuAttribute.getValueAsString());
            }
        }
        if(productMarketing != null){
            productSkuForm.setProductMarketingId("" + productMarketing.getId());
        }
        return productSkuForm;
    }
    
    public CustomerForm buildCustomerForm(final RequestData requestData, final Customer customer) throws Exception {
        final CustomerForm customerForm = new CustomerForm();
        if(customer != null){
            customerForm.setId(customer.getId().toString());
            customerForm.setVersion(customer.getVersion());
            customerForm.setCode(customer.getCode());
            customerForm.setLogin(customer.getLogin());
            customerForm.setTitle(customer.getTitle());
            customerForm.setFirstname(customer.getFirstname());
            customerForm.setLastname(customer.getLastname());
            customerForm.setEmail(customer.getEmail());
            customerForm.setPassword(customer.getPassword());
            customerForm.setDefaultLocale(customer.getDefaultLocale());
            customerForm.setActive(customer.isActive());
        }
        return customerForm;
    }
    
    public OrderForm buildOrderForm(final RequestData requestData, final OrderPurchase order) throws Exception {
        final OrderForm orderForm = new OrderForm();
        if(order != null){
            orderForm.setId(order.getId().toString());
            orderForm.setVersion(order.getVersion());
            orderForm.setStatus(order.getStatus());
            orderForm.setOrderNum(order.getOrderNum());
            if(order.getCustomer() != null){
                orderForm.setCustomerId(order.getCustomer().getId());
            }
            if(order.getBillingAddress() != null){
                orderForm.setBillingAddressId(order.getBillingAddress().getId());
            }
            if(order.getShippingAddress() != null){
                orderForm.setShippingAddressId(order.getShippingAddress().getId());
            }
        }
        return orderForm;
    }
    
    public RuleForm buildRuleForm(final RequestData requestData, final AbstractRuleReferential rule) throws Exception {
        final RuleForm ruleForm = new RuleForm();
        if(rule != null){
            ruleForm.setId(rule.getId());
            ruleForm.setVersion(rule.getVersion());
            ruleForm.setCode(rule.getCode());
            ruleForm.setName(rule.getName());
            ruleForm.setDescription(rule.getDescription());
            ruleForm.setSalience(rule.getSalience());
        }
        return ruleForm;
    }
    
    public WarehouseForm buildWarehouseForm(final RequestData requestData, final Warehouse warehouse) throws Exception {
        final WarehouseForm warehouseForm = new WarehouseForm();
        if(warehouse != null){
            warehouseForm.setId(warehouse.getId().toString());
            warehouseForm.setVersion(warehouse.getVersion());
            warehouseForm.setCode(warehouse.getCode());
            warehouseForm.setName(warehouse.getName());
            warehouseForm.setDescription(warehouse.getDescription());
        }
        return warehouseForm;
    }
    
    public DeliveryMethodForm buildDeliveryMethodForm(final RequestData requestData, final DeliveryMethod deliveryMethod) throws Exception {
        final DeliveryMethodForm deliveryMethodForm = new DeliveryMethodForm();
        if(deliveryMethod != null){
            deliveryMethodForm.setId(deliveryMethod.getId().toString());
            deliveryMethodForm.setVersion(deliveryMethod.getVersion());
            deliveryMethodForm.setCode(deliveryMethod.getCode());
            deliveryMethodForm.setName(deliveryMethod.getName());
            deliveryMethodForm.setDescription(deliveryMethod.getDescription());
            
            Map<String, String> prices = new HashMap<String, String>();
            for (Iterator<DeliveryMethodPrice> iterator = deliveryMethod.getPrices().iterator(); iterator.hasNext();) {
                DeliveryMethodPrice deliveryMethodPrice = (DeliveryMethodPrice) iterator.next();
                deliveryMethodForm.getPrices().put(deliveryMethodPrice.getCurrency().getCode(), deliveryMethodPrice.getPrice().toString());
            }
        }
        return deliveryMethodForm;
    }
    
    public TaxForm buildTaxForm(final RequestData requestData, final Tax tax) throws Exception {
        final TaxForm taxForm = new TaxForm();
        if(tax != null){
            taxForm.setId(tax.getId().toString());
            taxForm.setCode(tax.getCode());
            taxForm.setVersion(tax.getVersion());
            taxForm.setName(tax.getName());
            taxForm.setDescription(tax.getDescription());
            taxForm.setPercent("" + tax.getPercent());
        }
        return taxForm;
    }
    
    public RetailerForm buildRetailerForm(final RequestData requestData, final Retailer retailer) throws Exception {
        final RetailerForm retailerForm = new RetailerForm();
        if(retailer != null){
            retailerForm.setId(retailer.getId().toString());
            retailerForm.setCode(retailer.getCode());
            retailerForm.setName(retailer.getName());
            retailerForm.setDescription(retailer.getDescription());
            
            retailerForm.setBrand(retailer.isBrand());
    		retailerForm.setCorner(retailer.isCorner());
    		retailerForm.setOfficialRetailer(retailer.isOfficialRetailer());
    		retailerForm.setEcommerce(retailer.isEcommerce());
    		
    		if(retailer.getDefaultWarehouse() != null){
    			retailerForm.setWarehouseId(retailer.getDefaultWarehouse().getId().toString());
    		}

            if (retailer.getAddresses() != null) {
                RetailerAddress defaultAddress = retailer.getDefaultAddress();
                if (defaultAddress != null) {
                    retailerForm.setAddress1(defaultAddress.getAddress1());
                    retailerForm.setAddress2(defaultAddress.getAddress2());
                    retailerForm.setAddressAdditionalInformation(defaultAddress.getAddressAdditionalInformation());
                    retailerForm.setPostalCode(defaultAddress.getPostalCode());
                    retailerForm.setCity(defaultAddress.getCity());
                    retailerForm.setStateCode(defaultAddress.getStateCode());
                    retailerForm.setAreaCode(defaultAddress.getAreaCode());
                    retailerForm.setCountryCode(defaultAddress.getCountryCode());
                    
                    retailerForm.setLongitude(defaultAddress.getLongitude());
                    retailerForm.setLatitude(defaultAddress.getLatitude());

                    retailerForm.setPhone(defaultAddress.getPhone());
                    retailerForm.setMobile(defaultAddress.getMobile());
                    retailerForm.setFax(defaultAddress.getFax());
                    retailerForm.setEmail(defaultAddress.getEmail());
                    String websiteUrl = defaultAddress.getWebsite();
                    if (StringUtils.isNotEmpty(websiteUrl) && !websiteUrl.contains("http")) {
                        websiteUrl = "http://" + websiteUrl;
                    }
                    retailerForm.setWebsite(websiteUrl);
                }
            }
        }
        return retailerForm;
    }
    
    public StoreForm buildStoreForm(RequestData requestData, Store store) throws Exception {
    	final StoreForm storeForm = new StoreForm();
    	if(store != null){
	    	storeForm.setId(store.getId().toString());
            storeForm.setCode(store.getCode());
            storeForm.setName(store.getName());
            if(store.getRetailer() != null){
                storeForm.setRetailerId(store.getRetailer().getId().toString());
            }

            storeForm.setActive(store.isActive());
            storeForm.setPrimary(store.isPrimary());
            storeForm.setB2b(store.isB2b());
            storeForm.setB2c(store.isB2c());
            
            storeForm.setType(store.getType());
            
            storeForm.setAddress1(store.getAddress1());
            storeForm.setAddress2(store.getAddress2());
            storeForm.setAddressAdditionalInformation(store.getAddressAdditionalInformation());

            storeForm.setAreaCode(store.getAreaCode());
            storeForm.setPostalCode(store.getPostalCode());
            storeForm.setCity(store.getCity());
            storeForm.setStateCode(store.getStateCode());
            storeForm.setCountryCode(store.getCountryCode());

            storeForm.setLatitude(store.getLatitude());
            storeForm.setLongitude(store.getLongitude());

            storeForm.setPhone(store.getPhone());
            storeForm.setFax(store.getFax());
            storeForm.setEmail(store.getEmail());
            storeForm.setWebsite(store.getWebsite());
            
    	} else {
    	    MarketArea marketArea = requestData.getMarketArea();
            storeForm.setCountryCode(marketArea.getGeolocCountryCode());
    	}
    	
    	return storeForm;
    }
    
}