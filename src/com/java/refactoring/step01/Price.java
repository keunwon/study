package com.java.refactoring.step01;

abstract class Price {
    abstract int getPriceCode();
    abstract double getCharge(int daysRented);
    abstract int  getFrequentRenterPoints(int dayRented);
}

class ChildrensPrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.CHILDREND;
    }

    @Override
    double getCharge(int daysRented) {
        double result =  1.5;
        if (daysRented > 3) {
            result += (daysRented - 3) * 1.5;
        }
        return result;
    }

    @Override
    int getFrequentRenterPoints(int dayRented) {
        return 1;
    }

}

class NewReleasePrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(int dayRented) {
        return (dayRented > 1) ? 2: 1;
    }
}

class RegularPrice extends Price {

    @Override
    int getPriceCode() {
        return Movie.REGULAR;
    }

    @Override
    double getCharge(int daysRented) {
        double result  = 2;
        if (daysRented > 2) {
            result += (daysRented - 2) * 1.5;
        }
        return result;
    }

    @Override
    int getFrequentRenterPoints(int dayRented) {
        return 1;
    }
}