package com.example.kosproject.controller;

import com.example.kosproject.model.entity.Auth;
import com.example.kosproject.model.request.LoginRequest;
import com.example.kosproject.model.request.RegistrationRequest;
import com.example.kosproject.model.response.SuccessResponse;
import com.example.kosproject.service.AuthService;
import com.example.kosproject.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private EmailService emailService;


//    @Value("${url}")
//    private String url;


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationRequest registrationRequest) {
        String token = authService.register(registrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success registration", token));
    }

    @PostMapping("/admin/register")
    public ResponseEntity adminRegister(@RequestBody RegistrationRequest registrationRequest) {
        String token = authService.adminRegister(registrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success registration", token));
    }

    @GetMapping("/verification/{to}")
    public void verification(@PathVariable("to") String to){
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
        String subject = "KostKu - Account Activation";
        String message = "<h1>Aktivasi akun penghuni KostKu</h1>" +
                "<p>Silakan klik link berikut untuk aktivasi dan login akun penghuni KostKu</p>"+
                "<a href="+url+"/auth/verified/"+to+">Aktivasi</a>";
        emailService.sendMail(to,subject, message);
    }

    @GetMapping("/verified/{email}")
    public RedirectView verified(@PathVariable("email") String email) {
        Auth auth = authService.findByEmail(email);
        auth.setActive(true);
        authService.save(auth);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://www.google.com");
        return redirectView;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success login", token));
    }
}
