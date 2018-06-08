package com.shrmus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shrmus.mapper.UserMapper;
import com.shrmus.pojo.User;
import com.shrmus.pojo.UserExample;
import com.shrmus.service.UserService;

/**
 * 
 * <p>Title:UserServiceImpl</p>
 * <p>Description:</p>
 * @author Shr
 * @date 2018年6月9日上午12:33:26
 * @version
 */
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;

	/**
	 * 登录
	 */
	public User loginUser(User user) {
		UserExample userExample = new UserExample();
		com.shrmus.pojo.UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUserUsernameEqualTo(user.getUserUsername());
		criteria.andUserPasswordEqualTo(user.getUserPassword());
		List<User> userList = userMapper.selectByExample(userExample);
		if(0 == userList.size()) {
			return null;
		}else {
			user = userList.get(0);
			return user;
		}
	}
	
	/**
	 * 添加用户
	 */
	@Transactional
	public void addUser(User user) {
		// 密码加密
		// 添加用户
		userMapper.insert(user);
	}
	
	/**
	 * 注册用户
	 */
	@Transactional
	public void regUser(User user) {
		userMapper.insert(user);
	}

	/**
	 * 根据用户名获取用户信息
	 */
	@Override
	public User getUserByUsername(String username) {
		UserExample userExample = new UserExample();
		com.shrmus.pojo.UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUserUsernameEqualTo(username);
		List<User> userList = userMapper.selectByExample(userExample);
		User user = userList.get(0);
		return user;
	}
	
	/**
	 * 根据用户id获取用户信息
	 */
	@Override
	public User getUserById(Integer userId) {
		UserExample userExample = new UserExample();
		com.shrmus.pojo.UserExample.Criteria criteria = userExample.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<User> userList = userMapper.selectByExample(userExample);
		User user = userList.get(0);
		return user;
	}

	/**
	 * 修改用户的信息
	 */
	@Transactional
	public void updateUser(User user) {
		// 修改用户的信息
		userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 删除用户
	 */
	@Transactional
	public void deleteUser(Integer userId) {
		// 删除用户
		userMapper.deleteByPrimaryKey(userId);
	}

	/**
	 * 查找所有用户
	 */
	@Override
	public List<User> getUserList() {
		UserExample userExample = new UserExample();
		List<User> userList = userMapper.selectByExample(userExample);
		return userList;
	}
}
