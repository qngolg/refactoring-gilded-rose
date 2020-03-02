package com.gildedrose;

class GildedRose {
    private static final String AGED_BRIE = "Aged Brie";
    private static final String HAND_OF_RAGNAROS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT = "Backstage passes to a TAFKAL80ETC concert";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            initQuality(item);

            calculateSellIn(item);

            calculateQualityWithSellIn(item);
        }
    }

    private void calculateQualityWithSellIn(Item item) {
        if (item.sellIn < 0) {
            if (item.name.equals(AGED_BRIE)) {
                if (item.quality < 50) {
                    item.quality = item.quality + 1;
                }
            } else {
                if (item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                    item.quality = 0;
                } else {
                    if (item.quality > 0) {
                        if (item.name.equals(HAND_OF_RAGNAROS)) {
                            return;
                        }
                        item.quality = item.quality - 1;
                    }
                }
            }
        }
    }

    private void calculateSellIn(Item item) {
        if (item.name.equals(HAND_OF_RAGNAROS)) {
        } else {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void initQuality(Item item) {
        if (item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
            calculateQualityWhenLessThen50(item);
        } else {
            if (item.name.equals(AGED_BRIE)) {
                calculateQualityWhenLessThen50(item);
            } else {
                if (item.quality > 0) {
                    if (!item.name.equals(HAND_OF_RAGNAROS)) {
                        item.quality = item.quality - 1;
                    }
                }
            }
        }
    }

    private void calculateQualityWhenLessThen50(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;

            if (item.name.equals(BACKSTAGE_PASSES_TO_A_TAFKAL_80_ETC_CONCERT)) {
                if (item.sellIn < 11) {
                    addQualityWhenLessThen50(item);
                }

                if (item.sellIn < 6) {
                    if (item.quality < 50) {
                        item.quality = item.quality + 1;
                    }
                }
            }
        }
    }

    private void addQualityWhenLessThen50(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }
}
