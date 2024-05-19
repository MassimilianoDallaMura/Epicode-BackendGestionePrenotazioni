package backend_esame_3.Gestione_prenotazioni.bean;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
    @ElementCollection
    private List<String> codicePrenotazione;

    @Setter
    @Column(name = "data_scadenza", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataScadenza;

    public void setDataPrenotazione(LocalDate now) {
        this.data = now;
    }
}
