package com.grupo7.TrabajoDeCampo.service.documento;

import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.documento.DocumentoResponseAdministrador;
import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.documento.DocumentoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.documento.DocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import org.springframework.http.ContentDisposition;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Base64;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final GrupoRepository grupoRepository;

    public DocumentoService(DocumentoRepository documentoRepository,
                            GrupoRepository grupoRepository){
        this.documentoRepository = documentoRepository;
        this.grupoRepository = grupoRepository;
    }

    public List<DocumentoResponseAdministrador> listarDocumentos() {

        return documentoRepository.findAll()
                .stream()
                .map(d -> new DocumentoResponseAdministrador(
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

    public Optional<Documento> obtenerDocumentoPorId(Long oid){
        return documentoRepository.findById(oid);
    }

    public Documento crearDocumento(Documento documento, Long oid){
        Grupo grupo = grupoRepository.findById(oid)
        .orElseThrow(() -> new RuntimeException("Grupo no encontrado con oid: " + oid));
        documento.setGrupo(grupo);
        return documentoRepository.save(documento);
    }

    public Documento actualizarDocumento(Long oid, Documento documentoActualizado){
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

    public void eliminarDocumento(Long oid){
        documentoRepository.deleteById(oid);
    }




    public List<DocumentoResponseIntegrante> listarDocumentosPorGrupo(Long oidGrupo) {

        return documentoRepository.findByGrupoOidGrupoAndActivoTrue(oidGrupo)
                .stream()
                .map(doc -> new DocumentoResponseIntegrante(
                        doc.getOidDocumento(),
                        doc.getTitulo(),
                        doc.getAutores(),
                        doc.getEditorial(),
                        doc.getAnio(),
                        doc.getActivo()
                ))
                .toList();
    }


    public DocumentoResponseIntegrante obtenerDocumentoDelGrupo(
            Long oidDocumento,
            Usuario usuario
    ) {

        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        Documento doc = documentoRepository
                .findByOidDocumentoAndGrupoOidGrupoAndActivoTrue(oidDocumento, oidGrupo)
                .orElseThrow(() ->
                        new RuntimeException("Documento no encontrado o no pertenece a su grupo")
                );

        return new DocumentoResponseIntegrante(
                doc.getOidDocumento(),
                doc.getTitulo(),
                doc.getAutores(),
                doc.getEditorial(),
                doc.getAnio(),
                doc.getActivo()
        );
    }



}



