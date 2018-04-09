package com.hundredpercent.sample.dao.v1;

import com.hundredpercent.sample.dto.v1.Client;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 * 
 * This bean holds the trustlines available on the server. 
 * It's stored as a map of client IDs to Trustline
 * 
 */
@Component
public class TrustLineRepositoryImpl implements TrustLineRepository {

    public ConcurrentHashMap<String, TrustLine> trustLines;
    
    /*
    ** This identifies this server.
    ** It'll be used to communicate with another server
    */
    @Value("${service.id}")
    private String SERVICE_ID;
    
    //this is the remote server this one will be communicating with
    @Value("${partner.id}")
    private String PARTNER;
    
    String[] authorizedUsers;

    @PostConstruct
    public void init() {
        
        authorizedUsers = new String[] {PARTNER, SERVICE_ID};
        trustLines = new ConcurrentHashMap<>();
        TrustLineFactory<Client> factory = new TrustLineFactoryImpl();
        
        //initialize a trustline for both this server and the other server.
        
        for (String user : authorizedUsers) {
            TrustLine trustLine = factory.newTrustLine(new Client(user));
            trustLines.put(user, trustLine);
        }
    }

    @Override
    public TrustLine getTrustLine(Client client) {
        if (trustLines.containsKey(client.getId())) {
            return trustLines.get(client.getId());
        }
        throw new IllegalArgumentException("Unknown client");
    }

}
