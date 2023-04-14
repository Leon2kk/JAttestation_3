package ru.kushh.domain.draught;

public class Horse extends DraughtAnimal {
    public Horse(String name) {
        super(name);
        addCommand("Ходить");
        addCommand("Медлить");
    }
}
