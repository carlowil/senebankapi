package com.carlowil.senebankapi.dto;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private Integer userId;
    private Integer balance;
    private Boolean isOverdraft;
}
