package com.grupo7.TrabajoDeCampo.service.equipo;


import com.grupo7.TrabajoDeCampo.dto.dtoAdministrador.equipo.EquipoResponseAdministrador;
import com.grupo7.TrabajoDeCampo.dto.dtoDirector.equipo.EquipoRequestDirector;
import com.grupo7.TrabajoDeCampo.dto.dtoDirector.equipo.EquipoResponseDirector;
import com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.equipo.EquipoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.model.equipo.Equipo;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
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

    public List<EquipoResponseAdministrador> listarEquiposAdmin() {

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

    //DIRECTOR

    public List<EquipoResponseDirector> listarEquiposDirector(Usuario usuario) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return equipoRepository.findByGrupoOidGrupoAndActivoTrue(oidGrupo)
                .stream()
                .map(e -> new EquipoResponseDirector(
                        e.getOidEquipo(), e.getDenominacion(), e.getFechaIncorporacion(),
                        e.getMontoInvertido(), e.getDescripcion(), e.getActivo()
                ))
                .toList();
    }

    public EquipoResponseDirector obtenerEquipoDirector(Long oidEquipo, Usuario usuario) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        Equipo e = equipoRepository.findByOidEquipoAndGrupoOidGrupoAndActivoTrue(oidEquipo, oidGrupo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado en el grupo del director"));
        return new EquipoResponseDirector(
                e.getOidEquipo(), e.getDenominacion(), e.getFechaIncorporacion(),
                e.getMontoInvertido(), e.getDescripcion(), e.getActivo()
        );
    }

    public EquipoResponseDirector agregarEquipoDirector(Usuario usuario, EquipoRequestDirector request) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Grupo grupo = usuario.getPersona().getGrupo();
        Equipo equipo = new Equipo();
        equipo.setDenominacion(request.getDenominacion());
        equipo.setFechaIncorporacion(request.getFechaIncorporacion());
        equipo.setMontoInvertido(request.getMontoInvertido());
        equipo.setDescripcion(request.getDescripcion());
        equipo.setActivo(true);
        equipo.setGrupo(grupo);
        equipo = equipoRepository.save(equipo);
        return new EquipoResponseDirector(
                equipo.getOidEquipo(), equipo.getDenominacion(), equipo.getFechaIncorporacion(),
                equipo.getMontoInvertido(), equipo.getDescripcion(), equipo.getActivo()
        );
    }

    public EquipoResponseDirector editarEquipoDirector(Usuario usuario, Long oidEquipo, EquipoRequestDirector request) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        Equipo equipo = equipoRepository.findByOidEquipoAndGrupoOidGrupoAndActivoTrue(oidEquipo, oidGrupo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado en el grupo del director"));

        if (request.getDenominacion() != null) equipo.setDenominacion(request.getDenominacion());
        if (request.getFechaIncorporacion() != null) equipo.setFechaIncorporacion(request.getFechaIncorporacion());
        if (request.getMontoInvertido() != null) equipo.setMontoInvertido(request.getMontoInvertido());
        if (request.getDescripcion() != null) equipo.setDescripcion(request.getDescripcion());

        equipo = equipoRepository.save(equipo);
        return new EquipoResponseDirector(
                equipo.getOidEquipo(), equipo.getDenominacion(), equipo.getFechaIncorporacion(),
                equipo.getMontoInvertido(), equipo.getDescripcion(), equipo.getActivo()
        );
    }

    public void eliminarEquipoDirector(Usuario usuario, Long oidEquipo) {
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        Equipo equipo = equipoRepository.findByOidEquipoAndGrupoOidGrupoAndActivoTrue(oidEquipo, oidGrupo)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado en el grupo del director"));
        equipo.setActivo(false);
        equipoRepository.save(equipo);
    }
}

