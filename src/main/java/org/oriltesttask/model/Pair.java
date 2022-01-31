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
    @Column(name = "symbol1")
    private String symbol1;
    @Column(name = "symbol2")
    private String symbol2;
    @Column(name = "price")
    private Double price;
    @Column(name = "date")
    private OffsetDateTime date;

    public PairProfile toProfile() {
        return PairProfile.builder().price(price).symbol1(symbol1).symbol2(symbol2).date(date).build();
    }
}
