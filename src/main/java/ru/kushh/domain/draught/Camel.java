package ru.kushh.domain.draught;

public class Camel extends DraughtAnimal {
    public Camel(String name) {
        super(name);
        addCommand("Нести");
        addCommand("Отдыхать");
    }
}
