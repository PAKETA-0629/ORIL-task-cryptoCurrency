package org.oriltesttask.service;

import org.oriltesttask.model.Pair;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PairService {

    List<Pair> findAll();

    Pair findById(Long id);

    void createPair(Pair pair);

    Pair findMaxPrice(String currencyName);

    Pair findMinPrice(String currencyName);

    Page<Pair> getPage(String currencyName, Integer page, Integer size);
}
