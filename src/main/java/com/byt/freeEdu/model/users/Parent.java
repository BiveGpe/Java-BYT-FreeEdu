package com.byt.freeEdu.model.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parent")
public class Parent extends User {
    @NonNull
    @Column(name = "contact_info", nullable = false)
    private String contactInfo;
}