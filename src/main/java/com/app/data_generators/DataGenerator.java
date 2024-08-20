package com.app.data_generators;

import com.app.candidate.Candidate;
import com.app.candidate.CandidateComparators;
import com.app.electoral_district.ElectoralDistrict;
import com.app.voter.Voter;

import java.util.*;
import java.util.stream.Stream;

/**
 * Interface providing methods to generate data for the election simulation,
 * such as voters and candidates.
 */
public interface DataGenerator {

    /**
     * Generates a set of voters, each associated with an electoral district.
     * <p>
     * The number of voters per district is randomly determined within the given range.
     * </p>
     *
     * @param minVoters the minimum number of voters per district
     * @param maxVoters the maximum number of voters per district
     * @return a set of {@link Voter} objects
     * @throws IllegalArgumentException if {@code minVoters} is greater than or equal to {@code maxVoters}
     */
    static Set<Voter> generateVoters(int minVoters, int maxVoters) {

        if (minVoters >= maxVoters) {
            throw new IllegalArgumentException("Min voters cannot be greater than max voters");
        }

        var id = 1L;
        var voters = new HashSet<Voter>();

        for (var district : ElectoralDistrict.values()) {

            var randomAmountOfVoter = generateRandomNumber(minVoters, maxVoters);

            for (int i = 0; i < randomAmountOfVoter; i++) {
                voters.add(Voter.of(
                        id, district,
                        CandidateComparators.compareByNameThenSurnameThenElectoralDistrict));
                id++;
            }
        }
        return voters;
    }

    /**
     * Generates a set of candidates, each associated with an electoral district.
     * <p>
     * The number of candidates per district is randomly determined within the given range.
     * Each candidate is assigned a randomly generated name and surname.
     * </p>
     *
     * @param minCandidates the minimum number of candidates per district
     * @param maxCandidates the maximum number of candidates per district
     * @return a set of {@link Candidate} objects
     * @throws IllegalArgumentException if {@code minCandidates} is greater than or equal to {@code maxCandidates}
     */
    static Set<Candidate> generateCandidates(int minCandidates, int maxCandidates) {

        if (minCandidates >= maxCandidates) {
            throw new IllegalArgumentException("Min candidates cannot be greater than max candidates");
        }

        var candidates = new HashSet<Candidate>();
        var id = 1L;

        for (var district : ElectoralDistrict.values()) {

            var randomAmountOfCandidates = generateRandomNumber(minCandidates, maxCandidates);
            for (int i = 0; i < randomAmountOfCandidates; i++) {
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
     * Generates a random number within the specified range.
     *
     * @param min the minimum value (inclusive)
     * @param max the maximum value (exclusive)
     * @return a random integer between {@code min} (inclusive) and {@code max} (exclusive)
     */
    private static int generateRandomNumber(int min, int max) {
        return Stream
                .generate(() -> new Random()
                        .nextInt(min, max))
                .limit(1)
                .findFirst().orElse(5);
    }

    /**
     * Generates a random Polish first name.
     * <p>
     * The name is selected from a predefined list of common Polish names.
     * </p>
     *
     * @return a randomly selected first name
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
     * Generates a random Polish surname.
     * <p>
     * The surname is selected from a predefined list of common Polish surnames.
     * </p>
     *
     * @return a randomly selected surname
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