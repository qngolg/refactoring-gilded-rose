package com.gildedrose;

public class AgedBrie extends Item {

    public AgedBrie(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    public void initQuality() {
        super.calculateQualityWhenLessThen50();
    }

    @Override
    public void calculateSellIn() {
        sellIn = sellIn - 1;
    }

    @Override
    public void calculateQualityWithSellIn() {
        if (sellIn < 0) {
            if (quality < 50) {
                quality = quality + 1;
            }
        }
    }
}
