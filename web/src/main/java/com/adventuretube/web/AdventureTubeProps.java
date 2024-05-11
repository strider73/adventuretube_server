package com.adventuretube.web;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "adventuretube")
public class AdventureTubeProps {
    private int pageSize = 100;

    @Min(value = 1, message="must be between 1 and 25")
    @Max(value = 25, message="must be between 1 and 25")
    private double maxDistance = 2.0;
}
