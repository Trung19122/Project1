package com.hotel.hotel_management.service;

import com.hotel.hotel_management.Mapper.SportTypeMapper;
import com.hotel.hotel_management.dto.request.SportTypeRequest.SportTypeCreationRequest;
import com.hotel.hotel_management.dto.request.SportTypeRequest.SportTypeUpdateRequest;
import com.hotel.hotel_management.dto.response.SportTypeResponse;
import com.hotel.hotel_management.entity.SportType;
import com.hotel.hotel_management.exception.AppException;
import com.hotel.hotel_management.exception.ErrorCode;
import com.hotel.hotel_management.repository.SportTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportTypeService {
    private final SportTypeRepository sportTypeRepository;
    private final SportTypeMapper sportTypeMapper;

    public SportTypeResponse createSportType(SportTypeCreationRequest request) {

        if (sportTypeRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.SPORT_TYPE_EXISTED);
        }

        SportType sportType = sportTypeMapper.toSportType(request);

        return sportTypeMapper.toSportTypeResponse(
                sportTypeRepository.save(sportType)
        );
    }

    public List<SportTypeResponse> getSportTypes() {
        return sportTypeRepository.findAll()
                .stream()
                .map(sportTypeMapper::toSportTypeResponse)
                .toList();
    }

    public SportTypeResponse getSportTypeById(Long id) {

        SportType sportType = sportTypeRepository.findById(id)
                .orElseThrow(() ->
                        new AppException(ErrorCode.SPORT_TYPE_NOT_FOUND));

        return sportTypeMapper.toSportTypeResponse(sportType);
    }

    public SportTypeResponse updateSportType(Long id, SportTypeUpdateRequest request) {

        SportType sportType = sportTypeRepository.findById(id)
                .orElseThrow(() ->
                        new AppException(ErrorCode.SPORT_TYPE_NOT_FOUND));

        if (request.getName() != null
                && sportTypeRepository.existsByName(request.getName())
                && !sportType.getName().equalsIgnoreCase(request.getName())) {

            throw new AppException(ErrorCode.SPORT_TYPE_EXISTED);
        }

        sportTypeMapper.updateSportType(sportType, request);

        return sportTypeMapper.toSportTypeResponse(
                sportTypeRepository.save(sportType)
        );
    }

    public void deleteSportType(Long id) {

        if (!sportTypeRepository.existsById(id)) {
            throw new AppException(ErrorCode.SPORT_TYPE_NOT_FOUND);
        }

        sportTypeRepository.deleteById(id);
    }
}
