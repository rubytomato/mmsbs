package com.example.domain.dto;

import com.example.domain.constant.StandardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto implements Serializable {

    private static final long serialVersionUID = 7803523681004958221L;

    // アイテムID
    private Long itemId;
    // アイテム名
    private String itemName;
    // 価格
    private Integer price;
    // 販売開始日
    private LocalDateTime salesFrom;
    // 販売終了日
    private LocalDateTime salesTo;
    // 規格
    private StandardType standardType;
    // 在庫数
    private Integer stock;

    // カテゴリ名
    private String categoryName;
}
