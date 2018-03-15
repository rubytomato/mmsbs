package com.example.application.vo;

import com.example.domain.entity.Memo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemoView implements Serializable {
    private static final long serialVersionUID = -6945394718471482993L;

    private String title;
    private String description;
    @JsonProperty("completed")
    private Boolean done;

    public static MemoView from(final Memo memo) {
        return MemoView.builder()
                .title(memo.getTitle())
                .description(memo.getDescription())
                .done(memo.getDone())
                .build();
    }
}
