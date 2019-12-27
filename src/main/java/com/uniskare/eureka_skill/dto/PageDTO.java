package com.uniskare.eureka_skill.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class PageDTO {
    List<MomentShow> moment;
    int pageSize;
    int totalPage;
}
