package com.server.springacl.repository.acl;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "acl_sid_own_permission")
public class SidOwnPermissionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "acl_sid_id")
    private Integer aclSidId;

    @Column(name = "acl_permission_id")
    private Integer aclPermissionId;

    private Integer status;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "acl_sid_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AclSidEntity aclSidEntity;

    @ManyToOne
    @JoinColumn(name = "acl_permission_id", referencedColumnName = "id", insertable = false, updatable = false)
    private AclPermissionEntity aclPermission;

}
