package com.grupo7.TrabajoDeCampo.controller;
import com.grupo7.TrabajoDeCampo.DTO.*;
import com.grupo7.TrabajoDeCampo.DTO.Memoria.MemoriaDocumentoResponse;
import com.grupo7.TrabajoDeCampo.DTO.Memoria.MemoriaEquipoResponse;
import com.grupo7.TrabajoDeCampo.DTO.Memoria.MemoriaPersonaRequest;
import com.grupo7.TrabajoDeCampo.DTO.MemoriaResponse;
import com.grupo7.TrabajoDeCampo.DTO.tipoPersonaPackage.BecarioResponse;
import com.grupo7.TrabajoDeCampo.DTO.tipoPersonaPackage.IntegranteConsejoEducativoResponse;
import com.grupo7.TrabajoDeCampo.DTO.tipoPersonaPackage.InvestigadorResponse;
import com.grupo7.TrabajoDeCampo.DTO.tipoPersonaPackage.PersonalResponse;
import com.grupo7.TrabajoDeCampo.model.tipoPersonaPackage.Becario;
import com.grupo7.TrabajoDeCampo.model.tipoPersonaPackage.IntegranteConsejoEducativo;
import com.grupo7.TrabajoDeCampo.model.tipoPersonaPackage.Investigador;
import com.grupo7.TrabajoDeCampo.model.tipoPersonaPackage.Personal;
import com.grupo7.TrabajoDeCampo.service.DocumentoService;
import com.grupo7.TrabajoDeCampo.service.EquipoService;
import com.grupo7.TrabajoDeCampo.service.GrupoService;
import com.grupo7.TrabajoDeCampo.service.PersonaService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaDocumentoService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaEquipoService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaPersonaService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage.BecarioService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage.IntegranteConsejoEducativoService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage.InvestigadorService;
import com.grupo7.TrabajoDeCampo.service.tipoPersonaPackage.PersonalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.grupo7.TrabajoDeCampo.model.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Administrador")
public class AdministradorController {

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
    @Autowired
    private MemoriaService memoriaService;
    @Autowired
    private MemoriaPersonaService memoriaPersonaService;
    @Autowired
    private MemoriaDocumentoService memoriaDocumentoService;
    @Autowired
    private MemoriaEquipoService memoriaEquipoService;


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
    @GetMapping("/documentos/listarDocumentos")
    public List<DocumentoResponse> listarDocumentos() {
        return documentoService.listarDocumentos();
    }


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
    @DeleteMapping("/documentos/eliminarDocumento/{oidDocumeto}")
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
    @GetMapping("/equipos/listarEquipos")
    public List<EquipoResponse> listarEquipos() {
        return equipoService.listarEquipos();
    }

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
    public Persona crearPersona(@RequestBody PersonaRequest persona, @PathVariable("oidGrupo") Long oidGrupo){
        return personaService.crearPersona(persona, oidGrupo);
    }

    //listar todos los equipos
    @GetMapping("/personas/listarPersonas")
    public List<PersonaResponse> listarPersonas() {
        return personaService.listarPersonas();
    }


    //obtener una persona en especifico por ID
    @GetMapping("/personas/obtenerPersona/{oidPersona}")
    public Optional<Persona> obtenerPersonaPorId(@PathVariable("oidPersona") Long oidPersona) {
        return personaService.obtenerPersonaPorId(oidPersona); }


    //actualizar una persona
    @PutMapping("/personas/actualizarPersona/{oidPersona}") public Persona actualizarPersona(@PathVariable("oidPersona") Long oidPersona, @RequestBody PersonaRequest personaDto) {
        return personaService.actualizarPersona(personaDto, oidPersona); }



    //eliminar una persona
    @DeleteMapping("/personas/eliminarPersona/{oidPersona}") public void eliminarPersona (@PathVariable Long oidPersona) {
        personaService.eliminarPersona(oidPersona); }


    //-----------------------------------BECARIOS-----------------------------------

    //listar todas las becarios
    @GetMapping ("/personas/becarios/listarBecarios")
    public List<BecarioResponse> listarBecarios(){ return becarioService.listarBecarios();}


