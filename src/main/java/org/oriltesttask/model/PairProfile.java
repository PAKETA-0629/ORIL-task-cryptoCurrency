package org.oriltesttask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PairProfile {

    @JsonProperty("lprice")
    private Double price;
    @JsonProperty("curr1")
    private String cryptoCurrency;
    @JsonProperty("curr2")
    private String currency;
    private OffsetDateTime date;

    public Pair toEntity() {
        return Pair.builder().price(price).cryptoCurrency(cryptoCurrency).currency(currency).date(date).build();
    }
}
