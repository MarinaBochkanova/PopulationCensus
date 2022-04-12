package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");

        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 100_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        long result =
                persons.stream()
                        .filter(person -> person.getAge() <= 18)
                        .count();
        System.out.println("Количество несовершеннолетних: " + result);

        List<String> conscripts =
                persons.stream()
                        .filter(person -> person.getSex().equals(Sex.MAN))
                        .filter(person -> person.getAge() > 18)
                        .filter(person -> person.getAge() < 27)
                        .map(person -> person.getFamily())
                        .collect(Collectors.toList());
        System.out.println("Список призывников: ");
        int number = 0;
        for (String conscript : conscripts) {
            number += 1;
            System.out.println(number + ". " + conscript);
        }

        List<Person> workingPopulation =
                persons.stream()
                        .filter(person -> person.getAge() > 18)
                        .filter(person -> person.getSex().equals(Sex.MAN) ? person.getAge() < 65 : person.getAge() < 60)
                        .filter(person -> person.getEducation().equals(Education.HIGHER))
                        .sorted(Comparator.comparing(person -> person.getFamily()))
                        .collect(Collectors.toList());

        System.out.println("Список потенциально работоспособных людей с высшим образованием: ");
        int i = 0;
        for (Person working : workingPopulation) {
            i += 1;
            System.out.println(i + ". " + working);
        }

    }
}
