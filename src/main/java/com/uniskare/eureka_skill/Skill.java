package com.uniskare.eureka_skill;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "skill")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int skillId;//有必要声明递增吗？
    private String userId;
    private String cover;
    private String video;
    private String displayPic;
    private String title;
    private String content;
    private double price;
    private String unit;
    private int model; // 0 represent 1->1   1 represent 1->all
    private String fullType;
    private String subtype;
    private String subSubtype;
    private double score;
    private Timestamp date;

}
