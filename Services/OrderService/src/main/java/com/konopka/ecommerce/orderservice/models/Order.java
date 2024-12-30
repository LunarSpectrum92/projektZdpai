package com.konopka.ecommerce.orderservice.models;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    private BigDecimal totalAmount;

    @CreationTimestamp
    private LocalDateTime orderDate;

    private String clientId;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProductsList;

    @Enumerated(EnumType.STRING)
    private PaymentMethods paymentMethods;


    @Enumerated(EnumType.STRING)
    private Status status;



}
