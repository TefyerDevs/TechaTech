package net.tefyert.techatech.api.utils;

import net.minecraft.util.RandomSource;


import static net.minecraft.ChatFormatting.*;

public class Value {
    public static final RandomSource RNG = RandomSource.createThreadSafe();


    public static final int LV = 1;
    public static final int MV = 2;
    public static final int HV = 3;
    public static final int EV = 4;
    public static final int IV = 5;
    public static final int LuV = 6;
    public static final int ZPM = 7;
    public static final int UV = 8;
    public static final int UHV = 9;
    public static final int UEV = 10;
    public static final int UIV = 11;
    public static final int UXV = 12;
    public static final int OpV = 13;
    public static final int MAX = 14;

    public static final int[] ALL_TIERS = new int[] { LV, MV, HV, EV, IV, LuV, ZPM, UV, UHV, UEV, UIV, UXV, OpV,
            MAX };
    public static final int TIER_COUNT = ALL_TIERS.length;


    public static final String[] VNF = new String[] {
            GRAY + "LV",
            AQUA + "MV",
            GOLD + "HV",
            DARK_PURPLE + "EV",
            BLUE + "IV",
            LIGHT_PURPLE + "LuV",
            RED + "ZPM",
            DARK_AQUA + "UV",
            DARK_RED + "UHV",
            GREEN + "UEV",
            DARK_GREEN + "UIV",
            YELLOW + "UXV",
            BLUE.toString() + BOLD + "OpV",
            RED.toString() + BOLD + "MAX" };

    public static final String[] VLVH = new String[] {
            "Basic",
            AQUA + "Advanced",
            GOLD + "Advanced",
            DARK_PURPLE + "Advanced",
            BLUE + "Elite",
            LIGHT_PURPLE + "Elite",
            RED + "Elite",
            DARK_AQUA + "Ultimate",
            DARK_RED + "Epic",
            GREEN + "Epic",
            DARK_GREEN + "Epic",
            YELLOW + "Epic",
            BLUE.toString() + BOLD + "Legendary",
            RED.toString() + BOLD + "MAX" };

    public static final String[] VLVT = new String[] {
            "" + RESET,
            "" + RESET,
            "II" + RESET,
            "III" + RESET,
            "" + RESET,
            "II" + RESET,
            "III" + RESET,
            "" + RESET,
            "" + RESET,
            "II" + RESET,
            "III" + RESET,
            "IV" + RESET,
            "" + RESET,
            "" + RESET };

    public static final String[] LVT = new String[] {
            "I",
            "II",
            "III",
            "IV",
            "V",
            "VI",
            "VII",
            "VIII",
            "IX",
            "X",
            "XI",
            "XII",
            "XIII",
            "XIV",
    };

    /**
     * Color values for the voltages
     */
    public static final int[] VC = new int[] { 0xDCDCDC, 0xFF6400, 0xFFFF1E, 0x808080, 0xF0F0F5, 0xE99797,
            0x7EC3C4, 0x7EB07E, 0xBF74C0, 0x0B5CFE, 0x914E91, 0x488748, 0x8C0000, 0x2828F5 };

    /**
     * The long names for the voltages
     */
    public static final String[] VOLTAGE_NAMES = new String[] { "Low Voltage", "Medium Voltage",
            "High Voltage", "Extreme Voltage", "Insane Voltage", "Ludicrous Voltage", "ZPM Voltage", "Ultimate Voltage",
            "Ultra High Voltage", "Ultra Excessive Voltage", "Ultra Immense Voltage", "Ultra Extreme Voltage",
            "Overpowered Voltage", "Maximum Voltage" };

}
