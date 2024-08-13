package net.tefyert.techatech.api.block.cable;

import net.minecraft.util.StringRepresentable;

import javax.annotation.Nonnull;

public enum ConnectorType implements StringRepresentable {
    NONE,
    CABLE,
    BLOCK;

    public static final ConnectorType[] VALUES = values();

    @Override
    @Nonnull
    public String getSerializedName() {
        return name().toLowerCase();
    }
}
