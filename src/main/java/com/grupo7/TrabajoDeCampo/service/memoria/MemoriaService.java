package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.memoria.*;
import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.memoria.*;
import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import com.grupo7.TrabajoDeCampo.model.equipo.Equipo;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaDocumento;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaEquipo;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaDocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaEquipoRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaPersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaRepository;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.util.List;

import static com.grupo7.TrabajoDeCampo.model.persona.TipoPersona.*;

@Service
public class MemoriaService {

    private final MemoriaRepository memoriaRepository;
    private final MemoriaDocumentoRepository memoriaDocumentoRepository;
    private final MemoriaEquipoRepository memoriaEquipoRepository;
    private final MemoriaPersonaRepository memoriaPersonaRepository;
    private final GrupoRepository grupoRepository;

    public MemoriaService(MemoriaRepository memoriaRepository, GrupoRepository grupoRepository, MemoriaDocumentoRepository memoriaDocumentoRepository, MemoriaEquipoRepository memoriaEquipoRepository, MemoriaPersonaRepository memoriaPersonaRepository) {
        this.memoriaRepository = memoriaRepository;
        this.grupoRepository = grupoRepository;
        this.memoriaDocumentoRepository = memoriaDocumentoRepository;
        this.memoriaEquipoRepository = memoriaEquipoRepository;
        this.memoriaPersonaRepository = memoriaPersonaRepository;

    }


