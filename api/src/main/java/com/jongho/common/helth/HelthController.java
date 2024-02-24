package com.jongho.common.helth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helth")
public class HelthController {
    @GetMapping()
    public String check() {
        return "helth check success!";
    }
}
