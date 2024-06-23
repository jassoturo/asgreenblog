package com.server.springacl.configuration.webfilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
@Slf4j
public class AclEvaluatorService implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || targetDomainObject == null || !(permission instanceof String)) {
            return false;
        }

        String targetType = targetDomainObject.toString();
        return hasPrivilege(authentication, targetType, permission.toString().toUpperCase());

    }


    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {

        if (authentication == null || targetType == null || !(permission instanceof String)) {
            return false;
        }

        return hasPrivilege(authentication, targetType, permission.toString().toUpperCase(), targetId.toString());
    }

    private boolean hasPrivilege(Authentication authentication, String targetType, String permission) {
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            AclGrantedAuthority aclGrantedAuthority = (AclGrantedAuthority) ga;
            if (aclGrantedAuthority.hasPermission(combine(targetType, permission, null))) {
                return true;
            }
        }
        return false;
    }

    private boolean hasPrivilege(Authentication authentication, String targetType, String permission, String targetId) {
        for (GrantedAuthority ga : authentication.getAuthorities()) {
            AclGrantedAuthority aclGrantedAuthority = (AclGrantedAuthority) ga;
            if (aclGrantedAuthority.hasPermission(combine(targetType, permission, targetId))) {
                return true;
            }
        }
        return false;
    }

    private String combine(String targetType, String permission, String targetId) {
        if (targetId == null) {
            return targetType + "." + permission;
        }

        return targetType + "." + permission + "." + targetId;
    }
}
