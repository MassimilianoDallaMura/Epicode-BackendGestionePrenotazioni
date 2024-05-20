package backend_esame_3.Gestione_prenotazioni.repositiry;

import backend_esame_3.Gestione_prenotazioni.bean.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface EdificioRepository extends JpaRepository<Edificio, Long> {

}
