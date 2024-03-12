package com.thekey.stylekeyserver.test.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class SaveTestResultRequest {

    private Map<Long, Integer> stylePoints;
}
