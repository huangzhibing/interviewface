package com.qc.mapper;

import com.qc.pojo.Userlogin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserloginMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Userlogin record);

    int insertSelective(Userlogin record);

    Userlogin selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(Userlogin record);

    int updateByPrimaryKey(Userlogin record);

    Userlogin selectByUserlogin(Userlogin userlogin);
}