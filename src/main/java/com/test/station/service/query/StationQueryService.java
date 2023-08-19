package com.test.station.service.query;

import com.test.station.entity.Station;
import com.test.station.enums.Order;
import com.test.station.exceptions.ResourceNotFoundException;
import com.test.station.repository.StationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.test.station.enums.Order.ASC;

@Service
public class StationQueryService {

    private final StationRepository stationRepository;

    public StationQueryService(final StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public List<Station> getAll() {
        return stationRepository.getAllActiveStations();
    }


    public List<Station> getAllExcludingDeleted() {
        return stationRepository.getAllStationsExcludingDeleted();
    }

    public Station getById(final Long id) {
        return stationRepository.getStationByIdExcludingDeleted(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Station found with given id:: "+id));
    }

    public List<Station> getStationsByLimit(final Integer limit) {
        int page = 0; //can be dynamic from the input
        return stationRepository.getStationsByPagination(PageRequest.of(page, limit));
    }

    public List<Station> getStationsBySortAndPricing(final Order orderBy,
                                                     final String param) {
        PageRequest request = orderBy.equals(ASC) ?
                PageRequest.of(0, 10, Sort.by(param).ascending()):
                PageRequest.of(0, 10, Sort.by(param).descending());

        return stationRepository.getStationsByPagination(request);
    }
}
