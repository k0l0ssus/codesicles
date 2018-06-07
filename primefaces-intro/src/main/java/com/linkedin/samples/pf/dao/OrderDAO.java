package com.linkedin.samples.pf.dao;

import com.linkedin.samples.pf.dto.Customer;
import com.linkedin.samples.pf.dto.Order;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author SIGINT-X
 */
public interface OrderDAO<M> extends Serializable {

    
    M getOrders();

    M getOrders(Customer customer);

    M getOrders(String customerId);

    <T> T getOrder(long orderId);
    
    <O> O saveOrder(Order order);

}
