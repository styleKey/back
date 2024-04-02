package com.thekey.stylekeyserver.stylepoint.service;

import com.thekey.stylekeyserver.stylepoint.entity.StylePoint;
import com.thekey.stylekeyserver.stylepoint.dto.request.StylePointRequest;
import java.util.List;

public interface StylePointAdminService {

    StylePoint findById(Long id);

    List<StylePoint> findAll();

    StylePoint update(Long id, StylePointRequest requestDto);

}
