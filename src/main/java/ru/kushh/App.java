package ru.kushh;

import ru.kushh.domain.service.AnimalRegistry;
public class App 
{
    public static void main( String[] args )
    {
        AnimalRegistry registry = new AnimalRegistry();
        registry.displayMenu();
    }
}
