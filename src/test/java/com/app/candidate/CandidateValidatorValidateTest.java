package com.app.candidate;

import com.app.electoral_district.ElectoralDistrict;
import com.app.extension.CandidateValidatorExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(CandidateValidatorExtension.class)
@RequiredArgsConstructor
public class CandidateValidatorValidateTest {
    private final CandidateValidator candidateValidator;

    @Test
    @DisplayName("When the name is null")
    public void test1() {

        var candidate = Candidate
                .builder()
                .name(null)
                .surname("Filak")
                .id(1L)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThat( candidateValidator.validate(candidate))
                .isEqualTo(List.of("Name cannot be null"));
    }

    @Test
    @DisplayName("When the name is empty")
    public void test2() {

        var candidate = Candidate
                .builder()
                .name("")
                .surname("Filak")
                .id(1L)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThat( candidateValidator.validate(candidate))
                .isEqualTo(List.of("Name cannot be empty"));
    }

    @Test
    @DisplayName("When the name does not match the regex pattern")
    public void test3() {

        var candidate = Candidate
                .builder()
                .name("darek")
                .surname("Filak")
                .id(1L)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThat( candidateValidator.validate(candidate))
                .isEqualTo(List.of("Name does not match regex"));
    }

    @Test
    @DisplayName("When the surname is null")
    public void test4() {

        var candidate = Candidate
                .builder()
                .name("Darek")
                .surname(null)
                .id(1L)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThat( candidateValidator.validate(candidate))
                .isEqualTo(List.of("Surname cannot be null"));
    }

    @Test
    @DisplayName("When the surname is empty")
    public void test5() {

        var candidate = Candidate
                .builder()
                .name("Darek")
                .surname("")
                .id(1L)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThat( candidateValidator.validate(candidate))
                .isEqualTo(List.of("Surname cannot be empty"));
    }

    @Test
    @DisplayName("When the surname does not match the regex pattern")
    public void test6() {

        var candidate = Candidate
                .builder()
                .name("Darek")
                .surname("filak")
                .id(1L)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThat( candidateValidator.validate(candidate))
                .isEqualTo(List.of("Surname does not match regex"));
    }

    @Test
    @DisplayName("When the electoral district is null")
    public void test7(){
        var candidate = Candidate
                .builder()
                .name("Darek")
                .surname("Filak")
                .id(1L)
                .electoralDistrict(null)
                .build();

        Assertions.assertThat(candidateValidator.validate(candidate))
                .isEqualTo(List.of("Electoral district cannot be null"));
    }

    @Test
    @DisplayName("When the id is less than 1")
    public void test8(){
        var candidate = Candidate
                .builder()
                .name("Darek")
                .surname("Filak")
                .id(0L)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThat(candidateValidator.validate(candidate))
                .isEqualTo(List.of("Id cannot be less than 1"));
    }

    @Test
    @DisplayName("When the initial number of votes is incorrect")
    public void test9(){
        var candidate = Candidate
                .builder()
                .name("Darek")
                .surname("Filak")
                .id(1L)
                .numberOfVotes(4)
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        Assertions.assertThat(candidateValidator.validate(candidate))
                .isEqualTo(List.of("Number of votes cannot be different from 0"));
    }

    @Test
    @DisplayName("When the name, surname, and electoral district are null, and id is less than 1")
    public void test10(){
        var candidate = Candidate
                .builder()
                .name(null)
                .surname(null)
                .id(0L)
                .numberOfVotes(0)
                .electoralDistrict(null)
                .build();

        Assertions.assertThat(candidateValidator.validate(candidate))
                .isEqualTo(List.of(
                        "Name cannot be null",
                        "Surname cannot be null",
                        "Electoral district cannot be null",
                        "Id cannot be less than 1"));
    }

}
