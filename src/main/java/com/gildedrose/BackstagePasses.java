package com.gildedrose;

public class BackstagePasses extends Item {
    public BackstagePasses(int sellIn, int quality) {
        super("Backstage passes to a TAFKAL80ETC concert", sellIn, quality);
    }

    @Override
    public void initQuality() {
        calculateQualityWhenLessThen50();
    }

    @Override
    public void calculateSellIn() {
        sellIn -= 1;
    }

    @Override
    public void calculateQualityWithSellIn() {
        if (sellIn < 0) {
            quality = 0;
        }
    }

    @Override
    protected void calculateQualityWhenLessThen50() {
        if (quality < 50) {
            quality = quality + 1;
            if (sellIn < 11) {
                addQualityWhenLessThen50();
            }

            if (sellIn < 6) {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }
        }
    }
}
