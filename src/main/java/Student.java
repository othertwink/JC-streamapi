import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Student {
    private String name;
    private Map<String, Integer> grades;

    public Student(String name, Map<String, Integer> grades) {
        this.name = name;
        this.grades = grades;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }
}

class ParallelStreamCollectMapAdvancedExample {
    public static void main(String[] args) {
        List<Student> students = Arrays.asList(
                new Student("Student1", Map.of("Math", 90, "Physics", 85)),
                new Student("Student2", Map.of("Math", 95, "Physics", 88)),
                new Student("Student3", Map.of("Math", 88, "Chemistry", 92)),
                new Student("Student4", Map.of("Physics", 78, "Chemistry", 85))
        );

        students.parallelStream().flatMap(student -> student.getGrades().entrySet().stream()) // получаем мап студента и превращаем в стрим
                .collect(Collectors.groupingBy((Map.Entry::getKey),Collectors.averagingInt(Map.Entry::getValue))) // собираем в мапу Предмет:Средняя оценка
                .forEach((subj,avg) -> System.out.println(subj + " : " + avg));
    }
}

