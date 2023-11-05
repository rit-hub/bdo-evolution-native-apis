package com.bdo.evolution_native.client.model.employee;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CustEmpAddRq
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustEmpAddRq {
    @JsonProperty("rqUID")
    private RqUID rqUID;
    @JsonProperty("cardAcctId")
    private CardAcctIdType cardAcctId;

    @JsonProperty("custId")
    private CustIdType custId;

    @JsonProperty("employment")
    private CustEmpRecType employment;

}
