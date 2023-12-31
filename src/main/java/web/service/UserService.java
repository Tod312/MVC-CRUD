package web.service;

import java.util.List;

import web.model.User;

public interface UserService {
	void create(User user);
	void update(User user);
	void delete(Integer id);
	User get(Integer id);
	List<User> getAllUser();
}
