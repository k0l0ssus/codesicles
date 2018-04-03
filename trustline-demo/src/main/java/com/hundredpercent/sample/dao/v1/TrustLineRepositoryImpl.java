package com.hundredpercent.sample.dao.v1;

import com.hundredpercent.sample.dto.v1.Client;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 */
@Component
public class TrustLineRepositoryImpl implements TrustLineRepository {

    public ConcurrentHashMap<String, TrustLine> trustLines;
    String[] authorizedUsers = {"xyz", "abc"};

    @PostConstruct
    public void init() {
        trustLines = new ConcurrentHashMap<String, TrustLine>();
        TrustLineFactory<Client> factory = new TrustLineFactoryImpl();
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
