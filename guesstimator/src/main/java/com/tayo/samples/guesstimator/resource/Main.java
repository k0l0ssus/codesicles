package com.tayo.samples.guesstimator.resource;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.tayo.samples.guesstimator.config.InjectionBinder;

import java.io.IOException;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main class.
 *
 */
public class Main {
    // Base URI the Grizzly HTTP server will listen on
    public static final String BASE_URI = "http://localhost:8080/guestimate/";
    private static final Logger LOGGER = Logger.getLogger("Guestimate Server");
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.tayo.samples.guesstimator package
        final ResourceConfig rc = new ResourceConfig().packages("com.tayo.samples.guesstimator.resource")
        											  .register(new InjectionBinder())
        											  .register(new LoggingFeature(LOGGER, Level.INFO, LoggingFeature.Verbosity.PAYLOAD_ANY, 2048));

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args){
        final HttpServer server = startServer();
        
        System.out.println(String.format("Guesstimator started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        server.stop();
    }
}

