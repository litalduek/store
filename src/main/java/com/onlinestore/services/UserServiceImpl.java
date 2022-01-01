package com.onlinestore.services;

import com.onlinestore.dto.Message;
import com.onlinestore.dto.Mapper;
import com.onlinestore.dto.user.LoginData;
import com.onlinestore.dto.user.SignupData;
import com.onlinestore.model.ERole;
import com.onlinestore.model.Role;
import com.onlinestore.model.User;
import com.onlinestore.security.jwt.JwtUtils;
import com.onlinestore.security.services.UserDetailsImpl;
import com.onlinestore.util.PaymentRepository;
import com.onlinestore.util.RoleRepository;
import com.onlinestore.util.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service("userService")
public class UserServiceImpl implements UserService , ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder encoder;
    private Mapper mapper = new Mapper();


    public void run(ApplicationArguments args) {
        if(!roleRepository.existsByName(ERole.ROLE_CUSTOMER)){
            roleRepository.save(new Role(ERole.ROLE_CUSTOMER));
        }
        if(!roleRepository.existsByName(ERole.ROLE_OWNER)){
            roleRepository.save(new Role(ERole.ROLE_OWNER));
        }
    }

    public Message login(LoginData loginData) {
        Message message;
        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getUserName(), loginData.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            message = new Message(mapper.toJwtDto(userDetails, jwt).toString(),true);
        }catch (RuntimeException e){
            message = new Message("Login failed.",false);
            logger.error("Login failed." + e.getMessage());
        }
        return message;
    }

    public Message signup(SignupData signUp) {
        try {
            if (userRepository.existsByUsername(signUp.getUserName())) {
                return new Message("Error: Username is already taken!", false);
            }
            if (userRepository.existsByEmail(signUp.getEmail())) {
                return new Message("Error: Email is already in use!", false);
            }
            User user = mapper.toUser(signUp);
            user.setPassword(encoder.encode(user.getPassword()));
            paymentRepository.save(user.getPayment());
            user.getRoles().forEach(role -> roleRepository.save(role));
            userRepository.save(user);

        }catch (EntityNotFoundException e){
            logger.error(e.getMessage());
            return new Message("Error: Role is not found.", false);
        }
        catch (RuntimeException e){
            return new Message("Error while creating a user" + e.getMessage(), false);
        }

        return new Message("User registered successfully!" , true);
    }

    @Override
    public User getUser(String userName) {
        User user;
        try{
             user = userRepository.findByUsername(userName).orElseThrow(() -> new EntityNotFoundException("Error: User is not found."));
        }catch (EntityNotFoundException e){
            logger.error(e.getMessage());
            return new User();
        }
        return user;
    }

}
