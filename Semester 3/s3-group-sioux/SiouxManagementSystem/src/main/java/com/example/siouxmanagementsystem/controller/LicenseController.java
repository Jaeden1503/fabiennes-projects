package com.example.siouxmanagementsystem.controller;

import com.example.siouxmanagementsystem.business.UseCases.LicensePlateUseCase;
import com.example.siouxmanagementsystem.domain.LicensePlate;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/license")
@AllArgsConstructor
@CrossOrigin(origins = {"*"})
public class LicenseController {
    private final LicensePlateUseCase licensePlateUseCase;

    //Receives a license from the camera/python script
    @PostMapping(path = "/detection", consumes = {"application/json"})
    public String postLicense (@RequestBody @Valid LicensePlate request) {
        System.out.println(request.getLicensePlate()); //eg. K188HV

        String response = licensePlateUseCase.compareLicense(request.getLicensePlate());

        return "license plate received: " + response;
    }
}
