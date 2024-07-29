package com.app.candidate;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Validator interface for Candidate objects.
 * It provides method to validate a Candidate instance for criteria like name, surname, electoral district,
 * id and number of votes where each criteria has its own validation logic.
 */
@AllArgsConstructor
public class CandidateValidator implements Validator<Candidate> {

    private final String nameRegex;
    private final String surnameRegex;

    /**
     * Validates a Candidate object with respect to criteria like name, surname, electoral district, id and number of votes.
     * Any failures in validation are collected and returned in a List.
     *
     * @param candidate The Candidate instance to be validated.
     * @return A List of String, where each string is a descriptive error message. If List is empty, validation passed.
     */
    @Override
    public List<String> validate(Candidate candidate) {
        var errors = new ArrayList<String>();

        if(candidate.name == null){
            errors.add("Name cannot be null");
        }else if(candidate.name.isEmpty()){
            errors.add("Name cannot be empty");
        }else if(!candidate.name.matches(nameRegex)){
            errors.add("Name does not match regex");
        }

        if(candidate.surname == null){
            errors.add("Surname cannot be null");
        }else if (candidate.surname.isEmpty()){
            errors.add("Surname cannot be empty");
        }else  if(!candidate.surname.matches(surnameRegex)){
            errors.add("Surname does not match regex");
        }
        if(candidate.electoralDistrict == null){
            errors.add("Electoral district cannot be null");
        }

        if(candidate.id < 1){
            errors.add("Id cannot be less than 1");
        }

        if(candidate.numberOfVotes != 0){
            errors.add("Number of votes cannot be different from 0");
        }

        return errors;
    }
}