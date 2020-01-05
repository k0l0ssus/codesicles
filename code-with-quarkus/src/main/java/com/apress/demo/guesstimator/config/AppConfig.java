package com.apress.demo.guesstimator.config;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;
import java.util.logging.Logger;

@ApplicationPath("quarkus")
public class AppConfig extends ResourceConfig {
    final Logger LOGGER = Logger.getLogger("MyResource");
    public AppConfig(){
        super.packages("com.apress.demo.quarkus");
    }
}
