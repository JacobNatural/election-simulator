package com.app.extension;


import com.app.candidate.Candidate;
import com.app.electoral_district.ElectoralDistrict;
import com.app.candidate.CandidateValidator;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CandidateExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(Candidate.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return Candidate.of(
                1L, "Jan",
                "Waldek", ElectoralDistrict.GDYNIA,
                new CandidateValidator("[A-Z][a-z]+","[A-Z][a-z]+"));
    }
}
