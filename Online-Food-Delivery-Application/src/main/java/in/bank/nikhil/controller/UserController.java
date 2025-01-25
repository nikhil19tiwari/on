package in.bank.nikhil.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import in.bank.nikhil.model.User;
import in.bank.nikhil.model.AddCart;
import in.bank.nikhil.model.FoodItem;
import in.bank.nikhil.service.UserService;

import in.bank.nikhil.service.FoodItemService;
import in.bank.nikhil.serviceImpl.EmailService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/v1/api/user")
public class UserController {

	
    // Autowire the UserService
    @Autowired
    private UserService service;

    // Autowire the FoodItemService
    @Autowired
    private FoodItemService foodItemService;

    // Autowire the EmailService
    @Autowired
    private EmailService emailService;

    /* Home page */
    @GetMapping("/home")
    public String getHomePage() {
        return "home";
    }

    /* Signup page */
    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }
    
    /* Verify OTP page */
    @GetMapping("/verify")
    public String getVerifyPage() {
        return "verify";
    }
    

    /* Signup user */
    @PostMapping("/signupUser")
    public String signupData(@ModelAttribute User user) {
        // Save user data into the database
        service.createUser(user);

        // Send email
        emailService.sendEmail(user.getEmail(),
                "Account Opening Request Successful!",
                "Hello " + user.getUserName() + ", Welcome to my Online Food Delivery Application."
                        + " Today " + LocalDate.now() + ", enjoy a 15% discount on all food items!");

        return "UserInterface";
    }

    /* Login page */
    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    /* Contact page */
    @GetMapping("/contact")
    public String getContactPage() {
        return "contact";
    }
    
    /* Serve image */
    @GetMapping("/image/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable Long id) {
        FoodItem foodItem = foodItemService.getFoodItemById(id);
        return foodItem.getImage(); // Return image bytes
    }

    /* Add Food Item page */
    @GetMapping("/addFood")
    public String showAddFoodPage() {
        return "addFood"; // Thymeleaf template for adding food
    }

    /* Save Food Item with image as byte array */
    @PostMapping("/add-food")
    public String saveFoodItem(@RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("price") double price,
                               @RequestParam("image") MultipartFile imageFile) throws IOException {

        // Convert image to byte array
        byte[] imageBytes = imageFile.getBytes();

        // Create and save food item
        FoodItem foodItem = new FoodItem();
        foodItem.setName(name);
        foodItem.setDescription(description);
        foodItem.setPrice(price);
        foodItem.setImage(imageBytes); // Set the image byte array

        foodItemService.saveFoodItem(foodItem);

        return "redirect:/v1/api/user/foodItems"; // Redirect to the food item list
    }

    /* List Food Items */
    @GetMapping("/foodItems")
    public String listFoodItems(Model model) {
        List<FoodItem> foodItems = foodItemService.getAllFoodItems();
        model.addAttribute("foodItems", foodItems);
        return "foodList"; // Thymeleaf template for food items list
    }
    
    @GetMapping("/getAll")
    public String findAll(Model model) {
        List<FoodItem> foodItems = foodItemService.getAllFoodItems();
        model.addAttribute("foodItems", foodItems);
        return "itemInterface"; // Ensure this matches the filename in /templates/
    }

    
    @GetMapping("/updateFood")
    public String updateFood() {
        return "updateForm";
    }
    
    /* Save Food Item with image as byte array */
    @PostMapping("/update")
    public String updateFoodItem(
    	                    	@RequestParam("id") Long id,
    		                   @RequestParam("name") String name,
                               @RequestParam("description") String description,
                               @RequestParam("price") double price,
                               @RequestParam("image") MultipartFile imageFile) throws IOException {
   
    	
    	FoodItem item = foodItemService.getFoodItemById(id);

        // Convert image to byte array
        byte[] imageBytes = imageFile.getBytes();

        // Create and save food item
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setImage(imageBytes); // Set the image byte array

        foodItemService.saveFoodItem(item);

        return "redirect:/v1/api/user/foodItems"; // Redirect to the food item list
    }

    @GetMapping("/delete")
    public String deleteFood() {
        return "delete";
    }
    
    @PostMapping("/deleteFood")
    public String deleteFoodItem(
    	                    	@RequestParam("id") Long id
    	                    	) throws IOException {
   
    	foodItemService.deleteFoodItem(id);
        return "redirect:/v1/api/user/foodItems"; // Redirect to the food item list
    }


    @GetMapping("/adminDashboard")
    public String getadminDashboardPage() {
        return "adminDashboard";
    }
    
    @GetMapping("/adminLogin")
    public String getadminLoginPage() {
        return "adminLogin";
    }
    
  /////////////////////////////////////////////////////////////////////////////////////

 // Add to Cart Endpoint (no userId and foodItemId)
    
    
    @PostMapping("/add-to-cart")
    public ResponseEntity<?> addToCart(@RequestBody AddCart cartItem) {
        // Logic to add the cart item (you can store the cart items in a session or database)
       	System.out.println("hiii");
        List<AddCart> cartItems = new ArrayList<>();
        cartItems.add(cartItem); // Add the new item to the cart

        // Here you can add your actual cart handling logic
       	System.out.println("hiii");
        // Return a success message with the cart item details
        return ResponseEntity.ok().body("success");
    }

    // Buy Now Endpoint
    @PostMapping("/buy-now")
    public ResponseEntity<?> buyNow(@RequestBody AddCart cartItem) {
        // Simulate the buying process (add to cart, process payment, etc.)
    	System.out.println("hiii");
        List<AddCart> cartItems = new ArrayList<>();
        cartItems.add(cartItem); // Simulate adding the item to the cart or processing the purchase

        // Here you can implement your order processing logic (like sending confirmation emails, etc.)
       	System.out.println("hello");
        return ResponseEntity.ok().body(("Purchase successful!"));
    }


    @GetMapping("/it")
    public String gginPage() {
        return "items";
    }
    

    @GetMapping("/car")
    public String showCartPage() {
        return "cart"; // This will look for cart.html in the templates folder
    
}
    

    @GetMapping("/payment")
    public String pay() {
        return "payment"; // This will look for cart.html in the templates folder
    
}
    
    
    @GetMapping("/recipi")
    public String recipi() {
        return "recipi"; // This will look for cart.html in the templates folder
    
}
    }

  
