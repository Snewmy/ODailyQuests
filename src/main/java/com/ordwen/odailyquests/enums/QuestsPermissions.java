package com.ordwen.odailyquests.enums;

public enum QuestsPermissions {

    QUEST_USE("oweeklyquests.use"),
    QUEST_SHOW("oweeklyquests.show"),
    QUEST_REROLL("oweeklyquests.reroll"),
    QUESTS_SHOW_PLAYER("oweeklyquests.player"),
    QUESTS_SHOW_GLOBAL("oweeklyquests.global"),
    QUESTS_SHOW_EASY("oweeklyquests.easy"),
    QUESTS_SHOW_MEDIUM("oweeklyquests.medium"),
    QUESTS_SHOW_HARD("oweeklyquests.hard"),
    QUESTS_ADMIN("oweeklyquests.admin"),
    ;

    private final String permission;

    /**
     * Permission constructor.
     * @param permission permission (String).
     */
    QuestsPermissions(String permission) {
        this.permission = permission;
    }

    /**
     * Get permission.
     * @return permission.
     */
    public String getPermission() {
        return this.permission;
    }
}
