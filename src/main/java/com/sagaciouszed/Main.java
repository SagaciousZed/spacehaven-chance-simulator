package com.sagaciouszed;

import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        EnumMap<Item, AtomicInteger> counts = new EnumMap<>(Item.class);
        for (Item item : Item.values()) {
            counts.put(item, new AtomicInteger(0));
        }
        System.out.println(counts);
        var iterations = 1_000_000_000;
        for (int i = 0; i < iterations; i++) {
            counts.get(roll()).incrementAndGet();
        }
        counts.forEach((key, value) -> {
            var s = key.toString();
            var count = value.get();
            System.out.println(s + ": " + ((double) count / (double) iterations));
        });
        System.out.println("Iterations: " + iterations);
    }

    public static Item roll() {
        float pistolChance = 0.07f;
        float rifleChance = 0.05f;
        float attachmentChance = 0.09f;
        float armor = 0.05f;
        float itemChance = 0.25f;
        float emptyChance = 1.0f - pistolChance - rifleChance - attachmentChance - itemChance - armor;

        //Some other code
        int rolls = 5;
        Item i = Item.NULL;

        boolean empty = Game.random.nextFloat() < emptyChance;

        if (!empty) {
            while (--rolls > 0) {
                if (Game.random.nextFloat() < itemChance) {
                    i = Item.ITEM;
                    break;
                }
                if (Game.random.nextFloat() < attachmentChance) {
                    i = Item.ATTACHMENT;
                    break;
                }
                if (Game.random.nextFloat() < pistolChance) {
                    i = Item.PISTOL;
                    break;
                }
                if (Game.random.nextFloat() < rifleChance) {
                    i = Item.RIFLE;
                    break;
                }
                if (Game.random.nextFloat() < armor) {
                    i = Item.ARMOR;
                    break;
                }
            }
        }
        return i;
    }
}
