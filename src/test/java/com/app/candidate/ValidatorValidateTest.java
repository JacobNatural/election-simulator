package com.app.candidate;

import com.app.electoral_district.ElectoralDistrict;
import com.app.extension.CandidateValidatorExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CandidateValidatorExtension.class)
@RequiredArgsConstructor
public class ValidatorValidateTest {
    private final CandidateValidator candidateValidator;

    @Test
    @DisplayName("When validate is null")
    public void test1(){

        var candidate = Candidate
                .builder()
                .name("Darek")
                .surname("Filak")
                .id(1L)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThatThrownBy(
                () -> Validator.validate(candidate, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Validator cannot be null");
    }

    @Test
    @DisplayName("When validate throw exception with error")
    public void test2(){

        var candidate = Candidate
                .builder()
                .name(null)
                .surname(null)
                .id(1L)
                .numberOfVotes(2)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThatThrownBy(
                        () -> Validator.validate(candidate, candidateValidator))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name cannot be null\n" +
                        "Surname cannot be null\n" +
                        "Number of votes cannot be different from 0");
    }
}
