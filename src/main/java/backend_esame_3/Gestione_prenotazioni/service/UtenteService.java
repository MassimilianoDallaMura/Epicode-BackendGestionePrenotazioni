package backend_esame_3.Gestione_prenotazioni.service;

import backend_esame_3.Gestione_prenotazioni.bean.Utente;
import backend_esame_3.Gestione_prenotazioni.repositiry.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service            //logica di come deve gestire i dati
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public void inserisciUtente(Utente utente){
        utenteRepository.save(utente);
    }

    public Optional<Utente> getUtente(Long id) {
        return Optional.of(utenteRepository.findById(id).get());
    }

    public void cancellaUtente(Long id) {
        utenteRepository.deleteById(id);
    }

    public List<Utente> getStudenti() {
        utenteRepository.findAll();
        return List.of();
    }


    public Optional<Utente> getUtenteByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }
}


