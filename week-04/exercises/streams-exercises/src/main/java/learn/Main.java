package learn;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        StudentDataStore ds = new StudentDataStore();
        List<Student> students = ds.all();

        // 0. Print all students
        // iteration solution
//        for (Student student : students) {
//            System.out.println(student);
//        }

        // stream solution
//        students.stream().forEach(System.out::println);

        // 1. Print students from Argentina

        Stream<Student> studentsFromArgentina = students.stream().filter(student -> student.getCountry().equalsIgnoreCase("Argentina"));

//        studentsFromArgentina.forEach(System.out::println);


        // 2. Print students whose last names starts with 'T'.

        Stream<Student> studentsStartT = students.stream().filter(student -> student.getFirstName().startsWith("T"));

//        studentsStartT.forEach(System.out::println);

        // 3. Print students from Argentina, ordered by GPA

        studentsFromArgentina.sorted(Comparator.comparing(Student::getGpa).reversed());
//                .forEach(System.out::println);



        // 4. Print the bottom 10% (100 students) ranked by GPA.
int result = (int) (students.size()*.1);
        Stream<Student> studentBottom10 = students.stream().sorted(Comparator.comparing(Student::getGpa))
                .limit(result);
//        studentBottom10.forEach(System.out::println);

        // 5. Print the 4th - 6th ranked students by GPA from Argentina

        Object[] student4And6 = students.stream().sorted(Comparator.comparing(Student::getGpa).reversed()).limit(6).toArray();
//        System.out.println(student4And6[3]);
//        System.out.println(student4And6[5]);


        // 6. Is anyone from Maldives?
        Stream<Student> studentsFromMaldives = students.stream().filter(student -> student.getCountry().equalsIgnoreCase("Maldives"));
//        studentsFromMaldives.forEach(System.out::println);


        // 7. Does everyone have a non-null, non-empty email address?
        List<Student>studentNonNull = students.stream().filter(student -> student.getEmailAddress().length() == 0).collect(Collectors.toList());


        // 8. Print students who are currently registered for 5 courses.
        Map<String, Long> student5Course = students.stream().collect(Collectors.groupingBy(Student::getMajor, Collectors.counting()));
        for (String course : student5Course.keySet()){
            System.out.println(course + ": " + student5Course.get(course));
        }


        // 9. Print students who are registered for the course "Literary Genres".

        // 10. Who has the latest birthday? Who is the youngest?

        // 11. Who has the highest GPA? There may be a tie.

        // 12. Print every course students are registered for, including repeats.

        // 13. Print a distinct list of courses students are registered for.

        // 14. Print a distinct list of courses students are registered for, ordered by name.

        // 15. Count students per country.

        // 16. Count students per country. Order by most to fewest students.

        // 17. Count registrations per course.

        // 18. How many registrations are not graded (GradeType == AUDIT)?

        // 19. Create a new type, StudentSummary with fields for Country, Major, and IQ.
        //     Map Students to StudentSummary, then sort and limit by IQ (your choice of low or high).

        // 20. What is the average GPA per country (remember, it's random fictional data).

        // 21. What is the maximum GPA per country?

        // 22. Print average IQ per Major ordered by IQ ascending.

        // 23. STRETCH GOAL!
        // Who has the highest pointPercent in "Sacred Writing"?
    }
}