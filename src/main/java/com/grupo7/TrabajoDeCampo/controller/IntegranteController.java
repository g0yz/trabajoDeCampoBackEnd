package com.grupo7.TrabajoDeCampo.controller;


import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.documento.DocumentoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.equipo.EquipoResponseIntegrante;
import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.grupo.GrupoResponseAdministrador;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.service.documento.DocumentoService;
import com.grupo7.TrabajoDeCampo.service.equipo.EquipoService;
import com.grupo7.TrabajoDeCampo.service.grupo.GrupoService;
import com.grupo7.TrabajoDeCampo.service.persona.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
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


    //-----------------------------------PERSONAS-----------------------------------


    //listar todas las personas del grupo
    //@GetMapping ("/personas/listarPersonas")

    //obtener una persona en especifico del grupo
    //@GetMapping("/personas/obtenerPersona/{oidPersona}")



    //-----------------------------------BECARIOS-----------------------------------

    //listar todas las becarios del grupo
    //@GetMapping ("/personas/becarios/listarBecarios")


    //obtener una becario en especifico del grupo
    //@GetMapping("/personas/becarios/obtenerBecario/{oidBecario}")



    //-----------------------------------INVESTIGADORES-----------------------------------

    //listar todos las investigadores del grupo
    //@GetMapping ("/personas/investigadores/listarInvestigadores")


    //obtener una investigador en especifico del grupo
    //@GetMapping("/personas/investigadores/obtenerInvestigador/{oidInvestigador}")


    //-----------------------------------INTEGRANTES CONSEJO EDUCATIVO-----------------------------------

    //listar todos las integrantes del Consejo Educativo del grupo
    //@GetMapping ("/personas/integranteConsejoEducativos/listarIntegrantesConsejoEducativo")


    //obtener una integranteConsejoEducativo en especifico del grupo
    //@GetMapping("/personas/integranteConsejoEducativos/obtenerIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")



    //-----------------------------------PERSONAL-----------------------------------

    //listar todo el personal del grupo
    //@GetMapping ("/personas/personal/listarPersonal")


    //obtener un personal en especifico del grupo
    //@GetMapping("/personas/personal/obtenerPersonal/{oidPersonal}")


    //-----------------------------------MEMORIA-----------------------------------













}
