package backend_esame_3.Gestione_prenotazioni.service;

import backend_esame_3.Gestione_prenotazioni.bean.Edificio;
import backend_esame_3.Gestione_prenotazioni.bean.Postazione;
import backend_esame_3.Gestione_prenotazioni.enums.TipoPostazione;
import backend_esame_3.Gestione_prenotazioni.repositiry.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.List;

@Service
public class PostazioneService {
    @Autowired
    private PostazioneRepository postazioneRepository;

    public void inserisciPostazione(Postazione postazione){
       postazioneRepository.save(postazione);
    }

    public Postazione getPostazione(Long id){
        return postazioneRepository.findById(id).get();
    }

    public List<Postazione> getPostazioni(){
        return postazioneRepository.findAll();
    }

    public void cancellaPostazione(Long id){
        postazioneRepository.deleteById(id);
    }




}