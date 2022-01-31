package org.oriltesttask.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.OffsetDateTime;

@Builder
@Data
@Entity
@Table(name = "pairs_prices")
@AllArgsConstructor
@NoArgsConstructor
public class Pair {

    @Id
    private Long id;
    @Column(name = "cryptoCurrency")
    private String cryptoCurrency;
    @Column(name = "currency")
    private String currency;
    @Column(name = "price")
    private Double price;
    @Column(name = "date")
    private OffsetDateTime date;

    public PairProfile toProfile() {
        return PairProfile.builder().price(price).cryptoCurrency(cryptoCurrency).currency(currency).date(date).build();
    }
}
