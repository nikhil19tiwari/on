package in.bank.nikhil.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.bank.nikhil.model.User;

public interface UserRepo extends JpaRepository<User, Long> {
    public User findByUserName(String UserName);
	
	public User findByEmailAndPhoneAndUserName(String email,String phone,String userName);
	
	public User findByEmail(String email);
	
	public List<User> findAll();

	public boolean existsByUserName(String UserName);
	public boolean existsByEmail(String email);
	public boolean existsByPhone(String phone);
	public boolean existsByEmailAndPassword(String email,String password);
	
	
}
