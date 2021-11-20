package com.stsoft.socksaccounting.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stsoft.socksaccounting.entity.SocksType;
import com.stsoft.socksaccounting.repository.SocksTypeRepository;

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
}
