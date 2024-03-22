package com.weavus.bank.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "transinfo")
public class TransInfo {

    @Id
    // insert시 1씩 증가하게 하는 어노테이션
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int no;
    private String userId;
    private String date;
    private String d_station;
    private String a_station;
    private int fare;
    private String reg_date;
    private String status;
    //リファクタリング


}
