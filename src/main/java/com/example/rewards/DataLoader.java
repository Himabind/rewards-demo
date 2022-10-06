package com.example.rewards;

import com.example.rewards.entity.Transaction;
import com.example.rewards.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements ApplicationRunner {

    private TransactionRepository transactionRepository;

    @Autowired
    public DataLoader(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void run(ApplicationArguments args) {

        // Populating some data set in h2database
        int year = 2022;
        int month = 1;
        int day = 1;
        int maxMonth = LocalDate.now().getMonthValue();
        int maxDay = LocalDate.now().getDayOfMonth();

        for (int i = 1; i < 100; i++) {
            Transaction simpleEntity = new Transaction();
            simpleEntity.setDate(LocalDate.of(year, month, day));
            simpleEntity.setAmount(createRandomIntBetween(10,300));
            simpleEntity.setCustomerId(createRandomIntBetween(1,9));

            transactionRepository.save(simpleEntity);

             day = day + 2;
             if (month == 2 && day > 28) day = 28;
             if (i % 10 == 0) {
                month = month + 1;
                day = 1;
            }
            if (month == maxMonth && day == maxDay)
                break;
        }

    }

    private int createRandomIntBetween(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }



}
