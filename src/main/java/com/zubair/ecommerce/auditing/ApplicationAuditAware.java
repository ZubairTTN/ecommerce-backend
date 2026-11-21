package com.zubair.ecommerce.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/* Auditing class to getLastModified and created by for the
entities
 */
@Component("auditorAware") /* This registers your class as a Spring bean with the name: auditorAware and is being used in ApplicationAuditAware class*/
public class ApplicationAuditAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || !authentication.isAuthenticated())
        {
            return Optional.of("system"); /* Optional.of() expects a non-null value. */
        }
        return Optional.ofNullable(authentication.getName());
    }
}
