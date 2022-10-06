package com.example.rewards.controller;

import com.example.rewards.entity.Transaction;
import com.example.rewards.model.Rewards;
import com.example.rewards.service.RewardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rewards")
public class RewardsController {

    private List<Transaction> transactions = new ArrayList<Transaction>();

    @Autowired
    private RewardsService rewardsService;

    private final Logger LOGGER = LoggerFactory.getLogger(RewardsController.class);

    /*@GetMapping(path = "{customerId}")
    public List<Transaction> getTransactions(@PathVariable("customerId") Integer customerId)
    {
       return rewardsService.fetchTransactionListByCustomerId(customerId);
    }*/

    @GetMapping(path = "{customerId}")
    public Rewards calculateRewardsByCustomerId(@PathVariable("customerId") Integer customerId)
    {
        LOGGER.info("Inside calculateRewardsByCustomerId of RewardsController");
        return rewardsService.getRewards(customerId);
    }



}



