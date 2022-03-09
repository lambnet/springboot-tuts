package com.example.demo.dto;

import javax.validation.constraints.NotEmpty;

public record ProductDto(String productName, long price, long stock){}
