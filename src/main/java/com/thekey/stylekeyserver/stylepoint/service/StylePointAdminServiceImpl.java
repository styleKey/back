package com.thekey.stylekeyserver.stylepoint.service;

import com.thekey.stylekeyserver.stylepoint.StylePointErrorMessage;
import com.thekey.stylekeyserver.stylepoint.domain.StylePoint;
import com.thekey.stylekeyserver.stylepoint.dto.request.StylePointRequest;
import com.thekey.stylekeyserver.stylepoint.repository.StylePointRepository;
import jakarta.persistence.*;

import java.util.List;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class StylePointAdminServiceImpl implements StylePointAdminService {

    private final StylePointRepository stylePointRepository;

    @Override
    public StylePoint findById(Long id) {
        return stylePointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(StylePointErrorMessage.NOT_FOUND_STYLE_POINT.get() + id));
    }

    @Override
    public List<StylePoint> findAll() {
        return stylePointRepository.findAll();
    }

    @Override
    public StylePoint update(Long id, StylePointRequest requestDto) {
        StylePoint stylePoint = stylePointRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(StylePointErrorMessage.NOT_FOUND_STYLE_POINT.get() + id));

        stylePoint.update(requestDto.getTitle(),
                requestDto.getDescription(),
                requestDto.getImage());

        return stylePoint;
    }

}
