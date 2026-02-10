package com.grupo7.TrabajoDeCampo.service.documento;

import com.grupo7.TrabajoDeCampo.dto.dtoAdministrador.documento.DocumentoResponseAdministrador;
import com.grupo7.TrabajoDeCampo.dto.dtoDirector.documento.DocumentoRequestDirector;
import com.grupo7.TrabajoDeCampo.dto.dtoDirector.documento.DocumentoResponseDirector;
import com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.documento.DocumentoResponseIntegrante;
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

    public List<DocumentoResponseAdministrador> listarDocumentosAdmin() {

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

    public List<DocumentoResponseIntegrante> listarDocumentosDelGrupoIntegrante(Long oidGrupo) {

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


    public DocumentoResponseIntegrante obtenerDocumentoDelGrupoIntegrante(
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


    //DIRECTOR

    public List<DocumentoResponseDirector> listarDocumentosDirector(Usuario usuario) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return documentoRepository.findByGrupoOidGrupoAndActivoTrue(oidGrupo)
                .stream()
                .map(d -> new DocumentoResponseDirector(
                        d.getOidDocumento(), d.getTitulo(), d.getAutores(), d.getEditorial(), d.getAnio(), d.getActivo()
                ))
                .toList();
    }

    public DocumentoResponseDirector obtenerDocumentoDirector(Long oidDocumento, Usuario usuario) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        Documento d = documentoRepository.findByOidDocumentoAndGrupoOidGrupoAndActivoTrue(oidDocumento, oidGrupo)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado en el grupo del director"));
        return new DocumentoResponseDirector(
                d.getOidDocumento(), d.getTitulo(), d.getAutores(), d.getEditorial(), d.getAnio(), d.getActivo()
        );
    }

    public DocumentoResponseDirector agregarDocumentoDirector(Usuario usuario, DocumentoRequestDirector request) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Grupo grupo = usuario.getPersona().getGrupo();
        Documento documento = new Documento();
        documento.setTitulo(request.getTitulo());
        documento.setAutores(request.getAutores());
        documento.setEditorial(request.getEditorial());
        documento.setAnio(request.getAnio());
        documento.setActivo(true);
        documento.setGrupo(grupo);
        documento = documentoRepository.save(documento);
        return new DocumentoResponseDirector(
                documento.getOidDocumento(), documento.getTitulo(), documento.getAutores(),
                documento.getEditorial(), documento.getAnio(), documento.getActivo()
        );
    }

    public DocumentoResponseDirector editarDocumentoDirector(Usuario usuario, Long oidDocumento, DocumentoRequestDirector request) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        Documento documento = documentoRepository.findByOidDocumentoAndGrupoOidGrupoAndActivoTrue(oidDocumento, oidGrupo)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado en el grupo del director"));

        if (request.getTitulo() != null) documento.setTitulo(request.getTitulo());
        if (request.getAutores() != null) documento.setAutores(request.getAutores());
        if (request.getEditorial() != null) documento.setEditorial(request.getEditorial());
        if (request.getAnio() != null) documento.setAnio(request.getAnio());

        documento = documentoRepository.save(documento);
        return new DocumentoResponseDirector(
                documento.getOidDocumento(), documento.getTitulo(), documento.getAutores(),
                documento.getEditorial(), documento.getAnio(), documento.getActivo()
        );
    }

    public void eliminarDocumentoDirector(Usuario usuario, Long oidDocumento) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        Documento documento = documentoRepository.findByOidDocumentoAndGrupoOidGrupoAndActivoTrue(oidDocumento, oidGrupo)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado en el grupo del director"));
        documento.setActivo(false);
        documentoRepository.save(documento);
    }
}



