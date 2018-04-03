
package com.hundredpercent.sample.dto.v1;

/**
 *
 * @author SIGINT-X
 */
public class TrustLineBuilder {
    
   private String trustLineId; 
   
   public TrustLineBuilder trustLineId(String trustLineId){
       this.trustLineId = trustLineId;
       return this;
   }

}
