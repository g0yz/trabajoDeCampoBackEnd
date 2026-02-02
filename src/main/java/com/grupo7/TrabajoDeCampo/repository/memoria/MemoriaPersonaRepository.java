package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.model.Memoria;
import com.grupo7.TrabajoDeCampo.model.MemoriaPersona;
import com.grupo7.TrabajoDeCampo.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoriaPersonaRepository extends JpaRepository<MemoriaPersona, Long> {

    // listar personas de una memoria
    List<MemoriaPersona> findByMemoria(Memoria memoria);

    // buscar persona en una memoria (para evitar duplicados)
    Optional<MemoriaPersona> findByMemoriaAndPersona(Memoria memoria, Persona persona);
}
