package in.bank.nikhil.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Fetch admin credentials from application.properties file
    @Value("${admin.username}")
    private String adminUsername;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.role}")
    private String adminRole;

    /**
     * Configures an in-memory user for authentication.
     * The username, password, and role are dynamically fetched from the properties file.
     * Passwords are encrypted using BCrypt for security.
     *
     * @return InMemoryUserDetailsManager with admin credentials.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Create admin user with encrypted password
        UserDetails admin = User.withUsername(adminUsername)
                                .password(passwordEncoder().encode(adminPassword)) // Encrypt password
                                .roles(adminRole) // Assign role
                                .build();

        return new InMemoryUserDetailsManager(admin); // Store user in memory
    }

    /**
     * Configures the password encoder to hash passwords using BCrypt.
     * BCrypt is a strong hashing function commonly used for passwords.
     *
     * @return PasswordEncoder implementation.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Encrypt passwords
    }

    /**
     * Configures security rules for the application, defining:
     * - URL access restrictions
     * - Login and logout behavior
     *
     * @param http HttpSecurity object for configuring security.
     * @return Configured SecurityFilterChain.
     * @throws Exception if there is an issue with the configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF for simplicity (useful for development/testing purposes)
            .csrf().disable()

            // Define URL access rules
            .authorizeRequests()
                .requestMatchers("/v1/api/user/adminDashboard/**").hasRole(adminRole) // Only admins can access admin dashboard
                .requestMatchers("/v1/api/user/adminLogin").permitAll() // Allow access to admin login page
                .requestMatchers("/v1/api/user/addFood").authenticated()
                .requestMatchers("/v1/api/user/getAll").authenticated()
                .requestMatchers("/v1/api/user/delete").authenticated()
                .requestMatchers("/v1/api/user/updateFood").authenticated()
                .anyRequest().permitAll() // Require authentication for all other URLs

            // Configure form-based login
            .and()
            .formLogin()
                .loginPage("/v1/api/user/adminLogin") // Custom login page URL
                .loginProcessingUrl("/v1/api/user/process-login") // URL to submit the login form
                .defaultSuccessUrl("/v1/api/user/adminDashboard", true) // Redirect here on successful login
                .failureUrl("/v1/api/user/adminLogin?error=true") // Redirect here on failed login
                .permitAll() // Allow everyone to access the login page

            // Configure logout behavior
            .and()
            .logout()
                .logoutUrl("/v1/api/user/logout") // URL for logging out
                .logoutSuccessUrl("/v1/api/user/login?logout=true") // Redirect to login after logout
                .invalidateHttpSession(true) // Invalidate session on logout
                .clearAuthentication(true) // Clear authentication details
                .permitAll(); // Allow everyone to access logout

        return http.build(); // Build the security configuration
    }
}
