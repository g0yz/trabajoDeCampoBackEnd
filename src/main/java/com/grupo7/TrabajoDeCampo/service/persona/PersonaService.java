package com.grupo7.TrabajoDeCampo.service.persona;

import com.grupo7.TrabajoDeCampo.dto.persona.PersonaRequest;

import com.grupo7.TrabajoDeCampo.dto.persona.PersonaResponse;


import com.grupo7.TrabajoDeCampo.handler.Role;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Becario;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Investigador;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Personal;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.PersonaRepository;

import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.BecarioRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.IntegranteConsejoEducativoRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.InvestigadorRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.PersonalRepository;
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


    public List<PersonaResponse> listarPersonas() {

        return personaRepository.findAll()
                .stream()
                .map(p -> new PersonaResponse(
                        p.getOidPersona(),
                        p.getNombre(),
                        p.getApellido(),
                        p.getHorasSemanales(),
                        p.getTipoPersona(),
                        // INVESTIGADOR
                        p.getInvestigador() != null ? p.getInvestigador().getCategoriaUTN() : null,
                        p.getInvestigador() != null ? p.getInvestigador().getProgramaDeIncentivos() : null,
                        p.getInvestigador() != null ? p.getInvestigador().getDedicacion() : null,
                        p.getInvestigador() != null ? p.getInvestigador().getGradoAcademico() : null,
                        // BECARIO
                        p.getBecario() != null ? p.getBecario().getFuenteFinanciamiento() : null,
                        p.getBecario() != null ? p.getBecario().getTipoBecario() : null,
                        // PERSONAL
                        p.getPersonal() != null ? p.getPersonal().getTipoPersonal() : null,
                        // CONSEJO EDUCATIVO
                        p.getIntegranteConsejoEducativo() != null ? p.getIntegranteConsejoEducativo().getCargo() : null,
                        // GRUPO
                        p.getGrupo().getOidGrupo(),
                        p.getGrupo().getNombreGrupo()
                ))
                .toList();
    }


    public Optional<Persona> obtenerPersonaPorId(Long oid) {
        return personaRepository.findById(oid);
    }


    public Grupo obtenerGrupoDePersona(Long oidPersona) {
        return personaRepository.findById(oidPersona)
                .map(Persona::getGrupo)
                .orElse(null);
    }

    public Persona crearPersona(PersonaRequest personaDto, Long oidGrupo) {

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

        if (personaDto.getTipoPersona() == TipoPersona.Becario) {

            //defini asi los constructores porque se ponen la gorra y no quieren iniciar con datos

            Becario becario = new Becario();
            becario.setTipoBecario(personaDto.getTipoBecario());
            becario.setFuenteFinanciamiento(personaDto.getFuenteFinanciamiento());

            //establecemos la relacion de Becario con persona
            becario.setPersona(persona);
            //establecemos la relacion de persona con Becario
            persona.setBecario(becario);
            becarioRepository.save((becario));

        } else if (personaDto.getTipoPersona() == TipoPersona.Investigador) {

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

        } else if (personaDto.getTipoPersona() == TipoPersona.Personal) {

            //defini asi los constructores porque se ponen la gorra y no quieren iniciar con datos
            Personal personal = new Personal();

            personal.setTipoPersonal(personaDto.getTipoPersonal());
            //establecemos la relacion de Personal con persona
            personal.setPersona(persona);
            //establecemos la relacion de persona con Personal
            persona.setPersonal(personal);
            personalRepository.save(personal);

        } else if (personaDto.getTipoPersona() == TipoPersona.IntegranteConsejoEducativo) {

            IntegranteConsejoEducativo integranteConsejoEducativo = new IntegranteConsejoEducativo();

            integranteConsejoEducativo.setCargo(personaDto.getCargo());
            integranteConsejoEducativo.setPersona(persona);
            persona.setIntegranteConsejoEducativo(integranteConsejoEducativo);
            integranteConsejoEducativoRepository.save(integranteConsejoEducativo);

        }
        return personaRepository.save(persona);
    }


    public Persona actualizarPersona(PersonaRequest personaDto, Long oid) {

        Persona persona = personaRepository.findById(oid).orElseThrow(() -> new RuntimeException("Persona no encontrada con oid: " + oid));

        if (personaDto.getNombre() != null)
            persona.setNombre(personaDto.getNombre());
        if (personaDto.getApellido() != null)
            persona.setApellido(personaDto.getApellido());
        if (personaDto.getHorasSemanales() != null)
            persona.setHorasSemanales(personaDto.getHorasSemanales());

        if (persona.getBecario() != null) {
            if (personaDto.getTipoBecario() != null)
                persona.getBecario().setTipoBecario(personaDto.getTipoBecario());
            if (personaDto.getFuenteFinanciamiento() != null)
                persona.getBecario().setFuenteFinanciamiento(personaDto.getFuenteFinanciamiento());
        }


        if (persona.getInvestigador() != null) {
            if (personaDto.getGradoAcademico() != null)
                persona.getInvestigador().setGradoAcademico(personaDto.getGradoAcademico());
            if (personaDto.getDedicacion() != null)
                persona.getInvestigador().setDedicacion(personaDto.getDedicacion());
            if (personaDto.getCategoriaUTN() != null)
                persona.getInvestigador().setCategoriaUTN(personaDto.getCategoriaUTN());
            if (personaDto.getProgramaDeIncentivos() != null)
                persona.getInvestigador().setProgramaDeIncentivos(personaDto.getProgramaDeIncentivos());
        }


        if (persona.getIntegranteConsejoEducativo() != null) {
            if (personaDto.getCargo() != null)
                persona.getIntegranteConsejoEducativo().setCargo((personaDto.getCargo()));
        }


        if (persona.getPersonal() != null) {
            if (personaDto.getTipoPersonal() != null)
                persona.getPersonal().setTipoPersonal(personaDto.getTipoPersonal());
        }

        return personaRepository.save(persona);

    }


    public void desactivarPersona(Long oidPersona) {

        Persona persona = personaRepository.findById(oidPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));


        // Desactivar tipo si existe
        if (persona.getInvestigador() != null) {
            persona.getInvestigador().setActivo(false);
        }

        if (persona.getBecario() != null) {
            persona.getBecario().setActivo(false);
        }

        if (persona.getIntegranteConsejoEducativo() != null) {
            persona.getIntegranteConsejoEducativo().setActivo(false);
        }

        if (persona.getPersonal() != null) {
            persona.getPersonal().setActivo(false);
        }

        // Desactivar persona
        persona.setActivo(false);

        personaRepository.save(persona);
    }




    public PersonaResponse agregarPersonaAGrupo(
            Usuario usuario,
            PersonaRequest personaDto
    ) {

        // Validar grupo
        if (usuario.getPersona() == null ||
                usuario.getPersona().getGrupo() == null) {

            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        // Validar rol
        if (usuario.getRole() != Role.DIRECTOR &&
                usuario.getRole() != Role.VICEDIRECTOR) {

            throw new RuntimeException("No tiene permisos para agregar personas");
        }

        Grupo grupo = usuario.getPersona().getGrupo();

        // Crear persona base
        Persona persona = new Persona();

        persona.setNombre(personaDto.getNombre());
        persona.setApellido(personaDto.getApellido());
        persona.setHorasSemanales(personaDto.getHorasSemanales());
        persona.setTipoPersona(personaDto.getTipoPersona());
        persona.setActivo(true);
        persona.setGrupo(grupo);

        persona = personaRepository.save(persona);


        // ===============================
        // BECARIO
        // ===============================
        if (personaDto.getTipoPersona() == TipoPersona.Becario) {

            Becario becario = new Becario();

            becario.setTipoBecario(personaDto.getTipoBecario());
            becario.setFuenteFinanciamiento(personaDto.getFuenteFinanciamiento());

            becario.setPersona(persona);
            persona.setBecario(becario);

            becarioRepository.save(becario);
        }


        // ===============================
        // INVESTIGADOR
        // ===============================
        else if (personaDto.getTipoPersona() == TipoPersona.Investigador) {

            Investigador investigador = new Investigador();

            investigador.setCategoriaUTN(personaDto.getCategoriaUTN());
            investigador.setProgramaDeIncentivos(personaDto.getProgramaDeIncentivos());
            investigador.setDedicacion(personaDto.getDedicacion());
            investigador.setGradoAcademico(personaDto.getGradoAcademico());

            investigador.setPersona(persona);
            persona.setInvestigador(investigador);

            investigadorRepository.save(investigador);
        }


        // ===============================
        // PERSONAL
        // ===============================
        else if (personaDto.getTipoPersona() == TipoPersona.Personal) {

            Personal personal = new Personal();

            personal.setTipoPersonal(personaDto.getTipoPersonal());

            personal.setPersona(persona);
            persona.setPersonal(personal);

            personalRepository.save(personal);
        }


        // ===============================
        // CONSEJO
        // ===============================
        else if (personaDto.getTipoPersona() == TipoPersona.IntegranteConsejoEducativo) {

            IntegranteConsejoEducativo ice = new IntegranteConsejoEducativo();

            ice.setCargo(personaDto.getCargo());

            ice.setPersona(persona);
            persona.setIntegranteConsejoEducativo(ice);

            integranteConsejoEducativoRepository.save(ice);
        }


        Persona guardada = personaRepository.save(persona);


        // ===============================
        // RESPONSE
        // ===============================
        return new PersonaResponse(
                guardada.getOidPersona(),
                guardada.getNombre(),
                guardada.getApellido(),
                guardada.getHorasSemanales(),
                guardada.getTipoPersona(),
                // INVESTIGADOR
                guardada.getInvestigador() != null ? guardada.getInvestigador().getCategoriaUTN() : null,
                guardada.getInvestigador() != null ? guardada.getInvestigador().getProgramaDeIncentivos() : null,
                guardada.getInvestigador() != null ? guardada.getInvestigador().getDedicacion() : null,
                guardada.getInvestigador() != null ? guardada.getInvestigador().getGradoAcademico() : null,
                // BECARIO
                guardada.getBecario() != null ? guardada.getBecario().getFuenteFinanciamiento() : null,
                guardada.getBecario() != null ? guardada.getBecario().getTipoBecario() : null,
                // PERSONAL
                guardada.getPersonal() != null ? guardada.getPersonal().getTipoPersonal() : null,
                // CONSEJO EDUCATIVO
                guardada.getIntegranteConsejoEducativo() != null ? guardada.getIntegranteConsejoEducativo().getCargo() : null,
                // GRUPO
                guardada.getGrupo().getOidGrupo(),
                guardada.getGrupo().getNombreGrupo()
        );
    }


    public PersonaResponse editarPersonaDelGrupo(
            Usuario usuario,
            Long oidPersona,
            PersonaRequest personaDto
    ) {

        // Validar grupo
        if (usuario.getPersona() == null ||
                usuario.getPersona().getGrupo() == null) {

            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        // Validar rol
        if (usuario.getRole() != Role.DIRECTOR &&
                usuario.getRole() != Role.VICEDIRECTOR) {

            throw new RuntimeException("No tiene permisos para editar personas");
        }

        Long oidGrupoUsuario = usuario.getPersona().getGrupo().getOidGrupo();


        // Buscar persona
        Persona persona = personaRepository.findById(oidPersona)
                .orElseThrow(() ->
                        new RuntimeException("Persona no encontrada")
                );


        // Validar que pertenezca al grupo
        if (persona.getGrupo().getOidGrupo() != oidGrupoUsuario) {
            throw new RuntimeException("No puede editar personas de otro grupo");
        }

        if (personaDto.getNombre() != null)
            persona.setNombre(personaDto.getNombre());

        if (personaDto.getApellido() != null)
            persona.setApellido(personaDto.getApellido());

        if (personaDto.getHorasSemanales() != null)
            persona.setHorasSemanales(personaDto.getHorasSemanales());

        // DATOS SEGÚN TIPO

        // Becario
        if (persona.getBecario() != null) {

            if (personaDto.getTipoBecario() != null)
                persona.getBecario().setTipoBecario(personaDto.getTipoBecario());

            if (personaDto.getFuenteFinanciamiento() != null)
                persona.getBecario().setFuenteFinanciamiento(
                        personaDto.getFuenteFinanciamiento()
                );
        }


        // Investigador
        if (persona.getInvestigador() != null) {

            if (personaDto.getCategoriaUTN() != null)
                persona.getInvestigador().setCategoriaUTN(
                        personaDto.getCategoriaUTN()
                );

            if (personaDto.getProgramaDeIncentivos() != null)
                persona.getInvestigador().setProgramaDeIncentivos(
                        personaDto.getProgramaDeIncentivos()
                );

            if (personaDto.getDedicacion() != null)
                persona.getInvestigador().setDedicacion(
                        personaDto.getDedicacion()
                );

            if (personaDto.getGradoAcademico() != null)
                persona.getInvestigador().setGradoAcademico(
                        personaDto.getGradoAcademico()
                );
        }


        // Personal
        if (persona.getPersonal() != null) {

            if (personaDto.getTipoPersonal() != null)
                persona.getPersonal().setTipoPersonal(
                        personaDto.getTipoPersonal()
                );
        }


        // Consejo
        if (persona.getIntegranteConsejoEducativo() != null) {

            if (personaDto.getCargo() != null)
                persona.getIntegranteConsejoEducativo().setCargo(
                        personaDto.getCargo()
                );
        }


        Persona actualizada = personaRepository.save(persona);


        return new PersonaResponse(
                actualizada.getOidPersona(),
                actualizada.getNombre(),
                actualizada.getApellido(),
                actualizada.getHorasSemanales(),
                actualizada.getTipoPersona(),
                // INVESTIGADOR
                actualizada.getInvestigador() != null ? actualizada.getInvestigador().getCategoriaUTN() : null,
                actualizada.getInvestigador() != null ? actualizada.getInvestigador().getProgramaDeIncentivos() : null,
                actualizada.getInvestigador() != null ? actualizada.getInvestigador().getDedicacion() : null,
                actualizada.getInvestigador() != null ? actualizada.getInvestigador().getGradoAcademico() : null,
                // BECARIO
                actualizada.getBecario() != null ? actualizada.getBecario().getFuenteFinanciamiento() : null,
                actualizada.getBecario() != null ? actualizada.getBecario().getTipoBecario() : null,
                // PERSONAL
                actualizada.getPersonal() != null ? actualizada.getPersonal().getTipoPersonal() : null,
                // CONSEJO EDUCATIVO
                actualizada.getIntegranteConsejoEducativo() != null ? actualizada.getIntegranteConsejoEducativo().getCargo() : null,
                // GRUPO
                actualizada.getGrupo().getOidGrupo(),
                actualizada.getGrupo().getNombreGrupo()
        );
    }



    public void quitarPersonaDelGrupo(
            Usuario usuario,
            Long oidPersona
    ) {

        // Validar que el usuario tenga grupo
        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("El usuario no pertenece a ningún grupo");
        }

        // Validar rol
        if (usuario.getRole() != Role.DIRECTOR &&
                usuario.getRole() != Role.VICEDIRECTOR) {

            throw new RuntimeException("No tiene permisos para quitar personas");
        }

        Long oidGrupoUsuario =
                usuario.getPersona().getGrupo().getOidGrupo();

        // Buscar persona del mismo grupo y activa
        Persona persona = personaRepository
                .findByOidPersonaAndGrupoOidGrupoAndActivoTrue(
                        oidPersona,
                        oidGrupoUsuario
                )
                .orElseThrow(() ->
                        new RuntimeException("Persona no encontrada en su grupo")
                );

        // Soft delete
        persona.setActivo(false);

        personaRepository.save(persona);
    }






}








