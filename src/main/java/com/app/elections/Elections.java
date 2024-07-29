package com.app.elections;

import com.app.candidate.Candidate;
import com.app.candidate.CandidateMapper;
import com.app.voter.Voter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a single instance of an election.
 * This class maintains the set of candidates and voters, maps voters to certain districts
 * and also implements the procedure for starting and conducting an election.
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class Elections {

    private final Set<Candidate> candidates;
    private final Set<Voter> voters;

    /**
     * Factory method to create a new instance of Elections with given set of candidates and voters.
     * It also validates the given sets for null or empty conditions.
     *
     * @param candidates Set of Candidate objects participating in the election.
     * @param voters Set of Voter objects who will vote in the election.
     * @return a new instance of Elections with given candidates and voters.
     * @throws IllegalArgumentException if candidates or voters are null or empty.
     */
    public static Elections of(Set<Candidate> candidates, Set<Voter> voters) {

        if(candidates == null){
            throw new IllegalArgumentException("Candidates cannot be null");
        }

        if(candidates.isEmpty()){
            throw new IllegalArgumentException("Candidates cannot be empty");
        }

        if(voters == null){
            throw new IllegalArgumentException("Voters cannot be null");
        }

        if(voters.isEmpty()){
            throw new IllegalArgumentException("Voters cannot be empty");
        }

        var elections = new Elections(candidates, voters);
        elections.fillVotersDependOnDistrict();

        return elections;
    }

    /**
     * Performs a mapping of candidates to the voters who are in the same electoral district.
     */
    private void fillVotersDependOnDistrict() {
        voters.forEach(voter -> voter.fillCandidateDependOnDistrict(candidates));
    }

    /**
     * Assigns candidates to the voters not considering the electoral district.
     * Usually run when a tie-breaker round is required in the elections.
     */
    private void fillVotersNotDependOnDistrict() {
        voters.forEach(voter -> voter.fillCandidateNotDependOnDistrict(candidates));
    }

    /**
     * Starts the election process and returns the result as a Map after each round of voting.
     * Key in the map is the round number and value is the leader or set of leaders after that round.
     *
     * @return a Map where the key is the round number and value is the result after that round.
     */
    public Map<Integer, String> startElections() {


        var checker = true;
        var amountOfElections = 1;
        var votesResults = new HashMap<Integer, String>();


        while (checker) {

            voters.forEach(Voter::choseRandom);

          var  voteWithLeader = candidates
                    .stream()
                    .collect(Collectors.groupingBy(
                            CandidateMapper.toNumberOfVotes::applyAsInt, Collectors.toSet()))
                    .entrySet()
                    .stream()
                    .max(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue)
                    .orElseThrow();

            votesResults.put(amountOfElections, voteWithLeader.toString());

            if (voteWithLeader.size() > 1) {
                voteWithLeader.forEach(Candidate::resetVotesBeforeNextRound);
                candidates.clear();
                candidates.addAll(voteWithLeader);
                fillVotersNotDependOnDistrict();
            } else {
                checker = false;
            }

            amountOfElections++;
        }
        return votesResults;
    }
}