package com.app.candidate;

import com.app.electoral_district.ElectoralDistrict;
import lombok.*;

/**
 * Represents a candidate with information about their votes and electoral district.
 */
@Builder
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Candidate {

    /** The unique identifier of the candidate. */
    final long id;

    /** The first name of the candidate. */
    final String name;

    /** The surname of the candidate. */
    final String surname;

    /** The number of votes the candidate has received. By default, it is 0. */
    @Builder.Default
    int numberOfVotes = 0;

    /** The electoral district to which the candidate is allocated. */
    final ElectoralDistrict electoralDistrict;

    /**
     * Constructor for the Candidate class.
     *
     * @param id - Unique identifier of the candidate.
     * @param name - the first name of the candidate.
     * @param surname - the surname of the candidate.
     * @param electoralDistrict - the district in which the candidate is running.
     * @param validator - validator to validate the candidate object.
     * @return candidate - The created and validated candidate object.
     */
    public static Candidate of(
            long id, String name, String surname,
            ElectoralDistrict electoralDistrict, Validator<Candidate> validator){

        var candidate = Candidate
                .builder()
                .id(id)
                .name(name)
                .surname(surname)
                .electoralDistrict(electoralDistrict)
                .build();

        Validator.validate(candidate, validator);
        return candidate;
    }

    /**
     * Checks if the candidate is from the specified electoral district.
     *
     * @param electoralDistrict - the district to check
     * @return boolean - Returns true if the candidate is from the specified district, false otherwise.
     */
    public boolean hasElectoralDistrict(ElectoralDistrict electoralDistrict) {
        return this.electoralDistrict.equals(electoralDistrict);
    }

    /**
     * Increments the vote count for the candidate by one.
     */
    public void addVote() {
        numberOfVotes++;
    }

    /**
     * Resets the candidate's votes to zero. Typically used before a new round of voting.
     */
    public void resetVotesBeforeNextRound(){
        numberOfVotes = 0;
    }
}