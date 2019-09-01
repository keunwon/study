package com.keunwon.java8.chap08;

import javax.jws.Oneway;
import java.util.function.Consumer;

public class OnlineBankingLamda {

    public static void main(String ... args) {
        new OnlineBankingLamda().processCustomer(1337, customer -> System.out.println("custoner"));
        new OnlineBankingLamda().processCustomer(1337, Customer::run);
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer customer = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(customer);
    }

    static private class Customer {
        public void run() {
            System.out.println("111");
        }
    }
    static private class Database{
        static Customer getCustomerWithId(int id){ return new Customer();}
    }
}
