package com.hundredpercent.sample.dao.v1;

import com.hundredpercent.sample.dto.v1.Client;

/**
 *
 * @author SIGINT-X
 */
public interface TrustLineRepository {

    TrustLine getTrustLine(Client client) throws Exception;
}
