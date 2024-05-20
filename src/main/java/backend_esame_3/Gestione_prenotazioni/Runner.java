package backend_esame_3.Gestione_prenotazioni;
import backend_esame_3.Gestione_prenotazioni.bean.Edificio;
import backend_esame_3.Gestione_prenotazioni.bean.Postazione;
import backend_esame_3.Gestione_prenotazioni.bean.Utente;
import backend_esame_3.Gestione_prenotazioni.enums.TipoPostazione;
import backend_esame_3.Gestione_prenotazioni.service.EdificioService;
import backend_esame_3.Gestione_prenotazioni.service.PostazioneService;
import backend_esame_3.Gestione_prenotazioni.service.PrenotazioneService;
import backend_esame_3.Gestione_prenotazioni.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@PropertySource("application.properties")
public class Runner implements CommandLineRunner {

    @Autowired
    private EdificioService edificioService;


    @Autowired
    private PostazioneService postazioneService;
    @Autowired
    private PrenotazioneService prenotazioneService;
    @Autowired
    private UtenteService utenteService;



    @Override
    public void run(String... args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(GestionePrenotazioniApplication.class);

//        Edificio edificioA = ctx.getBean("EdificioA", Edificio.class);
//        Edificio edificioB = ctx.getBean("EdificioB", Edificio.class);
//        Edificio edificioC = ctx.getBean("EdificioC", Edificio.class);
//        Edificio edificioD = ctx.getBean("EdificioD", Edificio.class);
//        Edificio edificioE = ctx.getBean("EdificioE", Edificio.class);
//
//        edificioService.inserisciEdificio(edificioA);
//        edificioService.inserisciEdificio(edificioB);
//        edificioService.inserisciEdificio(edificioC);
//        edificioService.inserisciEdificio(edificioD);
//        edificioService.inserisciEdificio(edificioE);
//
//        Postazione postazione1 = ctx.getBean("DellaScala", Postazione.class);
//        Postazione postazione2 = ctx.getBean("Savoia", Postazione.class);
//        Postazione postazione3 = ctx.getBean("Vittorio Emanuele", Postazione.class);
//        Postazione postazione4 = ctx.getBean("Preistoria", Postazione.class);
//        Postazione postazione5 = ctx.getBean("Medioevo", Postazione.class);
//        Postazione postazione6 = ctx.getBean("Rinascimento", Postazione.class);
//
//        postazioneService.inserisciPostazione(postazione1);
//        postazioneService.inserisciPostazione(postazione2);
//        postazioneService.inserisciPostazione(postazione3);
//        postazioneService.inserisciPostazione(postazione4);
//        postazioneService.inserisciPostazione(postazione5);
//        postazioneService.inserisciPostazione(postazione6);
//
//        Utente utente1 = ctx.getBean("Azzurra", Utente.class);
//        Utente utente2 = ctx.getBean("Marrone", Utente.class);
//        Utente utente3 = ctx.getBean("Celeste", Utente.class);
//
//
//        utenteService.inserisciUtente(utente1);
//        utenteService.inserisciUtente(utente2);
//        utenteService.inserisciUtente(utente3);



        Scanner scanner = new Scanner(System.in);


        // Richiesta dell'ID dell'utente
        System.out.println("Inserisci l'ID dell'utente:");
        Long utenteId = scanner.nextLong();
        Utente utente = utenteService.getUtente(utenteId).orElse(null);

        if (utente != null) {
            // Estrai la lista degli edifici
            List<Edificio> edifici = edificioService.getEdifici();

            // Estrai le città dai dati degli edifici
            List<String> cittaDisponibili = edifici.stream()
                    .map(Edificio::getCitta)
                    .distinct() // Rimuovi le città duplicate
                    .collect(Collectors.toList());

            // Visualizza le città disponibili
            System.out.println("Città disponibili:");
            for (String citta : cittaDisponibili) {
                System.out.println("- " + citta);
            }

            // Richiesta della città desiderata
            System.out.println("Inserisci la città desiderata:");
            String citta = scanner.next();

            // Recupera i tipi di postazione disponibili per la città selezionata
            List<TipoPostazione> tipiPostazioneDisponibili = postazioneService.getTipiPostazioneDisponibiliPerCitta(citta);

            // Visualizza i tipi di postazione disponibili
            System.out.println("Tipi di postazione disponibili per la città " + citta + ":");
            for (TipoPostazione tipo : tipiPostazioneDisponibili) {
                System.out.println("- " + tipo);
            }

            // Chiede il tipo di postazione desiderato
            System.out.println("Inserisci il tipo di postazione desiderato:");
            String tipoPostazioneString = scanner.next();
            TipoPostazione tipoPostazione = TipoPostazione.valueOf(tipoPostazioneString.toUpperCase());



            // Trova le postazioni disponibili in base alla città e al tipo desiderati
            LocalDate data = LocalDate.now(); // Puoi impostare una data specifica se necessario
            List<Postazione> postazioniDisponibili = postazioneService.trovaPostazioniPerTipoECitta(tipoPostazione, citta, data);

            if (!postazioniDisponibili.isEmpty()) {
                System.out.println("Postazioni disponibili:");
                for (Postazione postazione : postazioniDisponibili) {
                    System.out.println(postazione);
                }

                // Selezione della postazione
                System.out.println("Inserisci l'ID della postazione desiderata:");
                Long postazioneId = scanner.nextLong();
                Postazione postazioneSelezionata = postazioniDisponibili.stream()
                        .filter(p -> p.getId().equals(postazioneId))
                        .findFirst()
                        .orElse(null);

                System.out.println("Inserisci la data di prenotazione (AAAA-MM-GG):");
                String dataPrenotazioneString = scanner.next();
                LocalDate dataPrenotazione = LocalDate.parse(dataPrenotazioneString);

                System.out.println("Inserisci il codice della prenotazione:");
                String codicePrenotazione = scanner.next();


                if (postazioneSelezionata != null) {
                    // Effettua la prenotazione
                    prenotazioneService.prenotaPostazione(postazioneSelezionata, utente, dataPrenotazione, codicePrenotazione);

                } else {
                    System.out.println("Postazione non valida.");
                }

            } else {
                System.out.println("Nessuna postazione disponibile in base alla selezione.");
            }
        } else {
            System.out.println("Utente non trovato.");
        }

    }
}



