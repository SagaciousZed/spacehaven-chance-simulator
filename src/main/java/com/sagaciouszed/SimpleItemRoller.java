package com.sagaciouszed;

import java.util.LinkedHashMap;
import java.util.Map;

public class SimpleItemRoller {

    static Map<Item, Float> chances;

    static {
        chances = new LinkedHashMap<>();
        chances.put(Item.PISTOL, 0.07f);
        chances.put(Item.RIFLE, 0.05f);
        chances.put(Item.ATTACHMENT, 0.09f);
        chances.put(Item.ARMOR, 0.05f);
        chances.put(Item.ITEM, 0.25f);
    }

    public static Item roll() {
        Item i = Item.NULL;
        float roll = Game.random.nextFloat();

        for (var entry : chances.entrySet()) {
            if (roll < entry.getValue()) {
                i = entry.getKey();
                break;
            } else {
                roll = roll - entry.getValue();
            }
        }

        return i;
    }
}

