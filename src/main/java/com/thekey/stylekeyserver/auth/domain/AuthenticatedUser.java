package com.thekey.stylekeyserver.auth.domain;

import io.swagger.v3.oas.annotations.Hidden;

import java.io.Serializable;
import java.util.UUID;

@Hidden
public interface AuthenticatedUser extends Serializable {
    UUID getUserId();
}

