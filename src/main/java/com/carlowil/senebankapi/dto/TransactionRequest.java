package com.carlowil.senebankapi.dto;

import lombok.Data;

@Data
public class TransactionRequest {
    private Integer userId;
    private Integer accountFromId;
    private Integer accountToId;
    private Integer payload;
}
