package com.bdo.evolution_native.client.model.customerlist;

import com.bdo.evolution_native.client.model.StatusType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * The type Cust list inq rs.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CustListInqRs {
    @JsonProperty("rqUID")
    private RqUID rqUID;

    @JsonProperty("status")
    private StatusType status;

    @JsonProperty("recCtrlOut")
    private RecCtrlOutType recCtrlOut;

    @JsonProperty("custBasics")
    private List<CustBasicDtlType> custBasics;

}
