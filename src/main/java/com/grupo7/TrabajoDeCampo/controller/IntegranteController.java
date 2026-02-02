package com.grupo7.TrabajoDeCampo.controller;


import com.grupo7.TrabajoDeCampo.DTO.PersonaRequest;
import com.grupo7.TrabajoDeCampo.model.*;
import com.grupo7.TrabajoDeCampo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Integrante")
public class IntegranteController {


    @Autowired
    private PersonaService personaService;


    //-----------------------------------GRUPOS-----------------------------------
    //visualizar grupo del integrante
    // @GetMapping("/grupo/visualizarGrupo/{oidPersona}")
    // public Grupo visualizarGrupo(@PathVariable("oidPersona") Long oidPersona) {
        //    return personaService.obtenerGrupoDePersona(oidPersona);
        //}



    //-----------------------------------DOCUMENTOS-----------------------------------
    //listar todos los documentos del grupo
    //@GetMapping ("/documentos/listarDocumentos")

    //obtener un documento en especifico del grupo
    //@GetMapping("/documentos/obtenerDocumento/{oidDocumento}")


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
