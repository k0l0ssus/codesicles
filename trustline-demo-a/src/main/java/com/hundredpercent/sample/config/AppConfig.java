
package com.hundredpercent.sample.config;

import javax.annotation.PostConstruct;
import org.apache.camel.CamelContext;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author SIGINT-X
 */
@Configuration
public class AppConfig {
    
    @Autowired
    CamelContext ctxt;
    
    @PostConstruct
    public void init(){
        ctxt.setStreamCaching(true);
       
    }
    
    
    /*
    * Provider handles JSON marshalling and unmarshalling 
    */
    @Bean(name="jacksonJsonProvider")
    public JacksonJsonProvider jacksonJsonProvier(){
        JacksonJsonProvider provider = new JacksonJsonProvider();
        provider.configure(Feature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        provider.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        return provider;
    }

}
