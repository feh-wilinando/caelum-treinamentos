package br.com.caelum.treinamentos.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by nando on 07/06/17.
 */
@RestController
public class HomeController {

    @GetMapping("/who-are-you")
    public String home(HttpServletRequest request){
        return request.getLocalAddr() + "\n";
    }

    @GetMapping("/")
    public String busy() throws InterruptedException {

        Thread.sleep(10000);
        return "Work is finished";
    }
}
