package com.fruit.dao;

import java.util.List;

import com.fruit.model.Admins;

public interface AdminsMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admins record);

    int insertSelective(Admins record);

    Admins selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admins record);

    int updateByPrimaryKey(Admins record);

	List<Admins> findAdminByName(String name);
}