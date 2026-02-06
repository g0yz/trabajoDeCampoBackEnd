package com.grupo7.TrabajoDeCampo.service.persona.tipoPersona;

import com.grupo7.TrabajoDeCampo.dto.dtoAdministrador.tipoPersona.InvestigadorResponseAdministrador;
import com.grupo7.TrabajoDeCampo.dto.dtoIntegrante.persona.InvestigadorResponseIntegrante;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Investigador;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.repository.persona.PersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.InvestigadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestigadorService {
    private final InvestigadorRepository investigadorRepository;
    private final PersonaRepository personaRepository;

    public InvestigadorService(InvestigadorRepository investigadorRepository, PersonaRepository personaRepository){
        this.investigadorRepository= investigadorRepository;
        this.personaRepository = personaRepository;
    }


    public List<InvestigadorResponseAdministrador> listarInvestigadores() {
        return investigadorRepository.findAll()
                .stream()
                .map(i -> new InvestigadorResponseAdministrador(
                        i.getOidInvestigador(),
                        i.getCategoriaUTN(),
                        i.getProgramaDeIncentivos(),
                        i.getDedicacion(),
                        i.getGradoAcademico(),
                        i.getActivo(),

                        // Persona
                        i.getPersona().getNombre(),
                        i.getPersona().getApellido(),
                        i.getPersona().getHorasSemanales(),

                        // Grupo
                        i.getPersona().getGrupo().getOidGrupo(),
                        i.getPersona().getGrupo().getNombreGrupo()
                ))
                .toList();
    }

    public InvestigadorResponseAdministrador obtenerInvestigadorPorId(Long oidInvestigador) {

        Investigador i = investigadorRepository.findById(oidInvestigador)
                .orElseThrow(() -> new RuntimeException("Investigador no encontrado"));

        return new InvestigadorResponseAdministrador(
                i.getOidInvestigador(),
                i.getCategoriaUTN(),
                i.getProgramaDeIncentivos(),
                i.getDedicacion(),
                i.getGradoAcademico(),
                i.getActivo(),

                // Persona
                i.getPersona().getNombre(),
                i.getPersona().getApellido(),
                i.getPersona().getHorasSemanales(),

                // Grupo
                i.getPersona().getGrupo().getOidGrupo(),
                i.getPersona().getGrupo().getNombreGrupo()
        );
    }




    public Investigador crearInvestigador (Investigador datosInvestigador, Long oid){
        Persona persona = personaRepository.findById(oid)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));


        Investigador investigador = new Investigador();
        investigador.setPersona(persona);

        investigador.setCategoriaUTN(datosInvestigador.getCategoriaUTN());
        investigador.setProgramaDeIncentivos(datosInvestigador.getProgramaDeIncentivos());
        investigador.setDedicacion(datosInvestigador.getDedicacion());
        investigador.setGradoAcademico(datosInvestigador.getGradoAcademico());

        return investigadorRepository.save(investigador);
    }



    public Investigador actualizarInvestigador (Long oid, Investigador investigadorActualizado){
        Investigador investigador = investigadorRepository.findById(oid).orElseThrow(() -> new RuntimeException("Investigador no encontrada con oid: " + oid));

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


    public void eliminarInvestigador(Long oid){ investigadorRepository.deleteById(oid);}


    public List<InvestigadorResponseIntegrante> listarInvestigadoresDelGrupo(Long oidGrupo) {

        return investigadorRepository
                .findByPersonaGrupoOidGrupoAndPersonaActivoTrue(oidGrupo)
                .stream()
                .map(i -> new InvestigadorResponseIntegrante(
                        i.getOidInvestigador(),
                        i.getCategoriaUTN(),
                        i.getProgramaDeIncentivos(),
                        i.getDedicacion(),
                        i.getGradoAcademico(),
                        i.getActivo(),
                        i.getPersona().getNombre(),
                        i.getPersona().getApellido(),
                        i.getPersona().getHorasSemanales()
                ))
                .toList();
    }

    public InvestigadorResponseIntegrante obtenerInvestigadorDelGrupo(
            Long oidGrupo,
            Long oidInvestigador) {

        Investigador investigador = investigadorRepository
                .findByOidInvestigadorAndPersonaGrupoOidGrupoAndPersonaActivoTrue(
                        oidInvestigador, oidGrupo
                )
                .orElseThrow(() -> new RuntimeException("Investigador no encontrado"));

        return new InvestigadorResponseIntegrante(
                investigador.getOidInvestigador(),
                investigador.getCategoriaUTN(),
                investigador.getProgramaDeIncentivos(),
                investigador.getDedicacion(),
                investigador.getGradoAcademico(),
                investigador.getActivo(),
                investigador.getPersona().getNombre(),
                investigador.getPersona().getApellido(),
                investigador.getPersona().getHorasSemanales()
        );
    }




}
