package jpabook.jpashop.entity;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member") // order table에 있는 member에 의해 맵핑 된거야
    private List<Order> orders;
}
