package com.zubair.ecommerce.entity.category;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
/* Ram: 4gb, 6gb,  Storage: 256GB, 512GB etc...*/
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class CategoryMetaDataFieldValues {

    @EmbeddedId /* Use the two fields inside CategoryMetadataFieldValueId as the primary key of this entity. */
    private CategoryMetadataFieldValueId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryMetadataFieldId") /* Take the primary key of the categoryMetaDataField object and use it as the categoryMetadataFieldId part of my composite primary key. */
    @JoinColumn(name = "category_metadata_field_id")
    private CategoryMetaDataField categoryMetaDataField;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("categoryId")
    @JoinColumn(name = "category_id")
    private Category category;

    private String value;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastUpdated;
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private String createdBy;
    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

}
