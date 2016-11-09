package sprms.repository;

import org.springframework.data.repository.CrudRepository;

import sprms.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
}
