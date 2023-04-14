package ru.kushh.domain.domestic;

import ru.kushh.domain.domestic.DomesticAnimal;

public class Dog extends DomesticAnimal {
    public Dog(String name) {
        super(name);
        addCommand("Сидеть");
        addCommand("Оставать");
    }
}
