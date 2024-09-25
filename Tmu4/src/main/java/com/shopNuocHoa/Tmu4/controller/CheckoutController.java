package com.shopNuocHoa.Tmu4.controller;

import com.shopNuocHoa.Tmu4.models.checkout.CheckOut;
import com.shopNuocHoa.Tmu4.repository.CheckoutRepository;
import com.shopNuocHoa.Tmu4.repository.UserRepository;
import com.shopNuocHoa.Tmu4.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkouts")
public class CheckoutController {
    @Autowired
    EmailService emailService;
    @Autowired
    CheckoutRepository repository;
    @Autowired
    UserRepository userRepository;
    @GetMapping("/{id}")
    public String checkout(){
        return "delivery";
    }
    @PostMapping("/purchase")
    @ResponseBody
    public void purchase(@RequestBody CheckOut checkoutDto){
        // luu don hang vao db
        CheckOut checkOut = new CheckOut();
        checkOut.setEmail(checkoutDto.getEmail());
        checkOut.setCity(checkoutDto.getCity());
        checkOut.setPerfume(checkoutDto.getPerfume());
        checkOut.setFirstName(checkoutDto.getFirstName());
        checkOut.setLastName(checkoutDto.getLastName());
        checkOut.setTotal(checkoutDto.getTotal());
        checkOut.setQuantity(checkoutDto.getQuantity());
        checkOut.setAddress(checkoutDto.getAddress());
        checkOut.setPhoneNumber(checkoutDto.getPhoneNumber());
        checkOut.setUser(userRepository.findByEmail(checkoutDto.getEmail()));
        repository.save(checkOut);
        // gui mail cho nhan vien
        emailService.sendMail();
    }
}
