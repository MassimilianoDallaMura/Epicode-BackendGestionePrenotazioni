package backend_esame_3.Gestione_prenotazioni.repositiry;

import backend_esame_3.Gestione_prenotazioni.bean.Postazione;
import backend_esame_3.Gestione_prenotazioni.bean.Prenotazione;
import backend_esame_3.Gestione_prenotazioni.bean.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long> {
    List<Prenotazione> findByDataAndPostazione(LocalDate data, Postazione postazione);
    List<Prenotazione> findByDataAndUtente(LocalDate data, Utente utente);

//    List<Prenotazione> findByDataPrenotazioneAndPostazione(LocalDate dataPrenotazione, Postazione postazione);
}
