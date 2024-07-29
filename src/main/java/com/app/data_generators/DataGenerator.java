package com.app.data_generators;

import com.app.candidate.Candidate;
import com.app.candidate.CandidateComparators;
import com.app.electoral_district.ElectoralDistrict;
import com.app.voter.Voter;

import java.util.*;
import java.util.stream.Stream;

/**
 * Utility interface for generating sets of Candidates and Voters.
 * It provides static methods for generating these sets with varying sizes, and with various attributes.
 */
public interface DataGenerator {

    /**
     * Generates a Set of Voters with a random number for each Electoral District
     * The number of voters per district is defined within the range of minVoters and maxVoters.
     *
     * @param minVoters Minimum number of voters per district.
     * @param maxVoters Maximum number of voters per district.
     * @return a Set<Voter> of newly created Voter objects.
     * @throws IllegalArgumentException if minVoters is greater than or equal to maxVoters.
     */
    static Set<Voter> generateVoters(int minVoters, int maxVoters) {

        if(minVoters >= maxVoters){
            throw new IllegalArgumentException("Min voters cannot be greater than max voters");
        }

        var id = 1L;
        var voters = new HashSet<Voter>();

        for(var district : ElectoralDistrict.values()){

            var randomAmountOfVoter = generateRandomNumber(minVoters, maxVoters);

            for(int i = 0; i < randomAmountOfVoter; i++){
                voters.add(Voter.of(
                        id, district,
                        CandidateComparators.compareByNameThenSurnameThenElectoralDistrict));
                id++;
            }
        }
        return voters;
    }

    /**
     * Generates a Set of Candidates with a random number for each Electoral District
     * The number of candidates per district is defined within the range of minCandidates and maxCandidates.
     * Each candidate is given a random name and surname from a predefined list of names and surnames.
     *
     * @param minCandidates Minimum number of candidates per district.
     * @param maxCandidates Maximum number of candidates per district.
     * @return a Set<Candidate> of newly created Candidate objects.
     * @throws IllegalArgumentException if minCandidates is greater than or equal to maxCandidates.
     */
    static Set<Candidate> generateCandidates(int minCandidates, int maxCandidates) {

        if(minCandidates >= maxCandidates){
            throw new IllegalArgumentException("Min candidates cannot be greater than max candidates");
        }

        var candidates = new HashSet<Candidate>();
        var id = 1L;

        for(var district : ElectoralDistrict.values()){

            var randomAmountOfCandidates = generateRandomNumber(minCandidates, maxCandidates);
            for(int i = 0; i < randomAmountOfCandidates; i++) {
                candidates.add(Candidate
                        .builder()
                        .id(id)
                        .name(generateName())
                        .surname(generatePolishSurname())
                        .electoralDistrict(district)
                        .build());
                id++;
            }
        }
        return candidates;

    }

    /**
     * Generates a random number between the specified min and max values.
     *
     * @param min The minimum value (inclusive)
     * @param max The maximum value (exclusive)
     * @return a random int value between min (inclusive) and max (exclusive).
     */
    private static int generateRandomNumber(int min, int max){
        return Stream
                .generate(() -> new Random()
                        .nextInt(min, max))
                .limit(1)
                .findFirst().orElse(5);
    }

    /**
     * Generates a random Polish name from an array of predefined names.
     *
     * @return a String representing a random Polish name.
     */
    private static String generateName() {
        String[] names = {
                "Adam", "Andrzej", "Bartosz", "Dawid", "Dominik",
                "Grzegorz", "Jakub", "Jan", "Jerzy", "Karol",
                "Krzysztof", "Łukasz", "Maciej", "Marek", "Marcin",
                "Michał", "Paweł", "Piotr", "Rafał", "Robert",
                "Sebastian", "Szymon", "Tomasz", "Wojciech", "Zbigniew",
                "Agnieszka", "Alicja", "Anna", "Barbara", "Beata",
                "Ewa", "Grażyna", "Helena", "Izabela", "Jadwiga",
                "Joanna", "Katarzyna", "Kinga", "Krystyna", "Małgorzata",
                "Maria", "Marta", "Monika", "Natalia", "Olga",
                "Patrycja", "Paulina", "Renata", "Sylwia", "Zofia"};
        return names[generateRandomNumber(0, names.length)];
    }

    /**
     * Generates a random Polish surname from an array of predefined surnames.
     *
     * @return a String representing a random Polish surname.
     */
    private static String generatePolishSurname() {

        String[] surnames = {
                "Kowalski", "Nowak", "Wiśniewski", "Wójcik", "Kowalczyk",
                "Kamiński", "Lewandowski", "Zieliński", "Szymański", "Woźniak",
                "Dąbrowski", "Kozłowski", "Jankowski", "Mazur", "Wojciechowski",
                "Kwiatkowski", "Krawczyk", "Kaczmarek", "Piotrowski", "Grabowski",
                "Pawłowski", "Michalski", "Zając", "Król", "Wieczorek", "Jabłoński",
                "Wróbel", "Nowicki", "Majewski", "Olszewski", "Stępień", "Malinowski",
                "Jaworski", "Adamczyk", "Dudek", "Pawlak", "Górski", "Nowiński",
                "Sikora", "Walczak", "Baran", "Laskowski", "Urbaniak", "Szczepański",
                "Kucharski", "Wilk", "Lis", "Mazurek", "Wasilewski"};

        return surnames[generateRandomNumber(0, surnames.length)];
    }
}