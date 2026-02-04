package com.grupo7.TrabajoDeCampo.service;

import com.grupo7.TrabajoDeCampo.DTO.DocumentoResponse;
import com.grupo7.TrabajoDeCampo.DTO.DocumentoResponseGrupo;
import com.grupo7.TrabajoDeCampo.model.Documento;
import com.grupo7.TrabajoDeCampo.model.Grupo;
import com.grupo7.TrabajoDeCampo.model.Usuario;
import com.grupo7.TrabajoDeCampo.repository.DocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.GrupoRepository;
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

    public List<DocumentoResponse> listarDocumentos() {

        return documentoRepository.findAll()
                .stream()
                .map(d -> new DocumentoResponse(
                        d.getOidDocumento(),
                        d.getTitulo(),
                        d.getAutores(),
                        d.getEditorial(),
                        d.getAnio(),
                        d.getNombreArchivo(),
                        d.getTipoArchivo(),
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

    public ResponseEntity<byte[]> descargarArchivo(Long oidDocumento) {

        Documento documento = documentoRepository.findById(oidDocumento)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        if (documento.getArchivoBase64() == null) {
            throw new RuntimeException("El documento no tiene archivo asociado");
        }

        byte[] archivoBytes = Base64.getDecoder()
                .decode(documento.getArchivoBase64());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(documento.getTipoArchivo()));
        headers.setContentDisposition(
                ContentDisposition.attachment()
                        .filename(documento.getNombreArchivo())
                        .build()
        );
        headers.setContentLength(archivoBytes.length);

        return ResponseEntity.ok()
                .headers(headers)
                .body(archivoBytes);
    }


    public List<DocumentoResponseGrupo> listarDocumentosPorGrupo(Long oidGrupo) {

        return documentoRepository.findByGrupoOidGrupo(oidGrupo)
                .stream()
                .map(doc -> new DocumentoResponseGrupo(
                        doc.getOidDocumento(),
                        doc.getTitulo(),
                        doc.getAutores(),
                        doc.getEditorial(),
                        doc.getAnio(),
                        doc.getNombreArchivo(),
                        doc.getTipoArchivo(),
                        doc.getActivo()
                ))
                .toList();
    }


    public DocumentoResponseGrupo obtenerDocumentoDelGrupo(
            Long oidDocumento,
            Usuario usuario
    ) {

        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        Documento doc = documentoRepository
                .findByOidDocumentoAndGrupoOidGrupo(oidDocumento, oidGrupo)
                .orElseThrow(() ->
                        new RuntimeException("Documento no encontrado o no pertenece a su grupo")
                );

        return new DocumentoResponseGrupo(
                doc.getOidDocumento(),
                doc.getTitulo(),
                doc.getAutores(),
                doc.getEditorial(),
                doc.getAnio(),
                doc.getNombreArchivo(),
                doc.getTipoArchivo(),
                doc.getActivo()
        );
    }



}



