package com.grupo7.TrabajoDeCampo.service.tipoPersonaPackageService;

import com.grupo7.TrabajoDeCampo.model.Investigador;
import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackageRepository.InvestigadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestigadorService {
    private final InvestigadorRepository investigadorRepository;

    public InvestigadorService(InvestigadorRepository investigadorRepository){
        this.investigadorRepository= investigadorRepository;
    }

    public List<Investigador> listarInvestigadores(){ return investigadorRepository.findAll();}

    public Optional<Investigador> obtenerInvestigadorPorId(Long id){return investigadorRepository.findById(id);}

    public Investigador crearInvestigador (Persona persona){
        Investigador investigador = new Investigador();
        investigador.setPersona(persona);
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
