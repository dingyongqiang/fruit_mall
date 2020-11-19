package com.fruit.dao;

import java.util.List;

import com.fruit.model.Address;

public interface AddressMapper {
    int deleteByPrimaryKey(Integer addrId);

    int insert(Address record);

    int insertSelective(Address record);

    Address selectByPrimaryKey(Integer addrId);

    int updateByPrimaryKeySelective(Address record);

    int updateByPrimaryKey(Address record);

	List<Address> findAddrByUserId(Integer userId);

}