package com.weavus.bank.dao;

import com.weavus.bank.dto.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountInfoDao extends JpaRepository<UserInfo, String> {
    UserInfo findByIdAndPassword(String id, String password);

}
