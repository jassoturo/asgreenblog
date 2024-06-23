package com.server.springacl.domain.acl;

public interface GrantRoleUseCase {

    boolean grant(Integer userId, Integer aclSid);
}
