package com.app.candidate;

import java.util.Comparator;

/**
 * Provides a collection of Comparators for Comparing Candidate objects.
 *
 * This interface consists exclusively of static fields, each a Comparator implementing
 * a commonly used comparison operation on Candidate objects.
 */
public interface CandidateComparators {

    /**
     * Compare Candidates by name.
     */
    Comparator<Candidate> compareByName = Comparator.comparing(candidate -> candidate.name);

    /**
     * Compare Candidates by surname.
     */
    Comparator<Candidate> compareBySurname = Comparator.comparing(candidate -> candidate.surname);

    /**
     * Compare Candidates by electoral district.
     */
    Comparator<Candidate> compareByElectoralDistrict = Comparator.comparing(candidate -> candidate.electoralDistrict);

    /**
     * Chain comparison: compare by name, then surname, then electoral district.
     */
    Comparator<Candidate> compareByNameThenSurnameThenElectoralDistrict =
            compareByName.thenComparing(compareBySurname).thenComparing(compareByElectoralDistrict);
}