package fr.nuggetreckt.nswbot.util;

public enum MessageManager {
    //Error messages
    NO_PERMISSION_MESSAGE("Vous n'avez pas la permission !"),
    BAD_CHANNEL_MESSAGE("Mauvais salon ! Merci d'utiliser le salon %s !"),
    TICKET_ALREADY_CREATED("Vous avez déjà créé un ticket !"),
    TICKET_MEMBER_ALREADY_PRESENT_MESSAGE("Ce membre est déjà présent dans le ticket !"),
    TICKET_MEMBER_NOT_PRESENT_MESSAGE("Ce membre n'est pas présent dans le ticket !"),
    OPTION_ALREADY_ACTIVATED_MESSAGE("L'option est déjà activée !"),
    OPTION_ALREADY_DEACTIVATED_MESSAGE("L'option est déjà désactivée !"),
    NOT_IN_TICKET_MESSAGE("Vous n'êtes pas dans un ticket !"),
    NO_DESCRIPTION_MESSAGE("Veuillez mettre une description !"),

    //Success messages
    TICKET_MEMBER_ADDED_MESSAGE("%s a été ajouté au ticket !"),
    TICKET_MEMBER_REMOVED_MESSAGE("%s a été retiré du ticket !"),
    TICKET_CREATION_ENABLED_MESSAGE("Création de ticket **activée.**"),
    TICKET_CREATION_DISABLED_MESSAGE("Création de ticket **désactivée.**"),
    TICKET_CREATED_MESSAGE("Ticket créé avec succès ! (%s)"),
    TICKET_DELETE_TIME_MESSAGE("Le ticket sera supprimé dans 10 secondes."),
    SUGGESTION_SENT_MESSAGE("Suggestion envoyée avec succès !"),
    ;

    private final String message;

    MessageManager(String s) {
        this.message = s;
    }

    public String getMessage() {
        return "> " + this.message;
    }
}
