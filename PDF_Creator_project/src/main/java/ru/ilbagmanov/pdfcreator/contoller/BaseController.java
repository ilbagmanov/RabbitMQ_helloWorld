package ru.ilbagmanov.pdfcreator.contoller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseController {

    @GetMapping(value = "/hello")
    public String getRequest() {
        return "OK";
    }
}
