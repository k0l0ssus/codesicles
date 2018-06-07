package com.linkedin.samples.pf.pages;

import com.linkedin.samples.pf.dao.ProductDAO;
import com.linkedin.samples.pf.dto.Product;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.PrimeFaces;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SIGINT-X
 */
@ViewScoped
@Named("productPage")
public class ProductListing implements Serializable {

    @Inject
    private transient ProductDAO<Product> productDao;

    private Product selectedProduct;

    private String currentMessage;
    
    private List<Product> products;
    
    @PostConstruct
    public void init(){
        products = (List<Product>) productDao.getProducts();
    }

    public void selectProduct(long product) {
        Product result = productDao.getProduct(product);
        if (result != null) {
            selectedProduct = result;
        } else {
            currentMessage = "Unknown product";

        }
    }
    
    public void cartPeek(){
        PrimeFaces.current().dialog().openDynamic("/shopping_cart.xhtml");
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }

    public void setCurrentMessage(String currentMessage) {
        this.currentMessage = currentMessage;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
