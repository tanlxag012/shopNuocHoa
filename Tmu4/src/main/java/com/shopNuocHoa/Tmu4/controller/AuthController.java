package com.shopNuocHoa.Tmu4.controller;

import com.shopNuocHoa.Tmu4.dto.PerfumeDto;
import com.shopNuocHoa.Tmu4.models.checkout.CheckOut;
import com.shopNuocHoa.Tmu4.models.perfume.Perfume;
import com.shopNuocHoa.Tmu4.models.perfume.PerfumeType;
import com.shopNuocHoa.Tmu4.payload.LoginRequest;
import com.shopNuocHoa.Tmu4.payload.SignUpInfo;
import com.shopNuocHoa.Tmu4.repository.CheckoutRepository;
import com.shopNuocHoa.Tmu4.repository.PerfumeRepository;
import com.shopNuocHoa.Tmu4.repository.PerfumeTypeRepository;
import com.shopNuocHoa.Tmu4.security.service.UserDetailsImpl;
import com.shopNuocHoa.Tmu4.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    PerfumeRepository perfumeRepository;
    @Autowired
    PerfumeTypeRepository typeRepository;
    @Autowired
    CheckoutRepository checkoutRepository;
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }
    @GetMapping("/signIn")
    public String index(){
        return "index";
    }
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @PostMapping("/signup")
    public String register(SignUpInfo info, Model model){
        return authService.registerUser(info,model);
    }
    @PostMapping("/signin")
    public String signin(LoginRequest loginRequest, HttpServletResponse response, Model model) throws IOException {
        return authService.authenticateUser(loginRequest,response,model);
    }
    @PostMapping("/addPerfumes")
    @ResponseBody
    public void addPerfumes(@RequestBody List<PerfumeDto> perfumeList){
        perfumeList.forEach(el -> {
            PerfumeType perfumeType = typeRepository.findByName(el.getPerfumeType());
            Perfume perfume = new Perfume();
            perfume.setName(el.getName());
            perfume.setDescription(el.getDescription());
            perfume.setPrice(el.getPrice());
            perfume.setQuantity(el.getQuantity());
            perfume.setPerfumeType(perfumeType);
            perfumeRepository.save(perfume);
        });
    }
    @GetMapping("/perfumes/{id}")
    @ResponseBody
    public Optional<Perfume> getAllPerfume(@PathVariable Long id){
        return perfumeRepository.findById(id);
    }

    @GetMapping("/donHang")
    public String donHang(){
        return "donHang";
    }
    @GetMapping("/getAllInvoice")
    @ResponseBody
    public List<CheckOut> getAllInvoice(){
        return checkoutRepository.findAll();
    }
    @GetMapping("/suggest/{name}")
    @ResponseBody
    public List<Perfume> searchProducts(@PathVariable String name){
        return perfumeRepository.searchProductsIgnoreCase(name);
    }
    @GetMapping("/getEmail")
    @ResponseBody
    public String getCurrentUserEmail() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            String email = ((UserDetailsImpl) principal).getEmail();
            return email;
        } else {
            return null;
        }
    }
    @GetMapping("/getUsername")
    @ResponseBody
    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) principal).getUsername();
        } else {
            return null;
        }
    }
    @GetMapping("/example")
    @ResponseBody
    public ResponseEntity<String> getExample() {
        return ResponseEntity.noContent().build(); // Trả về 204 No Content
    }
    @GetMapping("/perfumeType")
    @ResponseBody
    public List<PerfumeType> perfumeTypeList(){
        return typeRepository.findAll();
    }

    @GetMapping("/gio-hang")
    public String gioHang(){
        return "checkoutList";
    }
}
