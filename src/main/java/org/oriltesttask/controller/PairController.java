package org.oriltesttask.controller;

import org.oriltesttask.model.Pair;
import org.oriltesttask.model.PairProfile;
import org.oriltesttask.service.PairServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public PairProfile getMinPrice(@RequestParam(name = "name") String currencyName) {
        return pairService.findMinPrice(currencyName);
    }

    @GetMapping("/cryptocurrencies/maxprice")
    public PairProfile getMaxPrice(@RequestParam(name = "name") String currencyName) {
        return pairService.findMaxPrice(currencyName);
    }

    @GetMapping("/cryptocurrencies")
    public Page<Pair> getPage(@RequestParam(name = "name") String currencyName, @RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return pairService.getPage(currencyName, page, size);
    }

    @GetMapping("/csv/download")
    public ResponseEntity<Resource> getCSV() {
        String filename = "pairs.csv";
        InputStreamResource file = new InputStreamResource(pairService.generateCSV());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);

    }
}
