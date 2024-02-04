package com.thekey.stylekeyserver.coordinatelook.service;

import com.thekey.stylekeyserver.coordinatelook.domain.CoordinateLook;
import com.thekey.stylekeyserver.coordinatelook.dto.response.CoordinateLookResponse;
import com.thekey.stylekeyserver.coordinatelook.dto.request.CoordinateLookRequest;
import java.util.List;

public interface CoordinateLookAdminService {

    CoordinateLook create(CoordinateLookRequest requestDto);

    CoordinateLook findById(Long id);

    List<CoordinateLook> findAll();

    List<CoordinateLook> findByStylePointId(Long id);

    CoordinateLook update(Long id, CoordinateLookRequest requestDto);

    void deleteById(Long id);

    void deleteItemFromCoordinateLook(Long coordinateLookId, Long itemId);

//    CoordinateLookResponse convertToDto(CoordinateLook coordinateLook);

//    CoordinateLookResponse convertToResponseDto(CoordinateLook coordinateLook);
}
