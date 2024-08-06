package com.leonardo.awesomepizza.enums;

public enum MessaggioErroreEnum {
	
	UTENTE_NON_TROVATO("Utente con ID %d non trovato"),
	ORDINE_NON_TROVATO("Ordine con ID %d non trovato"),
	PIZZA_NON_TROVATA("Pizza con ID %d non trovata"),
	NO_ORDINI_IN_CODA("Non ci sono ordini da preparare"),
	NO_ORDINE_PER_UTENTE("Nessun dato per l'Utente con ID %d e Ordine con ID %d"),
	STATO_ORDINE_NON_MODIFICABILE("Non è possibile cambiare lo stato dell'ordine con ID %d"),
	ORDINE_NON_COMPLETABILE("Non è possibile completare l'ordine con ID %d");

	private final String messageTemplate;

	MessaggioErroreEnum(String messageTemplate) {
        this.messageTemplate = messageTemplate;
    }

    public String getMessage(Object... args) {
        return String.format(messageTemplate, args);
    }

}
