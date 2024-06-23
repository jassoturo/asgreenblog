package com.server.springacl.configuration.webfilter;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class AclGrantedAuthority implements GrantedAuthority {
    private String permission;
    @Override
    public String getAuthority() {
        return permission;
    }

    public boolean hasPermission(String permission) {
        return this.permission.equalsIgnoreCase(permission);
    }

    public AclGrantedAuthority setPermission(String permission) {
        this.permission = permission;
        return this;
    }

    public String getPermission() {
        return permission;
    }
}
