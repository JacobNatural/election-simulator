package com.app.candidate;

import com.app.extension.CandidateExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CandidateExtension.class)
@RequiredArgsConstructor
public class CandidateMapperToNumberOfVotes {
    private final Candidate candidate;

    @Test
    @DisplayName("When the candidate mapper returns the vote value from a candidate")
    public void test1(){

        Assertions.assertThat(
                CandidateMapper
                        .toNumberOfVotes.applyAsInt(candidate))
                .isZero();
    }
}
