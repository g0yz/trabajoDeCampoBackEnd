package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.model.Memoria;
import com.grupo7.TrabajoDeCampo.model.MemoriaDocumento;
import com.grupo7.TrabajoDeCampo.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemoriaDocumentoRepository extends JpaRepository<MemoriaDocumento, Long> {

    // listar documentos de una memoria
    List<MemoriaDocumento> findByMemoria(Memoria memoria);

    // buscar relación memoria-documento (para evitar duplicados / borrar)
    Optional<MemoriaDocumento> findByMemoriaAndDocumento(Memoria memoria, Documento documento);
}
