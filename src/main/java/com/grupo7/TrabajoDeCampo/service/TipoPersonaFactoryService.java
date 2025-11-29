package com.grupo7.TrabajoDeCampo.service;
import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.model.TipoPersona;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackageService.BecarioService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackageService.InvestigadorService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackageService.PersonalService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackageService.IntegranteConsejoEducativoService;

import org.springframework.stereotype.Service;

@Service
public class TipoPersonaFactoryService {

    private final BecarioService becarioService;
    private final InvestigadorService investigadorService;
    private final PersonalService personalService;
    private final IntegranteConsejoEducativoService integranteConsejoEducativoService;

    public TipoPersonaFactoryService(BecarioService becarioService, InvestigadorService investigadorService, PersonalService personalService, IntegranteConsejoEducativoService integranteConsejoEducativoService){
        this.becarioService = becarioService;
        this.investigadorService = investigadorService;
        this.personalService =  personalService ;
        this.integranteConsejoEducativoService = integranteConsejoEducativoService;

    }

    public void agregarTipoPersonaAPersona(Persona persona){

        if (persona.getTipoPersona() == null)
            throw new RuntimeException("tipoPersona no puede ser null");

        if (persona.getTipoPersona() == TipoPersona.Becario) {
            becarioService.crearBecario(persona);
        }
        else if (persona.getTipoPersona() == TipoPersona.Investigador) {
            investigadorService.crearInvestigador(persona);
        }
        else if (persona.getTipoPersona() == TipoPersona.Personal) {
            personalService.crearPersonal(persona);
        }
        else if (persona.getTipoPersona() == TipoPersona.IntegranteConsejoEducativo) {
            integranteConsejoEducativoService.crearintegranteConsejoEducativo(persona);
        }
        else {
            throw new RuntimeException("TipoPersona desconocido: " + persona.getTipoPersona());
        }


    }


}
