package com.bdo.evolution_native.model.retrivemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * AliasDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AliasDetails {
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("SecondSurname")
    private String secondSurname;
    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("MiddleName")
    private String middleName;

    @JsonProperty("TitlePrefix")
    private String titlePrefix;

    @JsonProperty("NameSuffix")
    private String nameSuffix;

    @JsonProperty("BusinessName")
    private String businessName;

}
