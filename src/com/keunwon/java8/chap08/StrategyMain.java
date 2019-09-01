package com.keunwon.java8.chap08;

public class StrategyMain {

    public static void main(String ... args) {
        Validator v1 = new Validator(new IsNumeric());
        System.out.println(v1.validate("aaaa"));
        Validator v2 = new Validator(new IsAllLowerCase ());
        System.out.println(v2.validate("bbbb"));


        Validator v3 = new Validator((s) -> s.matches("[a-z]+"));
        System.out.println(v3.validate("bbb"));

        Validator v4 = new Validator((s) -> s.matches("\\d+"));
        System.out.println(v4.validate("aaa"));
    }


    public interface ValidationStrategy {
        boolean execute(String s);
    }

    static public class IsAllLowerCase implements ValidationStrategy {

        @Override
        public boolean execute(String s) {
            return s.matches("[a-z]+");
        }
    }

    static public class IsNumeric implements ValidationStrategy {

        @Override
        public boolean execute(String s) {
            return s.matches("\\d+");
        }
    }

    static public class Validator {
        private final ValidationStrategy strategy;

        public Validator(ValidationStrategy strategy) {
            this.strategy = strategy;
        }

        public boolean validate(String s) {
            return strategy.execute(s);
        }
    }
}
