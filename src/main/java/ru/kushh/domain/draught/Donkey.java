package ru.kushh.domain.draught;

public class Donkey extends DraughtAnimal {
    public Donkey(String name) {
        super(name);
        addCommand("Нести");
        addCommand("Рычать");
    }
}
