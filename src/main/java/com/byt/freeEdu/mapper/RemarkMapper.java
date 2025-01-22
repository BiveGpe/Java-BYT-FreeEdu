package com.byt.freeEdu.mapper;

import com.byt.freeEdu.model.DTO.RemarkDto;
import com.byt.freeEdu.model.Remark;
import com.byt.freeEdu.service.users.StudentService;
import com.byt.freeEdu.service.users.TeacherService;
import org.mapstruct.*;

import java.time.format.DateTimeFormatter;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RemarkMapper {

    @Mapping(target = "remarkId", source = "remarkId")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "teacherId", expression = "java(remark.getTeacher().getUserId())")
    @Mapping(target = "teacherFirstName", expression = "java(remark.getTeacher().getFirstname())")
    @Mapping(target = "teacherLastName", expression = "java(remark.getTeacher().getLastname())")
    @Mapping(target = "addDate", expression = "java(formatDate(remark.getAddDate()))")
    @Mapping(target = "studentId", expression = "java(remark.getStudent().getUserId())")
    @Mapping(target = "studentFirstName", expression = "java(remark.getStudent().getFirstname())")
    @Mapping(target = "studentLastName", expression = "java(remark.getStudent().getLastname())")
    RemarkDto toDto(Remark remark);

    @Mapping(target = "remarkId", source = "remarkId")
    @Mapping(target = "content", source = "content")
    @Mapping(target = "addDate", source = "addDate")
    @Mapping(target = "student", expression = "java(studentService.getStudentById(dto.getStudentId()))")
    @Mapping(target = "teacher", expression = "java(teacherService.getTeacherById(dto.getTeacherId()))")
    Remark toEntity(RemarkDto dto, @Context StudentService studentService, @Context TeacherService teacherService);

    default String formatDate(java.time.LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return date.format(formatter);
    }
}
