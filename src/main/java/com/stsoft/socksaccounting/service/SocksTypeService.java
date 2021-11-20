package com.stsoft.socksaccounting.service;

import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.stsoft.socksaccounting.entity.SocksType;
import com.stsoft.socksaccounting.jsonclasses.SocksRequestJSON;
import com.stsoft.socksaccounting.repository.SocksTypeRepository;
import com.stsoft.socksaccounting.validators.RequestOperationsConstraint;

@Service
public class SocksTypeService {
    @Autowired
    private SocksTypeRepository socksTypeRepository;

    public SocksType save(SocksType socksType) {
        return socksTypeRepository.save(socksType);
    }

    public Long getCountCottonPartLessThan(String color, int cottonPart) {
        return socksTypeRepository.getSumQuantityByColorAndCottonPartLessThan(color, cottonPart);
    }

    public Long getCountCottonPartMoreThan(String color, int cottonPart) {
        return socksTypeRepository.getSumQuantityByColorAndCottonPartMoreThan(color, cottonPart);
    }

    public Long getCountCottonPartEqual(String color, int cottonPart) {
        List<SocksType> res = socksTypeRepository.findByColorAndCottonPart(color, cottonPart);
        if (res == null || res.isEmpty())
            return 0L;
        return res.get(0).getQuantity();
    }

    public SocksType getCottonPartEqual(String color, int cottonPart) {
        List<SocksType> res = socksTypeRepository.findByColorAndCottonPart(color, cottonPart);
        if (res != null && !res.isEmpty())
            return socksTypeRepository.findByColorAndCottonPart(color, cottonPart).get(0);
        else
            return null;
    }
    
    public boolean addIncomingSocks(SocksRequestJSON input) {
        SocksType currentSocks = getCottonPartEqual(input.color, input.cottonPart);
        if (currentSocks == null) {
            SocksType newSocks = new SocksType();
            newSocks.setColor(input.color);
            newSocks.setCottonPart(input.cottonPart);
            newSocks.setQuantity(input.quantity);
            save(newSocks);
        } else {
            currentSocks.setQuantity(currentSocks.getQuantity() + input.quantity);
            save(currentSocks);
        }
        return true;
    }
    
    public boolean removeDispatchSocks(SocksRequestJSON input) {
        SocksType currentSocks = getCottonPartEqual(input.color, input.cottonPart);
        if (currentSocks == null)
            return false;
        if (currentSocks.getQuantity() < input.quantity)
            return false;

        currentSocks.setQuantity(currentSocks.getQuantity() - input.quantity);
        save(currentSocks);
        return true;
    }
    public String getSocks(String color, String operation, int cottonPart) {
        switch (operation.toLowerCase()) {
        case "morethan":
            return getCountCottonPartMoreThan(color, cottonPart) + "";
        case "lessthan":
            return getCountCottonPartLessThan(color, cottonPart) + "";
        case "equal":
            return getCountCottonPartEqual(color, cottonPart) + "";
        }
        return "0";
    }
}
