package com.grupo7.TrabajoDeCampo.service;

import com.grupo7.TrabajoDeCampo.DTO.PersonaCrearDTO;

import com.grupo7.TrabajoDeCampo.model.*;


import com.grupo7.TrabajoDeCampo.repository.GrupoRepository;
import com.grupo7.TrabajoDeCampo.repository.PersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.CargoRepository;

import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage.BecarioRepository;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage.IntegranteConsejoEducativoRepository;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage.InvestigadorRepository;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackage.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PersonaService {

    private final PersonaRepository personaRepository;
    private final GrupoRepository grupoRepository;
    private final CargoRepository cargoRepository;
    private final BecarioRepository becarioRepository;
    private final IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository;
    private final PersonalRepository personalRepository;
    private final InvestigadorRepository investigadorRepository;

    public PersonaService(PersonaRepository personaRepository, GrupoRepository grupoRepository, CargoRepository cargoRepository, BecarioRepository becarioRepository, IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository, PersonalRepository personalRepository,
                          InvestigadorRepository investigadorRepository) {
        this.personaRepository = personaRepository;
        this.grupoRepository = grupoRepository;
        this.cargoRepository = cargoRepository;
        this.becarioRepository = becarioRepository;
        this.integranteConsejoEducativoRepository = integranteConsejoEducativoRepository;
        this.personalRepository = personalRepository;
        this.investigadorRepository = investigadorRepository;
    }


    public List<Persona> listarPersonas(){
        return personaRepository.findAll(); }

    public Optional<Persona> obtenerPersonaPorId(Long id){
        return personaRepository.findById(id); }


    public Persona crearPersona(PersonaCrearDTO personaDto, Long oidGrupo) {

        Grupo grupo = grupoRepository.findById(oidGrupo)
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado"));

        Persona persona = new Persona(
                personaDto.getNombre(),
                personaDto.getApellido(),
                personaDto.getHorasSemanales(),
                personaDto.getTipoPersona()
        );

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


            integranteConsejoEducativo.setPersona(persona);
            persona.setIntegranteConsejoEducativo(integranteConsejoEducativo);
            integranteConsejoEducativoRepository.save(integranteConsejoEducativo);

        }
            return personaRepository.save(persona);
    }


    public Persona actualizarPersona(Long id, Persona personaActualizada) {
        Persona persona = personaRepository.findById(id) .orElseThrow(() -> new RuntimeException("Persona no encontrada con id: " + id));

        if (personaActualizada.getNombre() != null)
            persona.setNombre(personaActualizada.getNombre());
        if (personaActualizada.getApellido() != null)
            persona.setApellido(personaActualizada.getApellido());
        if (personaActualizada.getHorasSemanales() != null)
            persona.setHorasSemanales(personaActualizada.getHorasSemanales());
        return personaRepository.save(persona);
    }



    public void eliminarPersona(Long id) {
        personaRepository.deleteById(id);
    }


}