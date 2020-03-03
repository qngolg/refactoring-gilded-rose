package com.gildedrose;

import java.util.stream.Stream;

class GildedRose {

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Stream.of(items)
                .peek(Item::initQuality)
                .peek(Item::calculateSellIn)
                .forEach(Item::calculateQualityWithSellIn);
    }


}
