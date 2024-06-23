package com.server.springacl.repository.acl;

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
@Table(name = "acl_permission")
public class AclPermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String permission;

    private String description;


    @Column(updatable = false, name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(updatable = false, name = "updated_At")
    @UpdateTimestamp
    private Timestamp updatedAt;
}
