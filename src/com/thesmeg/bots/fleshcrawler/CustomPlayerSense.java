package com.thesmeg.bots.fleshcrawler;

import com.runemate.game.api.hybrid.player_sense.PlayerSense;
import com.runemate.game.api.hybrid.util.calculations.Random;

import java.util.function.Supplier;

public class CustomPlayerSense {
    public enum Key {
        SPAM_CLICK_COUNT("thesmeg_spam_click_count_low", () -> Random.nextInt(1, 4)),
        MIN_RUN_ENERGY("thesmeg_min_run_energy", () -> Random.nextInt(1, 41)),
        MIN_HP("thesmeg_min_hp", () -> Random.nextInt(25, 40)),
        EXECUTION_DELAY_MAX("thesmeg_execution_delay_max", () -> Random.nextInt(1000, 2500));
//        CAMERA_PITCH("thesmeg_camera_pitch", () -> Random.nextDouble(0.5, 1.0)),
//        CAMERA_YAW("thesmeg_camera_yaw", () -> Random.nextInt(0,360));

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

    public static void initializeKeys() {
        for (Key key : Key.values()) {
            if (PlayerSense.get(key.name) == null) {
                PlayerSense.put(key.name, key.supplier.get());
            }
        }
    }
}
