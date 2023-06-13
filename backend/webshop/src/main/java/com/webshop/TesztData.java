package com.webshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesztData {
    
    @GetMapping("/data")
    public DataObject getData() {
        DataObject data = new DataObject();
        data.setName("JackieX");
        data.setIsAdmin(true);
        data.setEmail("teszt@gmail.com");

        return data;
    }
    
    
}

