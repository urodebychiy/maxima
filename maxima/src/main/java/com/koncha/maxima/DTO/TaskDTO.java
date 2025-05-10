package com.koncha.maxima.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskDTO {
    private String title;
    private String description;
}
