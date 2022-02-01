package org.oriltesttask.service;

import org.oriltesttask.model.Pair;
import org.oriltesttask.model.PairProfile;
import org.springframework.data.domain.Page;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PairService {

    List<PairProfile> findAll();

    Pair findById(Long id);

    void createPair(Pair pair);

    PairProfile findMaxPrice(String cryptoCurrency);

    PairProfile findMinPrice(String cryptoCurrency);

    Page<Pair> getPage(String cryptoCurrency, Integer page, Integer size);

    ByteArrayInputStream generateCSV();
}
