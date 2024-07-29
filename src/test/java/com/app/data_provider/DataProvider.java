package com.app.data_provider;

import com.app.candidate.Candidate;
import com.app.candidate.CandidateComparators;
import com.app.electoral_district.ElectoralDistrict;
import com.app.voter.Voter;

import java.util.TreeSet;

public interface DataProvider {

    Candidate CANDIDATE1 = Candidate
            .builder()
            .id(1L)
            .name("Zenek")
            .surname("Segalski")
            .electoralDistrict(ElectoralDistrict.WARSZAWA)
            .build();

    Candidate CANDIDATE2 = Candidate
            .builder()
            .id(2L)
            .name("Antek")
            .surname("Mierzwa")
            .electoralDistrict(ElectoralDistrict.KATOWICE)
            .build();

    Candidate CANDIDATE3 = Candidate
            .builder()
            .id(3L)
            .name("Michal")
            .surname("Antoniak")
            .electoralDistrict(ElectoralDistrict.GDYNIA)
            .build();

    Candidate CANDIDATE4 = Candidate
            .builder()
            .id(4L)
            .name("Zenek")
            .surname("Mucha")
            .electoralDistrict(ElectoralDistrict.LUBLIN)
            .build();

    Voter VOTER1 = Voter.builder()
            .id(1L)
            .electoralDistrict(ElectoralDistrict.WARSZAWA)
            .candidates(new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict))
            .build();

    Voter VOTER2 = Voter.builder()
            .id(2L)
            .electoralDistrict(ElectoralDistrict.KATOWICE)
            .candidates(new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict))
            .build();

    Voter VOTER3 = Voter.builder()
            .id(3L)
            .electoralDistrict(ElectoralDistrict.KATOWICE)
            .candidates(new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict))
            .build();

    Voter VOTER4 = Voter.builder()
            .id(4L)
            .electoralDistrict(ElectoralDistrict.GDYNIA)
            .candidates(new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict))
            .build();
}
