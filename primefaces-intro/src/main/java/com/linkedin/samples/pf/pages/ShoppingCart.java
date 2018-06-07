package com.linkedin.samples.pf.pages;

import com.linkedin.samples.pf.dao.OrderDAO;
import com.linkedin.samples.pf.dto.Order;
import com.linkedin.samples.pf.dto.Product;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author SIGINT-X
 */
@SessionScoped
@Named("shoppingCart")
public class ShoppingCart implements Serializable {

    @Inject
    transient private OrderDAO<Collection<Order>> orderDao;

    private Order pendingOrder;

    private int quantityValue;
    
    private String currentTheme = "bluesky";

    public void addToCart(Product product) {
        if (pendingOrder == null) {
            pendingOrder = new Order();
             }
        
        pendingOrder.getOrderItems().put(product, quantityValue);
        BigDecimal price = product.getPricePer();
        BigDecimal cost = price.multiply(new BigDecimal(quantityValue));
        pendingOrder.setOrderTotal(pendingOrder.getOrderTotal().add(cost));
    }

    public void removeFromCart(Product product) {
        Integer qtty = pendingOrder.getOrderItems().get(product);
        BigDecimal cost = product.getPricePer().multiply(new BigDecimal(qtty));
        BigDecimal newCost =  pendingOrder.getOrderTotal().subtract(cost);
        pendingOrder.setOrderTotal(newCost);
        pendingOrder.getOrderItems().remove(product);

    }

    public void placeOrder() {
        if (pendingOrder != null) {
            String sessionId =   FacesContext.getCurrentInstance().getExternalContext().getSessionId(false);
            pendingOrder.setCustomerId(sessionId);
            pendingOrder.setCustomerId(sessionId);
      
            orderDao.saveOrder(pendingOrder);
            pendingOrder = null;
            quantityValue = 0;
        }
    }

    public Order getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(Order pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public int getQuantityValue() {
        return quantityValue;
    }

    public void setQuantityValue(int quantityValue) {
        this.quantityValue = quantityValue;
    }

    public String getCurrentTheme() {
        return currentTheme;
    }

    public void setCurrentTheme(String currentTheme) {
        this.currentTheme = currentTheme;
    }

    
    
}
