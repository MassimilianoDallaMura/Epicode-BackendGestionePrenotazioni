package backend_esame_3.Gestione_prenotazioni.appConfig;


import backend_esame_3.Gestione_prenotazioni.bean.Edificio;
import backend_esame_3.Gestione_prenotazioni.bean.Postazione;
import backend_esame_3.Gestione_prenotazioni.bean.Prenotazione;
import backend_esame_3.Gestione_prenotazioni.bean.Utente;
import backend_esame_3.Gestione_prenotazioni.enums.TipoPostazione;
import backend_esame_3.Gestione_prenotazioni.repositiry.EdificioRepository;
import backend_esame_3.Gestione_prenotazioni.repositiry.PostazioneRepository;
import backend_esame_3.Gestione_prenotazioni.repositiry.PrenotazioneRepository;
import backend_esame_3.Gestione_prenotazioni.repositiry.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.PropertySource;



@Configuration
@PropertySource("application.properties")
public class AppConfig {
        @Bean("EdificioA")
//        @Primary
        public Edificio edificioA(){
            Edificio edificioA = new Edificio();
            edificioA.setNome("EdificioA");
            edificioA.setIndirizzo("Via Roma 1");
            edificioA.setCitta("Torino");
            return edificioA;
        }



        @Bean("EdificioB")
        public Edificio edificioB(){
            Edificio edificioB = new Edificio();
            edificioB.setNome("EdificioB");
            edificioB.setIndirizzo("Via Siracusa 4");
            edificioB.setCitta("Torino");
            return edificioB;
        }


        @Bean("EdificioC")
        public Edificio edificioC(){
            Edificio edificioC = new Edificio();
            edificioC.setNome("EdificioC");
            edificioC.setIndirizzo("Via Torrepiano 20");
            edificioC().setCitta("Verona");
            return edificioC;
        }

        @Bean("EdificioD")
        public Edificio edificioD(){
            Edificio edificioD = new Edificio();
            edificioD.setNome("EdificioD");
            edificioD.setIndirizzo("Via Civetta 9");
            edificioD.setCitta("Firenze");
            return edificioD;
        }

        @Bean("EdificioE")
        public Edificio edificioE(){
            Edificio edificioE = new Edificio();
            edificioE.setNome("EdificioB");
            edificioE.setIndirizzo("Via Siracusa 4");
            edificioE.setCitta("Firenze");
            return edificioE;
        }


        @Bean("DellaScala")
        public Postazione dellascala(){
            Postazione dellascala = new Postazione();
            dellascala.setCodice("369a098");
            dellascala.setDescrizione("Postazione openspace");
            dellascala.setTipo(TipoPostazione.OPENSPACE);
            dellascala.setNumeroMassimoOccupanti(10);
            dellascala.setEdificio(edificioC());
            return dellascala;
        }

        @Bean("Savoia")
        public Postazione savoia(){
            Postazione savoia= new Postazione();
            savoia.setCodice("139a544");
            savoia.setDescrizione("Postazione openspace");
            savoia.setTipo(TipoPostazione.OPENSPACE);
            savoia.setNumeroMassimoOccupanti(12);
            savoia.setEdificio(edificioA());
            return savoia;
        }

        @Bean("Vittorio Emanuele")
        public Postazione vittorioemanuele(){
            Postazione vittorioemanuele= new Postazione();
            vittorioemanuele.setCodice("139a544");
            vittorioemanuele.setDescrizione("Postazione openspace");
            vittorioemanuele.setTipo(TipoPostazione.OPENSPACE);
            vittorioemanuele.setNumeroMassimoOccupanti(8);
            vittorioemanuele.setEdificio(edificioB());
            return vittorioemanuele;
        }


        @Bean("Preistoria")
        public Postazione preistoria(){
            Postazione preistoria = new Postazione();
            preistoria.setCodice("019d292");
            preistoria.setDescrizione("Postazioni openspace");
            preistoria.setTipo(TipoPostazione.OPENSPACE);
            preistoria.setNumeroMassimoOccupanti(10);
            preistoria.setEdificio(edificioD());
            return preistoria;
        }

        @Bean("Medioevo")
        public Postazione medioevo(){
            Postazione medioevo = new Postazione();
            medioevo.setCodice("722f893");
            medioevo.setDescrizione("Postazioni per riunioni");
            medioevo.setTipo(TipoPostazione.SALA_RIUNIONI);
            medioevo.setNumeroMassimoOccupanti(20);
            medioevo.setEdificio(edificioE());
           return medioevo;
        }

        @Bean("Rinascimento")
        public Postazione rinascimento(){
            Postazione rinascimento = new Postazione();
            rinascimento.setCodice("722f893");
            rinascimento.setDescrizione("Postazioni private");
            rinascimento.setTipo(TipoPostazione.PRIVATO);
            rinascimento.setNumeroMassimoOccupanti(2);
            rinascimento.setEdificio(edificioE());
            return rinascimento;
        }

    @Bean("Azzurra")
    public Utente azzurra(){
        Utente azzurra = new Utente();
        azzurra.setUsername("AzzurraBianchi");
        azzurra.setNome("Azzurra");
        azzurra.setCognome("Bianchi");
        azzurra.setEmail("azzurra@bianchi.it");
        return azzurra;
    }

    @Bean("Marrone")
    public Utente marrone(){
        Utente marrone = new Utente();
        marrone.setUsername("MarroneNeri");
        marrone.setNome("Marrone");
        marrone.setCognome("Neri");
        marrone.setEmail("marrone@neri.it");
        return marrone;
    }

    @Bean("Celeste")
    public Utente celeste(){
        Utente celeste = new Utente();
        celeste.setUsername("CelesteVerdi");
        celeste.setNome("Celeste");
        celeste.setCognome("Verdi");
        celeste.setEmail("celeste@verdi.it");
        return celeste;
    }
    }


