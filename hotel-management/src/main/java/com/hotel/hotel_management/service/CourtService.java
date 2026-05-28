package com.hotel.hotel_management.service;

import com.hotel.hotel_management.Mapper.CourtMapper;
import com.hotel.hotel_management.dto.request.CourtResponse.CourtCreationRequest;
import com.hotel.hotel_management.dto.request.CourtResponse.CourtUpdateRequest;
import com.hotel.hotel_management.dto.request.VenueRequest.VenueCreationRequest;
import com.hotel.hotel_management.dto.request.VenueRequest.VenueUpdateRequest;
import com.hotel.hotel_management.dto.response.CourtResponse;
import com.hotel.hotel_management.dto.response.VenueResponse;
import com.hotel.hotel_management.entity.Court;
import com.hotel.hotel_management.entity.SportType;
import com.hotel.hotel_management.entity.User;
import com.hotel.hotel_management.entity.Venue;
import com.hotel.hotel_management.exception.AppException;
import com.hotel.hotel_management.exception.ErrorCode;
import com.hotel.hotel_management.repository.CourtRepository;
import com.hotel.hotel_management.repository.SportTypeRepository;
import com.hotel.hotel_management.repository.UserRepository;
import com.hotel.hotel_management.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CourtService {

    private final CourtRepository courtRepository;
    private final CourtMapper courtMapper;
    private final VenueRepository venueRepository;
    private final SportTypeRepository sportTypeRepository;

    public CourtResponse createCourt(CourtCreationRequest request) {
        Venue venue = venueRepository.findById(request.getVenueId())
                .orElseThrow(() -> new AppException(ErrorCode.VENUE_NOT_FOUND));

        SportType sportType = sportTypeRepository.findById(request.getSportTypeId())
                .orElseThrow(() -> new AppException(ErrorCode.SPORT_TYPE_NOT_FOUND));

        if (courtRepository.existsByNameAndVenue(request.getName(), venue)) {
            throw new AppException(ErrorCode.COURT_EXISTED);
        }

        Court court = courtMapper.toCourt(request);
        court.setVenue(venue);
        court.setSportType(sportType);
        court.setStatus(Court.CourtStatus.AVAILABLE);

        return courtMapper.toCourtResponse(courtRepository.save(court));
    }

    public List<CourtResponse> getCourts() {
        return courtRepository.findAll()
                .stream()
                .map(courtMapper::toCourtResponse)
                .toList();
    }

    public CourtResponse getCourtById(Long id) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COURT_NOT_FOUND));

        return courtMapper.toCourtResponse(court);
    }

    public void deleteCourt(Long id) {
        if (!courtRepository.existsById(id)) {
            throw new AppException(ErrorCode.COURT_NOT_FOUND);
        }

        courtRepository.deleteById(id);
    }

    public CourtResponse updateCourt(Long id, CourtUpdateRequest request) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COURT_NOT_FOUND));

        courtMapper.updateCourt(court, request);

        return courtMapper.toCourtResponse(courtRepository.save(court));
    }
}
