package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.dto.memoria.*;

import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;

import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaDetalle;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaPersona;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class MemoriaService {

    private final MemoriaRepository memoriaRepository;
    private final MemoriaDocumentoRepository memoriaDocumentoRepository;
    private final MemoriaEquipoRepository memoriaEquipoRepository;
    private final MemoriaPersonaRepository memoriaPersonaRepository;
    private final GrupoRepository grupoRepository;

    public MemoriaService(
            MemoriaRepository memoriaRepository,
            GrupoRepository grupoRepository,
            MemoriaDocumentoRepository memoriaDocumentoRepository,
            MemoriaEquipoRepository memoriaEquipoRepository,
            MemoriaPersonaRepository memoriaPersonaRepository
    ) {
        this.memoriaRepository = memoriaRepository;
        this.grupoRepository = grupoRepository;
        this.memoriaDocumentoRepository = memoriaDocumentoRepository;
        this.memoriaEquipoRepository = memoriaEquipoRepository;
        this.memoriaPersonaRepository = memoriaPersonaRepository;
    }

    // ========================= ADMIN =========================

    public Memoria crearMemoriaAdmin(Long oidGrupo, Integer anio) {
        Grupo grupo = grupoRepository.findById(oidGrupo)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        memoriaRepository.findByGrupoAndAnio(grupo, anio)
                .ifPresent(m -> {
                    throw new RuntimeException("Ya existe una memoria para ese año");
                });

        Memoria memoria = new Memoria(
                new Timestamp(System.currentTimeMillis()),
                anio,
                grupo
        );

        return memoriaRepository.save(memoria);
    }

    public List<MemoriaResponse> listarTodasLasMemoriasAdmin() {
        return memoriaRepository.findAll()
                .stream()
                .map(m -> new MemoriaResponse(
                        m.getOidMemoria(),
                        m.getAnio(),
                        m.getFechaCreacion(),
                        m.getGrupo().getOidGrupo(),
                        m.getGrupo().getNombreGrupo()
                ))
                .toList();
    }

    public MemoriaDetalleResponse obtenerMemoriaEspecifica(Long oidMemoria) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        List<MemoriaPersonaResponse> personas =
                memoriaPersonaRepository.findByMemoria(memoria)
                        .stream()
                        .map(mp -> new MemoriaPersonaResponse(
                                mp.getNombre(),
                                mp.getApellido(),
                                mp.getHorasSemanales(),
                                mp.getTipoPersona(),
                                mp.getCategoriaUTN(),
                                mp.getProgramaDeIncentivos(),
                                mp.getDedicacion(),
                                mp.getGradoAcademico(),
                                mp.getFuenteDeFinanciamiento(), // <-- primero fuente
                                mp.getTipoBecario(),
                                mp.getTipoPersonal(),
                                mp.getCargo()
                        ))
                        .toList();

        List<MemoriaDocumentoResponse> documentos =
                memoriaDocumentoRepository.findByMemoria(memoria)
                        .stream()
                        .map(md -> new MemoriaDocumentoResponse(
                                md.getOidDocumento(),
                                md.getTitulo(),
                                md.getAutores(),
                                md.getEditorial(),
                                md.getAnio()
                        ))
                        .toList();

        List<MemoriaEquipoResponse> equipos =
                memoriaEquipoRepository.findByMemoria(memoria)
                        .stream()
                        .map(me -> new MemoriaEquipoResponse(
                                me.getOidEquipo(),
                                me.getDenominacion(),
                                me.getFechaIncorporacion(),
                                me.getMontoInvertido(),
                                me.getDescripcion()
                        ))
                        .toList();

        return new MemoriaDetalleResponse(
                memoria.getOidMemoria(),
                memoria.getAnio(),
                memoria.getFechaCreacion(),
                memoria.getGrupo(),
                documentos,
                equipos,
                personas
        );
    }



    public MemoriaDetalleResponse obtenerMemoriaEspecificaGrupo(
            Authentication auth,
            Long oidMemoria
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        Long oidGrupoUsuario = usuario.getPersona().getGrupo().getOidGrupo();


        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("No tenés permisos para ver esta memoria");
        }

        List<MemoriaPersonaResponse> personas =
                memoriaPersonaRepository.findByMemoria(memoria)
                        .stream()
                        .map(mp -> new MemoriaPersonaResponse(
                                mp.getNombre(),
                                mp.getApellido(),
                                mp.getHorasSemanales(),
                                mp.getTipoPersona(),
                                mp.getCategoriaUTN(),
                                mp.getProgramaDeIncentivos(),
                                mp.getDedicacion(),
                                mp.getGradoAcademico(),
                                mp.getFuenteDeFinanciamiento(),
                                mp.getTipoBecario(),
                                mp.getTipoPersonal(),
                                mp.getCargo()
                        ))
                        .toList();

        List<MemoriaDocumentoResponse> documentos =
                memoriaDocumentoRepository.findByMemoria(memoria)
                        .stream()
                        .map(md -> new MemoriaDocumentoResponse(
                                md.getOidDocumento(),
                                md.getTitulo(),
                                md.getAutores(),
                                md.getEditorial(),
                                md.getAnio()
                        ))
                        .toList();

        List<MemoriaEquipoResponse> equipos =
                memoriaEquipoRepository.findByMemoria(memoria)
                        .stream()
                        .map(me -> new MemoriaEquipoResponse(
                                me.getOidEquipo(),
                                me.getDenominacion(),
                                me.getFechaIncorporacion(),
                                me.getMontoInvertido(),
                                me.getDescripcion()
                        ))
                        .toList();

        return new MemoriaDetalleResponse(
                memoria.getOidMemoria(),
                memoria.getAnio(),
                memoria.getFechaCreacion(),
                memoria.getGrupo(),
                documentos,
                equipos,
                personas
        );
    }



































    private MemoriaPersonaResponse mapMemoriaPersona(MemoriaPersona mp) {
        return new MemoriaPersonaResponse(
                mp.getNombre(),
                mp.getApellido(),
                mp.getHorasSemanales(),
                mp.getTipoPersona(),
                mp.getCategoriaUTN(),
                mp.getProgramaDeIncentivos(),
                mp.getDedicacion(),
                mp.getGradoAcademico(),
                mp.getFuenteDeFinanciamiento(), // <-- primero fuente
                mp.getTipoBecario(),
                mp.getTipoPersonal(),
                mp.getCargo()
        );
    }




    public List<MemoriaResponse> listarMemoriasDelGrupo(Long oidGrupo) {
        return memoriaRepository.findByGrupoOidGrupo(oidGrupo)
                .stream()
                .map(m -> new MemoriaResponse(
                        m.getOidMemoria(),
                        m.getAnio(),
                        m.getFechaCreacion()
                ))
                .toList();
    }


    // ========================= INTEGRANTE =========================
    // public MemoriaDetalle obtenerMemoriaIntegrante(Long oidMemoria) {

    //Memoria memoria = memoriaRepository.findById(oidMemoria)
    //          .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));


    //}


}







