package com.test.station.controller;

import com.test.station.dto.StationSaveRequest;
import com.test.station.dto.StationUpdateRequest;
import com.test.station.entity.Station;
import com.test.station.enums.Order;
import com.test.station.service.command.StationCommandService;
import com.test.station.service.query.StationQueryService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StationController {

    private final StationCommandService stationCommandService;
    private final StationQueryService stationQueryService;

    public StationController(final StationCommandService stationCommandService,
                             final StationQueryService stationQueryService) {
        this.stationCommandService = stationCommandService;
        this.stationQueryService = stationQueryService;
    }

    @PostMapping("/stations")
    @SneakyThrows
    public ResponseEntity<Station> saveStation(@Valid @RequestBody StationSaveRequest stationSaveRequest) {
        return ResponseEntity.ok(stationCommandService.saveStation(stationSaveRequest));
    }

    @GetMapping("/stations")
    public ResponseEntity<List<Station>> getAllStations(){
        return ResponseEntity.ok(stationQueryService.getAll());
    }

    @GetMapping("/stations/{id}")
    public ResponseEntity<Station> getStationById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok(stationQueryService.getById(id));
    }

    @PutMapping("/stations/{id}/edit")
    public ResponseEntity<Station> editStationDetailsById(@PathVariable(name = "id") Long id,
                                                          @RequestBody StationUpdateRequest stationUpdateRequest) {
        return ResponseEntity.ok(stationCommandService.updateStation(id, stationUpdateRequest));
    }

    @DeleteMapping("/stations/{id}")
    public ResponseEntity<Void> deleteStation(@PathVariable(name = "id") Long id) {
        stationCommandService.deleteStation(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/station/by/limit")
    public ResponseEntity<List<Station>> getStationsByLimit(@RequestParam(name = "limit") Integer limit) {
        return ResponseEntity.ok(stationQueryService.getStationsByLimit(limit));
    }

    @GetMapping("/station/by/sort/and/pricing")
    public ResponseEntity<List<Station>> getStationsSortByPricing(@RequestParam(name = "sort") Order orderBy,
                                                                  @RequestParam(name = "param") String value) {
        return ResponseEntity.ok(stationQueryService.getStationsBySortAndPricing(orderBy, value));
    }
}
