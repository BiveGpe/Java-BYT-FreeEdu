package com.byt.freeEdu.service.users;

import com.byt.freeEdu.model.users.Student;
import com.byt.freeEdu.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(int id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
        existingStudent.setFirstname(updatedStudent.getFirstname());
        existingStudent.setLastname(updatedStudent.getLastname());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setPassword(updatedStudent.getPassword());
        existingStudent.setParent(updatedStudent.getParent());
        existingStudent.setSchoolClass(updatedStudent.getSchoolClass());
        return studentRepository.save(existingStudent);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
