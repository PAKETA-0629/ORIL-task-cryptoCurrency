package org.oriltesttask.service;

import org.oriltesttask.model.Pair;
import org.springframework.data.domain.Page;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface PairService {

    List<Pair> findAll();

    Pair findById(Long id);

    void createPair(Pair pair);

    Pair findMaxPrice(String cryptoCurrency);

    Pair findMinPrice(String cryptoCurrency);

    Page<Pair> getPage(String cryptoCurrency, Integer page, Integer size);

    ByteArrayInputStream generateCSV();
}
