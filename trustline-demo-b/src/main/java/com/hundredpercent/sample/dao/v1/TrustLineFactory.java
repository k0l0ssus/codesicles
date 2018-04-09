package com.hundredpercent.sample.dao.v1;

/**
 *
 * @author SIGINT-X
 */
public interface TrustLineFactory<In> {

    TrustLine newTrustLine(In input);

}
