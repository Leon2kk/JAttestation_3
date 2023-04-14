package ru.kushh.domain.domestic;

import ru.kushh.domain.domestic.DomesticAnimal;

public class Hamster extends DomesticAnimal {
    public Hamster(String name) {
        super(name);
        addCommand("Бежать");
        addCommand("Стоять");
    }
}