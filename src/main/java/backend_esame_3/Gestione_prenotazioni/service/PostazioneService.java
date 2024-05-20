package backend_esame_3.Gestione_prenotazioni.service;

import backend_esame_3.Gestione_prenotazioni.bean.Edificio;
import backend_esame_3.Gestione_prenotazioni.bean.Postazione;
import backend_esame_3.Gestione_prenotazioni.enums.TipoPostazione;
import backend_esame_3.Gestione_prenotazioni.repositiry.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private PrenotazioneService prenotazioneService;

    public void inserisciPostazione(Postazione postazione) {
        postazioneRepository.save(postazione);
    }

    public Postazione getPostazione(Long id) {
        return postazioneRepository.findById(id).orElse(null);
    }

    public List<Postazione> getPostazioni() {
        return postazioneRepository.findAll();
    }

    public void cancellaPostazione(Long id) {
        postazioneRepository.deleteById(id);
    }


    public List<Postazione> trovaPostazioniPerTipoECitta(TipoPostazione tipo, String citta, LocalDate data) {
        List<Postazione> postazioni = postazioneRepository.findByTipoAndEdificio_Citta(tipo, citta);
        return postazioni.stream()
                .filter(postazione -> prenotazioneService.findPrenotazioniByDataAndPostazione(data, postazione).isEmpty())
                .collect(Collectors.toList());
    }

    public List<TipoPostazione> getTipiPostazioneDisponibiliPerCitta(String citta) {
        List<Postazione> postazioni = postazioneRepository.findByEdificio_Citta(citta);
        return postazioni.stream()
                .map(Postazione::getTipo)
                .distinct()
                .collect(Collectors.toList());
    }
}
