package ru.kushh.domain.domestic;

import ru.kushh.domain.domestic.DomesticAnimal;

public class Cat extends DomesticAnimal {
    public Cat(String name) {
        super(name);
        addCommand("Пригать");
        addCommand("Мурлычать");
    }
}
