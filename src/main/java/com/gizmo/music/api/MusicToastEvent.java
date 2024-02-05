package com.gizmo.music.api;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

public class MusicToastEvent extends Event implements ICancellableEvent {
    private Component text;
    private ItemStack icon;

    public MusicToastEvent(Component text, ItemStack icon) {
        this.text = text;
        this.icon = icon;
    }

    public Component getText() {
        return text;
    }

    public void setText(Component text) {
        this.text = text;
    }

    public ItemStack getIcon() {
        return icon;
    }

    public void setIcon(ItemStack icon) {
        this.icon = icon;
    }
}
