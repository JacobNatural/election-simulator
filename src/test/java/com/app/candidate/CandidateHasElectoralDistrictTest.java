package com.app.candidate;

import com.app.electoral_district.ElectoralDistrict;
import com.app.extension.CandidateExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CandidateExtension.class)
@RequiredArgsConstructor
public class CandidateHasElectoralDistrictTest {
    private final Candidate candidate;

    @Test
    @DisplayName("When a candidate has an electoral district")
    public void test1(){

        Assertions.assertThat(
                candidate.hasElectoralDistrict(ElectoralDistrict.GDYNIA))
                .isTrue();
    }

    @Test
    @DisplayName("When a candidate does not have an electoral district")
    public void test2(){

        Assertions.assertThat(
                        candidate.hasElectoralDistrict(ElectoralDistrict.KATOWICE))
                .isFalse();
    }
}
