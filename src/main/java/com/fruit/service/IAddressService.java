package com.fruit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fruit.model.Address;
import com.fruit.model.Areas;
import com.fruit.model.Cities;
import com.fruit.model.Provinces;


public interface IAddressService {
	List<Address> findAddressByUserId(Integer userId);
	Address findAddresById(Integer id);
	Provinces findProByProByName(String name);
	Cities findCityByCityName(String name,String provinceId);
	Areas findAreaByAreaName(String name,String cityId);
	Integer addAddress(Address addr);
	Integer updateAddress(Address addr);
	Integer deleteAddress(Integer addrId);
}
