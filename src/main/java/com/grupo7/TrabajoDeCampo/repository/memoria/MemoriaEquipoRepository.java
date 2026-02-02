package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.model.Equipo;
import com.grupo7.TrabajoDeCampo.model.Memoria;
import com.grupo7.TrabajoDeCampo.model.MemoriaEquipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoriaEquipoRepository extends JpaRepository<MemoriaEquipo, Long> {

    // listar equipos de una memoria
    List<MemoriaEquipo> findByMemoria(Memoria memoria);

    // buscar relación memoria-equipo
    Optional<MemoriaEquipo> findByMemoriaAndEquipo(Memoria memoria, Equipo equipo);
}
