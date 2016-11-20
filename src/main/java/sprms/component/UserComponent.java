package sprms.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import sprms.model.User;
import sprms.repository.UserRepository;
import sprms.service.UserService;

@Component
public class UserComponent implements UserService{

	@Autowired
	UserRepository repository;
	
	@Override
	public User addUser(User user) {
		return repository.save(user);
	}

	@Override
	public Iterable<User> getAllUser() {
		return repository.findAll();
	}

	@Override
	public User getUser(Long id) {
		return repository.findOne(id);
	}

}
