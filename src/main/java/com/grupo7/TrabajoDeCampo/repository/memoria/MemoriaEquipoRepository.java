package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.model.MemoriaEquipo;
import com.grupo7.TrabajoDeCampo.model.Memoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoriaEquipoRepository extends JpaRepository<MemoriaEquipo, Long> {

    List<MemoriaEquipo> findByMemoria(Memoria memoria);
}
