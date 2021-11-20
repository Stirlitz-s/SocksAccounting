package com.stsoft.socksaccounting.jsonclasses;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/*
 * класс SocksRequestJSON описывает тело запроса
 * для регистрации операций прихода/отпуска 
 * носок со склада
 */
public class SocksRequestJSON {
    public String color;// — цвет носков, строка (например, black, red, yellow);
    @NotBlank(message = "quantity can't be null")

    @Min(1)
    @Max(100)
    public int cottonPart; // — процентное содержание хлопка в составе носков, целое число от 0 до 100
                           // (например, 30, 18, 42);
    @NotBlank(message = "quantity can't be null")
    @Min(0)
    public long quantity;// — количество пар носков, целое число больше 0.
}
