package com.example.rewards.service;

import com.example.rewards.DemoProperties;
import com.example.rewards.entity.Transaction;
import com.example.rewards.model.Rewards;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RewardsServiceImpl implements RewardsService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private DemoProperties demoProperties;

    @Override
    public List<Transaction> fetchTransactionListByCustomerId(Integer customerId){

        return transactionRepository.findByCustomerId(customerId);
    }

    @Override
    public Rewards getRewards(Integer customerId){


        // year always must be current year.
        // currentMonth : current month by default, but can be replaced by value provided by customer,
        // numberOfMonths : default last 3 calendar months rewards (including the month), can be replaced by value by customer.


        int currentMonth =  LocalDate.now().getMonthValue();

        int numberOfMonths = demoProperties.getNumberOfMonths(); // must be between 1- 12 ;
        if(numberOfMonths > currentMonth || numberOfMonths> 12 )
            numberOfMonths = currentMonth;

        int firstMonth =  currentMonth - numberOfMonths;
        int maxAmt = demoProperties.getMaxAmt();
        int minAmt = demoProperties.getMinAmt();
        int maxRewardPoint = demoProperties.getMaxRewardPoint();
        int minRewardPoint = demoProperties.getMinRewardPoint();

        //Do Validate customerId before making a request.
        Map<Integer,List<Transaction>> transactionsGrouping = transactionRepository.findByCustomerId(customerId)
                    .stream().filter(transaction -> transaction.getAmount()>minAmt).toList()
                    .stream().filter(transaction -> transaction.getDate().getMonthValue()>firstMonth).toList()
                    .stream().collect(Collectors.groupingBy(t -> t.getDate().getMonthValue()));


        Map<Integer,Integer> rewardsMap = new HashMap<>();

        Iterator iterator = transactionsGrouping.keySet().iterator();
        int totalRewardsfor3Months = 0;
        while(iterator.hasNext()) {
            Integer monthKey = (Integer) iterator.next();
            List<Transaction> transactionList = transactionsGrouping.get(monthKey);
            int rewardsPerMonth = 0;
            int rewardsPerTransaction = 0;

            for (Transaction transaction : transactionList) {
                if (transaction.getAmount() > maxAmt)
                    rewardsPerTransaction = (transaction.getAmount() - maxAmt) * maxRewardPoint + minAmt;
                else
                    rewardsPerTransaction = (transaction.getAmount() - minAmt) * minRewardPoint;
                rewardsPerMonth = rewardsPerMonth + rewardsPerTransaction;
            }

            totalRewardsfor3Months = totalRewardsfor3Months + rewardsPerMonth;
            rewardsMap.put(monthKey, rewardsPerMonth);
        }

        Rewards rewards = new Rewards();
        rewards.setCustomerId(customerId);
        rewards.setRewardsPerMonth(rewardsMap);
        rewards.setRewardsTotal(totalRewardsfor3Months);

        return rewards;
    };



}
