package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.model.MemoriaPersona;
import com.grupo7.TrabajoDeCampo.model.Memoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoriaPersonaRepository extends JpaRepository<MemoriaPersona, Long> {

    List<MemoriaPersona> findByMemoria(Memoria memoria);
}
