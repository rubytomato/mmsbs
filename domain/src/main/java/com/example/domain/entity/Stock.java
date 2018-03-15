package com.example.domain.entity;

import com.example.domain.converter.JsonNotesConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="stock")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Stock implements Serializable {

    private static final long serialVersionUID = 3766264071895115867L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="stocks", nullable = false)
    private Integer stocks;
    @Convert(converter = JsonNotesConverter.class)
    @Column(name="notes")
    private Notes notes;
    @Column(name="create_at", nullable = false)
    private LocalDateTime createAt;
    @Column(name="update_at", nullable = false)
    private LocalDateTime updateAt;
    @Column(name="del_flag", nullable = false)
    private Boolean delFlag;

    @PrePersist
    private void prePersist() {
        createAt = LocalDateTime.now();
        updateAt = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        updateAt = LocalDateTime.now();
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Notes {
        private String color;
        private String shape;
        private String status;
    }

}
