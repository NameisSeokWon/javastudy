package com.weavus.bank.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;

@Entity
@Getter
@Setter
@ToString
@Table(name = "userinfo")
@DynamicInsert
public class UserInfo {

    @Id
    private String id;
    private String name;
    private String password;
    private String gender;
    private String reg_date;
    private String status;

}
