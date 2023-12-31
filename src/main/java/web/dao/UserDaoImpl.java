package web.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import web.model.User;

@Repository
public class UserDaoImpl implements UserDoa{
	
	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public void create(User user) {
		entityManager.persist(user);
	}

	@Override
	public void update(User user) {
		entityManager.merge(user);
	}

	@Override
	public void delete(Integer id) {
		entityManager.remove(entityManager.find(User.class, id));
	}

	@Override
	public User get(Integer id) {
		User user1 = entityManager.find(User.class, id);
		return user1;
	}

	@Override
	public List<User> getAllUser() {
		List<User> users = entityManager.createQuery("select u from User u", User.class).getResultList();
		return users;
	}

	public void addUsers() {
		String hql = "insert into User(name, birthday) values(:name, :birthday)";
		List<User> users = new ArrayList<User>();
		users.add(new User("Dmitriy", LocalDate.of(1999, 07, 03)));
		users.add(new User("Anton", LocalDate.of(1963, 8, 03)));
		users.add(new User("Sylvester", LocalDate.of(1946, 03, 21)));
		users.add(new User("Egor", LocalDate.of(1999, 01, 7)));
		users.add(new User("Roma", LocalDate.of(2001, 12, 31)));
		
		for(User u : users) {
			entityManager.createQuery(hql)
				.setParameter("name", u.getName())
				.setParameter("birthday", u.getBirthday())
				.executeUpdate();
		}
	}

}
