package com.app.voter;

import com.app.candidate.CandidateComparators;
import com.app.electoral_district.ElectoralDistrict;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class VoterOfTest {

    @Test
    @DisplayName("When the comparator is null")
    public void test1(){

        Assertions.assertThatThrownBy(
                () -> Voter.of(
                        1L, ElectoralDistrict.WARSZAWA,
                        null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Comparator cannot be null");
    }

    @Test
    @DisplayName("When the electoral district is negative")
    public void test2() {
        Assertions.assertThatThrownBy(
                () -> Voter.of(
                        1L, null, CandidateComparators.compareByName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ElectoralDistrict cannot be null");
    }

    @Test
    @DisplayName("When id is less than 1")
    public void test3(){
        Assertions.assertThatThrownBy(
                () -> Voter.of(
                        0, ElectoralDistrict.WARSZAWA, CandidateComparators.compareByElectoralDistrict))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id cannot be less than 1");
    }

    @Test
    @DisplayName("When a voter is correctly initialized")
    public void test4(){
        Assertions.assertThatNoException().isThrownBy(
                () -> Voter.of(1L, ElectoralDistrict.WARSZAWA, CandidateComparators.compareByName)
        );
    }
}
