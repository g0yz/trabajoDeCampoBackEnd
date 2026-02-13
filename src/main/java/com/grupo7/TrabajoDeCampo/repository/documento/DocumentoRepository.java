package com.grupo7.TrabajoDeCampo.repository.documento;

import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoResponse;
import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DocumentoRepository extends JpaRepository <Documento,Long>{

    List<Documento> findByGrupoOidGrupo(Long oidGrupo);

    Optional<Documento> findByOidDocumentoAndGrupoOidGrupo(Long oidDocumento, Long oidGrupo);

    // listar documentos activos del grupo
    List<DocumentoResponse> findByGrupoOidGrupoAndActivoTrue(Long oidGrupo);

    // obtener un documento específico, del grupo y activo
    Optional<DocumentoResponse> findByOidDocumentoAndGrupoOidGrupoAndActivoTrue(
            Long oidDocumento,
            Long oidGrupo
    );





}
