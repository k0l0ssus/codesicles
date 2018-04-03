
package com.hundredpercent.sample.dao.v1;

/**
 *
 * @author SIGINT-X
 */
public class TrustLineImpl implements TrustLine {
    
    
    private TrustLineImpl(String trustLineId){
        
    }

    @Override
    public void credit(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void debit(Object value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getTrustLineId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getTrustLineBalance() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
