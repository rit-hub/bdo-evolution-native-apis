package com.bdo.evolution_native.service;

import com.bdo.evolution_native.model.addcard.CardRequest;
import com.bdo.evolution_native.model.addcard.CardResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reactor.core.publisher.Mono;

public interface AddCardInfoService {
    Mono<CardResponse> addCardInfoPost(
        CardRequest request, ServletUriComponentsBuilder servlet);
}
