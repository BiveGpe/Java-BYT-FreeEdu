package com.byt.freeEdu.model.DTO;

import com.byt.freeEdu.model.Attendance;
import com.byt.freeEdu.model.enums.AttendanceEnum;
import com.byt.freeEdu.model.enums.SubjectEnum;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class AttendanceFormDto {

    private Map<Integer, AttendanceEnum> attendanceMap = new HashMap<>();
    private SubjectEnum globalSubject;

    public Map<Integer, AttendanceEnum> getAttendanceMap() {
        return attendanceMap;
    }

    public void setAttendanceMap(Map<Integer, AttendanceEnum> attendanceMap) {
        this.attendanceMap = attendanceMap;
    }

    public static AttendanceFormDto toDto(Integer id, AttendanceEnum status) {
        AttendanceFormDto dto = new AttendanceFormDto();
        dto.getAttendanceMap().put(id, status);
        return dto;
    }

    public SubjectEnum getGlobalSubject() {
        return globalSubject;
    }

    public void setGlobalSubject(SubjectEnum globalSubject) {
        this.globalSubject = globalSubject;
    }

    public static AttendanceFormDto toDto(Attendance attendance) {
        AttendanceFormDto dto = new AttendanceFormDto();
        Map<Integer, AttendanceEnum> attendanceMap = new HashMap<>();
        attendanceMap.put(attendance.getId(), attendance.getStatus());
        dto.setAttendanceMap(attendanceMap);
        return dto;
    }
}
