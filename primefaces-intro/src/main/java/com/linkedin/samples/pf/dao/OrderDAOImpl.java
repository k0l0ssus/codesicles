package com.linkedin.samples.pf.dao;

import com.linkedin.samples.pf.dto.Customer;
import com.linkedin.samples.pf.dto.Order;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.stream.Collectors.toList;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;

/**
 *
 * @author SIGINT-X
 */
@Singleton
public class OrderDAOImpl implements OrderDAO<Collection<Order>>, Serializable {

    ConcurrentHashMap<Long, Order> orders;

    long currentOrderId = 100000000;

    @PostConstruct
    public void init() {
        orders = new ConcurrentHashMap<Long, Order>();
    }

    @Override
    public Collection<Order> getOrders() {
        return orders.values().stream().collect(toList());
    }

    @Override
    public Collection<Order> getOrders(Customer customer) {
        return getOrders(customer.getCustomerId());
    }

    @Override
    public Collection<Order> getOrders(String customerId) {
        List<Order> orderList = orders.values().stream().filter(order -> order.getCustomerId() == customerId).collect(toList());
        return orderList;
    }

    @Override
    public Order getOrder(long orderId) {
        return orders.get(orderId);
    }

    @Override
    public Boolean saveOrder(Order order) {
        order.setOrderId(currentOrderId++);
        orders.put(order.getOrderId(), order);

        return true;
    }

}
