package com.keunwon.java8.chap08;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class ChainOfResponsibilityMain {

    public static void main(String ... args) {
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();

        p1.setSuccessor(p2);

        String result = p1.handle("input test labda");
        System.out.println(result);

        UnaryOperator<String> headerProcessing = (String s) -> "From Raoul, Mario and Alan : " + s;
        UnaryOperator<String> spellCheckerProcessing = (String s) -> s.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        System.out.println(pipeline.apply("input test labda"));
    }

    static private abstract class ProcessingObject<T> {
        protected  ProcessingObject<T> successor;

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);
            if (successor != null) {
                return successor.handle(r);
            }
            return r;
        }

        abstract protected T handleWork(T input);
    }

    static public class HeaderTextProcessing extends ProcessingObject<String> {

        @Override
        protected String handleWork(String input) {
            return "From Raoul, Mario and Alan : " + input;
        }
    }

    static class SpellCheckerProcessing extends ProcessingObject<String> {

        @Override
        protected String handleWork(String input) {
            return input.replaceAll("labda", "lambda");
        }
    }
}
