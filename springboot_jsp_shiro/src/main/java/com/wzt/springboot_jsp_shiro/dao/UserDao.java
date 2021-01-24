package com.wzt.springboot_jsp_shiro.dao;

import com.wzt.springboot_jsp_shiro.entity.Perms;
import com.wzt.springboot_jsp_shiro.entity.Role;
import com.wzt.springboot_jsp_shiro.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @User:Tao
 * @date:2021/1/7
 */
@Mapper
public interface UserDao {
    // 保存用户信息
    void save(User user);

    // 根据身份信息认证的方法
    User findByUserName(String username);

    // 根据用户名查询所有角色
    User findRolesByUserName(String username);

    // 根据角色id查询权限集合
    List<Perms> findPermsByRoleId(Integer id);
}
