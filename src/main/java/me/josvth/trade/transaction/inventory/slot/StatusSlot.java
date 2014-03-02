package me.josvth.trade.transaction.inventory.slot;

import me.josvth.trade.Trade;
import me.josvth.trade.tasks.SlotUpdateTask;
import me.josvth.trade.transaction.inventory.TransactionHolder;
import me.josvth.trade.util.ItemStackUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.Set;

public class StatusSlot extends Slot{

	private ItemStack consideringItem;
	private ItemStack acceptedItem;

	public StatusSlot(int slot, TransactionHolder holder) {
        super(slot, holder);
    }

    public ItemStack getConsideringItem() {
        return consideringItem;
    }

    public void setConsideringItem(ItemStack consideringItem) {
        this.consideringItem = consideringItem;
    }

    public ItemStack getAcceptedItem() {
        return acceptedItem;
    }

    public void setAcceptedItem(ItemStack acceptedItem) {
        this.acceptedItem = acceptedItem;
    }

    @Override
	public void update(TransactionHolder holder) {
		if (holder.getOtherTrader().hasAccepted()) {
			setItem(holder, ItemStackUtils.argument(acceptedItem.clone(), "%player%", holder.getOtherTrader().getName()));
		} else {
			setItem(holder, ItemStackUtils.argument(consideringItem.clone(), "%player%", holder.getOtherTrader().getName()));
		}
	}

	public static void updateStatusSlots(TransactionHolder holder, boolean nextTick) {

		final Set<StatusSlot> slots = holder.getSlotsOfType(StatusSlot.class);

		if (!nextTick) {
			for (Slot slot : slots) {
				slot.update(holder);
			}
		} else if (!slots.isEmpty()) {
			Bukkit.getScheduler().runTask(Trade.getInstance(), new SlotUpdateTask(holder, slots));
		}

	}

    public static StatusSlot deserialize(int slotID, TransactionHolder holder, SlotDescription description) {
        final StatusSlot slot = new StatusSlot(slotID, holder);
        slot.setAcceptedItem(ItemStackUtils.fromSection(description.getConfiguration().getConfigurationSection("accepted-item"), Trade.getInstance().getMessageManager()));
        slot.setConsideringItem(ItemStackUtils.fromSection(description.getConfiguration().getConfigurationSection("considering-item"), Trade.getInstance().getMessageManager()));
        return slot;
    }

}
