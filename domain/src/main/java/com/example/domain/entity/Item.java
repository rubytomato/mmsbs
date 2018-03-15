package com.example.domain.entity;

import com.example.domain.constant.StandardType;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"itemStocks"})
@EqualsAndHashCode(exclude = {"itemStocks"})
public class Item implements Serializable {

    private static final long serialVersionUID = -3153084093423004609L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="price", nullable = false)
    private Integer price;
    @Column(name="sales_from", nullable = false)
    private LocalDateTime salesFrom;
    @Column(name="sales_to", nullable = false)
    private LocalDateTime salesTo;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="standard_type", nullable = false)
    private StandardType standardType;
    @JoinColumn(name = "category_id", nullable = false)
    @ManyToOne
    private Category category;
    @Column(name="del_flag", nullable = false)
    private Boolean delFlag;
    @Column(name="create_at", nullable = false)
    private LocalDateTime createAt;
    @Column(name="update_at", nullable = false)
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<ItemStock> itemStocks;
}
