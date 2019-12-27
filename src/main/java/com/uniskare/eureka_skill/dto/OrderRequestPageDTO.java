package com.uniskare.eureka_skill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderRequestPageDTO {
    List<OrderRequestDTO> orders;
    int pageSize;
    int totalPage;
}
