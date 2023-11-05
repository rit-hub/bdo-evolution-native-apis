package com.bdo.evolution_native.client.model.addcard;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * CardAcctDtlInfoAddType
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
public class CardAcctDtlInfoAddType {
    @JsonProperty("shortName")
    private String shortName = null;

    @JsonProperty("custId")
    private CustIdType custId = null;

    @JsonProperty("cardAcctId")
    private CardAcctIdType cardAcctId = null;

    @JsonProperty("isoId")
    private String isoId = null;

    @JsonProperty("cardTypeCode")
    private String cardTypeCode = null;

    @JsonProperty("cardStatusCode")
    private String cardStatusCode = null;

    @JsonProperty("langCode")
    private String langCode = null;

    @JsonProperty("vipClass")
    private String vipClass = null;

    @JsonProperty("wdBalOverrideFlag")
    private Boolean wdBalOverrideFlag = null;

    @JsonProperty("cardGenNum")
    private Integer cardGenNum = null;

    @JsonProperty("cardGenNxtCycleFlag")
    private Boolean cardGenNxtCycleFlag = null;

    @JsonProperty("pinMailerNxtCycleCode")
    private String pinMailerNxtCycleCode = null;

    @JsonProperty("waiveTrnFeeFlag")
    private Boolean waiveTrnFeeFlag = null;

    @JsonProperty("waiveCardFeeFlag")
    private Boolean waiveCardFeeFlag = null;

    @JsonProperty("thirdPartyXferFlag")
    private Boolean thirdPartyXferFlag = null;

    @JsonProperty("cardExpDt")
    private String cardExpDt = null;

    @JsonProperty("expirationSpecDay")
    private String expirationSpecDay = null;

    @JsonProperty("nextCardReissueDt")
    private String nextCardReissueDt = null;

    @JsonProperty("nextPINIssueDt")
    private String nextPINIssueDt = null;

    @JsonProperty("nextCardFeeDt")
    private String nextCardFeeDt = null;

    @JsonProperty("embossingName")
    private String embossingName = null;

    @JsonProperty("remoteBankingFlag")
    private Boolean remoteBankingFlag = null;

}
