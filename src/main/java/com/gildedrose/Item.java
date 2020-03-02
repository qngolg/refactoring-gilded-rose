package com.gildedrose;

public class Item {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";

    public String name;

    public int sellIn;

    public int quality;

    public Item(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    public void initQuality() {
        if (name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
            calculateQualityWhenLessThen50();
        } else {
            if (name.equals(AGED_BRIE)) {
                calculateQualityWhenLessThen50();
            } else {
                if (quality > 0) {
                    if (!name.equals(HAND_OF_RAGNAROS)) {
                        quality = quality - 1;
                    }
                }
            }
        }
    }

    public void calculateQualityWithSellIn() {
        if (sellIn < 0) {
            if (name.equals(AGED_BRIE)) {
                if (quality < 50) {
                    quality = quality + 1;
                }
            } else {
                if (name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                    quality = 0;
                } else {
                    if (quality > 0) {
                        if (name.equals(HAND_OF_RAGNAROS)) {
                            return;
                        }
                        quality = quality - 1;
                    }
                }
            }
        }
    }

    public void calculateSellIn() {
        if (name.equals(HAND_OF_RAGNAROS)) {
        } else {
            sellIn = sellIn - 1;
        }
    }

    private void calculateQualityWhenLessThen50() {
        if (quality < 50) {
            quality = quality + 1;
            if (name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
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

    private void addQualityWhenLessThen50() {
        if (quality < 50) {
            quality = quality + 1;
        }
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
}
