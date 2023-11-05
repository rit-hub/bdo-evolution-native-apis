package com.bdo.evolution_native.client.model.retrivemodel;

import com.bdo.evolution_native.client.model.initiatemodel.CardAcctIdType;
import com.bdo.evolution_native.client.model.initiatemodel.CustIdType;
import com.bdo.evolution_native.client.model.initiatemodel.RqUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustProfBasicInqRq
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustProfBasicInqRq {
    @JsonProperty("rqUID")
    private RqUID rqUID;

    @JsonProperty("cardAcctId")
    private CardAcctIdType cardAcctId;

    @JsonProperty("custId")
    private CustIdType custId;

}
