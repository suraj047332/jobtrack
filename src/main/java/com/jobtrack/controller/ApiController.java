package com.jobtrack.controller;
import com.jobtrack.service.JobApplicationService;import org.springframework.security.core.Authentication;import org.springframework.web.bind.annotation.*;import java.util.Map;
@RestController @RequestMapping("/api") public class ApiController {private final JobApplicationService service;public ApiController(JobApplicationService s){service=s;}@GetMapping("/dashboard/stats") public Map<String,Long> stats(Authentication a){return service.stats(a.getName());}}
