package com.hotel.hotel_management.service;

import com.hotel.hotel_management.Mapper.VenueMapper;
import com.hotel.hotel_management.dto.request.VenueRequest.VenueCreationRequest;
import com.hotel.hotel_management.dto.request.VenueRequest.VenueUpdateRequest;
import com.hotel.hotel_management.dto.response.VenueResponse;
import com.hotel.hotel_management.entity.User;
import com.hotel.hotel_management.entity.Venue;
import com.hotel.hotel_management.exception.AppException;
import com.hotel.hotel_management.exception.ErrorCode;
import com.hotel.hotel_management.repository.UserRepository;
import com.hotel.hotel_management.repository.VenueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class VenueService {

    private final VenueRepository venueRepository;
    private final VenueMapper venueMapper;
    private final UserRepository userRepository;

    public VenueResponse createVenue(VenueCreationRequest request, String username) {
        User owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        Venue venue = venueMapper.toVenue(request);
        venue.setOwner(owner);
        venue.setStatus(Venue.VenueStatus.ACTIVE);

        return venueMapper.toVenueResponse(venueRepository.save(venue));
    }

    public List<VenueResponse> getVenues() {
        return venueRepository.findAll()
                .stream()
                .map(venueMapper::toVenueResponse)
                .toList();
    }

    public VenueResponse getVenueById(Long id) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.VENUE_NOT_FOUND));

        return venueMapper.toVenueResponse(venue);
    }

    public void deleteVenue(Long id) {
        if (!venueRepository.existsById(id)) {
            throw new AppException(ErrorCode.VENUE_NOT_FOUND);
        }

        venueRepository.deleteById(id);
    }

    public VenueResponse updateVenue(Long id, VenueUpdateRequest request) {
        Venue venue = venueRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.VENUE_NOT_FOUND));

        venueMapper.updateVenue(venue, request);

        return venueMapper.toVenueResponse(venueRepository.save(venue));
    }
}
