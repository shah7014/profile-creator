package com.learningbybuilding.resumeportal.profile;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String company;
    private String designation;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isPresent;
    @ElementCollection(targetClass = String.class)
    @CollectionTable(name = "responsibilities", joinColumns = {@JoinColumn(name="job_id")})
    private List<String> responsibilities;

    public String getFormattedStartDate() {
        return startDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }

    public String getFormattedEndDate() {
        return endDate.format(DateTimeFormatter.ofPattern("MMM yyyy"));
    }
}
