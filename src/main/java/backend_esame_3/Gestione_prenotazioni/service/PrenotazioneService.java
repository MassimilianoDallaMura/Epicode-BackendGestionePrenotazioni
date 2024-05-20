package backend_esame_3.Gestione_prenotazioni.service;

import backend_esame_3.Gestione_prenotazioni.bean.Postazione;
import backend_esame_3.Gestione_prenotazioni.bean.Prenotazione;
import backend_esame_3.Gestione_prenotazioni.bean.Utente;
import backend_esame_3.Gestione_prenotazioni.repositiry.PostazioneRepository;
import backend_esame_3.Gestione_prenotazioni.repositiry.PrenotazioneRepository;
import backend_esame_3.Gestione_prenotazioni.repositiry.UtenteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PrenotazioneService {
    @Autowired
    private PrenotazioneRepository prenotazioneRepository;
    @Autowired
    private PostazioneRepository postazioneRepository;
    @Autowired
    private UtenteRepository utenteRepository;
//    @Autowired
//    private Prenotazione prenotazione;


    public void inserisciPrenotazione(Prenotazione prenotazione) {
        prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione getPrenotazione(Long id){
        return prenotazioneRepository.findById(id).orElse(null);
    }

    public List<Prenotazione> getPrenotazioni(){
        return prenotazioneRepository.findAll();
    }

    public void cancellaPrenotazione(Long id){
        prenotazioneRepository.deleteById(id);
    }

    public List<Prenotazione> findPrenotazioniByDataAndPostazione(LocalDate data, Postazione postazione) {
        return prenotazioneRepository.findByDataAndPostazione(data, postazione);
    }

    @Transactional
    public void prenotaPostazione(Postazione postazione, Utente utente, LocalDate dataPrenotazione) {
        if (postazione != null && utente != null) {

            // Controlla se l'utente ha già una prenotazione per la stessa data
            List<Prenotazione> prenotazioniUtente = prenotazioneRepository.findByDataAndUtente(dataPrenotazione, utente);
            if (!prenotazioniUtente.isEmpty()) {
                System.out.println("L'utente ha già una prenotazione per questa data.");
                return;
            }
            // Controlla se la postazione è già prenotata per la stessa data
            List<Prenotazione> prenotazioniEsistenti = prenotazioneRepository.findByDataAndPostazione(dataPrenotazione, postazione);
            if (!prenotazioniEsistenti.isEmpty()) {
                System.out.println("La postazione è già prenotata per la data selezionata.");
                return;
            }
            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setDataPrenotazione(dataPrenotazione);
            prenotazione.setPostazione(postazione);
            prenotazione.setUtente(utente);

            // Imposta la data di scadenza per 24 ore
            LocalDateTime scadenza = LocalDateTime.now().plusHours(24);
            prenotazione.setDataScadenza(scadenza);

            // Aggiorna lo stato della postazione
            postazione.setLibera(false);

            // Genera un codice prenotazione unico
            String codicePrenotazione = UUID.randomUUID().toString();

            // Recupera i codici prenotazione attuali dell'utente
            List<String> codiciUtente = utente.getCodicePrenotazioneList();

            // Aggiungi il nuovo codice prenotazione alla lista esistente
            codiciUtente.add(codicePrenotazione);

            // Aggiorna la colonna della tabella Utente con la lista aggiornata di codici prenotazione
            utente.setCodicePrenotazioneList(codiciUtente);

            // Imposta il codice della prenotazione per la prenotazione
            prenotazione.setCodicePrenotazione(codicePrenotazione);

            // Salva la prenotazione e aggiorna la postazione
            prenotazioneRepository.save(prenotazione);
            postazioneRepository.save(postazione);

            // Aggiorna l'utente
            utenteRepository.save(utente);

            System.out.println("Prenotazione creata con successo!");
        } else {
            System.out.println("Postazione o Utente non trovato.");
        }
    }
}