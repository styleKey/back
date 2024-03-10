package com.thekey.stylekeyserver.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateResponse {

    private long id;

    private CreateResponse(long id) {
        this.id = id;
    }

    public static CreateResponse of(long id) {
        return new CreateResponse(id);
    }
}
