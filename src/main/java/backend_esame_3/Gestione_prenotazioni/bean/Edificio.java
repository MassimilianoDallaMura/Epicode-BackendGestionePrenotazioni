package backend_esame_3.Gestione_prenotazioni.bean;


import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "edifici")
public class Edificio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String indirizzo;
    private String citta;

    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Postazione> postazioni;

    @OneToMany(mappedBy = "edificio", cascade = CascadeType.ALL)
    private List<Utente> utente;


}

