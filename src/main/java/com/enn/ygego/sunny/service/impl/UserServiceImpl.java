package com.enn.ygego.sunny.service.impl;

import com.enn.ygego.sunny.dao.BaseDao;
import com.enn.ygego.sunny.dao.master.UserDao;
import com.enn.ygego.sunny.pojo.User;
import com.enn.ygego.sunny.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
* Title: UserServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User>  implements UserService {
	@Autowired
	private UserDao userDao;
	
	@Override
	protected BaseDao<User> getMapper() {
		return this.userDao;
	}
	
}
