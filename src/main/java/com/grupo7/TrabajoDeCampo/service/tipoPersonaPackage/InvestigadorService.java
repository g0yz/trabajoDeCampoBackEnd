package com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage;

import com.grupo7.TrabajoDeCampo.model.Becario;
import com.grupo7.TrabajoDeCampo.model.Investigador;
import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.repository.PersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage.InvestigadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigadorService {
    private final InvestigadorRepository investigadorRepository;
    private final PersonaRepository personaRepository;

    public InvestigadorService(InvestigadorRepository investigadorRepository, PersonaRepository personaRepository){
        this.investigadorRepository= investigadorRepository;
        this.personaRepository = personaRepository;
    }

    public List<Investigador> listarInvestigadores(){ return investigadorRepository.findAll();}

    public Optional<Investigador> obtenerInvestigadorPorId(Long id){return investigadorRepository.findById(id);}

    public Investigador crearInvestigador (Investigador datosInvestigador, Long personaId){
        Persona persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));


        Investigador investigador = new Investigador();
        investigador.setPersona(persona);

        investigador.setCategoriaUTN(datosInvestigador.getCategoriaUTN());
        investigador.setProgramaDeIncentivos(datosInvestigador.getProgramaDeIncentivos());
        investigador.setDedicacion(datosInvestigador.getDedicacion());
        investigador.setGradoAcademico(datosInvestigador.getGradoAcademico());

        return investigadorRepository.save(investigador);
    }



    public Investigador actualizarInvestigador (Long id, Investigador investigadorActualizado){
        Investigador investigador = investigadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Investigador no encontrada con id: " + id));

        if (investigadorActualizado.getCategoriaUTN() != null)
            investigador.setCategoriaUTN(investigadorActualizado.getCategoriaUTN());

        if (investigadorActualizado.getProgramaDeIncentivos() != null)
            investigador.setProgramaDeIncentivos(investigadorActualizado.getProgramaDeIncentivos());

        if (investigadorActualizado.getDedicacion() != null)
            investigador.setDedicacion(investigadorActualizado.getDedicacion());

        if (investigadorActualizado.getGradoAcademico() != null)
            investigador.setGradoAcademico(investigadorActualizado.getGradoAcademico());

        return investigadorRepository.save(investigador);
    }


    public void eliminarInvestigador(Long id){ investigadorRepository.deleteById(id);}



}
