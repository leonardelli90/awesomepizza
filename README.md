# awesome-pizza

## Problema

_Come pizzeria "Awesome Pizza" voglio creare il mio nuovo portale per gestire gli ordini dei miei clienti. Il portale non richiede la registrazione dell'utente per poter ordinare le sue pizze. Il pizzaiolo vede la coda degli ordini e li può prendere in carico uno alla volta. Quando la pizza è pronta, il pizzaiolo passa all'ordine successivo. L'utente riceve il suo codice d'ordine e può seguire lo stato dell'ordine fino all'evasione._

_Come team, procediamo allo sviluppo per iterazioni. Decidiamo che nella prima iterazione non sarà disponibile un'interfaccia grafica, ma verranno create delle API al fine di ordinare le pizze e aggiornarne lo stato. Decidiamo di utilizzare il framework Spring e Java (versione a tua scelta)._

_Decidiamo di progettare anche i test di unità sul codice oggetto di sviluppo._

## Stack tecnologico

Per il progetto sono state utilizzate le seguenti tecnologie:
* Spring Boot 3.3.2
* Java 17
* Lombok, per diminuire il _boilerplate code_
* Openapi
* Database H2, per valorizzare i dati all'interno del databese in memoria non sono stati usati script sql ma è stata creata la classe _DatabaseInitializer_ annotata con tag `@Configuration`

## Architettura

Per implementare questa prima istanza del portale ho deciso di impostare il progetto utilizzando un architettura Controller-Service-Repository.

Il controller gestische l'interfaccia REST.

Il service contiene la logica di Business.

Il repository serve per interfacciarsi con il database.

## Entity

Per mappare il database sono state create 4 entity per il progetto:

* `Ordine`, contiene le informazioni relative all'ordine
* `Pizza`, contiene le informazioni relative alle pizze che possono essere ordinate
* `Utente`, contiene le informazioni relative all'utente che crea l'ordine
* `OrdinePizza`, tabella associativa per la relazione N-N presente tra ordine e pizza

## Controller

All'interno del progetto sono stati creati 3 controller

* `OrdineController`, che contiene le chiamate per creare, recuperare e gestire i cambi di stato dell'ordine
* `PizzaController`, che contiene le chiamate per recuperare le informazioni delle pizze
* `UtenteController`, che permette all'utente di recuperare le informazioni relative al suo ordine

### OrdineController

Chiamate:

* `creaOrdine` - permette di creare il nuovo ordine
* `preparaOrdine` - permette di cambiare lo stato da `IN_CODA` a `IN_PREPARAZIONE`, sono stati implementati 2 metodi, uno che cambia lo stato all'ordine più vecchio, un secondo che cambia lo stato in base ad un id ordine
* `ordiniPerStato` - permette di mostrare la lista degli ordini per stato, se viene passato come stato il valore `ALL` vengono mostrati tutti gli ordini
* `completaOrdine` - permette di completare gli ordini `IN_PREPARAZIONE` a `PRONTO` passando l'id dell'ordine

Possibili evoluzioni:

Si potrebbe creare una implementazione di preparaOrdine in cui vengono gestiti più ordini contemporaneamente

### PizzaController

Chiamate: 

* `getMenuPizze` - permette di recuperare l'elenco di tutte le pizze presenti sul database

Possibili evoluzioni:

Aggiunta chiamate di gestione delle pizze (insert, update, delete)

### UtenteController

Chiamate:

* `controllaStatoOrdine` - permette di recuperare lo stato dell'ordine associato all'utente.

Possibili evoluzioni:

Agginuta chiamate di gestione degli utenti (insert, update, delete)

## Service

All'interno del progetto sono stati creati 5 service

* `OrdineService`
* `PizzaService`
* `UtenteService`
* `OrdineUtenteService`, contiene parte della logica di business di `getStatoOrdine` creato per evitare dipendenze circolari all'interno della SpringApplication
* `OrdinePizzaService`, contiene la logica per valorizzare la tabella associativa `OrdinePizza`

## Repository

All'interno del progetto sono stati creati 4 repository:

* `OrdinePizzaRepository`
* `OrdineRepository`
* `PizzaRepository`
* `UtenteRepository`

Nei repository in cui sono stati aggiunti dei metodi ad-hoc sono stati utilizzate le _JPA Query Methods_ ovvero sono state costruite le query utilizzando il nome dei metodi.

In alternativia si sarebbe potuto utilizzare il tag `@Query` e passare al suo interno la query

## Exception

Sono state definite 2 exception custom:

* `CheckException`, questa viene rilanciata quando ci sono errori logici, ad esempio si prova a passare uno stato da `IN_CODA` a `PRONTO`
* `DatoNotFoundException`, questa viene rilanciata quando non si trovano dati per i parametri passati

per gestire queste 2 eccezioni è stato utilizzata la classe _RestExceptionHandler_ annotandola con `@RestControllerAdvice`

## Classi di supporto

Sono stati creati 2 enum:
* `MessaggioErroreEnum`, contiene tutti i possibili messaggi di errore.
* `StatoEnum`, contiene i possibili stati i cui si può trovare un ordine.

Per alcune chiamate sono stati create classi di request e response ad-hoc.

Inoltre sono stati creati i DTO delle varie entity.
