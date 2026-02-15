package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaDocumentoResponse;
import com.grupo7.TrabajoDeCampo.handler.Role;
import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaDocumento;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.documento.DocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaDocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoriaDocumentoService {

    private final MemoriaDocumentoRepository memoriaDocumentoRepository;
    private final MemoriaRepository memoriaRepository;
    private final DocumentoRepository documentoRepository;

    public MemoriaDocumentoService(
            MemoriaDocumentoRepository memoriaDocumentoRepository,
            MemoriaRepository memoriaRepository,
            DocumentoRepository documentoRepository) {
        this.memoriaDocumentoRepository = memoriaDocumentoRepository;
        this.memoriaRepository = memoriaRepository;
        this.documentoRepository = documentoRepository;
    }


    //ADMINISTRADOR
    // agregar documento a memoria
    public void agregarDocumentoMemoriaAdmin(Long oidMemoria, Long oidDocumento) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Documento documento = documentoRepository.findById(oidDocumento)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        if (memoriaDocumentoRepository
                .findByMemoriaAndOidDocumento(memoria, oidDocumento)
                .isPresent()) {

            throw new RuntimeException("El documento ya está en la memoria");
        }

        MemoriaDocumento md = new MemoriaDocumento(memoria, documento);
        memoriaDocumentoRepository.save(md);
    }


    // listar documentos de una memoria
    public List<MemoriaDocumentoResponse> listarDocumentosPorMemoriaAdmin(Long oidMemoria) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        return memoriaDocumentoRepository.findByMemoria(memoria)
                .stream()
                .map(md -> new MemoriaDocumentoResponse(
                        md.getOidDocumento(),
                        md.getTitulo(),
                        md.getAutores(),
                        md.getEditorial(),
                        md.getAnio()
                ))
                .toList();
    }

    public void quitarDocumentoMemoriaAdmin(Long oidMemoria, Long oidDocumento) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        MemoriaDocumento md = memoriaDocumentoRepository
                .findByMemoriaAndOidDocumento(memoria, oidDocumento)
                .orElseThrow(() ->
                        new RuntimeException("El documento no está asociado a la memoria"));

        memoriaDocumentoRepository.delete(md);
    }


    public void agregarDocumentoAMemoriaDirector(
            Usuario usuario,
            Long oidMemoria,
            Long oidDocumento
    ) {

        // Validar usuario
        if (usuario.getPersona() == null ||
                usuario.getPersona().getGrupo() == null) {

            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        // Validar rol
        if (usuario.getRole() != Role.Director) {
            throw new RuntimeException("Solo el Director puede modificar memorias");
        }

        long oidGrupoUsuario =
                usuario.getPersona().getGrupo().getOidGrupo();

        // Buscar memoria
        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        // Validar que sea del mismo grupo
        if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("La memoria no pertenece a su grupo");
        }

        // Buscar documento
        Documento documento = documentoRepository.findById(oidDocumento)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        // Validar grupo del documento
        if (documento.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("El documento no pertenece a su grupo");
        }

        // Validar que no esté repetido
        if (memoriaDocumentoRepository
                .existsByMemoriaAndOidDocumento(memoria, oidDocumento)) {

            throw new RuntimeException("El documento ya está asociado a la memoria");
        }

        // Crear relación
        MemoriaDocumento md = new MemoriaDocumento(memoria, documento);

        memoriaDocumentoRepository.save(md);
    }


    public List<MemoriaDocumentoResponse> listarDocumentosDeMemoriaDirector(
            Usuario usuario,
            Long oidMemoria
    ) {

        // Validar usuario
        if (usuario.getPersona() == null ||
                usuario.getPersona().getGrupo() == null) {

            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        // Validar rol (solo Director)
        if (usuario.getRole() != Role.Director) {
            throw new RuntimeException("Solo el Director puede acceder");
        }

        long oidGrupoUsuario =
                usuario.getPersona().getGrupo().getOidGrupo();

        // Buscar memoria
        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        // Validar grupo
        if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("La memoria no pertenece a su grupo");
        }

        // Obtener documentos
        return memoriaDocumentoRepository.findByMemoria(memoria)
                .stream()
                .map(md -> new MemoriaDocumentoResponse(
                        md.getOidDocumento(),
                        md.getTitulo(),
                        md.getAutores(),
                        md.getEditorial(),
                        md.getAnio()
                ))
                .toList();
    }


    public void quitarDocumentoDeMemoriaDirector(
            Usuario usuario,
            Long oidMemoria,
            Long oidDocumento
    ) {

        // Validar usuario
        if (usuario.getPersona() == null ||
                usuario.getPersona().getGrupo() == null) {

            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        // Validar rol (solo Director)
        if (usuario.getRole() != Role.Director) {
            throw new RuntimeException("Solo el Director puede quitar documentos");
        }

        long oidGrupoUsuario =
                usuario.getPersona().getGrupo().getOidGrupo();

        // Buscar memoria
        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        // Validar grupo
        if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("La memoria no pertenece a su grupo");
        }

        // Buscar relación documento-memoria
        MemoriaDocumento md = memoriaDocumentoRepository
                .findByMemoriaAndOidDocumento(memoria, oidDocumento)
                .orElseThrow(() ->
                        new RuntimeException("El documento no está en la memoria"));

        // Eliminar relación
        memoriaDocumentoRepository.delete(md);
    }





}
