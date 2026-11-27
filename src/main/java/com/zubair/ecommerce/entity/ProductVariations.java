package com.zubair.ecommerce.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "product_variations")
@Getter
@Setter
@NoArgsConstructor
public class ProductVariations {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity_available")
    private Long quantityAvailable;

    @Column(precision = 12, scale = 2)
    private BigDecimal price;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, String> metadata = new HashMap<>();
    /*
    metadata.put("RAM", "8GB");
    metadata.put("Storage", "128GB");
    metadata.put("Color", "Blue");
    {
    "Color": "Black",
    "Size": "Large",
    "Material": "Cotton"
}
    */

    @Column(name = "primary_image_name")
    private String primaryImageName;

    @Column(name = "is_active")
    private Boolean isActive = true;
}
