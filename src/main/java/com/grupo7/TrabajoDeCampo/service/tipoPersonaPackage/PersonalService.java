package com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage;

import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.model.Personal;
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

    public List<Personal> listarPersonal(){ return personalRepository.findAll();}

    public Optional<Personal> obtenerPersonalPorId(Long oid){return personalRepository.findById(oid);}

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
