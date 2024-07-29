package com.app.extension;

import com.app.candidate.CandidateValidator;
import org.junit.jupiter.api.extension.*;


public class CandidateValidatorExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().equals(CandidateValidator.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new CandidateValidator("[A-Z][a-z]+","[A-Z][a-z]+");
    }
}
