package com.grupo7.TrabajoDeCampo.service.documento;

import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoArchivoResponse;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoRequest;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoResponse;

import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.documento.DocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import org.springframework.stereotype.Service;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
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

    public List<DocumentoResponse> listarDocumentosDelGrupo(Long oidGrupo) {

        return documentoRepository.findByGrupoOidGrupoAndActivoTrue(oidGrupo)
                .stream()
                .map(doc -> new DocumentoResponse(
                        doc.getOidDocumento(),
                        doc.getTitulo(),
                        doc.getAutores(),
                        doc.getEditorial(),
                        doc.getAnio(),
                        doc.getActivo(),
                        doc.getGrupo().getOidGrupo(),
                        doc.getGrupo().getNombreGrupo()
                ))
                .toList();
    }



    public DocumentoResponse agregarDocumento(Usuario usuario, DocumentoRequest request) {

        Grupo grupo = usuario.getPersona().getGrupo();

        Documento documento = new Documento();
        documento.setTitulo(request.getTitulo());
        documento.setAutores(request.getAutores());
        documento.setEditorial(request.getEditorial());
        documento.setAnio(request.getAnio());
        documento.setActivo(true);
        documento.setGrupo(grupo);
        documento = documentoRepository.save(documento);
        return new DocumentoResponse(
                documento.getOidDocumento(), documento.getTitulo(), documento.getAutores(),
                documento.getEditorial(), documento.getAnio(), documento.getActivo(),
                documento.getGrupo().getOidGrupo(), documento.getGrupo().getNombreGrupo()
        );
    }


    public List<DocumentoResponse> listarDocumentos(Usuario usuario) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return documentoRepository.findByGrupoOidGrupoAndActivoTrue(oidGrupo)
                .stream()
                .map(d -> new DocumentoResponse(
                        d.getOidDocumento(), d.getTitulo(), d.getAutores(), d.getEditorial(), d.getAnio(), d.getActivo(),d.getGrupo().getOidGrupo(),
                        d.getGrupo().getNombreGrupo()
                ))
                .toList();
    }


    public DocumentoResponse obtenerDocumento(Long oidDocumento, Usuario usuario) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        Documento documento = documentoRepository
                .findByOidDocumentoAndGrupoOidGrupoAndActivoTrue(oidDocumento, oidGrupo)
                .orElseThrow(() ->
                        new RuntimeException("Documento no encontrado en el grupo del director")
                );

        return new DocumentoResponse(
                documento.getOidDocumento(),
                documento.getTitulo(),
                documento.getAutores(),
                documento.getEditorial(),
                documento.getAnio(),
                documento.getActivo(),
                documento.getGrupo().getOidGrupo(),
                documento.getGrupo().getNombreGrupo()
        );
    }

    public DocumentoResponse editarDocumento(Usuario usuario, Long oidDocumento, DocumentoRequest request) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        Documento documento = documentoRepository.findByOidDocumentoAndGrupoOidGrupo(oidDocumento, oidGrupo)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado en el grupo del director"));

        if (request.getTitulo() != null) documento.setTitulo(request.getTitulo());
        if (request.getAutores() != null) documento.setAutores(request.getAutores());
        if (request.getEditorial() != null) documento.setEditorial(request.getEditorial());
        if (request.getAnio() != null) documento.setAnio(request.getAnio());

        documento = documentoRepository.save(documento);
        return new DocumentoResponse(
                documento.getOidDocumento(), documento.getTitulo(), documento.getAutores(),
                documento.getEditorial(), documento.getAnio(), documento.getActivo(),
                documento.getGrupo().getOidGrupo(), documento.getGrupo().getNombreGrupo()
        );
    }


    public void eliminarDocumento(Usuario usuario, Long oidDocumento) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        Documento documento = documentoRepository.findByOidDocumentoAndGrupoOidGrupo(oidDocumento, oidGrupo)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado en el grupo del director"));
        documento.setActivo(false);
        documentoRepository.save(documento);
    }




    public DocumentoArchivoResponse descargarDocumento(Long oidDocumento) {
        Documento documento = documentoRepository.findById(oidDocumento)
                .orElseThrow(() -> new RuntimeException(
                        "Documento no encontrado con oid: " + oidDocumento
                ));

        byte[] archivoBytes = documento.getArchivoBase64();

        InputStream archivoStream = new ByteArrayInputStream(archivoBytes);

        return new DocumentoArchivoResponse(
                archivoStream,
                documento.getNombreArchivo()
        );
    }



}



