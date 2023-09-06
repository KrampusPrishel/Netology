import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();
        System.out.println(count);

        List<String> conscripts = persons.stream()
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 27)
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println(conscripts);

        List<String> workable = persons.stream()
                .filter(person -> person.getEducation().equals(Education.HIGHER))
                .filter(person -> person.getAge() >= 18)
                .filter(person -> person.getAge() < 65)
                .filter(person -> {
                    return person.getAge() < 60 || person.getSex().equals(Sex.MAN);
                })
                .sorted(Comparator.comparing(person -> person.getFamily()))
                .map(person -> person.getFamily())
                .collect(Collectors.toList());
        System.out.println(workable);
    }
}