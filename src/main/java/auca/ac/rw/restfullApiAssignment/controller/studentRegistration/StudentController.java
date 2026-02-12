package auca.ac.rw.restfullApiAssignment.controller.studentRegistration;

import auca.ac.rw.restfullApiAssignment.modal.studentRegistration.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();
    private Long nextId = 1L;

    public StudentController() {
        students.add(new Student(nextId++, "John", "Doe", "john.doe@email.com", "Computer Science", 3.8));
        students.add(new Student(nextId++, "Jane", "Smith", "jane.smith@email.com", "Computer Science", 3.9));
        students.add(new Student(nextId++, "Mike", "Johnson", "mike.j@email.com", "Business", 3.2));
        students.add(new Student(nextId++, "Sarah", "Williams", "sarah.w@email.com", "Engineering", 3.6));
        students.add(new Student(nextId++, "David", "Brown", "david.b@email.com", "Computer Science", 3.5));
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Optional<Student> student = students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();
        
        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/major/{major}")
    public ResponseEntity<List<Student>> getStudentsByMajor(@PathVariable String major) {
        List<Student> result = students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .toList();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Student>> filterStudentsByGpa(@RequestParam Double gpa) {
        List<Student> result = students.stream()
                .filter(s -> s.getGpa() >= gpa)
                .toList();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Student> registerStudent(@RequestBody Student student) {
        student.setStudentId(nextId++);
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        Optional<Student> existingStudent = students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst();
        
        if (existingStudent.isPresent()) {
            Student student = existingStudent.get();
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setEmail(updatedStudent.getEmail());
            student.setMajor(updatedStudent.getMajor());
            student.setGpa(updatedStudent.getGpa());
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

