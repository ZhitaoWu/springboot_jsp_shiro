package com.wzt.springboot_jsp_shiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @User:Tao
 * @date:2021/1/19
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role implements Serializable {
    private Integer id;
    private String name;

    //定义权限的集合
    private List<Perms> perms;
}
