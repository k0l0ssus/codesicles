
package com.hundredpercent.sample.dto.v1;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author SIGINT-X
 */
public class TrustLineDebitRequest implements Serializable {
    
    private BigDecimal value;

    /**
     * @return the value
     */
    public BigDecimal getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(BigDecimal value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TrustLineDebitRequest{" + "value=" + value + '}';
    }
    
}
