package com.zubair.ecommerce.entity.category;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/* Composite Primary Key */

@Embeddable /* This class can be placed inside another entity, and its fields can become columns in that entity's database table. */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CategoryMetadataFieldValueId implements Serializable{

    private static final long serialVersionUID = 1L;

    private UUID categoryId;
    private UUID categoryMetadataFieldId;

}
