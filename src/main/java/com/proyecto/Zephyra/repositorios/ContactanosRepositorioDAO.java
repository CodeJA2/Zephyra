package com.proyecto.Zephyra.repositorios;

import com.proyecto.Zephyra.model.Contactanos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactanosRepositorioDAO extends JpaRepository<Contactanos, Long> {
    
}
