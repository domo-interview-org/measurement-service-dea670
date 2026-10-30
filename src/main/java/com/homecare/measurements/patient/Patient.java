package com.homecare.measurements.patient;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Document("patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    private String id;
    private String name;
    private List<String> careTeam;
}
