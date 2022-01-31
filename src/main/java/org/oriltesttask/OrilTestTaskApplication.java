package org.oriltesttask;

import lombok.extern.slf4j.Slf4j;
import org.oriltesttask.model.PairProfile;
import org.oriltesttask.service.PairServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;
import java.time.*;
import java.util.*;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class OrilTestTaskApplication {

    private static PairServiceImpl pairService;
    private static final RestTemplate restTemplate = get();
    private static final String[] currencies = {"BTC", "ETH", "XRP"};

    @Autowired
    public OrilTestTaskApplication(PairServiceImpl pairService1) {
        pairService = pairService1;
    }

    public static void main(String[] args) {
        SpringApplication.run(OrilTestTaskApplication.class, args);
    }

    @Scheduled(fixedDelay = 60000)
    private static void fetchCryptocurrencyRate() {
        for (String currency : currencies) {
            PairProfile pair = restTemplate.getForObject("https://cex.io/api/last_price/" + currency + "/USD/", PairProfile.class);
            if (pair == null) {
                log.error("Pair is null");
                //throw new Exception();
            }

            pair.setDate(OffsetDateTime.now());
            log.info("Fetch Cryptocurrency Last Price: " + pair.getSymbol1() + "|" + pair.getSymbol2() + "|" + pair.getPrice());
            pairService.createPair(pair.toEntity());
        }
    }

    private static RestTemplate get() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }
}
