package com.linkedin.samples.pf.dao;

import java.util.Collection;

/**
 *
 * @author SIGINT-X
 */
public interface ProductDAO<M> {

    M getProduct(long productId);

    <I> M getProduct(I productId);
    
    Collection<M> getProducts();

}
