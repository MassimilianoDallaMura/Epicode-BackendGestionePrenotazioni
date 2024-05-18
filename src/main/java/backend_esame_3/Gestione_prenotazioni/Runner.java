package backend_esame_3.Gestione_prenotazioni;


import backend_esame_3.Gestione_prenotazioni.bean.Edificio;
import backend_esame_3.Gestione_prenotazioni.bean.Utente;
import backend_esame_3.Gestione_prenotazioni.service.EdificioService;
import backend_esame_3.Gestione_prenotazioni.service.PostazioneService;
import backend_esame_3.Gestione_prenotazioni.service.PrenotazioneService;
import backend_esame_3.Gestione_prenotazioni.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
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

        // Salva edifici
        Edificio edificioA = ctx.getBean("EdificioA", Edificio.class);
        Edificio edificioB = ctx.getBean("EdificioB", Edificio.class);
        Edificio edificioC = ctx.getBean("EdificioC", Edificio.class);
        Edificio edificioD = ctx.getBean("EdificioD", Edificio.class);
        Edificio edificioE = ctx.getBean("EdificioE", Edificio.class);

        edificioService.inserisciEdificio(edificioA);
        edificioService.inserisciEdificio(edificioB);
        edificioService.inserisciEdificio(edificioC);
        edificioService.inserisciEdificio(edificioD);
        edificioService.inserisciEdificio(edificioE);

        // Salva utenti
        Utente utente1 = ctx.getBean("Azzurra", Utente.class);
        utenteService.inserisciUtente(utente1);

        // Aggiungi altri salvataggi se necessario
    }
}

