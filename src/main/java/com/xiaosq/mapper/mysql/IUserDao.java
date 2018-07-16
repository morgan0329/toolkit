package com.xiaosq.mapper.mysql;


import com.xiaosq.entity.User;

public interface IUserDao {
	
	User findByUserKey(String key);
	
}
