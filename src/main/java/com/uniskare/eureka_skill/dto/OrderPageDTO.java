package com.uniskare.eureka_skill.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author : SCH001
 * @description :
 */
@Data
@AllArgsConstructor
public class OrderPageDTO {
    List<OrderDTO> orders;
    int pageSize;
    int totalPage;
}
