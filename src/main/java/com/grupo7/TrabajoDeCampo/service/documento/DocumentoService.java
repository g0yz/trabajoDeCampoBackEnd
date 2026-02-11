package com.grupo7.TrabajoDeCampo.service.documento;

import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoResponse;

import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.documento.DocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final GrupoRepository grupoRepository;

    public DocumentoService(DocumentoRepository documentoRepository,
                            GrupoRepository grupoRepository){
        this.documentoRepository = documentoRepository;
        this.grupoRepository = grupoRepository;
    }

    //ADMINISTRADOR

    public List<DocumentoResponse> listarDocumentosAdmin() {

        return documentoRepository.findAll()
                .stream()
                .map(d -> new DocumentoResponse(
                        d.getOidDocumento(),
                        d.getTitulo(),
                        d.getAutores(),
                        d.getEditorial(),
                        d.getAnio(),
                        d.getActivo(),
                        d.getGrupo().getOidGrupo(),
                        d.getGrupo().getNombreGrupo()
                ))
                .toList();
    }

    public Optional<Documento> obtenerDocumentoPorIdAdmin(Long oid){
        return documentoRepository.findById(oid);
    }

    public Documento crearDocumentoAdmin(Documento documento, Long oid){
        Grupo grupo = grupoRepository.findById(oid)
        .orElseThrow(() -> new RuntimeException("Grupo no encontrado con oid: " + oid));
        documento.setGrupo(grupo);
        return documentoRepository.save(documento);
    }

    public Documento actualizarDocumentoAdmin(Long oid, Documento documentoActualizado){
        Documento documento = documentoRepository.findById(oid)
                .orElseThrow(() -> new RuntimeException("Documento No encontrado con oid: " + oid));
        if (documentoActualizado.getTitulo() != null){
            documento.setTitulo(documentoActualizado.getTitulo());
        }
        if (documentoActualizado.getAutores() != null){
            documento.setAutores(documentoActualizado.getAutores());
        }
        if (documentoActualizado.getEditorial() != null){
            documento.setEditorial(documentoActualizado.getEditorial());
        }
        if (documentoActualizado.getAnio() != null){
            documento.setAnio(documentoActualizado.getAnio());
        }
        if (documentoActualizado.getGrupo() != null){
            documento.setGrupo(documentoActualizado.getGrupo());
        }
        return documentoRepository.save(documento);
    }

    public void eliminarDocumentoAdmin(Long oid){
        documentoRepository.deleteById(oid);
    }


    //INTEGRANTE

    public List<DocumentoResponse> listarDocumentosDelGrupoIntegrante(Long oidGrupo) {

        return documentoRepository.findByGrupoOidGrupoAndActivoTrue(oidGrupo)
                .stream()
                .map(doc -> new DocumentoResponse(
                        doc.getOidDocumento(),
                        doc.getTitulo(),
                        doc.getAutores(),
                        doc.getEditorial(),
                        doc.getAnio(),
                        doc.getActivo()
                ))
                .toList();
    }


    public DocumentoResponse obtenerDocumentoDelGrupoIntegrante(
            Long oidDocumento,
            Usuario usuario
    ) {

        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        DocumentoResponse doc = documentoRepository
                .findByOidDocumentoAndGrupoOidGrupoAndActivoTrue(oidDocumento, oidGrupo)
                .orElseThrow(() ->
                        new RuntimeException("Documento no encontrado o no pertenece a su grupo")
                );

        return new DocumentoResponse(
                doc.getOidDocumento(),
                doc.getTitulo(),
                doc.getAutores(),
                doc.getEditorial(),
                doc.getAnio(),
                doc.getActivo()
        );
    }






}



