
package com.hundredpercent.sample.dto.v1;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author SIGINT-X
 */
public class TrustLineDebitResponse implements Serializable {
    
   private BigDecimal newBalance;

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal value) {
        this.newBalance = value;
    }

    @Override
    public String toString() {
        return "TrustLineDebitResponse{" + "value=" + newBalance + '}';
    }
    
}
