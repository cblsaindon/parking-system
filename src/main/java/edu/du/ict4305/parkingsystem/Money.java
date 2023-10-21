/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.du.ict4305.parkingsystem;

import java.util.Currency;
import java.util.Set;

/**
 * Money class representing dollar value in cents
 *
 * @author candace.saindon based on M. I. Schwartz and R. Judd class Money
 * starter class example
 */
public class Money implements Comparable<Money> {

    private long amount;
    private final Currency currency;

    public static Money of(double amt) {
        return Money.of(amt, Currency.getInstance("USD"));
    }

    public static Money of(String moneyString) {
        return new Money(moneyString);
    }

    public static Money of(double amt, Currency currency) {
        return new Money(amt, currency);
    }

    /*
     * Multiply the amount by 10^currency fraction digits
     * to represent the
     */
    private Money(double money, Currency currency) {
        this.currency = currency;
        int fractionDigits = currency.getDefaultFractionDigits();
        if (fractionDigits < 0) {
            fractionDigits = 0;
        }
        amount = Math.round(money * Math.pow(10, fractionDigits));
    }

    private Money(double money) {
        this.currency = Currency.getInstance("USD");
        int fractionDigits = currency.getDefaultFractionDigits();
        if (fractionDigits < 0) {
            fractionDigits = 0;
        }
        amount = Math.round(money * Math.pow(10, fractionDigits));
    }

    public Money(long money, Currency currency) {
        amount = money; // Don't scale the "cents" value
        this.currency = currency;
    }

    public Money(Money money) {
        this.amount = money.amount;
        currency = money.currency;
    }

    public Money(String moneyString) {
        Currency curr = fromSymbol(moneyString);
        String amt = moneyString.replaceAll("[^0-9.]", "");
        String[] dollarsCents = amt.split("[.]");
        long dollars = Long.parseLong(dollarsCents[0]);
        int fractionDigits = curr.getDefaultFractionDigits();
        long cents = 0L;
        int fractLength = 0;
        if (dollarsCents.length > 1) {
            fractLength = dollarsCents[1].length();
        }
        if (fractionDigits > 0 && fractLength > 0) {
            cents = Long.parseLong(dollarsCents[1].substring(0, Math.min(fractionDigits, fractLength)));
            for (; fractLength < fractionDigits; fractLength += 1) {
                cents *= 10;
            }
        }

        this.amount = (long) (dollars * Math.pow(10, fractionDigits) + cents);
        this.currency = curr;
    }

    static private Currency fromSymbol(String moneyString) {
        Currency result = Currency.getInstance("USD");
        Set<Currency> currencies = Currency.getAvailableCurrencies();
        for (Currency currency : currencies) {
            String code = currency.getCurrencyCode();
            String symbol = currency.getSymbol();
            if (moneyString.contains(code) || moneyString.contains(symbol)) {
                result = currency;
                break;
            }
        }
        return result;
    }

    public double getDollars() {
        return amount / 100.0;
    }

    public Currency getCurrency() {
        return currency;
    }

    public String toString() {
        return getClass().getName() + "[cents=" + amount + "]";
    }

    public static Money add(Money moneyOne, Money moneyTwo) {
        double added = moneyOne.getDollars() + moneyTwo.getDollars();
        return new Money(added,moneyOne.getCurrency());
    }

    public static Money subtract(Money l, Money r) {
        long lcents = l.getCents();
        long rcents = r.getCents();
        
        if (!l.currency.equals(r.currency)) {
            throw new CurrencyConversionException("Cannot convert currencies at this time");
        }
        
        Money result = new Money(lcents - rcents, l.currency);
        
        return result;
    }

    private long getCents() {
        return amount;
    }

    public static Money times(Money l, int n) {
        return new Money(l.getCents() * n, l.getCurrency());
    }

    public static Money times(Money l, double d) {
        long amt = (long) (l.getCents() * d);
        return new Money(amt, l.currency);
    }

    @Override
    public int compareTo(Money money) {
        if (!currency.equals(money.currency)) {
            return -1;
        }
        long result = amount - money.amount;
        if (result > 0L) {
            return 1;
        } else if (result < 0) {
            return -1;
        }
        return 0;
    }
}
