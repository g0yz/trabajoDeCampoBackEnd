package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaPersona;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.persona.TipoPersona;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Becario;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Investigador;
import com.grupo7.TrabajoDeCampo.model.persona.tipoPersona.Personal;
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

    }
