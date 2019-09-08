package com.thesmeg.bots.fleshcrawler.playersense;

import com.runemate.game.api.hybrid.player_sense.PlayerSense;
import com.runemate.game.api.hybrid.util.calculations.Random;

import java.util.function.Supplier;

public class CustomPlayerSense {
    public static void initializeKeys() {
        for (Key key : Key.values()) {
            if (PlayerSense.get(key.name) == null) {
                PlayerSense.put(key.name, key.supplier.get());
            }
        }
    }

    public enum Key {
        SPAM_CLICK_COUNT("thesmeg_spam_click_count", () -> Random.nextInt(1, 4)),
        MIN_RUN_ENERGY("thesmeg_min_run_energy", () -> Random.nextInt(1, 41)),
        MIN_HP("thesmeg_min_hp", () -> Random.nextInt(25, 40)),
        EXECUTION_DELAY_MAX("thesmeg_execution_delay_max", () -> Random.nextInt(1200, 2000)),
        EXECUTION_DELAY_MIN("thesmeg_execution_delay_max", () -> Random.nextInt(500, 800)),
        USE_DEPOSIT_ALL("thesmeg_use_deposit_all", () -> Random.nextBoolean());

        private final String name;
        private final Supplier supplier;

        Key(String name, Supplier supplier) {
            this.name = name;
            this.supplier = supplier;
        }

        public String getKey() {
            return name;
        }

        public Integer getAsInteger() {
            return PlayerSense.getAsInteger(name);
        }

        public Double getAsDouble() {
            return PlayerSense.getAsDouble(name);
        }

        public Long getAsLong() {
            return PlayerSense.getAsLong(name);
        }

        public Boolean getAsBoolean() {
            return PlayerSense.getAsBoolean(name);
        }
    }
}
