package com.fruit.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fruit.dao.UsersMapper;
import com.fruit.model.Admins;
import com.fruit.model.Goods;
import com.fruit.model.Users;
import com.fruit.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UsersMapper userMapper;

	@Override
	public Users login(String name, String pass) {
		List<Users> list = userMapper.findUserByName(name);
		for (Users a : list) {
			if(a.getUserPass().equals(pass)){
				return a;
			}
		}
		return null;
	}

	@Override
	public Integer updateUserInfo(Users user) {
		int i=userMapper.updateByPrimaryKeySelective(user);
		return i;
	}

	@Override
	public Users findUserById(Integer id) {
		Users  user=userMapper.selectByPrimaryKey(id);
		return null;
	}

	@Override
	public PageInfo<Users> findAllUsersBySplitPage(Integer page, Integer limit, String keyword) {
		List<Users> list =new ArrayList<Users>();
		PageHelper.startPage(page, limit);
		if(keyword!=null &&!keyword.trim().equals("")){
			//dd
			list=userMapper.findUserLikeName(keyword);
		}else{
			list = userMapper.findAll();
		}
		PageInfo<Users> info=new PageInfo<Users>(list);
		return info;
	}

	@Override
	public Integer changeUserState(Integer userId,Integer userState) {
		int i=userMapper.changeGoodsState( userId,userState);
		return i;
	}

	@Override
	public Integer deleteUser(Integer userId) {
		int i=userMapper.deleteByPrimaryKey(userId);
		return i;
	}

	@Override
	public Integer addUser(Users user) {
		int i=userMapper.insert(user);
		return i;
	}
	
	
}
