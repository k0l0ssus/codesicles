
package com.hundredpercent.sample.dao.v1;

/**
 *
 * @author SIGINT-X
 */
public interface TrustLineFactory<In> {
    
    public TrustLine newTrustLine(In input);

}
