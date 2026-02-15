package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaPersonaResponse;
import com.grupo7.TrabajoDeCampo.handler.Role;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaPersona;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Becario;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Investigador;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Personal;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.persona.PersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaPersonaRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.BecarioRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.IntegranteConsejoEducativoRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.InvestigadorRepository;
import com.grupo7.TrabajoDeCampo.repository.persona.tipoPersona.PersonalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class MemoriaPersonaService {

        private final MemoriaRepository memoriaRepository;
        private final PersonaRepository personaRepository;
        private final MemoriaPersonaRepository memoriaPersonaRepository;

        private final InvestigadorRepository investigadorRepository;
        private final BecarioRepository becarioRepository;
        private final PersonalRepository personalRepository;
        private final IntegranteConsejoEducativoRepository consejoRepository;

        public MemoriaPersonaService(
                MemoriaRepository memoriaRepository,
                PersonaRepository personaRepository,
                MemoriaPersonaRepository memoriaPersonaRepository,
                InvestigadorRepository investigadorRepository,
                BecarioRepository becarioRepository,
                PersonalRepository personalRepository,
                IntegranteConsejoEducativoRepository consejoRepository
        ) {
            this.memoriaRepository = memoriaRepository;
            this.personaRepository = personaRepository;
            this.memoriaPersonaRepository = memoriaPersonaRepository;
            this.investigadorRepository = investigadorRepository;
            this.becarioRepository = becarioRepository;
            this.personalRepository = personalRepository;
            this.consejoRepository = consejoRepository;
        }

        public MemoriaPersona agregarPersonaAMemoriaAdmin(Long oidMemoria, Long oidPersona) {

            Memoria memoria = memoriaRepository.findById(oidMemoria)
                    .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

            Persona persona = personaRepository.findById(oidPersona)
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

            if (memoriaPersonaRepository.existsByMemoriaAndOidPersona(memoria, oidPersona)) {
                throw new RuntimeException("La persona ya está asociada a la memoria");
            }

            // ===== DATOS COMUNES =====
            MemoriaPersona mp = new MemoriaPersona();
            mp.setMemoria(memoria);
            mp.setOidPersona(persona.getOidPersona());
            mp.setNombre(persona.getNombre());
            mp.setApellido(persona.getApellido());
            mp.setTipoPersona(persona.getTipoPersona());
            mp.setHorasSemanales(persona.getHorasSemanales());

            // ===== DATOS ESPECÍFICOS =====
            switch (persona.getTipoPersona()) {

                case Investigador -> {
                    Investigador i = investigadorRepository.findByPersona(persona)
                            .orElseThrow();
                    mp.setCategoriaUTN(i.getCategoriaUTN());
                    mp.setDedicacion(i.getDedicacion());
                    mp.setGradoAcademico(i.getGradoAcademico());
                    mp.setProgramaDeIncentivos(i.getProgramaDeIncentivos());
                }

                case Becario -> {
                    Becario b = becarioRepository.findByPersona(persona)
                            .orElseThrow();
                    mp.setTipoBecario(b.getTipoBecario().name());
                }

                case Personal -> {
                    Personal p = personalRepository.findByPersona(persona)
                            .orElseThrow();
                    mp.setTipoPersonal(p.getTipoPersonal().name());
                }

                case IntegranteConsejoEducativo -> {
                    IntegranteConsejoEducativo c = consejoRepository.findByPersona(persona)
                            .orElseThrow();
                    mp.setCargo(c.getCargo());
                }
            }

            return memoriaPersonaRepository.save(mp);
        }


        public List<MemoriaPersona> listarPersonasPorMemoriaAdmin(Long oidMemoria) {

            Memoria memoria = memoriaRepository.findById(oidMemoria)
                    .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

            return memoriaPersonaRepository.findByMemoria(memoria);
        }


        // quitar persona de una memoria (ADMIN)
        public void quitarPersonaAMemoriaAdmin(Long oidMemoria, Long oidPersona) {

            Memoria memoria = memoriaRepository.findById(oidMemoria)
                    .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

            MemoriaPersona mp = memoriaPersonaRepository
                    .findByMemoriaAndOidPersona(memoria, oidPersona)
                    .orElseThrow(() ->
                            new RuntimeException("La persona no está asociada a la memoria")
                    );

            memoriaPersonaRepository.delete(mp);
        }


        public MemoriaPersona agregarPersonaAMemoriaDirector(
                Usuario usuario,
                Long oidMemoria,
                Long oidPersona
        ) {

            // Validar usuario
            if (usuario.getPersona() == null ||
                    usuario.getPersona().getGrupo() == null) {

                throw new RuntimeException("El usuario no pertenece a ningún grupo");
            }

            // Validar rol (solo Director)
            if (usuario.getRole() != Role.Director) {
                throw new RuntimeException("Solo el Director puede agregar personas");
            }

            long oidGrupoUsuario =
                    usuario.getPersona().getGrupo().getOidGrupo();

            // Buscar memoria
            Memoria memoria = memoriaRepository.findById(oidMemoria)
                    .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

            // Validar grupo
            if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
                throw new RuntimeException("La memoria no pertenece a su grupo");
            }

            // Buscar persona
            Persona persona = personaRepository.findById(oidPersona)
                    .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

            // Validar que sea del mismo grupo
            if (persona.getGrupo().getOidGrupo() != oidGrupoUsuario) {
                throw new RuntimeException("La persona no pertenece a su grupo");
            }

            // Validar que no esté repetida
            if (memoriaPersonaRepository
                    .existsByMemoriaAndOidPersona(memoria, oidPersona)) {

                throw new RuntimeException("La persona ya está asociada a la memoria");
            }

            // ===== DATOS BASE =====
            MemoriaPersona mp = new MemoriaPersona();

            mp.setMemoria(memoria);
            mp.setOidPersona(persona.getOidPersona());
            mp.setNombre(persona.getNombre());
            mp.setApellido(persona.getApellido());
            mp.setHorasSemanales(persona.getHorasSemanales());
            mp.setTipoPersona(persona.getTipoPersona());

            // ===== DATOS ESPECÍFICOS =====
            switch (persona.getTipoPersona()) {

                case Investigador -> {
                    Investigador i = investigadorRepository
                            .findByPersona(persona)
                            .orElseThrow();

                    mp.setCategoriaUTN(i.getCategoriaUTN());
                    mp.setProgramaDeIncentivos(i.getProgramaDeIncentivos());
                    mp.setDedicacion(i.getDedicacion());
                    mp.setGradoAcademico(i.getGradoAcademico());
                }

                case Becario -> {
                    Becario b = becarioRepository
                            .findByPersona(persona)
                            .orElseThrow();

                    mp.setTipoBecario(b.getTipoBecario().name());
                    mp.setFuenteDeFinanciamiento(
                            b.getFuenteFinanciamiento()
                    );
                }

                case Personal -> {
                    Personal p = personalRepository
                            .findByPersona(persona)
                            .orElseThrow();

                    mp.setTipoPersonal(p.getTipoPersonal().name());
                }

                case IntegranteConsejoEducativo -> {
                    IntegranteConsejoEducativo c = consejoRepository
                            .findByPersona(persona)
                            .orElseThrow();

                    mp.setCargo(c.getCargo());
                }
            }

            return memoriaPersonaRepository.save(mp);
        }


        public List<MemoriaPersonaResponse> listarPersonasPorMemoriaDirector(
                Usuario usuario,
                Long oidMemoria
        ) {

            // Validar rol
            if (usuario.getRole() != Role.Director) {
                throw new RuntimeException("No tiene permisos");
            }

            // Obtener grupo del usuario
            Long oidGrupoUsuario =
                    usuario.getPersona()
                            .getGrupo()
                            .getOidGrupo();

            // Buscar memoria
            Memoria memoria = memoriaRepository.findById(oidMemoria)
                    .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

            // Validar grupo
            if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
                throw new RuntimeException("La memoria no pertenece a su grupo");
            }

            // Listar personas
            return memoriaPersonaRepository.findByMemoria(memoria)
                    .stream()
                    .map(mp -> new MemoriaPersonaResponse(
                            mp.getNombre(),
                            mp.getApellido(),
                            mp.getHorasSemanales(),
                            mp.getTipoPersona(),
                            mp.getCategoriaUTN(),
                            mp.getProgramaDeIncentivos(),
                            mp.getDedicacion(),
                            mp.getGradoAcademico(),
                            mp.getFuenteDeFinanciamiento(),
                            mp.getTipoBecario(),
                            mp.getTipoPersonal(),
                            mp.getCargo()
                    ))
                    .toList();
        }



        public void quitarPersonaDeMemoriaDirector(
                Usuario usuario,
                Long oidMemoria,
                Long oidPersona
        ) {

            // Validar rol
            if (usuario.getRole() != Role.Director) {
                throw new RuntimeException("No tiene permisos");
            }

            // Validar grupo del usuario
            Long oidGrupoUsuario =
                    usuario.getPersona()
                            .getGrupo()
                            .getOidGrupo();

            // Buscar memoria
            Memoria memoria = memoriaRepository.findById(oidMemoria)
                    .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

            // Validar que sea del grupo
            if (memoria.getGrupo().getOidGrupo() != oidGrupoUsuario) {
                throw new RuntimeException("La memoria no pertenece a su grupo");
            }

            // Buscar relación memoria-persona
            MemoriaPersona mp = memoriaPersonaRepository
                    .findByMemoriaAndOidPersona(memoria, oidPersona)
                    .orElseThrow(() -> new RuntimeException(
                            "La persona no está asociada a la memoria"));

            // Eliminar relación
            memoriaPersonaRepository.delete(mp);
        }




    }

