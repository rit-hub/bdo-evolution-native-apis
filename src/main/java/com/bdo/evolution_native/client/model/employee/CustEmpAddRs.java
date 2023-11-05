package com.bdo.evolution_native.client.model.employee;

import com.bdo.evolution_native.client.model.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustEmpAddRs
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustEmpAddRs {
    @JsonProperty("rqUID")
    private RqUID rqUID;

    @JsonProperty("status")
    private StatusType status;

    @JsonProperty("cardAcctId")
    private CardAcctIdType cardAcctId;

    @JsonProperty("custId")
    private CustIdType custId;

    @JsonProperty("employment")
    private CustEmpRecType employment;

}
