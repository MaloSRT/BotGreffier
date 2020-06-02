package fr.msrt.botgreffier.utils;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.List;

public class EntitiesUtils {

    /**
     * Formate une {@link List<Role>} en {@link String}.
     *
     * @param roles Liste des rôles
     * @return Liste formatée
     */
    public static String listOfRoles(List<Role> roles) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < 10 && i < roles.size(); i++) {
            out.append("\n - ").append(roles.get(i).getName()).append(" (ID : `").append(roles.get(i).getId()).append("`)");
        }
        if (roles.size() > 10) {
            out.append("\n [...]");
        }
        return out.toString();
    }

    /**
     * Formate une {@link List<Member>} en {@link String}.
     *
     * @param members Liste des membres
     * @return La liste formatée
     */
    public static String listOfMembers(List<Member> members) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < 10 && i < members.size(); i++) {
            out.append("\n - ").append(members.get(i).getUser().getAsTag()).append(" (ID : `").append(members.get(i).getId()).append("`)");
        }
        if (members.size() > 10) {
            out.append("\n [...]");
        }
        return out.toString();
    }

}