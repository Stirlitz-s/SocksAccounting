package com.stsoft.socksaccounting.jsonclasses;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/*
 * класс SocksRequestJSON описывает тело запроса
 * для регистрации операций прихода/отпуска 
 * носок со склада
 */
public class SocksRequestJSON {
    @NotNull(message = "color can't be null")
    public String color;// — цвет носков, строка (например, black, red, yellow);

    @Min(1)
    @Max(100)
    public int cottonPart; // — процентное содержание хлопка в составе носков, целое число от 0 до 100
                           // (например, 30, 18, 42);
    @Min(0)
    public long quantity;// — количество пар носков, целое число больше 0.
}
