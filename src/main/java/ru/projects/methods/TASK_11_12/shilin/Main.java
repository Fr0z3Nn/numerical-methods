package ru.projects.methods.TASK_11_12.shilin;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Student oleg = new Student("Олег",
                new HashSet<>(Arrays.asList(
                        new Lection("Матанализ", LocalDate.of(2020, 2, 1)),
                        new Lection("Философия", LocalDate.of(2020, 2, 2)),
                        new Lection("Физкультура", LocalDate.of(2020, 2, 5)))));
        Student andrey = new Student("Андрей",
                new HashSet<>(Arrays.asList(
                        new Lection("Философия", LocalDate.of(2020, 2, 2)),
                        new Lection("Физкультура", LocalDate.of(2020, 2, 5)))));
        Student kirill = new Student("Кирилл",
                new HashSet<>(Arrays.asList(new Lection("Английкий язык", LocalDate.of(2020, 2, 3)))));
        Student sergey = new Student("Сергей",
                new HashSet<>(Arrays.asList(
                        new Lection("История", LocalDate.of(2020, 2, 4)),
                        new Lection("Физкультура", LocalDate.of(2020, 2, 5)))));
        Student alexey = new Student("Алексей",
                new HashSet<>(Arrays.asList(
                        new Lection("Физкультура", LocalDate.of(2020, 2, 5)),
                        new Lection("Английкий язык", LocalDate.of(2020, 2, 3)))));
        Student anton = new Student("Антон",
                new HashSet<>(Arrays.asList(
                        new Lection("Английкий язык", LocalDate.of(2020, 2, 3)),
                        new Lection("Матанализ", LocalDate.of(2020, 2, 1)))));
        Student nikolay = new Student("Николай",
                new HashSet<>(Arrays.asList(
                        new Lection("Матанализ", LocalDate.of(2020, 2, 1)),
                        new Lection("Физкультура", LocalDate.of(2020, 2, 5)))));
        Student anna = new Student("Аня",
                new HashSet<>(Arrays.asList(
                        new Lection("Английкий язык", LocalDate.of(2020, 2, 3)),
                        new Lection("История", LocalDate.of(2020, 2, 4)))));
        Student vera = new Student("Вера",
                new HashSet<>(Arrays.asList(
                        new Lection("Философия", LocalDate.of(2020, 2, 2)),
                        new Lection("Матан", LocalDate.of(2020, 2, 2)),
                        new Lection("Физкультура", LocalDate.of(2020, 2, 5)))));
        Student alex = new Student("Кирилл",
                new HashSet<>(Arrays.asList(new Lection("Философия", LocalDate.of(2020, 2, 2)))));
        List<Student> students = new ArrayList<>(Arrays.asList(oleg, andrey, kirill, sergey, alexey, anton, nikolay, anna, vera, alex));
        //2
        /*students.forEach(n -> System.out.printf("%s посетил %d лекций\n", n.getName(), n.getCourses().size()));*/
        /*Map<String, Integer> map = new HashMap<>();
        students.forEach(n -> n.getCourses().forEach( course -> {
            map.merge(course.getName(), 1, Integer::sum);
        }));
        map.forEach((key, value) -> System.out.printf("%s - %d\n",key,value));*/
        /*students
                .stream()
                .flatMap(student -> student.getCourses().stream())
                .collect(Collectors.groupingBy(Lection::getName, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(System.out::println);*/
        /*Map<String,String> map = new HashMap<>();
        students.forEach(k -> {
          k.getCourses().forEach(n ->{
              map.merge(n.getName(),String.format("%s ",k.getName()),String::concat);
                  }
          );
        });
        map.forEach((k,v) -> System.out.printf("%s --- %s\n",k,v) );*/
        /*Map<Set<Lection>, List<Student>> map = students.stream().collect(Collectors.groupingBy(Student::getCourses));
        map.forEach((key, value) -> System.out.printf("%s - %s\n", key, value));*/
        //Map<String, Map.Entry<LocalDate, Long>> map = new HashMap<>();
        students
                .stream()
                .collect(Collectors.toMap
                        (Student::getName,
                                v -> v.getCourses()
                                        .stream()
                                        .collect
                                                (Collectors
                                                        .groupingBy
                                                                (Lection::getDate, Collectors.counting()))
                                        .entrySet()
                                        .stream()
                                        .max(Map.Entry.comparingByValue())
                                        .get(),
                                (l1, l2) -> {
                                    l1.setValue(l2.getValue());
                                    return l1;
                                }))
                .entrySet()
                .stream()
                .max(Comparator.comparingLong(o -> o.getValue().getValue()))
                .ifPresent(System.out::println);

    }

}
