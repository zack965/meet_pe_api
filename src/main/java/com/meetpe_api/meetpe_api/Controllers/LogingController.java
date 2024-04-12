package com.meetpe_api.meetpe_api.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
public class LogingController {
    Logger logger = LoggerFactory.getLogger(LogingController.class);
    @GetMapping("/tests/hello")
    @Operation(security = { @SecurityRequirement(name = "API-KEY") })
    public String hello()
    {
        logger.error("FATAL ERROR");
        return "Hello Daily Code Buffer!!";
    }
}
