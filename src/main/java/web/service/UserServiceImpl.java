package web.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.dao.UserDoa;
import web.model.User;

@Service
public class UserServiceImpl implements UserService{
	
	private UserDoa userDao;

	@Autowired
	public UserServiceImpl(UserDoa userDao) {
		this.userDao = userDao;
	}

	@Transactional
	@Override
	public void create(User user) {
		userDao.create(user);
	}

	@Transactional
	@Override
	public void update(User user) {
		userDao.update(user);
		
	}
	
	@Transactional
	@Override
	public void delete(Integer id) {
		userDao.delete(id);
		
	}

	@Transactional(readOnly = true)
	@Override
	public User get(Integer id) {
		return userDao.get(id);
	}
	

	@Transactional(readOnly = true)
	@Override
	public List<User> getAllUser() {
		return userDao.getAllUser();
	}
	
}
