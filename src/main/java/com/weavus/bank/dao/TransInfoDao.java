package com.weavus.bank.dao;

import com.weavus.bank.dto.TransInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransInfoDao extends JpaRepository<TransInfo, Integer> {

}
