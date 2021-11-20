package com.stsoft.socksaccounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.stsoft.socksaccounting.entity.SocksType;

@Repository
public interface SocksTypeRepository extends JpaRepository<SocksType, Integer> {

    public List<SocksType> findByColor(String color);

    public List<SocksType> findByColorAndCottonPart(String color, int cottonPart);

    @Query(value = "select sum(quantity) from socks_type where color = :ncolor and cotton_part > :npart")
    public Long getSumQuantityByColorAndCottonPartMoreThan(@Param("ncolor") String color,
            @Param("npart") int cottonPart);

    @Query(value = "select sum(quantity) from socks_type where color = :ncolor and cotton_part < :npart")
    public Long getSumQuantityByColorAndCottonPartLessThan(@Param("ncolor") String color,
            @Param("npart") int cottonPart);
}
