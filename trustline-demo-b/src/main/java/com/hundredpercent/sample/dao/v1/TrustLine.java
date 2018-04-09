package com.hundredpercent.sample.dao.v1;

/**
 *
 * @author SIGINT-X
 */
public interface TrustLine<T> {

    public void credit(T value);

    public void debit(T value);
    
    public String getTrustLineId();
    
    public T getTrustLineBalance();

}
