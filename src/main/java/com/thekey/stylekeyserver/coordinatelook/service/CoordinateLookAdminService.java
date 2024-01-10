package com.thekey.stylekeyserver.coordinatelook.service;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.CoordinateLookDto;
import java.util.List;

public interface CoordinateLookAdminService {

    CoordinateLook create(CoordinateLookDto requestDto);

    CoordinateLook findById(Long id);

    List<CoordinateLook> findAll();

    List<CoordinateLook> findByStylePointId(Long id);

    CoordinateLook update(Long id, CoordinateLookDto requestDto);

    void deleteById(Long id);

    void deleteItemFromCoordinateLook(Long coordinateLookId, Long itemId);

    CoordinateLookDto convertToDto(CoordinateLook coordinateLook);

    CoordinateLookDto convertToResponseDto(CoordinateLook coordinateLook);
}
