package com.keunwon.java8.chap08;

import java.util.ArrayList;
import java.util.List;

public class ObserverMain {

    public static void main(String ... args) {
        Feed feed = new Feed();
        feed.registerObserver(new NYTimes());
        feed.registerObserver(new Guardian());
        feed.registerObserver(new LeMonde());
        feed.notifyObservers("money queen wine test");

        Feed feedLambda = new Feed();

        feedLambda.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        });

        feedLambda.registerObserver(tweet -> {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London ..." + tweet);
            }
        });

        feedLambda.notifyObservers("moeny queen good!!");
    }

    interface Observer {
        void inform(String tweet);
    }

    interface Subject {
        void registerObserver(Observer observer);
        void notifyObservers(String tweet);
    }

    static class NYTimes implements Observer {

        @Override
        public void inform(String tweet) {
            if (tweet != null && tweet.contains("money")) {
                System.out.println("Breaking news in NY! " + tweet);
            }
        }
    }

    static class Guardian implements Observer {

        @Override
        public void inform(String tweet) {
            if (tweet != null && tweet.contains("queen")) {
                System.out.println("Yet another news in London ... " + tweet);
            }
        }
    }

    static class LeMonde implements Observer {

        @Override
        public void inform(String tweet) {
            if (tweet != null && tweet.contains("wine")) {
                System.out.println("Today cheese, wine and news! " + tweet);
            }
        }
    }

    static class Feed implements Subject {
        private final List<Observer> observers = new ArrayList<>();

        @Override
        public void registerObserver(Observer observer) {
            this.observers.add(observer);
        }

        @Override
        public void notifyObservers(String tweet) {
            observers.forEach(o -> o.inform(tweet));
        }
    }
}
