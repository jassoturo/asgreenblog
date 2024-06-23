package com.server.springacl.domain.acl;

public interface GrantPermissionUseCase {
    boolean grantToUserNonExpire(Integer userId, Integer permission);
    boolean grantToUser(Integer userId, Integer permissionm, boolean isExpire, Long expiredTime);
}
