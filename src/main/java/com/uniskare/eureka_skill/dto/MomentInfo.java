package com.uniskare.eureka_skill.dto;

import com.uniskare.eureka_skill.entity.Moment;
import com.uniskare.eureka_skill.entity.MomentPic;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;


@Data
@AllArgsConstructor
public class MomentInfo {
    Moment moment;
    List<MomentPic> momentPic;
}
