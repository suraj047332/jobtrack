package com.jobtrack.controller;
import com.jobtrack.service.*; import org.springframework.security.core.Authentication; import org.springframework.stereotype.Controller; import org.springframework.ui.Model; import org.springframework.web.bind.annotation.GetMapping;
@Controller public class DashboardController {private final JobApplicationService apps;private final UserService users;public DashboardController(JobApplicationService a,UserService u){apps=a;users=u;}
 @GetMapping("/dashboard") public String dashboard(Authentication auth,Model m){String email=auth.getName();m.addAttribute("user",users.byEmail(email));m.addAttribute("stats",apps.stats(email));m.addAttribute("recent",apps.recent(email));return "dashboard";}}
