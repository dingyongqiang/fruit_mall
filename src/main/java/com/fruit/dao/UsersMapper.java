package com.fruit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fruit.model.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

	List<Users> findAll();

	int changeGoodsState(@Param("userId")Integer userId,@Param("userState")Integer userState);

	List<Users> findUserLikeName(String keyword);

	List<Users> findUserByName(String name);
}