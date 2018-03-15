package com.example.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="pseudo_delay_view")
public class PseudoDelay implements Serializable {

    private static final long serialVersionUID = -7295335149208136304L;

    @Id
    private String id;
    @Column(name="sleep")
    private Integer sleep;
    @Column(name="create_at")
    private LocalDateTime createAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSleep() {
        return sleep;
    }

    public void setSleep(Integer sleep) {
        this.sleep = sleep;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "PseudoDelay{" +
                "id='" + id + '\'' +
                ", sleep=" + sleep +
                ", createAt=" + createAt +
                '}';
    }
}
