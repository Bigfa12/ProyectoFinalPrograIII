package com.gimnasio.demo.Controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('TRAINER')")

public class TrainerController {

}
