package com.nmc.si_getcreditscore;

public class LoanRequest {

    private final String ssn;
    private final double amount;
    private final Integer duration;

    public LoanRequest(String ssn, double amount, Integer duration) {
        this.ssn = ssn;
        this.amount = amount;
        this.duration = duration;
    }

    public double getAmount() {
        return amount;
    }

    public String getSsn() {
        return ssn;
    }

    public Integer getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "LoanRequest{" + "ssn=" + ssn + ", amount=" + amount + ", duration=" + duration + '}';
    }
}