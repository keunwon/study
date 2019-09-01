package com.keunwon.java8.chap08;

abstract class OnlineBanking {
    public void processCustomer(int id) {
        Customer customer = Database.getCustomerWithId(id);
        makeCustomerHappy(customer);
    }

    abstract void makeCustomerHappy(Customer customer);


    static private class Customer {}
    static private class Database {
        static Customer getCustomerWithId(int id){ return new Customer(); }
    }
}
