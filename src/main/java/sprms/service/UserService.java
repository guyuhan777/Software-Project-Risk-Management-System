package sprms.service;

import java.util.List;

import org.springframework.stereotype.Service;

import sprms.model.User;

@Service
public interface UserService {
	User addUser(User user);
	List<User> getAllUser();
}
