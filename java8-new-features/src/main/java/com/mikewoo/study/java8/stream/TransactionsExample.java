package com.mikewoo.study.java8.stream;

import com.mikewoo.study.java8.domain.Trader;
import com.mikewoo.study.java8.domain.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Eric Gui
 * @date 2018/7/26
 */
public class TransactionsExample {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );


        // 1. Find all transactions in the year 2011 and sort them by value (small to high).
        System.out.println("==========1.1=======");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue)) // (small to high)
                .forEach(System.out::println);

        System.out.println("==========1.2=======");
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue).reversed()) // (high to small)
                .forEach(System.out::println);

        // 2. What are all the unique cities where the traders work?
        System.out.println("==========2=======");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);

        // 3. Find all traders from Cambridge and sort them by name.
        System.out.println("==========3=======");
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        // 4. Return a string of all traders’ names sorted alphabetically.
        System.out.println("==========4.1=======");
        String result = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2 + " ");
        System.out.println(result);

        System.out.println("==========4.2=======");
        result = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2 + " ");
        System.out.println(result);

        // 5. Are any traders based in Milan?
        System.out.println("==========5=======");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Milan"))
                .findAny()
                .map(Transaction::getTrader)
                .ifPresent(System.out::println);

        // 6. Print all transactions’ values from the traders living in Cambridge.
        System.out.println("==========6=======");
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .sorted()
                .forEach(System.out::println);

        // 7. What’s the highest value of all the transactions?
        System.out.println("==========7=======");
        transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max)
                .ifPresent(System.out::println);

        // 8. Find the transaction with the smallest value.
        System.out.println("==========8=======");
        transactions.stream()
                .reduce((transaction1, transaction2) -> transaction1.getValue() < transaction2.getValue() ? transaction1 : transaction2)
                .ifPresent(System.out::println);
    }
}
