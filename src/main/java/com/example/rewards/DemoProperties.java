package com.example.rewards;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix="spring.demo")
@Configuration
public class DemoProperties {
    private Integer numberOfMonths;
    private Integer maxAmt;
    private Integer minAmt;
    private Integer maxRewardPoint;
    private Integer minRewardPoint;

    // Getters, Setters, Constructors, etc...


    public DemoProperties() {
    }

    public Integer getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(Integer numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public Integer getMaxAmt() {
        return maxAmt;
    }

    public void setMaxAmt(Integer maxAmt) {
        this.maxAmt = maxAmt;
    }

    public Integer getMinAmt() {
        return minAmt;
    }

    public void setMinAmt(Integer minAmt) {
        this.minAmt = minAmt;
    }

    public Integer getMaxRewardPoint() {
        return maxRewardPoint;
    }

    public void setMaxRewardPoint(Integer maxRewardPoint) {
        this.maxRewardPoint = maxRewardPoint;
    }

    public Integer getMinRewardPoint() {
        return minRewardPoint;
    }

    public void setMinRewardPoint(Integer minRewardPoint) {
        this.minRewardPoint = minRewardPoint;
    }
}
