package com.wzt.springboot_jsp_shiro.service.impl;

import com.wzt.springboot_jsp_shiro.dao.UserDao;
import com.wzt.springboot_jsp_shiro.entity.Perms;
import com.wzt.springboot_jsp_shiro.entity.User;
import com.wzt.springboot_jsp_shiro.service.UserService;
import com.wzt.springboot_jsp_shiro.utils.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @User:Tao
 * @date:2021/1/7
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;



    @Override
    public void register(User user) {
        // 处理业务调用dao
        // 明文密码进行md5 + salt + hash散列
        String salt = SaltUtil.getSalt(8); // 8位的随机盐
        user.setSalt(salt);// 将随机生成的盐值入用户数据中
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1024);
        user.setPassword(md5Hash.toHex());
        userDao.save(user);// 将用户数据传到持久层，保存在数据中
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findRolesByUserName(String username) {
        return userDao.findRolesByUserName(username);
    }

    @Override
    public List<Perms> findPermsByRoleId(Integer id) {
        return userDao.findPermsByRoleId(id);
    }
}
