package com.hundredpercent.sample.dao.v1;

import com.hundredpercent.sample.dto.v1.Client;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 *
 * @author SIGINT-X
 */
public class TrustLineFactoryImpl implements TrustLineFactory<Client> {
    
    
    

    public TrustLine newTrustLine(Client client) {
        return new TrustLineImpl(client.getId());
    }

    private class TrustLineImpl implements TrustLine<BigDecimal> {

        private final String trustLineId;

        private BigDecimal balance;

        private final ConcurrentLinkedDeque<LineTransaction> transactions;

        private TrustLineImpl(String trustLineId) {
            this.trustLineId = trustLineId;
            this.balance = new BigDecimal(0);
            transactions = new ConcurrentLinkedDeque<>();
        }

        @Override
        public void credit(BigDecimal value) {
            BigDecimal newValue = balance.subtract(value);
            balance = newValue;
            LineTransaction txn = new LineTransaction(Instant.now(), newValue);
            transactions.add(txn);
        }

        @Override
        public void debit(BigDecimal value) {
            BigDecimal newValue = balance.add(value);
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

    private class LineTransaction {

        Instant transactionTime;
        BigDecimal transactionValue;

        public LineTransaction(Instant time, BigDecimal value) {
            this.transactionTime = time;
            this.transactionValue = value;
        }

    }

}
