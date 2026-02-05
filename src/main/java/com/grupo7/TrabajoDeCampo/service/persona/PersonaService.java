package com.grupo7.TrabajoDeCampo.service.persona;

import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.persona.PersonaRequestAdministrador;

import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.tipoPersona.PersonaResponseAdministrador;


import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.Becario;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.Investigador;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.Personal;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.PersonaRepository;

import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersonaPackage.BecarioRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersonaPackage.IntegranteConsejoEducativoRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersonaPackage.InvestigadorRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersonaPackage.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final GrupoRepository grupoRepository;
    private final BecarioRepository becarioRepository;
    private final IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository;
    private final PersonalRepository personalRepository;
    private final InvestigadorRepository investigadorRepository;

    public PersonaService(PersonaRepository personaRepository, GrupoRepository grupoRepository, BecarioRepository becarioRepository, IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository, PersonalRepository personalRepository,
                          InvestigadorRepository investigadorRepository) {
        this.personaRepository = personaRepository;
        this.grupoRepository = grupoRepository;
        this.becarioRepository = becarioRepository;
        this.integranteConsejoEducativoRepository = integranteConsejoEducativoRepository;
        this.personalRepository = personalRepository;
        this.investigadorRepository = investigadorRepository;
    }


    public List<PersonaResponseAdministrador> listarPersonas() {

        return personaRepository.findAll()
                .stream()
                .map(p -> new PersonaResponseAdministrador(
                        p.getOidPersona(),
                        p.getNombre(),
                        p.getApellido(),
                        p.getHorasSemanales(),
                        p.getTipoPersona().name(),
                        p.getActivo(),
                        p.getGrupo().getOidGrupo(),
                        p.getGrupo().getNombreGrupo()
                ))
                .toList();
    }





    public Optional<Persona> obtenerPersonaPorId(Long oid){
        return personaRepository.findById(oid); }


    public Grupo obtenerGrupoDePersona(Long oidPersona) {
        return personaRepository.findById(oidPersona)
                .map(Persona::getGrupo)
                .orElse(null);
    }

    public Persona crearPersona(PersonaRequestAdministrador personaDto, Long oidGrupo) {

        Grupo grupo = grupoRepository.findById(oidGrupo)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        Persona persona = new Persona();
        persona.setNombre(personaDto.getNombre());
        persona.setApellido(personaDto.getApellido());
        persona.setHorasSemanales(personaDto.getHorasSemanales());
        persona.setTipoPersona(personaDto.getTipoPersona());
        persona.setActivo(true);

        persona.setGrupo(grupo);
        persona = personaRepository.save(persona);

        if(personaDto.getTipoPersona() == TipoPersona.Becario){

            //defini asi los constructores porque se ponen la gorra y no quieren iniciar con datos

            Becario becario = new Becario();
            becario.setTipoBecario(personaDto.getTipoBecario());
            becario.setFuenteFinanciamiento(personaDto.getFuenteFinanciamiento());

            //establecemos la relacion de Becario con persona
            becario.setPersona(persona);
            //establecemos la relacion de persona con Becario
            persona.setBecario(becario);
            becarioRepository.save((becario));

        }else if(personaDto.getTipoPersona() == TipoPersona.Investigador){

            //defini asi los constructores porque se ponen la gorra y no quieren iniciar con datos
            Investigador investigador = new Investigador();

            investigador.setCategoriaUTN(personaDto.getCategoriaUTN());
            investigador.setProgramaDeIncentivos(personaDto.getProgramaDeIncentivos());
            investigador.setDedicacion(personaDto.getDedicacion());
            investigador.setGradoAcademico(personaDto.getGradoAcademico());

            //establecemos la relacion de investigador con persona
            investigador.setPersona(persona);

            //establecemos la relacion de persona con Investigador
            persona.setInvestigador(investigador);

            investigadorRepository.save(investigador);

        }else if (personaDto.getTipoPersona() == TipoPersona.Personal){

            //defini asi los constructores porque se ponen la gorra y no quieren iniciar con datos
            Personal personal = new Personal();

            personal.setTipoPersonal(personaDto.getTipoPersonal());
            //establecemos la relacion de Personal con persona
            personal.setPersona(persona);
            //establecemos la relacion de persona con Personal
            persona.setPersonal(personal);
            personalRepository.save(personal);

        }else if (personaDto.getTipoPersona() == TipoPersona.IntegranteConsejoEducativo) {

            IntegranteConsejoEducativo integranteConsejoEducativo = new IntegranteConsejoEducativo();

            integranteConsejoEducativo.setCargo(personaDto.getCargo());
            integranteConsejoEducativo.setPersona(persona);
            persona.setIntegranteConsejoEducativo(integranteConsejoEducativo);
            integranteConsejoEducativoRepository.save(integranteConsejoEducativo);

        }
            return personaRepository.save(persona);
    }


    public Persona actualizarPersona(PersonaRequestAdministrador personaDto, Long oid) {

        Persona persona = personaRepository.findById(oid) .orElseThrow(() -> new RuntimeException("Persona no encontrada con oid: " + oid));

        if (personaDto.getNombre() != null)
            persona.setNombre(personaDto.getNombre());
        if (personaDto.getApellido() != null)
            persona.setApellido(personaDto.getApellido());
        if (personaDto.getHorasSemanales() != null)
            persona.setHorasSemanales(personaDto.getHorasSemanales());

        if(persona.getBecario() != null){
            if (personaDto.getTipoBecario() != null)
                persona.getBecario().setTipoBecario(personaDto.getTipoBecario());
            if (personaDto.getFuenteFinanciamiento() != null)
                persona.getBecario().setFuenteFinanciamiento(personaDto.getFuenteFinanciamiento());
        }


        if(persona.getInvestigador() != null){
            if (personaDto.getGradoAcademico() != null)
                persona.getInvestigador().setGradoAcademico(personaDto.getGradoAcademico());
            if (personaDto.getDedicacion() != null)
                persona.getInvestigador().setDedicacion(personaDto.getDedicacion());
            if (personaDto.getCategoriaUTN() != null)
                persona.getInvestigador().setCategoriaUTN(personaDto.getCategoriaUTN());
            if (personaDto.getProgramaDeIncentivos() != null)
                persona.getInvestigador().setProgramaDeIncentivos(personaDto.getProgramaDeIncentivos());
        }


        if(persona.getIntegranteConsejoEducativo() != null){
            if (personaDto.getCargo() != null)
                persona.getIntegranteConsejoEducativo().setCargo((personaDto.getCargo()));
        }


        if(persona.getPersonal() != null){
            if (personaDto.getTipoPersonal() != null)
                persona.getPersonal().setTipoPersonal(personaDto.getTipoPersonal());
        }

        return personaRepository.save(persona);

        }



    public void eliminarPersona(Long oid) {
        personaRepository.deleteById(oid);
    }


}
