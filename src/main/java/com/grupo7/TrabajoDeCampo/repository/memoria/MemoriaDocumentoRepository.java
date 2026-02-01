package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.model.MemoriaDocumento;
import com.grupo7.TrabajoDeCampo.model.Memoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoriaDocumentoRepository extends JpaRepository<MemoriaDocumento, Long> {

    List<MemoriaDocumento> findByMemoria(Memoria memoria);
}
