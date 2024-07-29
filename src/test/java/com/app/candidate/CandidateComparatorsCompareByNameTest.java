package com.app.candidate;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.app.data_provider.DataProvider.*;

public class CandidateComparatorsCompareByNameTest {

    @Test
    @DisplayName("When comparing by name works correctly")
    public void test1(){

        var candidates = new ArrayList<>(List.of(
                CANDIDATE1,
                CANDIDATE2,
                CANDIDATE3
        ));

        candidates.sort(CandidateComparators.compareByName);

        Assertions.assertThat(candidates)
                .isEqualTo(List.of(
                        CANDIDATE2, CANDIDATE3, CANDIDATE1));
    }

}

