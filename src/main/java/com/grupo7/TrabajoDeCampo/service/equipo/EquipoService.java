package com.grupo7.TrabajoDeCampo.service.equipo;


import com.grupo7.TrabajoDeCampo.dto.equipo.EquipoResponse;

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


    //ADMINISTRADOR

    public List<EquipoResponse> listarEquiposAdmin() {

        return equipoRepository.findAll()
                .stream()
                .map(e -> new EquipoResponse(
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

    public Optional<Equipo> obtenerEquipoPorIdAdmin(Long oid){
        return equipoRepository.findById(oid);
    }

    //MODIFICAR CON EQUIPOR ESPONSE ADMINISTRADOR
    public Equipo crearEquipoAdmin(Equipo equipo, Long oid){
        Grupo grupo = grupoRepository.findById(oid)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con oid: " + oid));
        equipo.setGrupo(grupo);
        return equipoRepository.save(equipo);
    }

    public Equipo actualizarEquipoAdmin(Long oid, Equipo equipoActualizado){
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

    public void eliminarEquipoAdmin(Long oid){
        equipoRepository.deleteById(oid);
    }


    //INTEGRANTE

    public List<EquipoResponse> listarEquiposDelGrupoIntegrante(Long oidGrupo) {

        return equipoRepository.findByGrupoOidGrupoAndActivoTrue(oidGrupo)
                .stream()
                .map(eq -> new EquipoResponse(
                        eq.getOidEquipo(),
                        eq.getDenominacion(),
                        eq.getFechaIncorporacion(),
                        eq.getMontoInvertido(),
                        eq.getDescripcion(),
                        eq.getActivo()
                ))
                .toList();
    }

    public EquipoResponse obtenerEquipoDelGrupoIntegrante(
            Long oidEquipo,
            Long oidGrupo
    ) {

        Equipo equipo = equipoRepository
                .findByOidEquipoAndGrupoOidGrupoAndActivoTrue(oidEquipo, oidGrupo)
                .orElseThrow(() ->
                        new RuntimeException("Equipo no encontrado o no pertenece al grupo")
                );

        return new EquipoResponse(
                equipo.getOidEquipo(),
                equipo.getDenominacion(),
                equipo.getFechaIncorporacion(),
                equipo.getMontoInvertido(),
                equipo.getDescripcion(),
                equipo.getActivo()
        );
    }

    //DIRECTOR



}

