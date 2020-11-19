package com.fruit.service;

import com.fruit.model.Admins;

public interface IAdminService {
	Admins login(String name,String pass);
	Integer updateAdmin(Admins admin);
	Admins findAdminById(Integer id);
}
