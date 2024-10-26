package com.uni.foodstock.config;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SchemaCurrentTenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        String identifier;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userLogado = authentication.getName();
            identifier = userLogado;
        return identifier;
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return false;
    }
}


