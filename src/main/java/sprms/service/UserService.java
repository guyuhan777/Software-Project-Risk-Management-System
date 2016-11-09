package sprms.service;

import org.springframework.stereotype.Service;

import sprms.model.User;

@Service
public interface UserService {
	User addUser(User user);
	Iterable<User> getAllUser();
}
