package com.gildedrose;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void should_update_quality_when_given_a_foo_item() {
        Item[] items = new Item[]{new Item("foo", 1, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
        assertThat(app.items[0].quality, is(4));
        assertThat(app.items[0].sellIn, is(0));
    }

    @Test
    public void should_update_quality_when_given_items_and_days() {
        Item[] items = new Item[]{
                new Item("+5 Dexterity Vest", 10, 20), //
                new AgedBrie(2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new BackstagePasses(15, 20),
                new BackstagePasses(10, 49),
                new BackstagePasses(5, 49),
                new BackstagePasses(1, 20),
                new Item("Conjured Mana Cake", 3, 6)};
        GildedRose gildedRose = new GildedRose(items);
        gildedRose.updateQuality();

        Item[] oneDayResult = new Item[]{
                new Item("+5 Dexterity Vest", 9, 19), //
                new AgedBrie(1, 1), //
                new Item("Elixir of the Mongoose", 4, 6), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new BackstagePasses(14, 21),
                new BackstagePasses(9, 50),
                new BackstagePasses(4, 50),
                new BackstagePasses(0, 23),
                new Item("Conjured Mana Cake", 2, 5)};
        assertTrue(assertItems(oneDayResult, items));

        Item[] twoDayResult = new Item[]{
                new Item("+5 Dexterity Vest", 8, 18), //
                new AgedBrie(0, 2), //
                new Item("Elixir of the Mongoose", 3, 5), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new BackstagePasses(13, 22),
                new BackstagePasses(8, 50),
                new BackstagePasses(3, 50),
                new BackstagePasses(-1, 0),
                new Item("Conjured Mana Cake", 1, 4)};
        gildedRose.updateQuality();
        assertTrue(assertItems(twoDayResult, items));
    }

    @Test
    public void should_update_version_when_give_a_dexterity_Vest() {
        Item dexterityVest = new Item("+5 Dexterity Vest", 9, 19);

        GildedRose gildedRose = new GildedRose(new Item[]{dexterityVest});
        gildedRose.updateQuality();

        assertEquals(8, dexterityVest.sellIn);
        assertEquals(18, dexterityVest.quality);
    }

    @Test
    public void should_update_version_when_give_a_aged_brie() {
        Item agedBrie = new AgedBrie(1, 1);

        GildedRose gildedRose = new GildedRose(new Item[]{agedBrie});
        gildedRose.updateQuality();

        assertEquals(0, agedBrie.sellIn);
        assertEquals(2, agedBrie.quality);
    }

    @Test
    public void should_update_version_when_given_a_backstage_passes() {
        Item backstagePasses = new BackstagePasses(14, 21);

        GildedRose gildedRose = new GildedRose(new Item[]{backstagePasses});
        gildedRose.updateQuality();

        assertEquals(13, backstagePasses.sellIn);
        assertEquals(22, backstagePasses.quality);
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
