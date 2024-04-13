package com.meetpe_api.meetpe_api.Controllers;

import com.meetpe_api.meetpe_api.DTO.Requests.Response.CreateResponseDto;
import com.meetpe_api.meetpe_api.DTO.Responses.Global.GlobalCreatedResponse;
import com.meetpe_api.meetpe_api.Entities.Response;
import com.meetpe_api.meetpe_api.Entities.User;
import com.meetpe_api.meetpe_api.Helpers.HttpResponseHelper;
import com.meetpe_api.meetpe_api.Repositories.ResponseRepository;
import com.meetpe_api.meetpe_api.Services.ResponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
public class ResponseController {
    private ResponseService responseService;

    public ResponseController(ResponseService responseService) {
        this.responseService = responseService;
    }

    @PostMapping("app/responses/create_response")
    @Operation(security = { @SecurityRequirement(name = "bearer-key"),@SecurityRequirement(name = "API-KEY") })
    public ResponseEntity<HashMap<String, String>> CreateResponse(@RequestBody CreateResponseDto createResponseDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        User currentUser = (User) authentication.getPrincipal();
        this.responseService.saveResponse(createResponseDto,currentUser);
        return ResponseEntity.ok(HttpResponseHelper.getGlobalCreatedResponse("message","response created successfully"));
    }
}
