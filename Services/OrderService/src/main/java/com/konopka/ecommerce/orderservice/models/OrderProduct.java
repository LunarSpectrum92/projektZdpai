package com.konopka.ecommerce.orderservice.models;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderProductId;

    @ManyToOne
    @JoinColumn(
            name = "order_id"
    )
    private Order order;

    private Integer productId;

    private Integer quantity;

    private BigDecimal price;
}
