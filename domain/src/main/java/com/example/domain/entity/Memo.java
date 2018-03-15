package com.example.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="memo")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Memo implements Serializable {

    private static final long serialVersionUID = -7888970423872473471L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "memo_id_seq", sequenceName = "memo_id_seq", allocationSize = 1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "memo_id_seq")
    private Long id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="description", nullable = false)
    private String description;
    @Column(name="done", nullable = false)
    private Boolean done;
    @Column(name="updated", nullable = false)
    private LocalDateTime updated;

    public static Memo of(String title, String description) {
        return Memo.builder()
                .title(title)
                .description(description)
                .done(false)
                .updated(LocalDateTime.now())
                .build();
    }

    @PrePersist
    private void prePersist() {
        done = false;
        updated = LocalDateTime.now();
    }

    @PreUpdate
    private void preUpdate() {
        updated = LocalDateTime.now();
    }

}
