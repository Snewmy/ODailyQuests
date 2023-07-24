package com.ordwen.odailyquests.quests.player.progression.checkers;

import com.ordwen.odailyquests.configuration.essentials.Synchronization;
import com.ordwen.odailyquests.configuration.functionalities.DisabledWorlds;
import com.ordwen.odailyquests.quests.types.GlobalQuest;
import com.ordwen.odailyquests.quests.player.progression.AbstractProgressionIncreaser;
import com.ordwen.odailyquests.quests.types.AbstractQuest;
import com.ordwen.odailyquests.quests.types.ItemQuest;
import com.ordwen.odailyquests.quests.QuestType;
import com.ordwen.odailyquests.quests.player.QuestsManager;
import com.ordwen.odailyquests.quests.player.progression.Progression;
import com.ordwen.odailyquests.tools.PluginLogger;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public abstract class AbstractItemChecker extends AbstractProgressionIncreaser {

    /**
     * Increase player quest progression.
     *
     * @param player    the player to increase progression for.
     * @param itemStack the item to increase progression for.
     * @param amount    the amount to increase progression by.
     * @param questType the quest type to increase progression for.
     */
    public void setPlayerQuestProgression(Player player, ItemStack itemStack, int amount, QuestType questType, String id) {

        //PluginLogger.warn("setPlayerQuestProgression called for " + player.getName() + " with item " + itemStack.getType().name() + " and amount " + amount + " and quest type " + questType.name() + " and id " + id);

        if (DisabledWorlds.isWorldDisabled(player.getWorld().getName())) {

            //PluginLogger.warn("World " + player.getWorld().getName() + " is disabled.");

            return;
        }

        if (QuestsManager.getActiveQuests().containsKey(player.getName())) {

            final HashMap<AbstractQuest, Progression> playerQuests = QuestsManager.getActiveQuests().get(player.getName()).getPlayerQuests();

            for (AbstractQuest abstractQuest : playerQuests.keySet()) {

                //PluginLogger.warn("Checking quest " + abstractQuest.getQuestName());

                final Progression progression = playerQuests.get(abstractQuest);
                if (!progression.isAchieved() && abstractQuest.getQuestType() == questType) {

                    //PluginLogger.warn("Quest " + abstractQuest.getQuestName() + " is not achieved and has the right quest type.");

                    boolean isRequiredItem = false;

                    if (abstractQuest instanceof GlobalQuest) {
                        isRequiredItem = true;
                    } else if (abstractQuest instanceof ItemQuest quest) {
                        if (quest.getRequiredItems() == null) isRequiredItem = true;
                        else {
                            for (ItemStack item : quest.getRequiredItems()) {
                                if (item.isSimilar(itemStack)) {
                                    isRequiredItem = true;
                                    break;
                                }
                            }
                        }
                    }

                    if (isRequiredItem) {

                        //PluginLogger.warn("Quest " + abstractQuest.getQuestName() + " can be progressed with this item.");

                        increaseProgression(player, progression, abstractQuest, amount);
                        if (!Synchronization.isSynchronised()) {
                            break;
                        }
                    }
                }
            }
        }
    }
}
