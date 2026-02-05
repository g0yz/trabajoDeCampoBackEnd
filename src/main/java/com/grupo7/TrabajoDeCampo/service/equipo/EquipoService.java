package com.grupo7.TrabajoDeCampo.service.equipo;


import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.equipo.EquipoResponseAdministrador;
import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.equipo.EquipoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.model.equipo.Equipo;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.repository.equipo.EquipoRepository;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {

    private final EquipoRepository equipoRepository;
    private final GrupoRepository grupoRepository;

    public EquipoService (EquipoRepository equipoRepository, GrupoRepository grupoRepository){
        this.equipoRepository = equipoRepository;
        this.grupoRepository = grupoRepository;
    }

    public List<EquipoResponseAdministrador> listarEquipos() {

        return equipoRepository.findAll()
                .stream()
                .map(e -> new EquipoResponseAdministrador(
                        e.getOidEquipo(),
                        e.getDenominacion(),
                        e.getFechaIncorporacion(),
                        e.getMontoInvertido(),
                        e.getDescripcion(),
                        e.getActivo(),
                        e.getGrupo().getOidGrupo(),
                        e.getGrupo().getNombreGrupo()
                ))
                .toList();
    }

    //MODIFICAR CON EQUIPORESPONSEADMINISTRADOR
    public Optional<Equipo> obtenerEquipoPorId(Long oid){
        return equipoRepository.findById(oid);
    }

    //MODIFICAR CON EQUIPOR ESPONSE ADMINISTRADOR
    public Equipo crearEquipo(Equipo equipo, Long oid){
        Grupo grupo = grupoRepository.findById(oid)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con oid: " + oid));
        equipo.setGrupo(grupo);
        return equipoRepository.save(equipo);
    }

    //MODIFICAR CON EQUIPORESPONSEADMINISTRADOR
    public Equipo actualizarEquipo(Long oid, Equipo equipoActualizado){
        Equipo equipo = equipoRepository.findById(oid)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado con oid: " + oid));

        if (equipoActualizado.getDenominacion() != null) {
            equipo.setDenominacion(equipoActualizado.getDenominacion());
        }

        if (equipoActualizado.getFechaIncorporacion() != null) {
            equipo.setFechaIncorporacion(equipoActualizado.getFechaIncorporacion());
        }

        if (equipoActualizado.getMontoInvertido() != null) {
            equipo.setMontoInvertido(equipoActualizado.getMontoInvertido());
        }

        if (equipoActualizado.getDescripcion() != null) {
            equipo.setDescripcion(equipoActualizado.getDescripcion());
        }

        if (equipoActualizado.getGrupo() != null) {
            equipo.setGrupo(equipoActualizado.getGrupo());
        }
        return equipoRepository.save(equipo);
    }

    public void eliminarEquipo(Long oid){
        equipoRepository.deleteById(oid);
    }


    public List<EquipoResponseIntegrante> listarEquiposDelGrupoIntegrante(Long oidGrupo) {

        return equipoRepository.findByGrupoOidGrupoAndActivoTrue(oidGrupo)
                .stream()
                .map(eq -> new EquipoResponseIntegrante(
                        eq.getOidEquipo(),
                        eq.getDenominacion(),
                        eq.getFechaIncorporacion(),
                        eq.getMontoInvertido(),
                        eq.getDescripcion(),
                        eq.getActivo()
                ))
                .toList();
    }

    public EquipoResponseIntegrante obtenerEquipoDelGrupoIntegrante(
            Long oidEquipo,
            Long oidGrupo
    ) {

        Equipo equipo = equipoRepository
                .findByOidEquipoAndGrupoOidGrupoAndActivoTrue(oidEquipo, oidGrupo)
                .orElseThrow(() ->
                        new RuntimeException("Equipo no encontrado o no pertenece al grupo")
                );

        return new EquipoResponseIntegrante(
                equipo.getOidEquipo(),
                equipo.getDenominacion(),
                equipo.getFechaIncorporacion(),
                equipo.getMontoInvertido(),
                equipo.getDescripcion(),
                equipo.getActivo()
        );
    }



}

