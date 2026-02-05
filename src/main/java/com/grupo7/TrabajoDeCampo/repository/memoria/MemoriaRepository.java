package com.grupo7.TrabajoDeCampo.repository.memoria;

import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface MemoriaRepository extends JpaRepository<Memoria, Long> {

    Optional<Memoria> findByGrupoAndAnio(Grupo grupo, Integer anio);

    List<Memoria> findByGrupo(Grupo grupo);


    // listar memorias de un grupo
    List<Memoria> findByGrupoOidGrupo(Long oidGrupo);

    // buscar una memoria específica del grupo
    Optional<Memoria> findByOidMemoriaAndGrupoOidGrupo(Long oidMemoria, Long oidGrupo);

}
