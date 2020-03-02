package com.gildedrose;

public class Item {

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public void initQuality() {
        if (quality > 0) {
            quality = quality - 1;
        }
    }

    public void calculateQualityWithSellIn() {
        if (sellIn < 0) {
            if (quality > 0) {
                quality = quality - 1;
            }
        }
    }

    public void calculateSellIn() {
        sellIn = sellIn - 1;
    }

    protected void calculateQualityWhenLessThen50() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    protected void addQualityWhenLessThen50() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
