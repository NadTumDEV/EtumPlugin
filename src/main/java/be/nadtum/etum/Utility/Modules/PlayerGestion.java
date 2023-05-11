package be.nadtum.etum.Utility.Modules;

import be.nadtum.etum.Vanilla.City.Fonctionnalité.Claim;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.UUID;

public class PlayerGestion {


    public static void createNewProfil(Player player){

        if(PlayerGestion.hasData(player.getName())){
            return;
        }
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".name", player.getName());
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".grade", "Akien");
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".money", 500);
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".mana", 0);
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".soul", 0);
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".job.name", "nojob");
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".job.niveau", 1);
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".job.xp", 0);
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".home.nb", 2);
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".claim", 200);
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".city", "NoCity");
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".staff.grade", "NoStaff");
        FichierGestion.getCfgPlayers().set("Profil." + PlayerGestion.get_uuid_from_name(player.getName()).toString() + ".staff.puissance", 0);

        player.sendMessage(PrefixMessage.serveur() + "votre profil a bien été créé");

        File fb = new File("plugins//fichiers//modération//" + player.getUniqueId() + ".yml");
        FichierGestion.createNewFile(fb);
    }



    //data profil
    public static String getPlayerName(String player){
        return FichierGestion.getCfgPlayers().getString("Profil." + get_uuid_from_name(player).toString() + ".name");
    }
    public static void setPlayerName(String playerName){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".name", playerName);
    }

    public static String getPlayerGrade(String playerName){
        return FichierGestion.getCfgPlayers().getString("Profil." + get_uuid_from_name(playerName).toString() + ".grade");
    }
    public static void setPlayerGrade(String playerName, String grade){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".grade", grade);
    }

    public static Integer getPlayerMana(String playerName){
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".mana");
    }
    public static void setPlayerMana(String playerName, Integer mana){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".mana", mana);
    }

    public static Long getPlayerMoney(String playerName){
        return FichierGestion.getCfgPlayers().getLong("Profil." + get_uuid_from_name(playerName).toString() + ".money");
    }
    public static void setPlayerMoney(String playerName, Long somme){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".money", somme);
    }

    public static String getPlayerJobName(String playerName){
        return FichierGestion.getCfgPlayers().getString("Profil." + get_uuid_from_name(playerName).toString() + ".job.name");
    }
    public static void setPlayerJobName(String playerName, String jobName){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".job.name", jobName);
    }

    public static Integer getPlayerJobNiveau(String playerName){
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".job.niveau");
    }
    public static void setPlayerJobNiveau(String playerName, Integer niveau){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".job.niveau", niveau);
    }

    public static Integer getPlayerJobXp(String playerName){
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".job.xp");
    }
    public static void setPlayerJobXp(String playerName, Integer xp){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".job.xp", xp);
    }

    public static Integer getPlayerHomeCount(String playerName){
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".Home.nb");
    }
    public static void setPlayerHomeCount(String playerName, Integer count){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".Home.nb", count);
    }

    public static String getPlayerCityName(String playerName){
        return FichierGestion.getCfgPlayers().getString("Profil." + get_uuid_from_name(playerName).toString() + ".city");
    }
    public static void setPlayerCityName(String playerName, String city){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".city", city);
    }

    public static Integer getPlayerClaimCount(String playerName){
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".claim");
    }
    public static void setPlayerClaimCount(String playerName, Integer claim){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".claim", claim);
    }

    public static String getPlayerStaffGrade(String playerName){
        return FichierGestion.getCfgPlayers().getString("Profil." + get_uuid_from_name(playerName).toString() + ".staff.grade");
    }
    public static void setPlayerStaffGrade(String playerName, String gradeStaff){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".staff.grade", gradeStaff);
    }

    public static Integer getPlayerStaffPuissance(String playerName){
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".staff.puissance");
    }
    public static void setPlayerStaffPuissance(String playerName, Integer puissance){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".staff.puissance", puissance);
    }

    //compétence métier
    public static String getPlayerJobsVoie(String playerName){
        if(!FichierGestion.getCfgPlayers().contains("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.voie")){
            return "pas de voie";
        }
        return FichierGestion.getCfgPlayers().getString("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.voie");
    }

    public static void setPlayerJobsVoie(String playerName, String voie){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.voie", voie);
    }

    public static Integer getPlayerJobsComp1(String playerName){
        if(!FichierGestion.getCfgPlayers().contains("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Un")){
            return null;
        }
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Un");
    }

    public static void setPlayerJobsComp1(String playerName, Integer nb){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Un", nb);
    }

    public static Integer getPlayerJobsCompDeux(String playerName){
        if(!FichierGestion.getCfgPlayers().contains("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Deux")){
            return null;
        }
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Deux");
    }

    public static void setPlayerJobsComp2(String playerName, Integer nb){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Deux", nb);
    }

    public static Integer getPlayerJobsComp3(String playerName){
        if(!FichierGestion.getCfgPlayers().contains("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Trois")){
            return null;
        }
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Trois");
    }

    public static void setPlayerJobsComp3(String playerName, Integer nb){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Trois", nb);
    }

    public static Integer getPlayerJobsTrancendance(String playerName){
        if(!FichierGestion.getCfgPlayers().contains("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Trancendance")){
            return null;
        }
        return FichierGestion.getCfgPlayers().getInt("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Trancendance");
    }

    public static void setPlayerJobsTrancendance(String playerName, Integer nb){
        FichierGestion.getCfgPlayers().set("Profil." + get_uuid_from_name(playerName).toString() + ".compétence.métier.Trancendance", nb);
    }




    //fonction :
    public static Boolean isPlayer(String player){
        return Bukkit.getPlayer(player) instanceof Player;
    }

    public static String getGradeDesign(String grade){
        return FichierGestion.getCfgPermission().getString("Grade."+ grade + ".design");
    }

    public static Boolean hasData(String playerName){
        return FichierGestion.getCfgPlayers().contains("Profil." + get_uuid_from_name(playerName));
    }

    public static boolean hasPermission(Player player, String permission) {
        String playerGradePermission = "Grade." + PlayerGestion.getPlayerGrade(player.getName()) + ".permission." + permission;

        if (!FichierGestion.getCfgPermission().contains(playerGradePermission) && !player.isOp()) {
            player.sendMessage(PrefixMessage.erreur() + " Vous n'avez pas la permission d'utiliser cette commande");
            return false;
        }

        return true;
    }

    public static Boolean canFly(Player player){
        if (player.isOp() || FichierGestion.getCfgPermission().contains("Grade." + PlayerGestion.getPlayerStaffGrade(player.getName()) + ".permission.fly")) {
            return true;
        }
        if(!Claim.isInDefaultWorld(player))return false;
        if(Claim.getNameCityOfClaim(player, player.getLocation().getX(), player.getLocation().getZ()) == null)return false;
        if(!Claim.getNameCityOfClaim(player, player.getLocation().getX(), player.getLocation().getZ()).equals(PlayerGestion.getPlayerCityName(player.getName()))){
            return false;
        }
        return true;
    }

    public static UUID get_uuid_from_name(String name){

        Player player = Bukkit.getPlayer(name);


        if(player != null){
            return player.getUniqueId();
        }else{
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(name);
            return offlinePlayer.getUniqueId();
        }
    }




}
