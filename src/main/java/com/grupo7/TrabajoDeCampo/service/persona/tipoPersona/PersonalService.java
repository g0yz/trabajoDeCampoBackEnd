package com.grupo7.TrabajoDeCampo.service.persona.tipoPersona;

import com.grupo7.TrabajoDeCampo.dto.tipoPersona.PersonalResponse;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Personal;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository){
        this.personalRepository = personalRepository;

    }

    public List<PersonalResponse> listarPersonal() {
        return personalRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    public PersonalResponse obtenerPersonalPorId(Long oidPersonal) {

        Personal personal = personalRepository.findById(oidPersonal)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));

        return mapearAResponse(personal);
    }

    private PersonalResponse mapearAResponse(Personal p) {
        return new PersonalResponse(
                p.getOidPersonal(),
                p.getTipoPersonal(),
                p.getActivo(),

                // Persona
                p.getPersona().getNombre(),
                p.getPersona().getApellido(),
                p.getPersona().getHorasSemanales(),

                // Grupo
                p.getPersona().getGrupo().getOidGrupo(),
                p.getPersona().getGrupo().getNombreGrupo()
        );
    }


    public Personal crearPersonal (Persona persona){
        Personal personal = new Personal();
        personal.setPersona(persona);
        return personalRepository.save(personal);
    }


    public Personal actualizarPersonal (Long oid, Personal personalActualizado){
        Personal personal = personalRepository.findById(oid).orElseThrow(() -> new RuntimeException("Personal no encontrado con oid: " + oid));

        if (personalActualizado.getTipoPersonal() != null)
            personal.setTipoPersonal(personalActualizado.getTipoPersonal());


        return personalRepository.save(personal);

    }

    public void eliminarPersonal (Long oid){ personalRepository.deleteById(oid);}



    public List<PersonalResponse> listarPersonalDelGrupo(Long oidGrupo) {

        return personalRepository
                .findByPersonaGrupoOidGrupoAndPersonaActivoTrue(oidGrupo)
                .stream()
                .map(p -> new PersonalResponse(
                        p.getOidPersonal(),
                        p.getTipoPersonal(),
                        p.getActivo(),
                        p.getPersona().getNombre(),
                        p.getPersona().getApellido(),
                        p.getPersona().getHorasSemanales()
                ))
                .toList();
    }



    public PersonalResponse obtenerPersonalDelGrupo(
            Long oidGrupo,
            Long oidPersonal) {

        Personal personal = personalRepository
                .findByOidPersonalAndPersonaGrupoOidGrupoAndPersonaActivoTrue(
                        oidPersonal, oidGrupo
                )
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));

        return new PersonalResponse(
                personal.getOidPersonal(),
                personal.getTipoPersonal(),
                personal.getActivo(),
                personal.getPersona().getNombre(),
                personal.getPersona().getApellido(),
                personal.getPersona().getHorasSemanales()
        );
    }








}
