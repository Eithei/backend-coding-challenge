package com.engagetech.controllers.health;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.pojo.ApiVisibility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(name = "Health Controller", description = "Verify the availability of the service.", visibility = ApiVisibility.PUBLIC)
@RestController
@RequestMapping(value = "/app/health")
public class HealthController {
    private static final String I_AM_OK = "The web service for Engage Tech is up and running! :)";

    @RequestMapping(value = "", method = RequestMethod.GET)
    @ApiMethod(description = "Returns a confirmation string of the service running.")
    public ResponseEntity<String> getHealthStatus() {
        return new ResponseEntity<>(I_AM_OK, HttpStatus.OK);
    }
}
