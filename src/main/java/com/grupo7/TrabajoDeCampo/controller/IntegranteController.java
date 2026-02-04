package com.grupo7.TrabajoDeCampo.controller;


import com.grupo7.TrabajoDeCampo.DTO.DocumentoResponse;
import com.grupo7.TrabajoDeCampo.DTO.DocumentoResponseGrupo;
import com.grupo7.TrabajoDeCampo.DTO.GrupoResponse;
import com.grupo7.TrabajoDeCampo.DTO.PersonaRequest;
import com.grupo7.TrabajoDeCampo.model.*;
import com.grupo7.TrabajoDeCampo.service.DocumentoService;
import com.grupo7.TrabajoDeCampo.service.GrupoService;
import com.grupo7.TrabajoDeCampo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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


    //-----------------------------------GRUPOS-----------------------------------
    //visualizar grupo del integrante
    @GetMapping("/grupo/ver")
    public GrupoResponse verGrupo(Authentication auth) { Usuario usuario = (Usuario) auth.getPrincipal();
        return grupoService.obtenerGrupoDelUsuario(usuario);
    }



    //-----------------------------------DOCUMENTOS-----------------------------------
    //listar todos los documentos del grupo
    @GetMapping ("/documentos/listarDocumentos")
    public List<DocumentoResponseGrupo> listarDocumentosDelGrupo(Authentication auth) {

        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        return documentoService.listarDocumentosPorGrupo(oidGrupo);
    }


    //obtener un documento en especifico del grupo
    @GetMapping("/documentos/visualizarDocumento/{oidDocumento}")
    public DocumentoResponseGrupo obtenerDocumento(@PathVariable Long oidDocumento,Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        return documentoService.obtenerDocumentoDelGrupo(oidDocumento, usuario);
    }

    //-----------------------------------EQUIPOS-----------------------------------

    //listar todos los equipos del grupo
    //@GetMapping ("/equipos/listarEquipos")

    //obtener un equipo en especifico del grupo
    //@GetMapping("/equipos/obtenerEquipo/{oidEquipo}")


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
