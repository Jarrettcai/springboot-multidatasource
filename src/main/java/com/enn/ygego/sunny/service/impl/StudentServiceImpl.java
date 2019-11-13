package com.enn.ygego.sunny.service.impl;

import com.enn.ygego.sunny.dao.BaseDao;
import com.enn.ygego.sunny.dao.cluster.StudentDao;
import com.enn.ygego.sunny.pojo.Student;
import com.enn.ygego.sunny.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 
* Title: StudentServiceImpl
* Description: 
* 用户操作实现类 
* Version:1.0.0  
* @author pancm
* @date 2018年3月19日
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student>  implements StudentService {
	@Autowired
	private StudentDao studentDao;
	
	@Override
	protected BaseDao<Student> getMapper() {
		return this.studentDao;
	}
	
}
