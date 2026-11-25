package com.zubair.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/* RAM, Storage, Processor ... */

@Entity
@Table(name = "category_metadata_field")
@Getter
@Setter
@NoArgsConstructor
public class CategoryMetadataField {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true, length = 40)
    private String name;

    @OneToMany(mappedBy = "metadataField")
    private List<CategoryMetadataFieldValues> fieldValues = new ArrayList<>();
}
