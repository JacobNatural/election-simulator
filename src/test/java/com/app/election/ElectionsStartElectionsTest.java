package com.app.election;

import com.app.candidate.Candidate;
import com.app.candidate.CandidateComparators;
import com.app.elections.Elections;
import com.app.electoral_district.ElectoralDistrict;
import com.app.voter.Voter;
import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

import java.util.*;

@RequiredArgsConstructor
public class ElectionsStartElectionsTest {
    private Candidate candidate1;
    private Candidate candidate2;
    private Candidate candidate3;
    private Voter voter1;
    private Voter voter2;
    private Voter voter3;
    private Voter voter4;

    @BeforeEach
    public void setUp() {
        candidate1 = Candidate
                .builder()
                .id(1L)
                .name("Zenek")
                .surname("Segalski")
                .electoralDistrict(ElectoralDistrict.WARSZAWA)
                .build();

        candidate2 = Candidate
                .builder()
                .id(2L)
                .name("Antek")
                .surname("Mierzwa")
                .electoralDistrict(ElectoralDistrict.KATOWICE)
                .build();

          candidate3 = Candidate
                  .builder()
                  .id(3L)
                  .name("Michal")
                  .surname("Antoniak")
                  .electoralDistrict(ElectoralDistrict.GDYNIA)
                  .build();

          voter1 = Voter.builder()
                  .id(1L)
                  .electoralDistrict(ElectoralDistrict.WARSZAWA)
                  .candidates(new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict))
                  .build();

          voter2 = Voter.builder()
                .id(2L)
                .electoralDistrict(ElectoralDistrict.KATOWICE)
                .candidates(new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict))
                .build();

          voter3 = Voter.builder()
                .id(3L)
                .electoralDistrict(ElectoralDistrict.KATOWICE)
                .candidates(new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict))
                .build();

          voter4 = Voter.builder()
                .id(4L)
                .electoralDistrict(ElectoralDistrict.GDYNIA)
                .candidates(new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict))
                .build();
    }
    @RepeatedTest(5)
    @DisplayName("When we start elections and there is only one round of voting")
    public void test1(){

        var candidates = new TreeSet<>(
                CandidateComparators.compareByNameThenSurnameThenElectoralDistrict);
        var data = new HashSet<>(Set.of(candidate1, candidate2, candidate3));
        var voters = new HashSet<>(Set.of(voter1,  voter2, voter3, voter4));
        candidates.addAll(data);


        Elections elections = Elections.of(candidates,voters);

        Assertions.assertThat(elections.startElections())
                .containsOnlyKeys(1);

    }

    @RepeatedTest(5)
    @DisplayName("When we start the elections and there is only one round of voting, we get one candidate")
    public void test2(){
        var candidates = new TreeSet<>(
                CandidateComparators.compareByNameThenSurnameThenElectoralDistrict);
        var data = new HashSet<>(Set.of(candidate1, candidate2, candidate3));
        var voters = new HashSet<>(Set.of(voter1, voter2, voter3, voter4));
        candidates.addAll(data);

        Elections elections = Elections.of(candidates,voters);

        Assertions.assertThat(elections.startElections())
                .size()
                .isEqualTo(1);

    }

    @RepeatedTest(5)
    @DisplayName("When we start elections and there is more than one round of voting")
        public void test3(){

        var candidates = new TreeSet<>(
                CandidateComparators.compareByNameThenSurnameThenElectoralDistrict);
        var data = new HashSet<>(Set.of(candidate1, candidate2, candidate3));
        var voters = new HashSet<>(Set.of(voter1, voter2, voter4));
        candidates.addAll(data);

        Elections elections = Elections.of(candidates,voters);

        var amountOfElections = elections
                .startElections()
                .keySet()
                .stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();

        Assertions.assertThat(amountOfElections).isGreaterThan(1);
    }

    @RepeatedTest(5)
    @DisplayName("When we start elections, there is more than one round of voting, and we get one candidate.")
    public void test4(){

        var candidates = new TreeSet<>(
                CandidateComparators.compareByNameThenSurnameThenElectoralDistrict);
        var data = new HashSet<>(Set.of(candidate1, candidate2, candidate3));
        var voters = new HashSet<>(Set.of(voter1, voter2, voter4));
        candidates.addAll(data);

        Elections elections = Elections.of(candidates,voters);

        Assertions
                .assertThat(elections.startElections())
                .size()
                .isGreaterThan(1);
    }
}

