package backend_esame_3.Gestione_prenotazioni.bean;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    @Column(name = "codice_prenotazione")
    private String codicePrenotazione; // Cambia il tipo a String

    @Setter
    @Column(name = "data_scadenza", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataScadenza;

    public void setDataPrenotazione(LocalDate now) {
        this.data = now;
    }

    // Metodi per gestire la conversione tra lista e stringa
    public List<String> getCodicePrenotazioneList() {
        if (codicePrenotazione != null && !codicePrenotazione.isEmpty()) {
            return Arrays.asList(codicePrenotazione.split(","));
        } else {
            return List.of();
        }
    }

    public void setCodicePrenotazioneList(List<String> codici) {
        this.codicePrenotazione = codici.stream().collect(Collectors.joining(","));
    }
}
