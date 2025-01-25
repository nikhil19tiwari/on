package in.bank.nikhil.service;

import java.util.List;

import in.bank.nikhil.model.FoodItem;
import in.bank.nikhil.model.User;

public interface UserService {
	public void createUser(User user);


	public User findByUserName(String UserName);
	public User findByEmail(String email);
	public List<User> findAllUser();
	public User findByUserCredentials(String email,String phone,String userName);

	public void updateUser(User user);

	public void deleteUser(String email,String password);

	public boolean checkUserExist(String userName);
	public boolean checkUserEmailExist(String email);
	public boolean checkLoginExist(String email,String password);
	public boolean existsByPhone(String phone);
	 public User findById(Long userId);
	
	
	
}
