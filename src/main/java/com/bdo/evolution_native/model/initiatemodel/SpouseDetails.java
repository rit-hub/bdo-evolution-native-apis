package com.bdo.evolution_native.model.initiatemodel;

import com.bdo.evolution_native.constants.EvolutionConstantUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * SpouseDetails
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Generated
@Valid
public class SpouseDetails {
    @JsonProperty("FirstName")
    private String firstName;

    @JsonProperty("MiddleName")
    private String middleName;

    @JsonProperty("LastName")
    private String lastName;

    @JsonProperty("DateOfBirth")
    @Pattern(regexp = EvolutionConstantUtils.REGEX_FOR_DATE, message = EvolutionConstantUtils.DATE_FORMAT_MESSAGE)
    private String dateOfBirth;

    @JsonProperty("PlaceOfBirth")
    private String placeOfBirth;

    @JsonProperty("WorkDetails")
    private String workDetails;
}