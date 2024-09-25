package com.shopNuocHoa.Tmu4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {
    @GetMapping("/product/{id}")
    public String product1(@PathVariable String id){
        return "perfume" + id;
    }
}
