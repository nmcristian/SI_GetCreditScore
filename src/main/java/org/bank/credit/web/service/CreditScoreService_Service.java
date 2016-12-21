package org.bank.credit.web.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 3.1.8
 * 2016-12-21T02:32:06.492+01:00
 * Generated source version: 3.1.8
 * 
 */
@WebServiceClient(name = "CreditScoreService", 
                  wsdlLocation = "http://139.59.154.97:8080/CreditScoreService/CreditScoreService?wsdl",
                  targetNamespace = "http://service.web.credit.bank.org/") 
public class CreditScoreService_Service extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://service.web.credit.bank.org/", "CreditScoreService");
    public final static QName CreditScoreServicePort = new QName("http://service.web.credit.bank.org/", "CreditScoreServicePort");
    static {
        URL url = null;
        try {
            url = new URL("http://139.59.154.97:8080/CreditScoreService/CreditScoreService?wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(CreditScoreService_Service.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "http://139.59.154.97:8080/CreditScoreService/CreditScoreService?wsdl");
        }
        WSDL_LOCATION = url;
    }

    public CreditScoreService_Service(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public CreditScoreService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public CreditScoreService_Service() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    public CreditScoreService_Service(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    public CreditScoreService_Service(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    public CreditScoreService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }    




    /**
     *
     * @return
     *     returns CreditScoreService
     */
    @WebEndpoint(name = "CreditScoreServicePort")
    public CreditScoreService getCreditScoreServicePort() {
        return super.getPort(CreditScoreServicePort, CreditScoreService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CreditScoreService
     */
    @WebEndpoint(name = "CreditScoreServicePort")
    public CreditScoreService getCreditScoreServicePort(WebServiceFeature... features) {
        return super.getPort(CreditScoreServicePort, CreditScoreService.class, features);
    }

}
