package backend_esame_3.Gestione_prenotazioni.service;

import backend_esame_3.Gestione_prenotazioni.bean.Edificio;
import backend_esame_3.Gestione_prenotazioni.repositiry.EdificioRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
    public class EdificioService {
    @Autowired
    private EdificioRepository edificioRepository;

    public void inserisciEdificio(Edificio edificio) {
        edificioRepository.save(edificio);
    }

    public Edificio getEdificio(Long id){
        return edificioRepository.findById(id).get();
    }

    public List<Edificio> getEdifici(){
        return edificioRepository.findAll();
    }

    public void cancellaEdifico(Long id){
        edificioRepository.deleteById(id);
    }

//    @Transactional
//    public List<String> getCittaDisponibili() {
//        // Recupera tutti gli edifici dal repository
//        List<Edificio> edifici = edificioRepository.findAll();
//
//        // Estrai le città dai dati degli edifici
//        return edifici.stream()
//                .map(Edificio::getCitta)
//                .distinct() // Rimuovi le città duplicate
//                .collect(Collectors.toList());
//    }
}



