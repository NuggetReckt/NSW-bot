package fr.nuggetreckt.nswbot.util;

public enum MessageManager {
    //Error messages
    NO_PERMISSION("Vous n'avez pas la permission !"),
    BAD_CHANNEL("Mauvais salon ! Merci d'utiliser le salon %s !"),
    TICKET_ALREADY_CREATED("Vous avez déjà créé un ticket !"),
    TICKET_MEMBER_ALREADY_PRESENT("Ce membre est déjà présent dans le ticket !"),
    TICKET_MEMBER_NOT_PRESENT("Ce membre n'est pas présent dans le ticket !"),
    OPTION_ALREADY_ACTIVATED("L'option est déjà activée !"),
    OPTION_ALREADY_DEACTIVATED("L'option est déjà désactivée !"),
    NOT_IN_TICKET("Vous n'êtes pas dans un ticket !"),
    NO_DESCRIPTION("Veuillez mettre une description !"),
    BAD_LINK_CODE("Ce code n'existe pas. Vérifiez que vous avez rentré le bon code en ayant exécuté la commande `/link` en jeu !"),
    ALREADY_LINKED("Vous êtes déjà link !"),
    NOT_LINKED("Vous devez être link pour effectuer cette action !"),
    NOT_LINKED_OHTER("Le membre choisi n'est pas link !"),
    CANNOT_CHANGE_NAME("Vous ne pouvez pas changer votre pseudo car vous êtes membre du staff."),

    //Success messages
    TICKET_MEMBER_ADDED("%s a été ajouté au ticket !"),
    TICKET_MEMBER_REMOVED("%s a été retiré du ticket !"),
    TICKET_CREATION_ENABLED("Création de ticket **activée.**"),
    TICKET_CREATION_DISABLED("Création de ticket **désactivée.**"),
    TICKET_CREATED("Ticket créé avec succès ! (%s)"),
    TICKET_DELETE_TIME("Le ticket sera supprimé dans 10 secondes."),
    SUGGESTION_SENT("Suggestion envoyée avec succès !"),
    MEMBER_LINKED("Vous avez été link avec succès ! Voulez-vous changer votre pseudo actuel par votre pseudo Minecraft ? *(recommandé)*"),
    MEMBER_NAME_CHANGED("Votre pseudo à été modifié, bon jeu sur NoSkillWorld !"),

    //Other messages
    MEMBER_NAMECHANGE_CANCELLED("Pas de soucis, bon jeu sur NoSkillWorld !"),
    TRANSCRIPT_MESSAGE_FILE("Transcript du ticket :"),
    ;

    private final String message;

    MessageManager(String s) {
        this.message = s;
    }

    public String getMessage() {
        return "> " + this.message;
    }
}
