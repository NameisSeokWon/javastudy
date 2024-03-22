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
@Table(name = "useraccountinfo")
@DynamicInsert
public class UserAccountInfo {

    @Id
    private String account;
    private String id;
    private String branch_name;
    private int branch_code;
    private float amount;

}
