package com.grupo7.TrabajoDeCampo.service.documento;

import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoArchivoResponse;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoRequest;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoResponse;

import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.documento.DocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
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
                        d.getGrupo().getNombreGrupo(),
                        d.getNombreArchivo(),
                        d.getArchivoBase64() != null
                                ? Base64.getEncoder().encodeToString(d.getArchivoBase64())
                                : null

                        ))
                .toList();
    }



    public Optional<Documento> obtenerDocumentoPorIdAdmin(Long oid){
        return documentoRepository.findById(oid);
    }

    public DocumentoResponse crearDocumentoAdmin(
            DocumentoRequest request,
            Long oidGrupo,
            MultipartFile archivo
    ) throws IOException {

        Grupo grupo = grupoRepository.findById(oidGrupo)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        Documento documento = new Documento();
        documento.setTitulo(request.getTitulo());
        documento.setAutores(request.getAutores());
        documento.setEditorial(request.getEditorial());
        documento.setAnio(request.getAnio());
        documento.setActivo(true);
        documento.setGrupo(grupo);

        if (archivo != null && !archivo.isEmpty()) {

            documento.setArchivoBase64(archivo.getBytes());

            String extension = "";
            String original = archivo.getOriginalFilename();

            if (original != null && original.contains(".")) {
                extension = original.substring(original.lastIndexOf("."));
            }

            documento.setNombreArchivo(request.getTitulo() + extension);
        }

        documento = documentoRepository.save(documento);

        return new DocumentoResponse(
                documento.getOidDocumento(),
                documento.getTitulo(),
                documento.getAutores(),
                documento.getEditorial(),
                documento.getAnio(),
                documento.getActivo(),
                documento.getGrupo().getOidGrupo(),
                documento.getGrupo().getNombreGrupo(),
                documento.getNombreArchivo(),
                documento.getArchivoBase64() != null
                        ? Base64.getEncoder().encodeToString(documento.getArchivoBase64())
                        : null
        );
    }

    public DocumentoResponse actualizarDocumentoAdmin(
            Long oid,
            DocumentoRequest request,
            MultipartFile archivo
    ) throws IOException {

        Documento documento = documentoRepository.findById(oid)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        if (request.getTitulo() != null)
            documento.setTitulo(request.getTitulo());

        if (request.getAutores() != null)
            documento.setAutores(request.getAutores());

        if (request.getEditorial() != null)
            documento.setEditorial(request.getEditorial());

        if (request.getAnio() != null)
            documento.setAnio(request.getAnio());

        if (archivo != null && !archivo.isEmpty()) {

            documento.setArchivoBase64(archivo.getBytes());

            String extension = "";
            String original = archivo.getOriginalFilename();

            if (original != null && original.contains(".")) {
                extension = original.substring(original.lastIndexOf("."));
            }

            documento.setNombreArchivo(documento.getTitulo() + extension);
        }

        documento = documentoRepository.save(documento);

        return new DocumentoResponse(
                documento.getOidDocumento(),
                documento.getTitulo(),
                documento.getAutores(),
                documento.getEditorial(),
                documento.getAnio(),
                documento.getActivo(),
                documento.getGrupo().getOidGrupo(),
                documento.getGrupo().getNombreGrupo(),
                documento.getNombreArchivo(),
                documento.getArchivoBase64() != null
                        ? Base64.getEncoder().encodeToString(documento.getArchivoBase64())
                        : null
        );
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
                        doc.getGrupo().getNombreGrupo(),
                        doc.getNombreArchivo(),
                        doc.getArchivoBase64() != null
                                ? Base64.getEncoder().encodeToString(doc.getArchivoBase64())
                                : null
                ))
                .toList();
    }

    public DocumentoResponse agregarDocumento(
            Usuario usuario,
            DocumentoRequest request,
            MultipartFile archivo
    ) {
        Grupo grupo = usuario.getPersona().getGrupo();

        Documento documento = new Documento();
        documento.setTitulo(request.getTitulo());
        documento.setAutores(request.getAutores());
        documento.setEditorial(request.getEditorial());
        documento.setAnio(request.getAnio());
        documento.setActivo(true);
        documento.setGrupo(grupo);

        if (archivo != null && !archivo.isEmpty()) {
            try {
                documento.setNombreArchivo(archivo.getOriginalFilename());
                documento.setArchivoBase64(archivo.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error al leer archivo", e);
            }
        }

        documento = documentoRepository.save(documento);

        return new DocumentoResponse(
                documento.getOidDocumento(),
                documento.getTitulo(),
                documento.getAutores(),
                documento.getEditorial(),
                documento.getAnio(),
                documento.getActivo(),
                documento.getGrupo().getOidGrupo(),
                documento.getGrupo().getNombreGrupo(),
                documento.getNombreArchivo(),
                documento.getArchivoBase64() != null
                        ? Base64.getEncoder().encodeToString(documento.getArchivoBase64())
                        : null
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
                        d.getOidDocumento(),
                        d.getTitulo(),
                        d.getAutores(),
                        d.getEditorial(),
                        d.getAnio(),
                        d.getActivo(),
                        d.getGrupo().getOidGrupo(),
                        d.getGrupo().getNombreGrupo(),
                        d.getNombreArchivo(),
                        d.getArchivoBase64() != null
                                ? Base64.getEncoder().encodeToString(d.getArchivoBase64())
                                : null
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
                documento.getGrupo().getNombreGrupo(),
                documento.getNombreArchivo(),
                documento.getArchivoBase64() != null
                        ? Base64.getEncoder().encodeToString(documento.getArchivoBase64())
                        : null
        );
    }

    public DocumentoResponse actualizarDocumento(
            Usuario usuario,
            Long oidDocumento,
            DocumentoRequest request,
            MultipartFile archivo
    ) {

        Documento documento = documentoRepository.findById(oidDocumento)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        // 🔐 Seguridad: solo su grupo
        Grupo grupoUsuario = usuario.getPersona().getGrupo();

        if (documento.getGrupo().getOidGrupo() != grupoUsuario.getOidGrupo()) {
            throw new RuntimeException("No puede modificar documentos de otro grupo");
        }

        // ✏️ Actualizar campos
        documento.setTitulo(request.getTitulo());
        documento.setAutores(request.getAutores());
        documento.setEditorial(request.getEditorial());
        documento.setAnio(request.getAnio());

        // 📎 Archivo opcional
        if (archivo != null && !archivo.isEmpty()) {
            try {
                documento.setNombreArchivo(archivo.getOriginalFilename());
                documento.setArchivoBase64(archivo.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error al procesar el archivo");
            }
        }

        documentoRepository.save(documento);

        return new DocumentoResponse(
                documento.getOidDocumento(),
                documento.getTitulo(),
                documento.getAutores(),
                documento.getEditorial(),
                documento.getAnio(),
                documento.getActivo(),
                documento.getGrupo().getOidGrupo(),
                documento.getGrupo().getNombreGrupo(),
                documento.getNombreArchivo(),
                documento.getArchivoBase64() != null
                        ? Base64.getEncoder().encodeToString(documento.getArchivoBase64())
                        : null
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




    public ResponseEntity<byte[]> descargarDocumento(Long id) {

        Documento documento = documentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        if (documento.getArchivoBase64() == null) {
            throw new RuntimeException("Este documento no tiene archivo asociado");
        }

        String nombreArchivo = documento.getNombreArchivo();

        MediaType mediaType = MediaTypeFactory
                .getMediaType(nombreArchivo)
                .orElse(MediaType.APPLICATION_OCTET_STREAM);

        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + nombreArchivo + "\"")
                .body(documento.getArchivoBase64());
    }



}



