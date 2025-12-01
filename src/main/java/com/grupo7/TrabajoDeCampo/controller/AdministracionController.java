package com.grupo7.TrabajoDeCampo.controller;
import com.grupo7.TrabajoDeCampo.DTO.PersonaCrearDTO;
import com.grupo7.TrabajoDeCampo.service.DocumentoService;
import com.grupo7.TrabajoDeCampo.service.EquipoService;
import com.grupo7.TrabajoDeCampo.service.GrupoService;
import com.grupo7.TrabajoDeCampo.service.PersonaService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage.BecarioService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage.IntegranteConsejoEducativoService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage.InvestigadorService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage.PersonalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.grupo7.TrabajoDeCampo.model.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/AdministracionController")
public class AdministracionController {

    @Autowired
    private GrupoService grupoService;
    @Autowired
    private DocumentoService documentoService;
    @Autowired
    private EquipoService equipoService;
    @Autowired
    private PersonaService personaService;
    @Autowired
    private BecarioService becarioService;
    @Autowired
    private InvestigadorService investigadorService;
    @Autowired
    private IntegranteConsejoEducativoService integranteConsejoEducativoService;
    @Autowired
    private PersonalService personalService;

    //-----------------------------------GRUPOS-----------------------------------
    //crear nuevo grupo
    @PostMapping("/grupos/agregarGrupo")
    public Grupo crearGrupo(@RequestBody Grupo grupo) { return grupoService.crearGrupo(grupo); }

    //listar todos los grupos
    @GetMapping ("/grupos/listarGrupos")
    public List<Grupo> listarGrupos(){ return grupoService.listarGrupos();}

    //obtener un grupo por ID
    @GetMapping("/grupos/obtenerGrupo/{oidGrupo}")
    public Optional<Grupo> obtenerGrupoPorId(@PathVariable("oidGrupo") Long oidGrupo) {
        return grupoService.obtenerGrupoPorId(oidGrupo);
    }

    //actualizar grupo
    @PutMapping("/grupos/actualizarGrupo/{oidGrupo}")
    public Grupo actualizarGrupo(@PathVariable("oidGrupo") Long oidGrupo, @RequestBody Grupo grupo) {
        return grupoService.actualizarGrupo(oidGrupo, grupo);
    }

    //eliminar grupo
    @DeleteMapping("/grupos/eliminarGrupo/{oidGrupo}")
    public void eliminarGrupo(@PathVariable("oidGrupo") Long oidGrupo) {
        grupoService.eliminarGrupo(oidGrupo);
    }


    //-----------------------------------DOCUMENTOS-----------------------------------

    //crear nuevo Documento
    @PostMapping("/documentos/agregarDocumento/{oidGrupo}")
    public Documento crearDocumento(@RequestBody Documento documento, @PathVariable("oidGrupo") Long oidGrupo){
        return documentoService.crearDocumento(documento, oidGrupo);
    }

    //listar todos los documentos
    @GetMapping ("/documentos/listarDocumentos")
    public List<Documento> listarDocumentos(){ return documentoService.listarDocumentos();}

    //obtener un documento por ID
    @GetMapping("/documentos/obtenerDocumento/{oidDocumento}")
    public Optional<Documento> obtenerDocumentoPorId(@PathVariable("oidDocumento") Long oidDocumento){
        return documentoService.obtenerDocumentoPorId(oidDocumento);
    }

    //actualizar un Documento
    @PutMapping("/documentos/actualizarDocumento/{oidDocumento}")
    public Documento actualizarDocumento(@PathVariable("oidDocumento") Long oidDocumento, @RequestBody Documento docuemntoActualizado){
        return documentoService.actualizarDocumento(oidDocumento, docuemntoActualizado);
    }

    //eliminar un Documento
    @DeleteMapping("/documentos/eliminarDocumentos/{oidDocumetos}")
    public void eliminarDocumento(@PathVariable Long oidDocumento) {
        documentoService.eliminarDocumento(oidDocumento);
    }

