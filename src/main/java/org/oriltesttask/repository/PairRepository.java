package org.oriltesttask.repository;

import org.oriltesttask.model.Pair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PairRepository extends JpaRepository<Pair, Long> {

    @Override
    Optional<Pair> findById(Long id);

    @Override
    List<Pair> findAll();

    @Query(nativeQuery = true, value = "SELECT * FROM pairs_prices WHERE price = (SELECT MIN(price) FROM pairs_prices WHERE symbol1=:currency_name);")
    Optional<Pair> findMinPrice(@Param(value = "currency_name")String currencyName);

    @Query(nativeQuery = true, value = "SELECT * FROM pairs_prices WHERE price = (SELECT MAX(price) FROM pairs_prices WHERE symbol1=:currency_name);")
    Optional<Pair> findMaxPrice(@Param(value = "currency_name")String currencyName);

    @Modifying
    @Query(nativeQuery = true, value = "INSERT INTO pairs_prices(symbol1, symbol2, price) VALUES(:symbol1, :symbol2, :price)")
    void createPair(@Param(value = "symbol1") String symbol1, @Param(value = "symbol2") String symbol2, @Param(value = "price") Double price);

    @Query(nativeQuery = true, value = "SELECT * FROM pairs_prices WHERE symbol1 = :currency_name")
    Page<Pair> getPage(@Param(value = "currency_name")String currencyName, Pageable pageable);
}
