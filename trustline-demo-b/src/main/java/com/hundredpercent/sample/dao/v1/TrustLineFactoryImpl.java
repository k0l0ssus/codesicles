package com.hundredpercent.sample.dao.v1;

import com.hundredpercent.sample.dto.v1.Client;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *
 * @author SIGINT-X
 * 
 * This factory will supply an instance of a trustline, given an identifier
 * that identifies the parties in a trustline
 * 
 */
public class TrustLineFactoryImpl implements TrustLineFactory<Client> {
    
    
    

    public TrustLine newTrustLine(Client client) {
        return new TrustLineImpl(client.getId());
    }

    
    /*
    
    This is the trustline implementation. It holds
    
    1. An identifier for a unique trustline
    2. The current balance of the trustline
    3. A linkedlist of the transactions that have occurred on the trustline
    
    */
    private class TrustLineImpl implements TrustLine<BigDecimal> {

        private final String trustLineId;

        private BigDecimal balance;

        private final ConcurrentLinkedDeque<LineTransaction> transactions;

        private TrustLineImpl(String trustLineId) {
            this.trustLineId = trustLineId;
            this.balance = new BigDecimal(0);
            transactions = new ConcurrentLinkedDeque<>();
        }

        /*
        **handles giving of credit to a client. Increases the value of the trustline
        */
        @Override
        public void credit(BigDecimal value) {
            BigDecimal newValue = balance.add(value);
            balance = newValue;
            LineTransaction txn = new LineTransaction(Instant.now(), newValue);
            transactions.add(txn);
        }

        /**
         * increase the debt value of the trustline
         * @param value 
         */
        @Override
        public void debit(BigDecimal value) {
            BigDecimal newValue = balance.subtract(value);
            balance = newValue;
            LineTransaction txn = new LineTransaction(Instant.now(), newValue);
            transactions.add(txn);
        }

        @Override
        public String getTrustLineId() {
            return this.trustLineId;
        }

        @Override
        public BigDecimal getTrustLineBalance() {
            return balance;
        }

    }

    /*
    * An individual trustline transaction for record keeping
    *
    */
    private class LineTransaction {

        Instant transactionTime;
        BigDecimal transactionValue;

        public LineTransaction(Instant time, BigDecimal value) {
            this.transactionTime = time;
            this.transactionValue = value;
        }

    }

}
