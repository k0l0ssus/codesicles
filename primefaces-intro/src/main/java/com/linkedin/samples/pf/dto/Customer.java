
package com.linkedin.samples.pf.dto;

import java.io.Serializable;

/**
 *
 * @author SIGINT-X
 */
public class Customer implements Serializable {
    
    private String customerId;
    private String customerAddress;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

}
