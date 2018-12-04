package controller;

import item.Item;

import java.util.Random;

public class PriceCrawler {
    private Item item;
    private double min = 5.99;
    private double max = 24.99;

    public double randomPrice(){
        Random random = new Random();
        return min +(max-min)*random.nextDouble();
    }


}