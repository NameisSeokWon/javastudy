package com.weavus.bank.user.login.service;

import com.weavus.bank.dao.UserInfoDao;
import com.weavus.bank.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {
    // Lombok을 통한 간결한 의존성 주입 방식
    // @RequiredArgsconstructor과 함께 사용
    private final UserInfoDao userInfoDao;

    public UserInfo login(String id, String password) {

        UserInfo userInfo = userInfoDao.findByIdAndPassword(id, password);

       /* if (userInfo == null) {
            return true;
        } else {
            return false;
        }*/
        return userInfo;
    }
    public UserInfo updateUserInfo(String id, String password, String name, String gender) {
        UserInfo userInfo = userInfoDao.findByIdAndPassword(id, password);
        if (userInfo != null) {

            userInfo.setName(name);
            userInfo.setPassword(password);
            userInfo.setGender(gender);
            return userInfoDao.save(userInfo);
        }
        return null;
    }
}
