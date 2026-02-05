package com.grupo7.TrabajoDeCampo.service.persona.tipoPersona;

import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.tipoPersonaPackage.PersonalResponseAdministrador;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.Personal;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersonaPackage.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalService {

    private final PersonalRepository personalRepository;

    public PersonalService(PersonalRepository personalRepository){
        this.personalRepository = personalRepository;

    }

    public List<PersonalResponseAdministrador> listarPersonal() {
        return personalRepository.findAll()
                .stream()
                .map(this::mapearAResponse)
                .toList();
    }

    public PersonalResponseAdministrador obtenerPersonalPorId(Long oidPersonal) {

        Personal personal = personalRepository.findById(oidPersonal)
                .orElseThrow(() -> new RuntimeException("Personal no encontrado"));

        return mapearAResponse(personal);
    }

    private PersonalResponseAdministrador mapearAResponse(Personal p) {
        return new PersonalResponseAdministrador(
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



}
