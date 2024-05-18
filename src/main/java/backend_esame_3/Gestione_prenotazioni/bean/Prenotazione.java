package backend_esame_3.Gestione_prenotazioni.bean;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    private Long id;
    private LocalDate data;
    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;
    @ManyToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public void setDataPrenotazione(LocalDate of) {

    }
}
