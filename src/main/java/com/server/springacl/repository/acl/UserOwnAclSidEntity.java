package com.server.springacl.repository.acl;

import com.server.springacl.repository.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_own_acl_sid")
public class UserOwnAclSidEntity {

    @Id
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "acl_sid_id")
    private Integer aclSidId;

    private Timestamp expiredAt;
    private boolean willExpired;


    @Column(updatable = false, name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(updatable = false, name = "updated_At")
    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userEntity", referencedColumnName = "id", insertable = false, updatable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aclSidEntity", referencedColumnName = "id", insertable = false, updatable = false)
    private AclSidEntity aclSidEntity;
}
