package jpabook.jpashop.entity;

import jakarta.persistence.*;
import jpabook.jpashop.entity.Delivery;
import jpabook.jpashop.entity.Member;
import jpabook.jpashop.entity.OrderItem;
import jpabook.jpashop.type.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderTime;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태
}
