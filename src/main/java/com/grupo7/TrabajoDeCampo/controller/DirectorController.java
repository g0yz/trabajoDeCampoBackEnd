package com.grupo7.TrabajoDeCampo.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*")
@RestController
@PreAuthorize("hasRole('Director')")
@RequestMapping("/director")
public class DirectorController {
}
