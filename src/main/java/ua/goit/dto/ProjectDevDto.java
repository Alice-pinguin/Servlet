package ua.goit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProjectDevDto {

        private String projectDate;
        private String projectName;
        private Integer devCount;
    }

