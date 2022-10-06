package com.example.rewards.service;


import com.example.rewards.entity.Transaction;
import com.example.rewards.model.Rewards;


import java.util.List;


public interface RewardsService {

    public List<Transaction> fetchTransactionListByCustomerId(Integer customerId);

    public Rewards getRewards(Integer customerId);

}
