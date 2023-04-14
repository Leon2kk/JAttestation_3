package ru.kushh.domain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ru.kushh.domain.Animal;
import ru.kushh.domain.domestic.Cat;
import ru.kushh.domain.domestic.Dog;
import ru.kushh.domain.domestic.DomesticAnimal;
import ru.kushh.domain.domestic.Hamster;
import ru.kushh.domain.draught.Camel;
import ru.kushh.domain.draught.Donkey;
import ru.kushh.domain.draught.DraughtAnimal;
import ru.kushh.domain.draught.Horse;

public class AnimalRegistry {
    private final List<DomesticAnimal> domesticAnimals = new ArrayList<>();
    private final List<DraughtAnimal> draughtAnimals = new ArrayList<>();
    private final Counter counter = new Counter();

    public void addAnimal(Animal animal) {
        if (animal instanceof DomesticAnimal) {
            domesticAnimals.add((DomesticAnimal)animal);
        } else if (animal instanceof DraughtAnimal) {
            draughtAnimals.add((DraughtAnimal)animal);
        } else {
            throw new IllegalArgumentException("Неизвестный вид животного");
        }
        counter.add();
    }

    public void listCommands(Animal animal) {
        animal.listCommands();
    }

    public void teachCommand(Animal animal, String command) {
        animal.teachCommand(command);
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println("1. Добавить новое животное");
            System.out.println("2. Команды животных");
            System.out.println("3. Новая команда");
            System.out.println("4. Выйти");
            System.out.print("Выбрать: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    try (Counter c = counter.start()) {
                        addAnimal(createAnimal(scanner));
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;
                case 2:
                    listAnimalCommands(scanner);
                    break;
                case 3:
                    teachAnimalCommand(scanner);
                    break;
                case 4:                    
                    break;
                default:
                    System.out.println("Некорректный выбор");
            }
        } while (choice != 4);
    }

    private Animal createAnimal(Scanner scanner) {
        System.out.println("Для чего:  1 - для дома, 2 - для хозяиства): ");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите имя зверца: ");
        String name = scanner.nextLine().trim();
        if (type == 1) {
            return createDomesticAnimal(name);
        } else if (type == 2) {
            return createDraughtAnimal(name);
        } else {
            throw new IllegalArgumentException("Указано не верно");
        }
    }

    private DomesticAnimal createDomesticAnimal(String name) {
        System.out.println("Введите какое животное 1.Собака, 2.Кошка, 3.Хомяк): ");
        Scanner scanner = new Scanner(System.in);
        int species = scanner.nextInt();
        scanner.nextLine(); 
        return switch (species) {
            case 1 -> new Dog(name);
            case 2 -> new Cat(name);
            case 3 -> new Hamster(name);
            default -> throw new IllegalArgumentException("Не верный выбор");
        };
    }

    private DraughtAnimal createDraughtAnimal(String name) {
        System.out.println("Введите какое животное 1.Лошадь, 2.Верблюд, 3.Осел: ");
        Scanner scanner = new Scanner(System.in);
        int species = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
        return switch (species) {
            case 1 -> new Horse(name);
            case 2 -> new Camel(name);
            case 3 -> new Donkey(name);
            default -> throw new IllegalArgumentException("Не верный выбор");
        };
    }

    private void listAnimalCommands(Scanner scanner) {
        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine().trim();
        Animal animal = findAnimal(name);
        if (animal != null) {
            listCommands(animal);
        } else {
            System.out.println("Животное не найдено");
        }
    }

    private void teachAnimalCommand(Scanner scanner) {
        System.out.println("Введите имя животного: ");
        String name = scanner.nextLine().trim();
        Animal animal = findAnimal(name);
        if (animal != null) {
            System.out.println("Введите команду: ");
            String command = scanner.nextLine().trim();
            teachCommand(animal, command);
        } else {
            System.out.println("Животное не найдено");
        }
    }

    private Animal findAnimal(String name) {
        for (DomesticAnimal animal : domesticAnimals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        for (DraughtAnimal animal : draughtAnimals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                return animal;
            }
        }
        return null;
    }

    private static class Counter implements AutoCloseable {
        private int count = 0;

        public Counter start() {
            count++;
            return this;
        }

        public void add() {
            count++;
        }

        @Override
        public void close() throws Exception {
            if (count != 1) {
                throw new Exception("Недопустимое использование");
            }
        }
    }
}
