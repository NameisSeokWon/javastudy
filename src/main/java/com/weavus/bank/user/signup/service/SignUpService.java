package com.weavus.bank.user.signup.service;

import com.weavus.bank.dao.UserInfoDao;
import com.weavus.bank.dto.UserInfo;
import com.weavus.bank.user.signup.entity.SignUpEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class SignUpService {
    private final UserInfoDao userInfoDao;

    public boolean signUp(SignUpEntity entity) {

        UserInfo userInfo = new UserInfo();
        userInfo.setId(entity.getId());
        userInfo.setPassword(entity.getPassword());
        userInfo.setName(entity.getName());
        userInfo.setGender(entity.getGender());

        UserInfo result = userInfoDao.save(userInfo);

        return result != null;
    }
    public boolean checkId(String id) {
        UserInfo check = userInfoDao.findById(id).orElse(null);
        return check != null;
    }
}
