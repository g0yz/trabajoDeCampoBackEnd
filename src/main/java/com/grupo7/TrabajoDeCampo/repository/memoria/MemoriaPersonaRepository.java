package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaPersonaResponse;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaPersona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoriaPersonaRepository
        extends JpaRepository<MemoriaPersona, Long> {

    boolean existsByMemoriaAndOidPersona(Memoria memoria, Long oidPersona);

    List<MemoriaPersona> findByMemoria(Memoria memoria);
}
