package com.byt.freeEdu.service;

import com.byt.freeEdu.mapper.RemarkMapper;
import com.byt.freeEdu.model.DTO.RemarkDto;
import com.byt.freeEdu.model.Remark;
import com.byt.freeEdu.model.users.Student;
import com.byt.freeEdu.model.users.Teacher;
import com.byt.freeEdu.repository.RemarkRepository;
import com.byt.freeEdu.repository.StudentRepository;
import com.byt.freeEdu.repository.TeacherRepository;
import com.byt.freeEdu.service.users.StudentService;
import com.byt.freeEdu.service.users.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RemarkService {

    private final RemarkRepository remarkRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final RemarkMapper remarkMapper;
    private final StudentService studentService;
    private final TeacherService teacherService;

    public RemarkService(RemarkRepository remarkRepository, StudentRepository studentRepository, TeacherRepository teacherRepository, RemarkMapper remarkMapper, StudentService studentService, TeacherService teacherService) {
        this.remarkRepository = remarkRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.remarkMapper = remarkMapper;
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    public List<Remark> getAllRemarks() {
        return new ArrayList<>(remarkRepository
                .findAll());
    }

    public RemarkDto getRemarkById(int remarkId) {
        Optional<Remark> remark = remarkRepository.findById(remarkId);
        RemarkDto dto = remarkMapper.toDto(remark.get());
        return dto;
    }

    public List<RemarkDto> getTeacherRemarksById(int id) {
        List<Remark> remarks = remarkRepository.getRemarkByTeacher(teacherRepository.findById(id).get());

        return remarks.stream()
                .map(remarkMapper::toDto)
                .collect(Collectors.toList());
    }

    public Remark getRemarkByContent(String name) {
        return remarkRepository.findByContent(name);
    }

    public List<Remark> getRemarksByStudentId(int studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        return remarkRepository.findByStudent(student);
    }

    @Transactional
    public Remark addRemark(String content, int studentId, int teacherId) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be empty");
        }

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + studentId));
        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new EntityNotFoundException("Teacher not found with ID: " + teacherId));

        LocalDate date = LocalDate.now();
        Remark remark = new Remark(content, date, student, teacher);
        return remarkRepository.save(remark);
    }

    @Transactional
    public Remark updateRemark(int id, RemarkDto updatedRemark) {
        Remark existingRemark = remarkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Remark not found with ID: " + id));

        if (isNotEmpty(updatedRemark.getContent())) {
            existingRemark.setContent(updatedRemark.getContent());
        }

        return remarkRepository.save(existingRemark);
    }

    @Transactional
    public void deleteRemark(int id) {
        Remark remark = remarkRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Remark not found with ID: " + id));
        remarkRepository.delete(remark);
    }

    private boolean isNotEmpty(String content) {
        return content != null && !content.trim().isEmpty();
    }
}
