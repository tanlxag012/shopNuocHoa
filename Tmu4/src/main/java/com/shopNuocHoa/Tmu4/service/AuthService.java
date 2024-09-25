package com.shopNuocHoa.Tmu4.service;

import com.shopNuocHoa.Tmu4.models.user.ERole;
import com.shopNuocHoa.Tmu4.models.user.Role;
import com.shopNuocHoa.Tmu4.models.user.User;
import com.shopNuocHoa.Tmu4.payload.LoginRequest;
import com.shopNuocHoa.Tmu4.payload.SignUpInfo;
import com.shopNuocHoa.Tmu4.repository.RoleRepository;
import com.shopNuocHoa.Tmu4.repository.UserRepository;
import com.shopNuocHoa.Tmu4.security.jwt.JwtUtils;
import com.shopNuocHoa.Tmu4.security.service.UserDetailsImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;


    public String registerUser(SignUpInfo info, Model model){

        if(userRepository.existsByEmail(info.getEmail())){
            model.addAttribute("message","Email đã được sử dụng");
            return "signup";
        }

        User user = new User(
                info.getUsername(),
                passwordEncoder.encode(info.getPassword()),
                info.getEmail());
        Role user_role = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Role is not found"));
        Set<Role> roles = new HashSet<>();
        roles.add(user_role);
        user.setRoles(roles);
        userRepository.save(user);
        model.addAttribute("message","Đăng kí thành công");
        return "signup";
    }

    public String authenticateUser(LoginRequest loginRequest, HttpServletResponse response, Model model) throws IOException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            Cookie cookie = jwtUtils.generateJwtCookie(userDetails);
            response.addCookie(cookie);
            response.sendRedirect("/api/auth/home");
            return "home";
        } catch (AuthenticationException exception) {
            model.addAttribute("message", "Thong tin login khong chinh xac");
            return "index";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