    //obtener una becario en especifico por ID
    @GetMapping("/personas/becarios/obtenerBecario/{oidBecario}")
    public ResponseEntity<BecarioResponse> obtenerBecarioPorId(
            @PathVariable Long oidBecario) {
        return ResponseEntity.ok(
                becarioService.obtenerBecarioPorId(oidBecario));
    }

    //actualizar una becario
    @PutMapping("/personas/becarios/actualizarBecario/{oidBecario}")
    public Becario actualizarBecario(@PathVariable("oidBecario") Long oidBecario, @RequestBody Becario becarioActualizada) {
        return becarioService.actualizarBecario(oidBecario, becarioActualizada);
    }

    //-----------------------------------INVESTIGADORES-----------------------------------

    //listar todas las investigadores
    @GetMapping ("/personas/investigadores/listarInvestigadores")
    public List<InvestigadorResponse> listarInvestigadors(){ return investigadorService.listarInvestigadores();}


    //obtener una investigador en especifico por ID
    @GetMapping("/personas/investigadores/obtenerInvestigador/{oidInvestigador}")
    public ResponseEntity<InvestigadorResponse> obtenerInvestigadorPorId(
            @PathVariable Long oidInvestigador) {
        return ResponseEntity.ok(investigadorService.obtenerInvestigadorPorId(oidInvestigador));
    }

    //actualizar una investigador
    @PutMapping("/personas/investigadores/actualizarInvestigador/{oidInvestigador}")
    public Investigador actualizarInvestigador(@PathVariable("oidInvestigador") Long oidInvestigador, @RequestBody Investigador investigadorActualizada) {
        return investigadorService.actualizarInvestigador(oidInvestigador, investigadorActualizada);
    }

    //-----------------------------------INTEGRANTES CONSEJO EDUCATIVO-----------------------------------

    //listar todas las integranteConsejoEducativos
    @GetMapping("/personas/integranteConsejoEducativos/listarIntegrantesConsejoEducativo")
    public List<IntegranteConsejoEducativoResponse> listarIntegrantesConsejoEducativo() {
        return integranteConsejoEducativoService.listarIntegrantesConsejoEducativo();
    }

    //obtener una integranteConsejoEducativo en especifico por ID
    @GetMapping("/personas/integranteConsejoEducativos/obtenerIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")
    public ResponseEntity<IntegranteConsejoEducativoResponse> obtenerIntegranteConsejoEducativoPorId(
            @PathVariable Long oidIntegranteConsejoEducativo) {

        return ResponseEntity.ok(
                integranteConsejoEducativoService.obtenerIntegranteConsejoEducativoPorId(
                        oidIntegranteConsejoEducativo
                )
        );
    }

    //actualizar una integranteConsejoEducativo
    @PutMapping("/personas/integranteConsejoEducativos/actualizarIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")
    public IntegranteConsejoEducativo actualizarIntegranteConsejoEducativo(@PathVariable("oidIntegranteConsejoEducativo") Long oidIntegranteConsejoEducativo, @RequestBody IntegranteConsejoEducativo integranteConsejoEducativoActualizada) {
        return integranteConsejoEducativoService.actualizarIntegranteConsejoEducativo(oidIntegranteConsejoEducativo, integranteConsejoEducativoActualizada);
    }

    //-----------------------------------PERSONAL-----------------------------------

    //listar todas las personal
    @GetMapping("/personas/personal/listarPersonal")
    public List<PersonalResponse> listarPersonal() {
        return personalService.listarPersonal();
    }


    @GetMapping("/personas/personal/obtenerPersonal/{oidPersonal}")
    public ResponseEntity<PersonalResponse> obtenerPersonalPorId(
            @PathVariable Long oidPersonal) {

        return ResponseEntity.ok(
                personalService.obtenerPersonalPorId(oidPersonal)
        );
    }

    //actualizar una personal
    @PutMapping("/personas/personal/actualizarPersonal/{oidPersonal}")
    public Personal actualizarPersonal(@PathVariable("oidPersonal") Long oidPersonal, @RequestBody Personal personalActualizada) {
        return personalService.actualizarPersonal(oidPersonal, personalActualizada);
    }


