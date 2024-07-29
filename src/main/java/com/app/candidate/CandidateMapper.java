package com.app.candidate;

import java.util.function.ToIntFunction;

/**
 * Provides a functional mapping interface for Candidate objects. 
 *
 * This interface defines functions that map a Candidate to an Integer, based on specified attributes of the Candidate.
 */
public interface CandidateMapper {
    /**
     * Maps a Candidate object to its number of votes.
     */
    ToIntFunction<Candidate> toNumberOfVotes = candidate -> candidate.numberOfVotes;
}