package com.app;

import com.app.candidate.CandidateComparators;
import com.app.data_generators.DataGenerator;
import com.app.elections.Elections;


import java.util.TreeSet;

/**
 * Main application class.
 */
public class App {

    /**
     * Application entry point.
     *
     * @param args the command-line arguments (not used in this application)
     */
    public static void main(String[] args) {


        // CANDIDATE TREE SET
        // Initialize a TreeSet of candidates, comparing candidates by name, surname and electoral district.
        var candidates = new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict);

        // CANDIDATES GENERATE
        // Populate the TreeSet with a generated set of candidates.
        candidates.addAll(DataGenerator.generateCandidates(15, 20));

        // VOTERS GENERATE
        // Generate a list of voters.
        var voters = DataGenerator.generateVoters(100, 110);

        // ELECTION INITIALIZATION
        // Initialize an election with the generated candidates and voters.
        var elections = Elections.of(candidates, voters);

        // START ELECTION AND GET LEADER
        // Start the election and print out the results (round number and leader) for each round.
      elections.startElections().entrySet().forEach(
              set -> System.out.println("Round: " + set.getKey() + ", " + set.getValue()));

    }
}