//        // Chiedi all'utente di inserire il suo username
//        System.out.println("Inserisci il tuo username:");
//        String username = scanner.nextLine();
//
//        // Ottieni l'utente dal servizio
//        Utente utente = utenteService.getUtenteByUsername(username)
//                .orElseThrow(() -> new RuntimeException("Utente non trovato"));
//
//        // Chiedi all'utente di inserire il tipo di postazione
//        System.out.println("Inserisci il tipo di postazione (PRIVATO, OPENSPACE o SALA_RIUNIONI):");
//        String tipoPostazioneStr = scanner.nextLine();
//        TipoPostazione tipoPostazione = TipoPostazione.valueOf(tipoPostazioneStr);
//
//        // Ottieni le città disponibili dalle informazioni sugli edifici
//        List<String> cittaDisponibili = edificioService.getCittaDisponibili();
//
//        // Mostra le città disponibili
//        System.out.println("Città disponibili:");
//        for (int i = 0; i < cittaDisponibili.size(); i++) {
//            System.out.println((i + 1) + ". " + cittaDisponibili.get(i));
//        }
//
//        // Chiedi all'utente di selezionare una città
//        System.out.println("Seleziona una città inserendo il numero corrispondente:");
//        int sceltaCitta = scanner.nextInt();
//        String cittaScelta = cittaDisponibili.get(sceltaCitta - 1);
//
//        // Ottieni la data odierna
//        LocalDate dataOdierna = LocalDate.now();
//
//        // Trova le postazioni disponibili per il tipo e la città specificati
//        List<Postazione> postazioniDisponibili = postazioneService.trovaPostazioniPerTipoECitta(tipoPostazione, cittaScelta, dataOdierna);
//
//        // Mostra i risultati
//        if (postazioniDisponibili.isEmpty()) {
//            System.out.println("Nessuna postazione disponibile per il tipo e la città specificati.");
//        } else {
//            System.out.println("Postazioni disponibili:");
//            for (Postazione postazione : postazioniDisponibili) {
//                System.out.println(postazione);
//            }
//        }
//
//    }
//
//}