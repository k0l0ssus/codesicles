package com.linkedin.samples.pf.pages;

import com.linkedin.samples.pf.constants.OrderStatus;
import com.linkedin.samples.pf.dao.OrderDAO;
import com.linkedin.samples.pf.dto.Order;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.el.ELContextEvent;
import javax.el.ELContextListener;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SIGINT-X
 */
@ViewScoped
@Named
public class OrderManagement implements Serializable {

    @Inject
    transient private OrderDAO<Collection<Order>> orderDao;

    private List<Order> orders;

    private List<Order> customerOrders;

    private Order order;

    @PostConstruct
    public void init() {
        orders = (List<Order>) orderDao.getOrders();

        FacesContext.getCurrentInstance().getApplication().addELContextListener(new ELContextListener() {
            @Override
            public void contextCreated(ELContextEvent evt) {
                evt.getELContext().getImportHandler()
                        .importClass("com.lynda.samples.pf.constants.OrderStatus");
            }
        });
    }

    public void fulfillOrder(Order order) {
      System.out.println("Fulfilling order "+order);
      orders.forEach(item -> {if(item.getOrderId()==order.getOrderId()){item.setOrderStatus(OrderStatus.FULFILLED);}});
    }
    
    public void viewOrder(Order order){
        this.order = order;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(List<Order> customerOrders) {
        this.customerOrders = customerOrders;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
