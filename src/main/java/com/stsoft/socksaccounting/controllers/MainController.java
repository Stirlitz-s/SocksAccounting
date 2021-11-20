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

import com.stsoft.socksaccounting.entity.SocksType;
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
        SocksType currentSocks = socksTypeService.getCottonPartEqual(input.color, input.cottonPart);
        if (currentSocks == null) {
            SocksType newSocks = new SocksType();
            newSocks.setColor(input.color);
            newSocks.setCottonPart(input.cottonPart);
            newSocks.setQuantity(input.quantity);
            socksTypeService.save(newSocks);
        } else {
            currentSocks.setQuantity(currentSocks.getQuantity() + input.quantity);
            socksTypeService.save(currentSocks);
        }

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/api/socks/outcome", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public HttpStatus outCome(@Valid @RequestBody SocksRequestJSON input) {
        SocksType currentSocks = socksTypeService.getCottonPartEqual(input.color, input.cottonPart);
        if (currentSocks == null)
            return HttpStatus.BAD_REQUEST;
        if (currentSocks.getQuantity() < input.quantity)
            return HttpStatus.BAD_REQUEST;

        SocksType newSocks = new SocksType();
        newSocks.setColor(input.color);
        newSocks.setCottonPart(input.cottonPart);
        newSocks.setQuantity(input.quantity);
        socksTypeService.save(newSocks);
        currentSocks.setQuantity(currentSocks.getQuantity() - input.quantity);
        socksTypeService.save(currentSocks);

        return HttpStatus.OK;
    }

    @RequestMapping(value = "/api/socks", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getSocks(@RequestParam(required = true) @NotNull String color,
            @RequestParam(required = true) @NotNull @RequestOperationsConstraint String operation,
            @RequestParam(required = true) @Min(0) @Max(100) int cottonPart) {
        switch (operation.toLowerCase()) {
        case "morethan":
            return socksTypeService.getCountCottonPartMoreThan(color, cottonPart) + "";
        case "lessthan":
            return socksTypeService.getCountCottonPartLessThan(color, cottonPart) + "";
        case "equal":
            return socksTypeService.getCountCottonPartEqual(color, cottonPart) + "";
        }
        return "0";
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        return new ResponseEntity<>("" + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
