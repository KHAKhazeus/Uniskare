package com.uniskare.eureka_user.dto;
// CHECKSTYLE:OFF
import com.uniskare.eureka_user.entity.User;
import com.uniskare.eureka_user.entity.UserPic;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CertificationDTO {
    User user;
    List<UserPic> userPics;
}
