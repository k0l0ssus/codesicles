
package com.linkedin.samples.pf.dto;

import com.linkedin.samples.pf.constants.OrderStatus;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author SIGINT-X
 */
public class Order implements Serializable {
    
    private long orderId;
    private String customerId;
    private Instant orderCreated;
    private Instant orderUpdated;
    private BigDecimal orderTotal;
    private OrderStatus orderStatus;
    private Map<Product,Integer> orderItems; 
    private Set<Product> products;
    
    public Order(){
        orderItems = new HashMap<>();
        orderCreated = Instant.now();
        orderTotal = BigDecimal.ZERO;
        orderStatus = OrderStatus.PENDING; 
    }

    /**
     * @return the orderId
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the customerId to set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the orderCreated
     */
    public Instant getOrderCreated() {
        return orderCreated;
    }

    /**
     * @param orderCreated the orderCreated to set
     */
    public void setOrderCreated(Instant orderCreated) {
        this.orderCreated = orderCreated;
    }

    /**
     * @return the orderUpdated
     */
    public Instant getOrderUpdated() {
        return orderUpdated;
    }

    /**
     * @param orderUpdated the orderUpdated to set
     */
    public void setOrderUpdated(Instant orderUpdated) {
        this.orderUpdated = orderUpdated;
    }

    /**
     * @return the orderStatus
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * @param orderStatus the orderStatus to set
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * @return the orderItems
     */
    public Map<Product,Integer> getOrderItems() {
        return orderItems;
    }

    /**
     * @param orderItems the orderItems to set
     */
    public void setOrderItems(Map<Product,Integer> orderItems) {
        this.orderItems = orderItems;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (int) (this.orderId ^ (this.orderId >>> 32));
        hash = 89 * hash + Objects.hashCode(this.orderCreated);
        hash = 89 * hash + Objects.hashCode(this.orderTotal);
        hash = 89 * hash + Objects.hashCode(this.orderItems);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.customerId != other.customerId) {
            return false;
        }
        if (!Objects.equals(this.orderCreated, other.orderCreated)) {
            return false;
        }
        if (!Objects.equals(this.orderTotal, other.orderTotal)) {
            return false;
        }
        if (!Objects.equals(this.orderItems, other.orderItems)) {
            return false;
        }
        return true;
    }

    public Set<Product> getProducts() {
        return orderItems.keySet();
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    
}
