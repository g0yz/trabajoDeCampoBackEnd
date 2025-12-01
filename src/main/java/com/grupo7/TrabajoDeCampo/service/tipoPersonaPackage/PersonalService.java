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

    public Optional<Personal> obtenerPersonalPorId(Long id){return personalRepository.findById(id);}

    public Personal crearPersonal (Persona persona){
        Personal personal = new Personal();
        personal.setPersona(persona);
        return personalRepository.save(personal);
    }


    public Personal actualizarPersonal (Long id, Personal personalActualizado){
        Personal personal = personalRepository.findById(id).orElseThrow(() -> new RuntimeException("Personal no encontrado con id: " + id));

        if (personalActualizado.getTipoPersonal() != null)
            personal.setTipoPersonal(personalActualizado.getTipoPersonal());


        return personalRepository.save(personal);

    }

    public void eliminarPersonal (Long id){ personalRepository.deleteById(id);}



}
