package com.stsoft.socksaccounting.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.DecimalMin;

/*
 * класс SocksType описывает сущность
 * каждого типа носок хранящихся наскладе 
 */

@Entity(name = "socks_type")
@Table(name = "tb_socks")
public class SocksType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "All socks mast have a color")
    private String color;// — цвет носков, строка (например, black, red, yellow);

    @NotBlank(message = "cottonPart can't be null")
    private int cottonPart; // — процентное содержание хлопка в составе носков, целое число от 0 до 100
                            // (например, 30, 18, 42);

    @NotBlank(message = "quantity can't be null")
    @DecimalMin(value = "0")
    private long quantity;// — количество пар носков, целое число больше 0.

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(int cottonPart) {
        this.cottonPart = cottonPart;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
