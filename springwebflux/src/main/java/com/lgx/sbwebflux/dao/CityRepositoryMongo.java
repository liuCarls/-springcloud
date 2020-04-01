package com.lgx.sbwebflux.dao;

import com.lgx.sbwebflux.entity.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CityRepositoryMongo extends ReactiveMongoRepository<City, Long> {
    Mono<City> findByCityName(String cityName);

}
