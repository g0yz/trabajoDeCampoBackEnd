package com.grupo7.TrabajoDeCampo.controller;


import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.documento.DocumentoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.equipo.EquipoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.grupo.GrupoResponseAdministrador;
import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.persona.BecarioResponseIntegrante;
import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.persona.IntegranteConsejoEducativoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.persona.InvestigadorResponseIntegrante;
import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.persona.PersonalResponseIntegrante;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.service.documento.DocumentoService;
import com.grupo7.TrabajoDeCampo.service.equipo.EquipoService;
import com.grupo7.TrabajoDeCampo.service.grupo.GrupoService;
import com.grupo7.TrabajoDeCampo.service.persona.PersonaService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.BecarioService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.IntegranteConsejoEducativoService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.InvestigadorService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@PreAuthorize("hasRole('Integrante')")
@RequestMapping("/integrante")
public class IntegranteController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private DocumentoService documentoService;

    @Autowired
    private EquipoService equipoService;

    @Autowired
    private BecarioService becarioService;

    @Autowired
    private InvestigadorService investigadorService;

    @Autowired
    private IntegranteConsejoEducativoService integranteConsejoEducativoService;

    @Autowired
    private PersonalService personalService;


    //-----------------------------------GRUPOS-----------------------------------
    //visualizar grupo del integrante
    @GetMapping("/grupo/ver")
    public GrupoResponseAdministrador verGrupo(Authentication auth) { Usuario usuario = (Usuario) auth.getPrincipal();
        return grupoService.obtenerGrupoDelUsuario(usuario);
    }



    //-----------------------------------DOCUMENTOS-----------------------------------
    //listar todos los documentos del grupo
    @GetMapping ("/documentos/listarDocumentos")
    public List<DocumentoResponseIntegrante> listarDocumentos(Authentication auth) {

        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        return documentoService.listarDocumentosPorGrupo(oidGrupo);
    }


    //obtener un documento en especifico del grupo
    @GetMapping("/documentos/visualizarDocumento/{oidDocumento}")
    public DocumentoResponseIntegrante obtenerDocumento(@PathVariable Long oidDocumento, Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        return documentoService.obtenerDocumentoDelGrupo(oidDocumento, usuario);
    }

    //-----------------------------------EQUIPOS-----------------------------------

    //listar todos los equipos del grupo
    @GetMapping("/equipos/listarEquipo")
    public List<EquipoResponseIntegrante> listarEquipos(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return equipoService.listarEquiposDelGrupoIntegrante(oidGrupo);
    }

    //obtener un equipo en especifico del grupo
    @GetMapping("/equipos/obtenerEquipo/{oidEquipo}")
    public EquipoResponseIntegrante obtenerEquipo( @PathVariable Long oidEquipo,Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return equipoService.obtenerEquipoDelGrupoIntegrante(oidEquipo, oidGrupo);
    }

    //-----------------------------------BECARIOS-----------------------------------

    //listar todas las becarios del grupo
    @GetMapping ("/personas/becarios/listarBecarios")
    public List<BecarioResponseIntegrante> listarBecarios(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        return becarioService.listarBecariosDelGrupo(oidGrupo);
    }


    //obtener una becario en especifico del grupo
    @GetMapping("/personas/becarios/obtenerBecario/{oidBecario}")
    public BecarioResponseIntegrante obtenerBecario( @PathVariable Long oidBecario, Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return becarioService.obtenerBecarioDelGrupo(oidGrupo, oidBecario);
    }


    //-----------------------------------INVESTIGADORES-----------------------------------

    //listar todos las investigadores del grupo
    @GetMapping ("/personas/investigadores/listarInvestigadores")
    public List<InvestigadorResponseIntegrante> listarInvestigadoresDelGrupo(
            Authentication auth) {

        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        return investigadorService.listarInvestigadoresDelGrupo(oidGrupo);
    }

    //obtener una investigador en especifico del grupo
    @GetMapping("/personas/investigadores/obtenerInvestigador/{oidInvestigador}")
    public InvestigadorResponseIntegrante obtenerInvestigadorDelGrupo(@PathVariable Long oidGrupo,@PathVariable Long oidInvestigador) {
        return investigadorService.obtenerInvestigadorDelGrupo(oidGrupo, oidInvestigador);
    }


    //-----------------------------------INTEGRANTES CONSEJO EDUCATIVO-----------------------------------

    //listar todos las integrantes del Consejo Educativo del grupo
    @GetMapping ("/personas/integranteConsejoEducativos/listarIntegrantesConsejoEducativo")
    public List<IntegranteConsejoEducativoResponseIntegrante> listarIntegrantesConsejoEducativoDelGrupo(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return integranteConsejoEducativoService.listarIntegrantesConsejoEducativoDelGrupo(oidGrupo);
    }

    //obtener una integranteConsejoEducativo en especifico del grupo
    @GetMapping("/personas/integranteConsejoEducativos/obtenerIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")
    public IntegranteConsejoEducativoResponseIntegrante obtenerIntegranteConsejoEducativoDelGrupo(@PathVariable Long oidGrupo, @PathVariable Long oidIntegranteConsejoEducativo) {
        return integranteConsejoEducativoService
                .obtenerIntegranteConsejoEducativoDelGrupo(
                        oidGrupo,
                        oidIntegranteConsejoEducativo
                );
    }


    //-----------------------------------PERSONAL-----------------------------------

    //listar todo el personal del grupo
    @GetMapping ("/personas/personal/listarPersonal")
    public List<PersonalResponseIntegrante> listarPersonalDelGrupo(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return personalService.listarPersonalDelGrupo(oidGrupo);
    }

    //obtener un personal en especifico del grupo
    @GetMapping("/personas/personal/obtenerPersonal/{oidPersonal}")
    public PersonalResponseIntegrante obtenerPersonalDelGrupo(
            @PathVariable Long oidGrupo,
            @PathVariable Long oidPersonal) {
        return personalService.obtenerPersonalDelGrupo(
                oidGrupo,
                oidPersonal
        );
    }

    //-----------------------------------MEMORIA-----------------------------------

    //listar todas las memorias del grupo


    //listar una memoria en especifico











}
