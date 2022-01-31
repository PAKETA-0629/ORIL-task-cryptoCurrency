package org.oriltesttask.controller;

import org.oriltesttask.model.Pair;
import org.oriltesttask.service.PairServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PairController {

    private final PairServiceImpl pairService;

    @Autowired
    public PairController(PairServiceImpl pairService) {
        this.pairService = pairService;
    }

    @GetMapping("/cryptocurrencies/minprice")
    public Pair getMinPrice(@RequestParam(name = "name") String currencyName) {
        return pairService.findMinPrice(currencyName);
    }

    @GetMapping("/cryptocurrencies/maxprice")
    public Pair getMaxPrice(@RequestParam(name = "name") String currencyName) {
        return pairService.findMaxPrice(currencyName);
    }

    @GetMapping("/cryptocurrencies")
    public Page<Pair> getPage(@RequestParam(name = "name") String currencyName, @RequestParam(required = false) int page, @RequestParam(required = false) int size) {
        size = size == 0 ? 10 : size;
        return pairService.getPage(currencyName, page, size);
    }
}
