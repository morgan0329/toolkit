package com.xiaosq.service;

import com.xiaosq.entity.User;
import com.xiaosq.mapper.mysql.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("qiYunUserService")
public class UserService implements UserDetailsService {

	@Autowired
	IUserDao userDao;
	
	@Override
	public User loadUserByUsername(String name)
			throws UsernameNotFoundException {
		
		User user = userDao.findByUserKey(name);
		if (user == null) {
			throw new UsernameNotFoundException("用户不存在！");
		}
		
		return user;
	}

}
