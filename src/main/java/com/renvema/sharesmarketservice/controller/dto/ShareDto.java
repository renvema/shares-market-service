package com.renvema.sharesmarketservice.controller.dto;

import com.renvema.sharesmarketservice.dao.entity.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShareDto {

    private String username;

    private String ticker;

    private Operation operation;

    private Integer amount;
}