    private MemoriaPersonaResponseAdministrador mapPersonaAdmin(
            Persona p, Grupo grupo) {

        return switch (p.getTipoPersona()) {

            case Investigador -> new MemoriaPersonaResponseAdministrador(
                    p.getOidPersona(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getHorasSemanales(),
                    p.getTipoPersona(),
                    p.getInvestigador().getCategoriaUTN(),
                    p.getInvestigador().getProgramaDeIncentivos(),
                    p.getInvestigador().getDedicacion(),
                    p.getInvestigador().getGradoAcademico(),
                    null,
                    null,
                    null,
                    null,
                    grupo.getOidGrupo()
            );

            case Becario -> new MemoriaPersonaResponseAdministrador(
                    p.getOidPersona(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getHorasSemanales(),
                    p.getTipoPersona(),
                    null,
                    null,
                    null,
                    null,
                    p.getBecario().getFuenteFinanciamiento(),
                    p.getBecario().getTipoBecario(),
                    null,
                    null,
                    grupo.getOidGrupo()
            );

            case Personal -> new MemoriaPersonaResponseAdministrador(
                    p.getOidPersona(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getHorasSemanales(),
                    p.getTipoPersona(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    p.getPersonal().getTipoPersonal(),
                    null,
                    grupo.getOidGrupo()
            );

            case IntegranteConsejoEducativo -> new MemoriaPersonaResponseAdministrador(
                    p.getOidPersona(),
                    p.getNombre(),
                    p.getApellido(),
                    p.getHorasSemanales(),
                    p.getTipoPersona(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    p.getIntegranteConsejoEducativo().getCargo(),
                    grupo.getOidGrupo()
            );
        };
    }





    private MemoriaPersonaResponseIntegrante mapPersonaIntegrante(Persona p) {

        return switch (p.getTipoPersona()) {

            case Investigador -> new MemoriaPersonaResponseIntegrante(
                    p.getNombre(),
                    p.getApellido(),
                    p.getHorasSemanales(),
                    p.getTipoPersona(),
                    p.getInvestigador().getCategoriaUTN(),
                    p.getInvestigador().getProgramaDeIncentivos(),
                    p.getInvestigador().getDedicacion(),
                    p.getInvestigador().getGradoAcademico(),
                    null,
                    null,
                    null,
                    null
            );

            case Becario -> new MemoriaPersonaResponseIntegrante(
                    p.getNombre(),
                    p.getApellido(),
                    p.getHorasSemanales(),
                    p.getTipoPersona(),
                    null,
                    null,
                    null,
                    null,
                    p.getBecario().getFuenteFinanciamiento(),
                    p.getBecario().getTipoBecario(),
                    null,
                    null
            );

            case Personal -> new MemoriaPersonaResponseIntegrante(
                    p.getNombre(),
                    p.getApellido(),
                    p.getHorasSemanales(),
                    p.getTipoPersona(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    p.getPersonal().getTipoPersonal(),
                    null
            );

            case IntegranteConsejoEducativo -> new MemoriaPersonaResponseIntegrante(
                    p.getNombre(),
                    p.getApellido(),
                    p.getHorasSemanales(),
                    p.getTipoPersona(),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    p.getIntegranteConsejoEducativo().getCargo()
            );
        };
    }







    public Memoria crearMemoria(Long oidGrupo, Integer anio) {
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

    public List<MemoriaResponseAdministrador> listarTodasLasMemorias() {

        return memoriaRepository.findAll()
                .stream()
                .map(m -> new MemoriaResponseAdministrador(
                        m.getOidMemoria(),
                        m.getAnio(),
                        m.getFechaCreacion().toInstant(),
                        m.getGrupo().getOidGrupo(),
                        m.getGrupo().getNombreGrupo()
                ))
                .toList();
    }


    public List<MemoriaResponseAdministrador> listarPorGrupo(Long oidGrupo) {

        Grupo grupo = grupoRepository.findById(oidGrupo)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        return memoriaRepository.findByGrupo(grupo)
                .stream()
                .map(m -> new MemoriaResponseAdministrador(
                        m.getOidMemoria(),
                        m.getAnio(),
                        m.getFechaCreacion().toInstant(),
                        grupo.getOidGrupo(),
                        grupo.getNombreGrupo()
                ))
                .toList();
    }


    public MemoriaDetalleResponseAdministrador obtenerMemoriaCompleta(Long oidMemoria) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        List<MemoriaDocumentoResponseAdministrador> documentos =
                memoriaDocumentoRepository.findByMemoria(memoria)
                        .stream()
                        .map(md -> {
                            Documento d = md.getDocumento();
                            return new MemoriaDocumentoResponseAdministrador(
                                    d.getOidDocumento(),
                                    d.getTitulo(),
                                    d.getAutores(),
                                    d.getEditorial(),
                                    d.getAnio(),
                                    d.getActivo()
                            );
                        })
                        .toList();

        List<MemoriaEquipoResponseAdministrador> equipos =
                memoriaEquipoRepository.findByMemoria(memoria)
                        .stream()
                        .map(me -> {
                            Equipo e = me.getEquipo();
                            return new MemoriaEquipoResponseAdministrador(
                                    e.getOidEquipo(),
                                    e.getDenominacion(),
                                    e.getFechaIncorporacion(),
                                    e.getMontoInvertido(),
                                    e.getDescripcion(),
                                    e.getActivo()
                            );
                        })
                        .toList();

        List<MemoriaPersonaResponseAdministrador> personas =
                memoriaPersonaRepository.findByMemoria(memoria)
                        .stream()
                        .map(mp -> mapPersonaAdmin(mp.getPersona(), memoria.getGrupo()))
                        .toList();

        return new MemoriaDetalleResponseAdministrador(
                memoria.getOidMemoria(),
                memoria.getAnio(),
                memoria.getFechaCreacion().toInstant(),
                memoria.getGrupo().getOidGrupo(),
                memoria.getGrupo().getNombreGrupo(),
                personas,
                documentos,
                equipos);

    }

    ;


    public List<MemoriaResponseIntegrante> listarMemoriasDelGrupo(Long oidGrupo) {

        return memoriaRepository
                .findByGrupoOidGrupo(oidGrupo)
                .stream()
                .map(m -> new MemoriaResponseIntegrante(
                        m.getOidMemoria(),
                        m.getAnio(),
                        m.getFechaCreacion()
                ))
                .toList();
    }


    public MemoriaDetalleResponseIntegrante obtenerMemoriaIntegrante(Long oidMemoria) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        List<MemoriaDocumentoResponseIntegrante> documentos =
                memoriaDocumentoRepository.findByMemoria(memoria)
                        .stream()
                        .map(md -> {
                            Documento d = md.getDocumento();
                            return new MemoriaDocumentoResponseIntegrante(
                                    d.getOidDocumento(),
                                    d.getTitulo(),
                                    d.getAutores(),
                                    d.getEditorial(),
                                    d.getAnio(),
                                    d.getActivo()
                            );
                        })
                        .toList();

        List<MemoriaEquipoResponseIntegrante> equipos =
                memoriaEquipoRepository.findByMemoria(memoria)
                        .stream()
                        .map(me -> {
                            Equipo e = me.getEquipo();
                            return new MemoriaEquipoResponseIntegrante(
                                    e.getOidEquipo(),
                                    e.getDenominacion(),
                                    e.getFechaIncorporacion(),
                                    e.getMontoInvertido(),
                                    e.getDescripcion(),
                                    e.getActivo()
                            );
                        })
                        .toList();

        List<MemoriaPersonaResponseIntegrante> personas =
                memoriaPersonaRepository.findByMemoria(memoria)
                        .stream()
                        .map(mp -> mapPersonaIntegrante(mp.getPersona()))
                        .toList();

        return new MemoriaDetalleResponseIntegrante(
                memoria.getOidMemoria(),          // opcional, pero está bien
                memoria.getAnio(),
                memoria.getFechaCreacion().toInstant(),
                documentos,
                equipos,
                personas
        );







    }

}