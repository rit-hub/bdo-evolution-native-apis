package com.bdo.evolution_native.client.model.addcard;

import com.bdo.evolution_native.client.model.initiatemodel.RqUID;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

/**
 * CardAcctAddRq
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CardAcctAddRq {
    @JsonProperty("rqUID")
    private RqUID rqUID = null;

    @JsonProperty("cardAcctDtlInfo")
    private CardAcctDtlInfoAddType cardAcctDtlInfo = null;

    @JsonProperty("acctLinkCnt")
    private Integer acctLinkCnt = null;

    @JsonProperty("acctLinks")
    private List<AcctLinkType> acctLinks = null;

    @JsonProperty("rqUserID")
    private String rqUserID = null;

    @JsonProperty("branchFlag")
    private String branchFlag = null;

    @JsonProperty("oldCardNum")
    private String oldCardNum = null;

    @JsonProperty("branchCode")
    private String branchCode = null;

}
