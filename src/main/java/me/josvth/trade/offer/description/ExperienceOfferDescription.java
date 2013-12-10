package me.josvth.trade.offer.description;

import me.josvth.bukkitformatlibrary.FormattedMessage;
import me.josvth.trade.offer.ExperienceOffer;
import me.josvth.trade.offer.OfferList;
import me.josvth.trade.transaction.inventory.TransactionHolder;
import me.josvth.trade.util.ItemStackUtils;
import org.bukkit.inventory.ItemStack;

public class ExperienceOfferDescription extends OfferDescription<ExperienceOffer> {

    private ItemStack experienceItem;
    private ItemStack experienceItemMirror;

    private int smallModifier;
    private int largeModifier;

    private FormattedMessage addMessage;
    private FormattedMessage removeMessage;
    private FormattedMessage insufficientMessage;

    @Override
    public ItemStack createItem(ExperienceOffer offer) {
        final ItemStack itemStack;
        if (experienceItem != null) {
            itemStack = experienceItem.clone();
            itemStack.setAmount(offer.getLevels());
        } else {
            itemStack = null;
        }
        return ItemStackUtils.argument(itemStack, "%levels%", String.valueOf(offer.getLevels()), "%small%", String.valueOf(smallModifier), "%large%", String.valueOf(largeModifier));
    }

    @Override
    public ItemStack createMirrorItem(ExperienceOffer offer, TransactionHolder holder) {
        final ItemStack itemStack;
        if (experienceItemMirror != null) {
            itemStack = experienceItemMirror.clone();
            itemStack.setAmount(offer.getLevels());
        } else {
            itemStack = null;
        }
        return ItemStackUtils.argument(itemStack, "%player%", holder.getOtherTrader().getName(), "%levels%", String.valueOf(offer.getLevels()));
    }

    @Override
    public ExperienceOffer createOffer(OfferList list, int offerIndex) {
        return null;
    }

    @Override
    public Class<ExperienceOffer> getOfferClass() {
        return ExperienceOffer.class;
    }

    public ItemStack getExperienceItem() {
        return experienceItem;
    }

    public void setExperienceItem(ItemStack experienceItem) {
        this.experienceItem = experienceItem;
    }

    public ItemStack getExperienceItemMirror() {
        return experienceItemMirror;
    }

    public void setExperienceItemMirror(ItemStack experienceItemMirror) {
        this.experienceItemMirror = experienceItemMirror;
    }

    public int getSmallModifier() {
        return smallModifier;
    }

    public void setSmallModifier(int smallModifier) {
        this.smallModifier = smallModifier;
    }

    public int getLargeModifier() {
        return largeModifier;
    }

    public void setLargeModifier(int largeModifier) {
        this.largeModifier = largeModifier;
    }

    public FormattedMessage getAddMessage() {
        return addMessage;
    }

    public void setAddMessage(FormattedMessage addMessage) {
        this.addMessage = addMessage;
    }

    public FormattedMessage getRemoveMessage() {
        return removeMessage;
    }

    public void setRemoveMessage(FormattedMessage removeMessage) {
        this.removeMessage = removeMessage;
    }

    public FormattedMessage getInsufficientMessage() {
        return insufficientMessage;
    }

    public void setInsufficientMessage(FormattedMessage insufficientMessage) {
        this.insufficientMessage = insufficientMessage;
    }
}
