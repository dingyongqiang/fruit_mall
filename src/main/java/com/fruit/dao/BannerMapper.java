package com.fruit.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fruit.model.Banner;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer bannerId);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer bannerId);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

	List<Banner> findAllBannerByLikeName(String keyword);

	List<Banner> findAllBanner();

	List<Banner> findAllShowBanner();

	Integer changeBannerState(@Param("bannerId")Integer bannerId,@Param("bannerState")Integer bannerState);
}