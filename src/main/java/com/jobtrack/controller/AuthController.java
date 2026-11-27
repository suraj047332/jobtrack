package com.jobtrack.controller;
import com.jobtrack.service.UserService; import jakarta.validation.constraints.*; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.validation.BindingResult; import org.springframework.web.bind.annotation.*; import jakarta.validation.Valid;
@Controller public class AuthController {
 private final UserService service; public AuthController(UserService service){this.service=service;}
 public static class RegisterForm {@NotBlank @Size(max=80) public String fullName; @NotBlank @Email public String email; @NotBlank @Size(min=8,max=72) public String password; @NotBlank public String confirmPassword;}
 @GetMapping("/register") public String register(Model m){m.addAttribute("form",new RegisterForm());return "register";}
 @PostMapping("/register") public String registerPost(@Valid @ModelAttribute("form") RegisterForm f,BindingResult result,Model m){if(!f.password.equals(f.confirmPassword))result.rejectValue("confirmPassword","match","Passwords do not match.");if(result.hasErrors())return "register";try{service.register(f.fullName,f.email,f.password);return "redirect:/login?registered";}catch(IllegalArgumentException e){m.addAttribute("error",e.getMessage());return "register";}}
}
