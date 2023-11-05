package com.bdo.evolution_native.client.model.retrivemodel;

import com.bdo.evolution_native.client.model.StatusType;
import com.bdo.evolution_native.client.model.initiatemodel.CardAcctIdType;
import com.bdo.evolution_native.client.model.initiatemodel.CustIdType;
import com.bdo.evolution_native.client.model.initiatemodel.CustProfBasicType;
import com.bdo.evolution_native.client.model.initiatemodel.RqUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustProfBasicInqRs
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustProfBasicInqRs {
    @JsonProperty("rqUID")
    private RqUID rqUID;

    @JsonProperty("status")
    private StatusType status;

    @JsonProperty("cardAcctId")
    private CardAcctIdType cardAcctId;

    @JsonProperty("custId")
    private CustIdType custId;

    @JsonProperty("custProfBasic")
    private CustProfBasicType custProfBasic;

}
