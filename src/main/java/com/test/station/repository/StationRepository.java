package com.test.station.repository;

import com.test.station.entity.Station;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {


    @Query("select s from Station s where s.stationStatus != 'DELETED' and s.stationId = :stationId")
    Optional<Station> getStationByIdExcludingDeleted(@Param(value = "stationId") Long id);

    //JPQL
    @Query("SELECT s FROM Station s WHERE s.stationStatus != 'DELETED'")
    List<Station> getAllStationsExcludingDeleted();

    //Native Query
    @Query(
            value = "SELECT * FROM tbl_station s WHERE s.station_status = 'ACTIVE'",
            nativeQuery = true
    )
    List<Station> getAllActiveStations();

    //Native Query
    @Query(
            value = "SELECT * FROM tbl_station s WHERE s.station_status = 'ACTIVE'",
            nativeQuery = true)
    List<Station> getStationsByPagination(PageRequest request);
}
