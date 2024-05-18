package backend_esame_3.Gestione_prenotazioni.bean;


import backend_esame_3.Gestione_prenotazioni.enums.TipoPostazione;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name= "postazioni")
public class Postazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codice;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private TipoPostazione tipo;
    private int numeroMassimoOccupanti;
    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
}
