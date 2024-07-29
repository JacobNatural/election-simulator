package com.app.candidate;

import com.app.extension.CandidateExtension;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(CandidateExtension.class)
@RequiredArgsConstructor
public class CandidateAddVoteTest {
    private final Candidate candidate;

    @Test
    @DisplayName("When votes increase correctly")
    public void test1(){

        candidate.addVote();

        Assertions
                .assertThat(CandidateMapper.toNumberOfVotes.applyAsInt(candidate))
                .isEqualTo(1);
    }
}
