 
package com.hundredpercent.sample;

import com.hundredpercent.sample.dto.v1.TrustLineCreditRequest;
import com.hundredpercent.sample.dto.v1.TrustLineDebitRequest;
import java.math.BigDecimal;
import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.spring.CamelSpringBootRunner;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.camel.test.spring.MockEndpointsAndSkip;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


/**
 *
 * @author SIGINT-X
 */
@SpringBootTest
@RunWith(CamelSpringBootRunner.class)
@MockEndpointsAndSkip("cxfrs*") //mock out outbound service calls
@MockEndpoints("direct:trustline-debit-processor") //intercept call to set expectation
public class CreditRouteTest {

    private TrustLineDebitRequest debitRequest;
    private TrustLineCreditRequest creditRequest;
    private Exchange creditEx;
    private Exchange debitEx;
    
    @EndpointInject(uri="mock:direct:trustline-debit-processor")
    MockEndpoint mockDebitRoute;
    
    @EndpointInject(uri="mock:direct:trustline-credit-processor")
    MockEndpoint mockCreditRoute;
    
    @Autowired
    CamelContext ctx;
    
    
    @Before
    public void init(){
        debitRequest = new TrustLineDebitRequest();
        debitRequest.setValue(BigDecimal.TEN);
        creditRequest = new TrustLineCreditRequest();
        creditRequest.setValue(BigDecimal.TEN);
        debitEx = ExchangeBuilder.anExchange(ctx).withBody(debitRequest).withHeader("clientId", "abc").build();
        creditEx = ExchangeBuilder.anExchange(ctx).withBody(creditRequest).withHeader("clientId", "abc").build();
        
        //set expectations for the endpoint
        mockDebitRoute.expectedBodiesReceived(debitRequest);
        mockDebitRoute.expectedMessageCount(1);
        
        mockCreditRoute.expectedBodiesReceived(creditRequest);
        mockCreditRoute.expectedMessageCount(1);
    }
    
    @Test
    public void sendDebitRequest() throws InterruptedException{
        ctx.createProducerTemplate().send(mockDebitRoute, debitEx);
        assertNull(debitEx.getException()); //check that no exceptions were thrown - all endpoints were processed
        mockDebitRoute.assertIsSatisfied(); //check that all the expectations of the endpoint were met.
    }
    
    @Test
    public void sendCreditRequest() throws InterruptedException{
        ctx.createProducerTemplate().send(mockCreditRoute, creditEx);
        assertNull(creditEx.getException()); //check that no exceptions were thrown - all endpoints were processed
        mockCreditRoute.assertIsSatisfied(); //check that all the expectations of the endpoint were met.
    }
    
}
