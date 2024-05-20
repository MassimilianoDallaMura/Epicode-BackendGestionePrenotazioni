package backend_esame_3.Gestione_prenotazioni.bean;


import backend_esame_3.Gestione_prenotazioni.enums.TipoPostazione;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    @Column()
    private boolean libera = true;;
    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
    @OneToMany(mappedBy = "postazione")
    private List<Prenotazione> prenotazioni;

    @Override
    public String toString() {
        return STR."Postazione{id=\{id}, codice='\{codice}\{'\''}, descrizione='\{descrizione}\{'\''}, tipo=\{tipo}, numeroMassimoOccupanti=\{numeroMassimoOccupanti}, libera=\{libera}\{'}'}";
    }

}
