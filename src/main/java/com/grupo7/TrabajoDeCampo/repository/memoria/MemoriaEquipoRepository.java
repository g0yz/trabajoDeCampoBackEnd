package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.model.equipo.Equipo;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaEquipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoriaEquipoRepository extends JpaRepository<MemoriaEquipo, Long> {
    Optional<MemoriaEquipo> findByMemoriaAndOidEquipo(Memoria memoria, Long oidEquipo);

    List<MemoriaEquipo> findByMemoria(Memoria memoria);
}
