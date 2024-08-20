package com.app;

import com.app.candidate.CandidateComparators;
import com.app.data_generators.DataGenerator;
import com.app.elections.Elections;

import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Main application class that sets up and runs an election simulation.
 */
public class App {

    /**
     * Application entry point.
     * <p>
     * This method parses command-line arguments for configuration parameters, generates candidates and voters,
     * initializes an election, and then starts the election, printing the results of each round.
     * </p>
     *
     * @param args the command-line arguments in the format --cMin, --cMax, --vMin, --vMax with integer values
     */
    public static void main(String[] args) {


        // Parse command-line arguments and populate the parameters map.
        var params = Arrays.stream(args)
                .filter(arg -> arg.matches("(--cMin|--cMax|--vMin|--vMax)=\\d+"))
                .map(arg -> arg.split("="))
                .collect(Collectors.toMap(arg -> arg[0], arg -> Integer.parseInt(arg[1])));


        // Initialize a TreeSet of candidates, comparing candidates by name, surname and electoral district.
        var candidates = new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict);

        // Populate the TreeSet with a generated set of candidates.
        candidates.addAll(DataGenerator.generateCandidates(
                params.getOrDefault("--cMin", 5),
                params.getOrDefault("--cMax", 10)));

        // Generate a list of voters.
        var voters = DataGenerator.generateVoters(
                params.getOrDefault("--vMin", 10),
                params.getOrDefault("--vMax", 50));

        // Initialize an election with the generated candidates and voters.
        var elections = Elections.of(candidates, voters);

        // Start the election and print out the results (round number and leader) for each round.
      elections.startElections().entrySet().forEach(
              set -> System.out.println("Round: " + set.getKey() + ", " + set.getValue()));

    }
}