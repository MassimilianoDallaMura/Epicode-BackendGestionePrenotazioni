package backend_esame_3.Gestione_prenotazioni.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "utenti")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
    @OneToMany(mappedBy = "utente") // utente è collegato a postazione tramite prenotazioni
    private List<Prenotazione> prenotazioni;

    @Column(name = "codice_prenotazione")
    private String codicePrenotazione; //

    // Metodi per gestire la conversione tra lista e stringa
    public List<String> getCodicePrenotazioneList() {
        if (codicePrenotazione != null && !codicePrenotazione.isEmpty()) {
            return new ArrayList<>(Arrays.asList(codicePrenotazione.split(",")));
        } else {
            return new ArrayList<>();
        }
    }


    public void setCodicePrenotazioneList(List<String> codici) {
        this.codicePrenotazione = codici.stream().collect(Collectors.joining(","));
    }
}
