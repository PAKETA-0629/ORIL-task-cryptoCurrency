package org.oriltesttask.service;

import lombok.extern.slf4j.Slf4j;
import org.oriltesttask.model.Pair;
import org.oriltesttask.repository.PairRepository;
import org.oriltesttask.util.CSVGenerator;
import org.oriltesttask.util.CryptoCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class PairServiceImpl implements PairService {

    private final PairRepository pairRepository;
    private final CryptoCurrency[] cryptoCurrencies = CryptoCurrency.values();

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
        pairRepository.createPair(pair.getCryptoCurrency(), pair.getCurrency(), pair.getPrice(), pair.getDate());
    }

    @Override
    public Pair findMaxPrice(String cryptoCurrency) {
        Pair pair = pairRepository.findMaxPrice(cryptoCurrency).orElse(null);
        if (pair == null) {
            log.error("Pair is empty");
            //throw new Exception();
        }
        return pair;
    }

    @Override
    public Pair findMinPrice(String cryptoCurrency) {
        Pair pair = pairRepository.findMinPrice(cryptoCurrency).orElse(null);
        if (pair == null) {
            log.error("Pair is empty");
            //throw new Exception();
        }
        return pair;
    }

    @Override
    public Page<Pair> getPage(String currencyName, Integer page, Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 10 : size;
        return pairRepository.getPage(currencyName, PageRequest.of(page, size, Sort.by("price").ascending()));
    }

    @Override
    public ByteArrayInputStream generateCSV() {
        List<List<String>> data = new ArrayList<>();
        for (CryptoCurrency cryptoCurrency : cryptoCurrencies) {
            List<String> row = Arrays.asList(
                    cryptoCurrency.toString(),
                    String.valueOf(findMinPrice(cryptoCurrency.toString()).getPrice()),
                    String.valueOf(findMaxPrice(cryptoCurrency.toString()).getPrice())
            );
            data.add(row);
        }
        return CSVGenerator.generate(data);
    }
}