    //-----------------------------------MEMORIA-----------------------------------
    @PostMapping("/memorias/crearMemoria/{oidGrupo}/{anio}")
    public Memoria crearMemoria(
            @PathVariable Long oidGrupo,
            @PathVariable Integer anio) {
        return memoriaService.crearMemoria(oidGrupo, anio);
    }

    // listar memorias por grupo
    @GetMapping("/memorias/listarMemorias/{oidGrupo}")
    public List<MemoriaResponse> listarMemorias(@PathVariable Long oidGrupo) {
        return memoriaService.listarPorGrupo(oidGrupo);
    }

    // obtener una memoria por ID
    @GetMapping("/memorias/obtenerMemoria/{oidMemoria}")
    public MemoriaResponse obtenerMemoriaPorId(@PathVariable Long oidMemoria) {
        return memoriaService.obtenerPorId(oidMemoria);
    }





    // agregar equipo a una memoria
    @PostMapping("/memorias/{oidMemoria}/equipos/{oidEquipo}")
    public MemoriaEquipo agregarEquipoAMemoria(@PathVariable Long oidMemoria, @PathVariable Long oidEquipo) {
        return memoriaEquipoService.agregarEquipo(oidMemoria, oidEquipo);
    }

    // listar equipos de una memoria
    @GetMapping("/memorias/{oidMemoria}/equipos")
    public List<MemoriaEquipoResponse> listarEquiposDeMemoria(
            @PathVariable Long oidMemoria) {

        return memoriaEquipoService.listarPorMemoria(oidMemoria);
    }

    // quitar equipo de una memoria
    @DeleteMapping("/memorias/{oidMemoria}/equipos/{oidEquipo}")
    public void quitarEquipoDeMemoria(@PathVariable Long oidMemoria, @PathVariable Long oidEquipo) {
        memoriaEquipoService.quitarEquipo(oidMemoria, oidEquipo);
    }


//-----------------------------------MEMORIA DOCUMENTO-----------------------------------

    // agregar documento a memoria
    @PostMapping("/memorias/{oidMemoria}/documentos/{oidDocumento}")
    public void agregarDocumentoAMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidDocumento) {

        memoriaDocumentoService.agregarDocumento(oidMemoria, oidDocumento);
    }

    //listar documentos de una memoria
    @GetMapping("/memorias/{oidMemoria}/documentos")
    public List<MemoriaDocumentoResponse> listarDocumentosDeMemoria(
            @PathVariable Long oidMemoria) {

        return memoriaDocumentoService.listarPorMemoria(oidMemoria);
    }

    // quitar documento de una memoria
    @DeleteMapping("/memorias/{oidMemoria}/documentos/{oidDocumento}")
    public void quitarDocumentoDeMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidDocumento) {

        memoriaDocumentoService.quitarDocumento(oidMemoria, oidDocumento);
    }

    //-----------------------------------MEMORIA PERSONA-----------------------------------

    // agregar persona a una memoria
    @PostMapping("/memorias/{oidMemoria}/personas/{oidPersona}")
    public MemoriaPersona agregarPersonaAMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidPersona,
            @RequestBody MemoriaPersonaRequest request) {

        return memoriaPersonaService.agregarPersona(
                oidMemoria,
                oidPersona,
                request.getTipoPersonaMemoria(),
                request.getHorasSemanales()
        );
    }

    // listar personas de una memoria
    @GetMapping("/memorias/{oidMemoria}/personas")
    public List<MemoriaPersona> listarPersonasDeMemoria(
            @PathVariable Long oidMemoria) {

        return memoriaPersonaService.listarPorMemoria(oidMemoria);
    }

    // quitar persona de una memoria
    @DeleteMapping("/memorias/{oidMemoria}/personas/{oidPersona}")
    public void quitarPersonaDeMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidPersona) {

        memoriaPersonaService.quitarPersona(oidMemoria, oidPersona);
    }







}