package org.oriltesttask.service;

import lombok.extern.slf4j.Slf4j;
import org.oriltesttask.model.Pair;
import org.oriltesttask.repository.PairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class PairServiceImpl implements PairService {

    private final PairRepository pairRepository;

    @Autowired
    public PairServiceImpl(PairRepository pairRepository) {
        this.pairRepository = pairRepository;
    }

    @Override
    public List<Pair> findAll() {
        List<Pair> pairs = pairRepository.findAll();
        if (pairs.isEmpty()) {
            log.error("Empty list of pairs");
            //throw new Exception();
        }
        return pairs;
    }

    @Override
    public Pair findById(Long id) {
        Pair pair = pairRepository.findById(id).orElse(null);
        if (pair == null) {
            log.error("Pair is empty");
            //throw new Exception();
        }
        return pair;
    }

    @Override
    public void createPair(Pair pair) {
        pairRepository.createPair(pair.getSymbol1(), pair.getSymbol2(), pair.getPrice());
    }

    @Override
    public Pair findMaxPrice(String currencyName) {
        Pair pair = pairRepository.findMaxPrice(currencyName).orElse(null);
        if (pair == null) {
            log.error("Pair is empty");
            //throw new Exception();
        }
        return pair;
    }

    @Override
    public Pair findMinPrice(String currencyName) {
        Pair pair = pairRepository.findMinPrice(currencyName).orElse(null);
        if (pair == null) {
            log.error("Pair is empty");
            //throw new Exception();
        }
        return pair;
    }
}
