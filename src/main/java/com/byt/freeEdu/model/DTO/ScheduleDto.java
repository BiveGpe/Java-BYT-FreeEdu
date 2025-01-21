package com.byt.freeEdu.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ScheduleDto {
    private LocalDate date;
    private String subjectName;
    private String className;
    private String teacherFullName;
}
