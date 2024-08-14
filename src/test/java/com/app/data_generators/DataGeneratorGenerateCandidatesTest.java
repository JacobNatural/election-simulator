package com.app.data_generators;

import com.app.electoral_district.ElectoralDistrict;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class DataGeneratorGenerateCandidatesTest {

    @Test
    @DisplayName("When min voters is greater than max voters")
    public void test1(){

        Assertions.assertThatThrownBy(() ->
                        DataGenerator.generateCandidates(30,20))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Min candidates cannot be greater than max candidates");
    }

    @RepeatedTest(5)
    @DisplayName("When it generates the correct amount of candidates")
    public void test2(){

        Assertions.assertThat(DataGenerator.generateCandidates(5,10))
                .hasSizeBetween(
                        ElectoralDistrict.values().length * 5,
                        ElectoralDistrict.values().length * 10);
    }

}
