package com.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="item_stock")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemStock implements Serializable {

    private static final long serialVersionUID = -7012583289204822258L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="stock", nullable = false)
    private Integer stock;
    @JoinColumn(name = "item_id", nullable = false)
    @ManyToOne
    private Item item;
    @Column(name="del_flag", nullable = false)
    private Boolean delFlag;
    @Column(name="create_at", nullable = false)
    private LocalDateTime createAt;
    @Column(name="update_at", nullable = false)
    private LocalDateTime updateAt;

}