    //-----------------------------------EQUIPOS-----------------------------------
    //crear nuevo equipo
    @PostMapping("/equipos/agregarEquipo/{oidGrupo}")
    public Equipo crearEquipo(@RequestBody Equipo equipo, @PathVariable("oidGrupo") Long oidGrupo){
        return equipoService.crearEquipo(equipo, oidGrupo);
    }

    //listar todos los equipos
    @GetMapping ("/equipos/listarEquipos")
    public List<Equipo> listarEquipos(){ return equipoService.listarEquipos();}

    //obtener un equipo por ID
    @GetMapping("/equipos/obtenerEquipo/{oidEquipo}")
    public Optional<Equipo> obtenerEquipoPorId(@PathVariable("oidEquipo") Long oidEquipo) {
        return equipoService.obtenerEquipoPorId(oidEquipo);
    }

    //actualizar un equipo
    @PutMapping("/equipos/actualizarEquipo/{oidEquipo}")
    public Equipo actualizarEquipo(@PathVariable("oidEquipo") Long oidEquipo, @RequestBody Equipo equipoActualizado) {
        return equipoService.actualizarEquipo(oidEquipo, equipoActualizado);
    }

    //eliminar un equipo
    @DeleteMapping("/equipos/eliminarEquipo/{oidEquipo}")
    public void eliminarEquipo(@PathVariable Long oidEquipo) {
        equipoService.eliminarEquipo(oidEquipo);
    }


    //-----------------------------------PERSONAS-----------------------------------
    //crear una persona a grupo
    @PostMapping("/personas/agregarPersona/{oidGrupo}")
    public Persona crearPersona(@RequestBody PersonaCrearDTO persona, @PathVariable("oidGrupo") Long oidGrupo){
        return personaService.crearPersona(persona, oidGrupo);
    }

    //listar todos los equipos
    @GetMapping ("/personas/listarPersonas")
    public List<Persona> listarPersonas(){ return personaService.listarPersonas();}

    //obtener una persona en especifico por ID
    @GetMapping("/personas/obtenerPersona/{oidPersona}")
    public Optional<Persona> obtenerPersonaPorId(@PathVariable("oidPersona") Long oidPersona) {
        return personaService.obtenerPersonaPorId(oidPersona); }


    //actualizar una persona
    @PutMapping("/personas/actualizarPersona/{oidPersona}") public Persona actualizarPersona(@PathVariable("oidPersona") Long oidPersona, @RequestBody Persona personaActualizada) {
        return personaService.actualizarPersona(oidPersona, personaActualizada); }



    //eliminar una persona
    @DeleteMapping("/personas/eliminarPersona/{oidPersona}") public void eliminarPersona (@PathVariable Long oidPersona) {
        personaService.eliminarPersona(oidPersona); }


    //-----------------------------------BECARIOS-----------------------------------

    //listar todas las becarios
    @GetMapping ("/personas/becarios/listarBecarios")
    public List<Becario> listarBecarios(){ return becarioService.listarBecarios();}


    //obtener una becario en especifico por ID
    @GetMapping("/personas/becarios/obtenerBecario/{oidBecario}")
    public Optional<Becario> obtenerBecarioPorId(@PathVariable("oidBecario") Long oidBecario) {
        return becarioService.obtenerBecarioPorId(oidBecario);
    }

    //actualizar una becario
    @PutMapping("/personas/becarios/actualizarBecario/{oidBecario}")
    public Becario actualizarBecario(@PathVariable("oidBecario") Long oidBecario, @RequestBody Becario becarioActualizada) {
        return becarioService.actualizarBecario(oidBecario, becarioActualizada);
    }

    //eliminar una becario
    @DeleteMapping("/personas/becarios/eliminarBecario/{oidBecario}")
    public void eliminarBecario (@PathVariable Long oidBecario) {
        becarioService.eliminarBecario(oidBecario);
    }

    //-----------------------------------INVESTIGADORES-----------------------------------

    //listar todas las investigadores
    @GetMapping ("/personas/investigadores/listarInvestigadores")
    public List<Investigador> listarInvestigadors(){ return investigadorService.listarInvestigadores();}


