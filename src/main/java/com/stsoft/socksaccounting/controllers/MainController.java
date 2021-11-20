package com.stsoft.socksaccounting.controllers;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.stsoft.socksaccounting.jsonclasses.SocksRequestJSON;
import com.stsoft.socksaccounting.service.SocksTypeService;
import com.stsoft.socksaccounting.validators.RequestOperationsConstraint;

@RestController
@Validated
public class MainController {
    @Autowired
    private SocksTypeService socksTypeService;

    @RequestMapping(value = "/api/socks/income", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpStatus inCome(@Valid @RequestBody SocksRequestJSON input) {
        if (!socksTypeService.addIncomingSocks(input)) 
            return HttpStatus.BAD_REQUEST;
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/api/socks/outcome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpStatus outCome(@Valid @RequestBody SocksRequestJSON input) {
        if (!socksTypeService.removeDispatchSocks(input))
            return HttpStatus.BAD_REQUEST;
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/api/socks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSocks(@RequestParam(required = true) @NotNull String color,
            @RequestParam(required = true) @NotNull @RequestOperationsConstraint String operation,
            @RequestParam(required = true) @Min(0) @Max(100) int cottonPart) {
        return socksTypeService.getSocks(color, operation, cottonPart);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("" + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
