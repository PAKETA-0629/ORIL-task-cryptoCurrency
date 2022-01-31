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
    private String symbol1;
    @JsonProperty("curr2")
    private String symbol2;
    private OffsetDateTime date;

    public Pair toEntity() {
        return Pair.builder().price(price).symbol1(symbol1).symbol2(symbol2).date(date).build();
    }
}
