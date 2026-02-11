package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaDocumento;
import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface MemoriaDocumentoRepository extends JpaRepository<MemoriaDocumento, Long> {

    // listar documentos de una memoria
    List<MemoriaDocumento> findByMemoria(Memoria memoria);

    // buscar relación memoria-documento (para evitar duplicados / borrar)
    Optional<MemoriaDocumento> findByMemoriaAndOidDocumento(Memoria memoria, Long oidDocumento);

}
