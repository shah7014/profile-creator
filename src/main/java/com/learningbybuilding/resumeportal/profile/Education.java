package com.learningbybuilding.resumeportal.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {
    private String college;
    private String qualification;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
}
