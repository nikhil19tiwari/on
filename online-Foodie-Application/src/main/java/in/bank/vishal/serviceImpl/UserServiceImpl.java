package in.bank.vishal.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bank.vishal.model.FoodItem;
import in.bank.vishal.model.User;
import in.bank.vishal.repo.UserRepo;
import in.bank.vishal.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	    // Autowired the UserRepo
		@Autowired
		private UserRepo userRepo;

		@Override
		public void createUser(User user) {
			 userRepo.save(user);
		}

		@Override
		public User findByUserName(String UserName) {
			return  userRepo.findByUserName(UserName);
		}

		@Override
		public User findByEmail(String email) {
			 
			return userRepo.findByEmail(email);
		}

		@Override
		public List<User> findAllUser() {

			return userRepo.findAll();
		}

		@Override
		public void updateUser(User user) {

	    	userRepo.save(user);
			
		}

		@Override
		public void deleteUser(String email, String password) {
			  boolean flag = checkUserEmailExist(email);
			  
			  User user = userRepo.findByEmail(email);
			  if(flag) {
				  userRepo.delete(user);
			  }
		}

		@Override
		public boolean checkUserExist(String userName) {
			 
			return userRepo.existsByUserName(userName);
		}

		@Override
		public boolean checkUserEmailExist(String email) {
		 
			return userRepo.existsByEmail(email);
		}

		@Override
		public User findByUserCredentials(String email, String phone, String userName) {
			
			return userRepo.findByEmailAndPhoneAndUserName(email, phone, userName);
		}

		@Override
		public boolean existsByPhone(String phone) {


			return userRepo.existsByPhone(phone);
		}

		@Override
		public boolean checkLoginExist(String email, String password) {

			return userRepo.existsByEmailAndPassword(email, password);
		}

		@Override
		public User findById(Long id) {
			
		    return userRepo.findById(id)
		                   .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
		}

		@Override
		public User findByMobileNumber(String modeileNumber) {


			return userRepo.findByPhone(modeileNumber);
		}



}