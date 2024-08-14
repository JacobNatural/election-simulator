package com.app;

import com.app.candidate.CandidateComparators;
import com.app.data_generators.DataGenerator;
import com.app.elections.Elections;

import java.util.HashMap;
import java.util.TreeSet;

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

        // Initialize a map to store configuration parameters for the election.
        var params = new HashMap<String, Integer>();

        // Parse command-line arguments and populate the parameters map.
        for(var arg : args){
            if(arg.matches("(--cMin|--cMax|--vMin|--vMax)=\\d+")){
                var splitArg = arg.split("=");
                params.put(splitArg[0], Integer.parseInt(splitArg[1]));
            }else {
                System.out.println(STR."Invalid argument \{arg}, usign default value");
            }

        }

        // Default number of candidates and voters for electoral district
        var minCandidatesAmountPerDistrict = params.getOrDefault("--cMin", 5);
        var maxCandidatesAmountPerDistrict = params.getOrDefault("--cMax", 10);
        var minVotersAmountPerDistrict = params.getOrDefault("--vMin", 10);
        var maxVotersAmountPerDistrict = params.getOrDefault("--vMax", 50);



        // Initialize a TreeSet of candidates, comparing candidates by name, surname and electoral district.
        var candidates = new TreeSet<>(CandidateComparators.compareByNameThenSurnameThenElectoralDistrict);

        // Populate the TreeSet with a generated set of candidates.
        candidates.addAll(DataGenerator.generateCandidates(minCandidatesAmountPerDistrict, maxCandidatesAmountPerDistrict));

        // Generate a list of voters.
        var voters = DataGenerator.generateVoters( minVotersAmountPerDistrict, maxVotersAmountPerDistrict);

        // Initialize an election with the generated candidates and voters.
        var elections = Elections.of(candidates, voters);

        // Start the election and print out the results (round number and leader) for each round.
      elections.startElections().entrySet().forEach(
              set -> System.out.println("Round: " + set.getKey() + ", " + set.getValue()));

    }
}