package com.hello.hellomessagequeue.step10;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
    Long stock(int stock);
}
