package io.goorm.etdservice.domain.games.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PalworldOptionDto {

//    TZ=UTC
    private Integer PLAYERS = 16;
    private Integer PORT=8211;
    private Integer PUID=1000;
    private Integer PGID=1000;
    private Boolean MULTITHREADING=false;
    private Boolean COMMUNITY=false;
//    PUBLIC_IP=
//    PUBLIC_PORT=
    private String SERVER_NAME="palworld-server-docker by Thijs van Loef";
    private String SERVER_DESCRIPTION="palworld-server-docker by Thijs van Loef";
    private String SERVER_PASSWORD="worldofpals";
    private String ADMIN_PASSWORD="adminPasswordHere";
    private Boolean UPDATE_ON_BOOT=true;
    private Boolean RCON_ENABLED=true;
    private Integer RCON_PORT=25575;
    private Integer QUERY_PORT=27015;
//    BACKUP_ENABLED=true
//    DELETE_OLD_BACKUPS=false
//    OLD_BACKUP_DAYS=30
//    BACKUP_CRON_EXPRESSION=0 0 * * *
//    AUTO_UPDATE_ENABLED=false
//    AUTO_UPDATE_CRON_EXPRESSION=0 * * * *
//    AUTO_UPDATE_WARN_MINUTES=30
//    AUTO_REBOOT_ENABLED=false
//    AUTO_REBOOT_EVEN_IF_PLAYERS_ONLINE=false
//    AUTO_REBOOT_WARN_MINUTES=5
//    AUTO_REBOOT_CRON_EXPRESSION=0 0 * * *
//    ENABLE_PLAYER_LOGGING=true
//    PLAYER_LOGGING_POLL_PERIOD=5

    private String DIFFICULTY="None";
    private Float DAYTIME_SPEEDRATE= 1.000000F;
    private Float NIGHTTIME_SPEEDRATE= 1.000000F;
    private Float EXP_RATE= 1.000000F;
    private Float PAL_CAPTURE_RATE= 1.000000F;
    private Float PAL_SPAWN_NUM_RATE = 1.000000F;
    private Float PAL_DAMAGE_RATE_ATTACK = 1.000000F;
    private Float PAL_DAMAGE_RATE_DEFENSE = 1.000000F;
    private Float PLAYER_DAMAGE_RATE_ATTACK= 1.000000F;
    private Float PLAYER_DAMAGE_RATE_DEFENSE = 1.000000F;
    private Float PLAYER_STOMACH_DECREASE_RATE = 1.000000F;
    private Float PLAYER_STAMINA_DECREASE_RATE = 1.000000F;
    private Float PLAYER_AUTO_HP_REGEN_RATE = 1.000000F;
    private Float PLAYER_AUTO_HP_REGEN_RATE_IN_SLEEP = 1.000000F;
    private Float PAL_STOMACH_DECREASE_RATE = 1.000000F;
    private Float PAL_STAMINA_DECREASE_RATE = 1.000000F;
    private Float PAL_AUTO_HP_REGEN_RATE = 1.000000F;
    private Float PAL_AUTO_HP_REGEN_RATE_IN_SLEEP = 1.000000F;
    private Float BUILD_OBJECT_DAMAGE_RATE = 1.000000F;
    private Float BUILD_OBJECT_DETERIORATION_DAMAGE_RATE = 1.000000F;
    private Float COLLECTION_DROP_RATE = 1.000000F;
    private Float COLLECTION_OBJECT_HP_RATE = 1.000000F;
    private Float COLLECTION_OBJECT_RESPAWN_SPEED_RATE = 1.000000F;
    private Float ENEMY_DROP_ITEM_RATE = 1.000000F;
    private String DEATH_PENALTY= "All";
    private String ENABLE_PLAYER_TO_PLAYER_DAMAGE="False";
    private String ENABLE_FRIENDLY_FIRE="False";
    private String ENABLE_INVADER_ENEMY="True";
    private String ACTIVE_UNKO = "False";
    private String ENABLE_AIM_ASSIST_PAD="True";
    private String ENABLE_AIM_ASSIST_KEYBOARD="False";
    private Integer DROP_ITEM_MAX_NUM = 3000;
    private Integer DROP_ITEM_MAX_NUM_UNKO = 100;
    private Integer BASE_CAMP_MAX_NUM = 128;
    private Integer BASE_CAMP_WORKER_MAX_NUM = 15;
    private Float DROP_ITEM_ALIVE_MAX_HOURS = 1.000000F;
    private String AUTO_RESET_GUILD_NO_ONLINE_PLAYERS="False";
    private Float AUTO_RESET_GUILD_TIME_NO_ONLINE_PLAYERS= 72.00000F;
    private Integer GUILD_PLAYER_MAX_NUM = 20;
    private Float PAL_EGG_DEFAULT_HATCHING_TIME = 72.00000F;
    private Float WORK_SPEED_RATE= 1.000000F;
    private String IS_MULTIPLAY="False";
    private String IS_PVP = "False";
    private String CAN_PICKUP_OTHER_GUILD_DEATH_PENALTY_DROP = "False";
    private String ENABLE_NON_LOGIN_PENALTY ="True";
    private String ENABLE_FAST_TRAVEL ="True";
    private String IS_START_LOCATION_SELECT_BY_MAP ="True";
    private String EXIST_PLAYER_AFTER_LOGOUT = "False";
    private String ENABLE_DEFENSE_OTHER_GUILD_PLAYER = "False";
    private Integer COOP_PLAYER_MAX_NUM=4;
    private String REGION="";
    private String USEAUTH = "True";
    private String BAN_LIST_URL="https://api.palworldgame.com/api/banlist.txt";
    private String SHOW_PLAYER_LIST = "True";


}
