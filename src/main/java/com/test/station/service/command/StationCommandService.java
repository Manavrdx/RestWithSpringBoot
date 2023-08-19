package com.test.station.service.command;

import com.test.station.dto.StationSaveRequest;
import com.test.station.dto.StationUpdateRequest;
import com.test.station.entity.Station;
import com.test.station.enums.Status;
import com.test.station.exceptions.GeneralBadRequestException;
import com.test.station.exceptions.ResourceNotFoundException;
import com.test.station.repository.StationRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class StationCommandService {

    private final StationRepository stationRepository;

    public StationCommandService(final StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @Transactional
    public Station saveStation(final StationSaveRequest stationSaveRequest) {
//        we can use mapstruct over here
        Station station = Station.builder()
                .stationAddress(stationSaveRequest.getStationAddress())
                .stationName(stationSaveRequest.getStationName())
                .stationPricing(stationSaveRequest.getStationPricing())
                .stationImage(stationSaveRequest.getStationImage())
                .stationStatus(Status.ACTIVE)
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();

        return stationRepository.save(station);
    }

    @Transactional
    public Station updateStation(final Long id, final StationUpdateRequest stationUpdateRequest) {
        Station station = stationRepository.getStationByIdExcludingDeleted(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Station found with given id:: " + id));

//        we can use mapstruct over here
        station.setStationName(stationUpdateRequest.getStationName());
        station.setStationImage(stationUpdateRequest.getStationImage());
        station.setStationPricing(stationUpdateRequest.getStationPricing());
        station.setStationAddress(stationUpdateRequest.getStationAddress());
        if (stationUpdateRequest.getStatus().equals(Status.DELETED)) {
            throw new GeneralBadRequestException("Invalid Status provided:: " +
                    stationUpdateRequest.getStatus());
        }
        station.setStationStatus(stationUpdateRequest.getStatus());
        station.setLastModifiedDate(LocalDateTime.now());

        return stationRepository.save(station);
    }

    @Transactional
    public void deleteStation(final Long id) {
        Station station = stationRepository.getStationByIdExcludingDeleted(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Station found with given id:: " + id));

        station.setStationStatus(Status.DELETED);
        station.setLastModifiedDate(LocalDateTime.now());

        stationRepository.save(station);
    }
}
