package com.gildedrose;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void should_update_quality_when_given_a_foo_item() {
        Item[] items = new Item[]{new Item("foo", 1, 5)};
        GildedRose app = new GildedRose(items);
        app.update_quality();
        assertEquals("should_update_quality_when_given_items", app.items[0].name);
        assertThat(app.items[0].quality, is(4));
        assertThat(app.items[0].sellIn, is(0));
    }

    @Test
    public void should_update_quality_when_given_items_and_days() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 1, 20),
                new Item("Conjured Mana Cake", 3, 6)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.update_quality();

        Item[] oneDayResult = new Item[]{
                new Item("+5 Dexterity Vest", 9, 19), //
                new Item("Aged Brie", 1, 1), //
                new Item("Elixir of the Mongoose", 4, 6), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 14, 21),
                new Item("Backstage passes to a TAFKAL80ETC concert", 9, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 4, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 23),
                new Item("Conjured Mana Cake", 2, 5)};
        assertTrue(assertItems(oneDayResult, items));

        Item[] twoDayResult = new Item[]{
                new Item("+5 Dexterity Vest", 8, 18), //
                new Item("Aged Brie", 0, 2), //
                new Item("Elixir of the Mongoose", 3, 5), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 13, 22),
                new Item("Backstage passes to a TAFKAL80ETC concert", 8, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", 3, 50),
                new Item("Backstage passes to a TAFKAL80ETC concert", -1, 0),
                new Item("Conjured Mana Cake", 1, 4)};
        gildedRose.update_quality();
        assertTrue(assertItems(twoDayResult, items));
    }

    private boolean assertItems(Item[] results, Item[] items) {
        for (int i = 0; i < results.length; i++)
            if (!assertItem(results[i], items[i])) {
                return false;
            }
        return true;
    }

    private boolean assertItem(Item result, Item item) {
        return result.name.equals(item.name)
                && result.sellIn == item.sellIn
                && result.quality == item.quality;
    }
}
