import java.util.*;

public class exercise {
    public static void main(String[] args) {
        HashMap<Student, String> hm = new HashMap<Student, String>();
        hm.put(new Student("Jonas", 20), "beijing");
        hm.put(new Student("bob", 21), "shanghai");
        hm.put(new Student("tom", 22), "wuhan");
// keyset iterate
        Set<Student> students = hm.keySet();
        for (Student student : students) {
            System.out.println(student.getName() + " " + student.getAge() + " " + hm.get(student));
        }
// foreach iterate
        hm.forEach((k, v) -> System.out.println(k.getName() + " " + k.getAge() + " " + v));

        // lambda
        Set<Map.Entry<Student, String>> entries = hm.entrySet();
        for (Map.Entry<Student, String> entry : entries) {
            System.out.println(entry.getKey().getName() + " " + entry.getKey().getAge() + " " + entry.getValue());
        }


    }
}