    //obtener una investigador en especifico por ID
    @GetMapping("/personas/investigadores/obtenerInvestigador/{oidInvestigador}")
    public Optional<Investigador> obtenerInvestigadorPorId(@PathVariable("oidInvestigador") Long oidInvestigador) {
        return investigadorService.obtenerInvestigadorPorId(oidInvestigador);
    }

    //actualizar una investigador
    @PutMapping("/personas/investigadores/actualizarInvestigador/{oidInvestigador}")
    public Investigador actualizarInvestigador(@PathVariable("oidInvestigador") Long oidInvestigador, @RequestBody Investigador investigadorActualizada) {
        return investigadorService.actualizarInvestigador(oidInvestigador, investigadorActualizada);
    }

    //eliminar una investigador
    @DeleteMapping("/personas/investigadores/eliminarInvestigador/{oidInvestigador}")
    public void eliminarInvestigador (@PathVariable Long oidInvestigador) {
        investigadorService.eliminarInvestigador(oidInvestigador);
    }

    //-----------------------------------INTEGRANTES CONSEJO EDUCATIVO-----------------------------------

    //listar todas las integranteConsejoEducativos
    @GetMapping ("/personas/integranteConsejoEducativos/listarIntegrantesConsejoEducativo")
    public List<IntegranteConsejoEducativo> listarIntegrantesConsejoEducativo(){ return integranteConsejoEducativoService.listarIntegrantesConsejoEducativo();}


    //obtener una integranteConsejoEducativo en especifico por ID
    @GetMapping("/personas/integranteConsejoEducativos/obtenerIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")
    public Optional<IntegranteConsejoEducativo> obtenerIntegranteConsejoEducativoPorId(@PathVariable("oidIntegranteConsejoEducativo") Long oidIntegranteConsejoEducativo) {
        return integranteConsejoEducativoService.obtenerIntegranteConsejoEducativoPorId(oidIntegranteConsejoEducativo);
    }

    //actualizar una integranteConsejoEducativo
    @PutMapping("/personas/integranteConsejoEducativos/actualizarIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")
    public IntegranteConsejoEducativo actualizarIntegranteConsejoEducativo(@PathVariable("oidIntegranteConsejoEducativo") Long oidIntegranteConsejoEducativo, @RequestBody IntegranteConsejoEducativo integranteConsejoEducativoActualizada) {
        return integranteConsejoEducativoService.actualizarIntegranteConsejoEducativo(oidIntegranteConsejoEducativo, integranteConsejoEducativoActualizada);
    }

    //eliminar una integranteConsejoEducativo
    @DeleteMapping("/personas/integranteConsejoEducativos/eliminarIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")
    public void eliminarIntegranteConsejoEducativo (@PathVariable Long oidIntegranteConsejoEducativo) {
        integranteConsejoEducativoService.eliminarIntegranteConsejoEducativo(oidIntegranteConsejoEducativo);
    }

    //-----------------------------------PERSONAL-----------------------------------

    //listar todas las personal
    @GetMapping ("/personas/personal/listarPersonal")
    public List<Personal> listarPersonal(){ return personalService.listarPersonal();}


    //obtener una personal en especifico por ID
    @GetMapping("/personas/personal/obtenerPersonal/{oidPersonal}")
    public Optional<Personal> obtenerPersonalPorId(@PathVariable("oidPersonal") Long oidPersonal) {
        return personalService.obtenerPersonalPorId(oidPersonal);
    }

    //actualizar una personal
    @PutMapping("/personas/personal/actualizarPersonal/{oidPersonal}")
    public Personal actualizarPersonal(@PathVariable("oidPersonal") Long oidPersonal, @RequestBody Personal personalActualizada) {
        return personalService.actualizarPersonal(oidPersonal, personalActualizada);
    }

    //eliminar una personal
    @DeleteMapping("/personas/personal/eliminarPersonal/{oidPersonal}")
    public void eliminarPersonal (@PathVariable Long oidPersonal) {
        personalService.eliminarPersonal(oidPersonal);
    }
}