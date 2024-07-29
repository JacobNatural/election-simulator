package com.app.voter;

import com.app.candidate.Candidate;
import com.app.electoral_district.ElectoralDistrict;
import lombok.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Class representing the voter in an election.
 */
@Builder
@EqualsAndHashCode
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Voter {

    // unique identifier for the voter
    private final long id;

    // electoral district to which the voter belongs
    private final ElectoralDistrict electoralDistrict;

    // set of candidates that the voter can vote for
    final Set<Candidate> candidates;

    /**
     * Method to create a new Voter.
     *
     * @param id unique identifier for the voter
     * @param electoralDistrict electoral district to which the voter belongs
     * @param comparator comparator to sort the candidates
     * @return Voter object
     */
    public static Voter of(
            long id, ElectoralDistrict electoralDistrict,
            Comparator<Candidate> comparator){

        // Throw exceptions if electoralDistrict, comparator is null or id is less than 1
        if(electoralDistrict == null){
                throw new IllegalArgumentException("ElectoralDistrict cannot be null");
        }

        if(comparator == null){
            throw new IllegalArgumentException("Comparator cannot be null");
        }

        if(id < 1){
            throw new IllegalArgumentException("Id cannot be less than 1");
        }

        // Create a set of candidates
        Set<Candidate> candidates = new TreeSet<>(comparator);

        // Return a new Voter object with provided parameters
        return new Voter(id, electoralDistrict, candidates);
    }

    /**
     * Method to assign candidates to the voter based on electoral district
     *
     * @param candidateSet set of candidates
     */
    public void fillCandidateDependOnDistrict(Set<Candidate> candidateSet) {
        this.candidates
                .addAll(candidateSet
                        .stream()
                        .filter(candidate -> candidate.hasElectoralDistrict(electoralDistrict))
                        .collect(Collectors.toSet()));
    }

    /**
     * Method to assign candidates to the voter without considering electoral district
     *
     * @param candidateSet set of candidates
     */
    public void fillCandidateNotDependOnDistrict(Set<Candidate> candidateSet){
        this.candidates.clear();
        this.candidates.addAll(candidateSet);
    }

    /**
     * Randomly select a candidate from the set and add a vote
     */
    public void choseRandom(){
        candidates.stream().toList().get(generateRandom(0, candidates.size())).addVote();
    }

    /**
     * Method to generate a random number within a specified range
     *
     * @param min minimum range
     * @param max maximum range
     * @return int randomly generated number in the range
     */
    private int generateRandom(int min, int max){
        return new Random().nextInt(min, max);
    }
}