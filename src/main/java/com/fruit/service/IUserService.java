package com.fruit.service;

import com.fruit.model.Users;
import com.github.pagehelper.PageInfo;


public interface IUserService {
	
	Users login(String userName,String userPass);
	
	Integer updateUserInfo(Users user);
	Users findUserById(Integer id);
	PageInfo<Users> findAllUsersBySplitPage(Integer page,Integer limit,String keyword);
	Integer changeUserState(Integer userId,Integer userState);
	Integer deleteUser(Integer userId);
	Integer addUser(Users user);
}
