
package com.example.rewards.model;


import lombok.*;
import java.util.Map;


@Data
@NoArgsConstructor

public class Rewards {

    private Integer customerId;
    private Integer rewardsTotal;
    private Map rewardsPerMonth;

    }
