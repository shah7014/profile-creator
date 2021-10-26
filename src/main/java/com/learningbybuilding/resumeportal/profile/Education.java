package com.learningbybuilding.resumeportal.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String college;
    private String qualification;
    private LocalDate startDate;
    private LocalDate endDate;
}
