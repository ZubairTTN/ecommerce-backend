package com.zubair.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/* RAM: 4GB, 6GB || Storage: 256GB, 512GB, 1TB */

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(
        columnNames = {
                "category_id",
                "category_metadata_id",
                "value"
        }
))
@Getter
@Setter
@NoArgsConstructor
public class CategoryMetadataFieldValues {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_metadata_id", nullable = false)
    private CategoryMetadataField metadataField;
}
