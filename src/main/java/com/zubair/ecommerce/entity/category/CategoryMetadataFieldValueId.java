package com.zubair.ecommerce.entity.category;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

/* Composite Primary Key */

@Embeddable
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
