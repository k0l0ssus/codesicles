package com.hundredpercent.sample.transformer.v1;

import com.hundredpercent.sample.constants.v1.RouteConstants;
import com.hundredpercent.sample.dao.v1.TrustLine;
import com.hundredpercent.sample.dao.v1.TrustLineRepository;
import com.hundredpercent.sample.dto.v1.Client;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.camel.Exchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author SIGINT-X
 * This transformer sets the value of the trustline balance on the exchange, so it's available throughout the application
 */

@Component("trustLineBalance")
public class TrustLineBalanceTransformer implements InOnlyTransformer<Exchange> {

    @Value("${partner.id}")
    private String PARTNER;

    @Autowired
    TrustLineRepository repo;

    @Override
    public void transform(Exchange in) {
        try {
            TrustLine<BigDecimal> trustLine = repo.getTrustLine(new Client(PARTNER));
            in.setProperty(RouteConstants.TRUST_LINE_BALANCE, trustLine.getTrustLineBalance());
        } catch (Exception ex) {
            Logger.getLogger(TrustLineBalanceTransformer.class.getName()).log(Level.SEVERE, null, ex);
            //ideally rethrow exception - don't swallow it. 
        }
    }

}
