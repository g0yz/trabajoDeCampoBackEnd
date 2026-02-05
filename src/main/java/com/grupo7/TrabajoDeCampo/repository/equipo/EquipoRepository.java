package com.grupo7.TrabajoDeCampo.repository.equipo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.grupo7.TrabajoDeCampo.model.equipo.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository extends JpaRepository <Equipo, Long> {



        List<Equipo> findByGrupoOidGrupo(Long oidGrupo);

        Optional<Equipo> findByOidEquipoAndGrupoOidGrupo(Long oidEquipo, Long oidGrupo);


}
