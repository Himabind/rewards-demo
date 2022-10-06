package com.example.rewards.service;

import com.example.rewards.entity.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RewardsServiceTest {


    @Autowired
    private RewardsService rewardsService;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void whenValidCustomerId_thenFindTransactions(){
        Integer customerId = 1;
        List<Transaction> list = rewardsService.fetchTransactionListByCustomerId(customerId);

        assertNotNull(list);

    }


}