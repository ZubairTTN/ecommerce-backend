package com.zubair.ecommerce.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.UUID;

/* UserRoleId class is used to create a composite primary key for a table that establishes a relationship between users and roles */

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class UserRoleId implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID userId;
    private UUID roleId;

}
