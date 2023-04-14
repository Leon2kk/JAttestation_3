package ru.kushh.domain;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private final String name;
    private final List<String> commands = new ArrayList<>();

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    protected void addCommand(String command) {
        commands.add(command);
    }

    public void listCommands() {
        System.out.println("Команды для " + getName() + ":");
        for (String command : commands) {
            System.out.println("- " + command);
        }
    }

    public void teachCommand(String command) {
        addCommand(command);
        System.out.println("Команда '" + command + "' добавлена " + getName() + "'в список команд");
    }
}
