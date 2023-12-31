package web.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import web.dao.UserDoa;
import web.model.User;

//@Component
public class InitDatabase {

	private UserDoa userDoa;
	
	@Autowired
	public InitDatabase(UserDoa daoImpl) {
		this.userDoa = daoImpl;
	}
	
	public void init() {
		List<User> users = new ArrayList<User>();
		users.add(new User("Dmitriy", LocalDate.of(1999, 07, 03)));
		users.add(new User("Anton", LocalDate.of(1963, 8, 03)));
		users.add(new User("Sylvester", LocalDate.of(1946, 03, 21)));
		users.add(new User("Egor", LocalDate.of(1999, 01, 7)));
		users.add(new User("Roma", LocalDate.of(2001, 12, 31)));
		
		for(User u : users) {
			userDoa.create(u);
		}
	}
	
}
