
package com.hundredpercent.sample.dto.v1;

import java.math.BigDecimal;

/**
 *
 * @author SIGINT-X
 */
public class TrustLineCreditResponse {
    
    private BigDecimal newBalance;

    /**
     * @return the newBalance
     */
    public BigDecimal getNewBalance() {
        return newBalance;
    }

    /**
     * @param newBalance the newBalance to set
     */
    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

}
