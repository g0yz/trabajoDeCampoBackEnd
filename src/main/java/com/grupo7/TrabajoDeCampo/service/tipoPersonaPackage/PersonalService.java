package com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage;

import com.grupo7.TrabajoDeCampo.DTO.tipoPersonaPackage.PersonalResponse;
import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.model.tipoPersonaPackage.Personal;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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



}
