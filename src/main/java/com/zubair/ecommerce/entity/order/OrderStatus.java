package com.zubair.ecommerce.entity.order;

import com.zubair.ecommerce.enums.OrderStatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderStatusId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderProductId")
    private OrderProduct orderProduct;

    @Enumerated(EnumType.STRING)
    private OrderStatusType fromStatus;

    @Enumerated(EnumType.STRING)
    private OrderStatusType toStatus;

    private String transitionNotesComment;

    private LocalDate transitionDate;
}
