package com.byt.freeEdu.mapper;

import com.byt.freeEdu.model.DTO.GradeDto;
import com.byt.freeEdu.model.Grade;
import com.byt.freeEdu.model.enums.SubjectEnum;
import com.byt.freeEdu.service.users.StudentService;
import com.byt.freeEdu.service.users.TeacherService;
import org.mapstruct.*;

import static com.byt.freeEdu.model.enums.SubjectEnum.*;

@org.mapstruct.Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR,
        componentModel = "spring",
        builder = @Builder(disableBuilder = true),
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface GradeMapper {

    @Mapping(target = "subjectEnum", source = "subject")
    @Mapping(target = "subject", source = "subject", qualifiedByName = "translateSubject")
    @Mapping(target = "studentId", expression = "java(grade.getStudent().getUserId())")
    @Mapping(target = "studentFirstName", expression = "java(grade.getStudent().getFirstname())")
    @Mapping(target = "studentLastName", expression = "java(grade.getStudent().getLastname())")
    @Mapping(target = "teacherId", expression = "java(grade.getTeacher().getUserId())")
    @Mapping(target = "teacherFirstName", expression = "java(grade.getTeacher().getFirstname())")
    @Mapping(target = "teacherLastName", expression = "java(grade.getTeacher().getLastname())")
    @Mapping(target = "gradeDate", source = "gradeDate")
    GradeDto toDto(Grade grade);

    @Mapping(target = "value", source = "value")
    @Mapping(target = "subject", source = "subject", qualifiedByName = "mapSubject")
    @Mapping(target = "student", expression = "java(studentService.getStudentById(gradeDto.getStudentId()))")
    @Mapping(target = "teacher", expression = "java(teacherService.getTeacherById(gradeDto.getTeacherId()))")
    Grade toEntity(GradeDto gradeDto, @Context StudentService studentService, @Context TeacherService teacherService);

    @Named("translateSubject")
    default String translateSubject(SubjectEnum subject) {
        if (subject == null) {
            return "Nieznany przedmiot";
        }
        switch (subject) {
            case POLISH:
                return "Język polski";
            case MATH:
                return "Matematyka";
            case GEOGRAPHY:
                return "Geografia";
            default:
                return "Nieznany przedmiot";
        }
    }
    @Named("mapSubject")
    default SubjectEnum mapSubject(String subject) {
        switch (subject) {
            case "POLISH":
                return POLISH;
            case "MATH":
                return MATH;
            case "GEOGRAPHY":
                return GEOGRAPHY;
            default:
                return POLISH;
        }
    }
}
