/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.finra.sample.filehandler.spring.config;

import java.util.HashMap;
import org.apache.camel.CamelContext;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.model.DataFormatDefinition;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.spi.DataFormatResolver;
import org.apache.camel.spring.boot.CamelContextConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author SIGINT-X
 */
@Configuration
public class BasicSpringConfig {

    @Bean(name = "properties")
    public PropertiesComponent dataBinding() {
        PropertiesComponent pc = new PropertiesComponent();
        pc.setLocation("classpath:config/application.properties");
        return pc;
    }

//    @Bean(name = "customJaxb")
//    public JaxbDataFormat jaxbDataFormat() {
//        return new JaxbDataFormat();
//    }

//    @Bean
//    CamelContextConfiguration contextConfiguration() {
//        return new CamelContextConfiguration() {
//            @Override
//            public void beforeApplicationStart(CamelContext context) {
//                context.setStreamCaching(true);
//                context.setDataFormatResolver((DataFormatResolver) new HashMap<String, DataFormatDefinition>().put("jaxb",new JaxbDataFormat()));
//            }
//
//            @Override
//            public void afterApplicationStart(CamelContext cc) {
//                
//            }
//        };
//    }

}
