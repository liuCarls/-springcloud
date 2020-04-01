package com.lgx.sbwebflux.handler;

import com.lgx.sbwebflux.dao.CityRepository;
import com.lgx.sbwebflux.entity.City;
import com.lgx.sbwebflux.error.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class CityHandler {
    private final CityRepository cityRepository;
    //hello
    public Mono<ServerResponse> helloCity(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello, City!"));
    }
    //shello?city=WenLing

    /**
     *
     *  ServerRequest 是对请求的封装。
     *  GlobalException 是封装的全局异常。
     * @param request
     * @return
     */
    public Mono<ServerResponse> sayHelloCity(ServerRequest request) {
        Optional<String> cityParamOptional = request.queryParam("city");
        if (!cityParamOptional.isPresent()) {
            throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR, "request param city is ERROR");
        }
//        return ServerResponse.ok().body("Hello," + cityParamOptional.get(), String.class); //会报错
        return ServerResponse.ok().body(Mono.just("Hello," + cityParamOptional.get()), String.class);
//        return Mono.just("Hello," + cityParamOptional.get());
//        Mono.justOrEmpty()：从一个 Optional 对象或 null 对象中创建 Mono。
    }


    @Autowired
    public CityHandler(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public Mono<Long> save(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.save(city)));
    }

    public Mono<City> findCityById(Long id) {
        return Mono.justOrEmpty(cityRepository.findCityById(id));
    }

    public Flux<City> findAllCity() {
        return Flux.fromIterable(cityRepository.findAll());
    }

    public Mono<Long> modifyCity(City city) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.updateCity(city)));
    }

    public Mono<Long> deleteCity(Long id) {
        return Mono.create(cityMonoSink -> cityMonoSink.success(cityRepository.deleteCity(id)));
    }
}
