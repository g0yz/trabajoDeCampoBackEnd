package com.grupo7.TrabajoDeCampo.controller;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoArchivoResponse;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoRequest;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoResponse;
import com.grupo7.TrabajoDeCampo.dto.equipo.EquipoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaDetalleResponse;
import com.grupo7.TrabajoDeCampo.dto.persona.PersonaRequest;
import com.grupo7.TrabajoDeCampo.dto.persona.PersonaResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.BecarioResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.IntegranteConsejoEducativoResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.InvestigadorResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.PersonalResponse;
import com.grupo7.TrabajoDeCampo.dto.usuario.CrearUsuarioRequest;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaDocumentoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaEquipoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaResponse;
import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import com.grupo7.TrabajoDeCampo.model.equipo.Equipo;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaDetalle;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaEquipo;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaPersona;
import com.grupo7.TrabajoDeCampo.model.persona.Persona;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.service.documento.DocumentoService;
import com.grupo7.TrabajoDeCampo.service.grupo.GrupoService;
import com.grupo7.TrabajoDeCampo.service.persona.PersonaService;
import com.grupo7.TrabajoDeCampo.service.usuario.UsuarioService;
import com.grupo7.TrabajoDeCampo.service.equipo.EquipoService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaDocumentoService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaEquipoService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaPersonaService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.BecarioService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.IntegranteConsejoEducativoService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.InvestigadorService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.PersonalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RestController
@PreAuthorize("hasRole('ADMINISTRADOR')")
@RequestMapping("/administrador")
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
    @Autowired
    private UsuarioService usuarioService;



    @PostMapping("/usuarios/asociarUsuario")
    public Usuario crearUsuario(@RequestBody CrearUsuarioRequest dto) {
        return usuarioService.crearUsuarioParaPersona(dto);
    }



    //-----------------------------------GRUPOS-----------------------------------
    //crear nuevo grupo
    @PostMapping("/grupos/agregarGrupo")
    public Grupo crearGrupoAdministrador(@RequestBody Grupo grupo) { return grupoService.crearGrupoAdmin(grupo); }

    //listar todos los grupos
    @GetMapping ("/grupos/listarGrupos")
    public List<Grupo> listarGrupos(){ return grupoService.listarGruposAdmin();}

    //obtener un grupo por ID
    @GetMapping("/grupos/obtenerGrupo/{oidGrupo}")
    public Optional<Grupo> obtenerGrupoPorId(@PathVariable("oidGrupo") Long oidGrupo) {
        return grupoService.obtenerGrupoPorIdAdmin(oidGrupo);
    }

    //actualizar grupo
    @PutMapping("/grupos/actualizarGrupo/{oidGrupo}")
    public Grupo actualizarGrupo(@PathVariable("oidGrupo") Long oidGrupo, @RequestBody Grupo grupo) {
        return grupoService.actualizarGrupoAdmin(oidGrupo, grupo);
    }

    //eliminar grupo
    @DeleteMapping("/grupos/eliminarGrupo/{oidGrupo}")
    public void eliminarGrupo(@PathVariable("oidGrupo") Long oidGrupo) {
        grupoService.eliminarGrupoAdmin(oidGrupo);
    }


    //-----------------------------------DOCUMENTOS-----------------------------------

    //crear nuevo Documento
    @PostMapping(
            value = "/documentos/agregarDocumento/{oidGrupo}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public DocumentoResponse crearDocumento(
            @PathVariable Long oidGrupo,
            @RequestPart("documento") DocumentoRequest request,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo
    ) throws IOException {

        return documentoService.crearDocumentoAdmin(
                request,
                oidGrupo,
                archivo
        );
    }

    //listar todos los documentos
    @GetMapping("/documentos/listarDocumentos")
    public List<DocumentoResponse> listarDocumentos() {
        return documentoService.listarDocumentosAdmin();
    }


    //obtener un documento por ID
    @GetMapping("/documentos/obtenerDocumento/{oidDocumento}")
    public Optional<Documento> obtenerDocumentoPorId(@PathVariable("oidDocumento") Long oidDocumento){
        return documentoService.obtenerDocumentoPorIdAdmin(oidDocumento);
    }

    //actualizar un Documento
    @PutMapping(
            value = "/documentos/actualizarDocumento/{oidDocumento}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public DocumentoResponse actualizarDocumento(
            @PathVariable Long oidDocumento,
            @RequestPart("documento") DocumentoRequest request,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo
    ) throws IOException {

        return documentoService.actualizarDocumentoAdmin(
                oidDocumento,
                request,
                archivo
        );
    }

    //eliminar un Documento
    @DeleteMapping("/documentos/eliminarDocumento/{oidDocumento}")
    public void eliminarDocumento(@PathVariable("oidDocumento") Long oidDocumento) {
        documentoService.eliminarDocumentoAdmin(oidDocumento);
    }

    @GetMapping("/documentos/descargarDocumento/{oidDocumento}")
    public ResponseEntity<byte[]> descargarDocumento(
            @PathVariable("oidDocumento") Long oidDocumento) {

        return documentoService.descargarDocumento(oidDocumento);
    }






    //-----------------------------------EQUIPOS-----------------------------------
    //crear nuevo equipo
    @PostMapping("/equipos/agregarEquipo/{oidGrupo}")
    public Equipo crearEquipo(@RequestBody Equipo equipo, @PathVariable("oidGrupo") Long oidGrupo){
        return equipoService.crearEquipoAdmin(equipo, oidGrupo);
    }

    //listar todos los equipos
    @GetMapping("/equipos/listarEquipos")
    public List<EquipoResponse> listarEquipos() {
        return equipoService.listarEquiposAdmin();
    }

    //obtener un equipo por ID
    @GetMapping("/equipos/obtenerEquipo/{oidEquipo}")
    public Optional<Equipo> obtenerEquipoPorId(@PathVariable("oidEquipo") Long oidEquipo) {
        return equipoService.obtenerEquipoPorIdAdmin(oidEquipo);
    }

    //actualizar un equipo
    @PutMapping("/equipos/actualizarEquipo/{oidEquipo}")
    public Equipo actualizarEquipo(@PathVariable("oidEquipo") Long oidEquipo, @RequestBody Equipo equipoActualizado) {
        return equipoService.actualizarEquipoAdmin(oidEquipo, equipoActualizado);
    }

    //eliminar un equipo
    @DeleteMapping("/equipos/eliminarEquipo/{oidEquipo}")
    public void eliminarEquipo(@PathVariable Long oidEquipo) {
        equipoService.eliminarEquipoAdmin(oidEquipo);
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



    //quitar una persona
    @PutMapping("/personas/quitarPersona/{oidPersona}") public void eliminarPersona (@PathVariable Long oidPersona) {
        personaService.desactivarPersona(oidPersona); }


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


    //-----------------------------------INTEGRANTES CONSEJO EDUCATIVO-----------------------------------

    //listar todas las integranteConsejoEducativos
    @GetMapping("/personas/integrantesConsejoEducativo/listarIntegrantesConsejoEducativo")
    public List<IntegranteConsejoEducativoResponse> listarIntegrantesConsejoEducativo() {
        return integranteConsejoEducativoService.listarIntegrantesConsejoEducativo();
    }

    //obtener una integranteConsejoEducativo en especifico por ID
    @GetMapping("/personas/integrantesConsejoEducativo/obtenerIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")
    public ResponseEntity<IntegranteConsejoEducativoResponse> obtenerIntegranteConsejoEducativoPorId(
            @PathVariable Long oidIntegranteConsejoEducativo) {

        return ResponseEntity.ok(
                integranteConsejoEducativoService.obtenerIntegranteConsejoEducativoPorId(
                        oidIntegranteConsejoEducativo
                )
        );
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


    //-----------------------------------MEMORIA-----------------------------------
    @PostMapping("/memorias/crearMemoria/{oidGrupo}/{anio}")
    public Memoria crearMemoria(
            @PathVariable Long oidGrupo,
            @PathVariable Integer anio) {
        return memoriaService.crearMemoriaAdmin(oidGrupo, anio);
    }

    //listar todas las memorias
    @GetMapping("/memorias/listadoMemorias")
    public List<MemoriaResponse> listarTodasLasMemorias() {
        return memoriaService.listarTodasLasMemoriasAdmin();
    }


    // obtener una memoria por ID
    @GetMapping("/memorias/obtenerMemoria/{oidMemoria}")
    public MemoriaDetalleResponse obtenerMemoriaPorId(@PathVariable Long oidMemoria) {
        return memoriaService.obtenerMemoriaEspecifica(oidMemoria);
    }

    //-----------------------------------MEMORIA EQUIPO-----------------------------------


    // agregar equipo a una memoria
    @PostMapping("/memorias/{oidMemoria}/agregarEquipo/{oidEquipo}")
    public MemoriaEquipo agregarEquipoAMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidEquipo) {

        return memoriaEquipoService.agregarEquipoAMemoriaAdmin(
                oidMemoria,
                oidEquipo
        );
    }

    // listar equipos de una memoria
    @GetMapping("/memorias/{oidMemoria}/listadoEquipos")
    public List<MemoriaEquipoResponse> listarEquiposDeMemoria(
            @PathVariable Long oidMemoria) {

        return memoriaEquipoService.listarEquipoPorMemoriaAdmin(oidMemoria);
    }

    // quitar equipo de una memoria
    @DeleteMapping("/memorias/{oidMemoria}/quitarEquipo/{oidEquipo}")
    public void quitarEquipoDeMemoria(@PathVariable Long oidMemoria, @PathVariable Long oidEquipo) {
        memoriaEquipoService.quitarEquipoAMemoriaAdmin(oidMemoria, oidEquipo);
    }


//-----------------------------------MEMORIA DOCUMENTO-----------------------------------

    // agregar documento a memoria
    @PostMapping("/memorias/{oidMemoria}/agregarDocumento/{oidDocumento}")
    public void agregarDocumentoAMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidDocumento) {

        memoriaDocumentoService.agregarDocumentoMemoriaAdmin(oidMemoria, oidDocumento);
    }

    //listar documentos de una memoria
    @GetMapping("/memorias/{oidMemoria}/listadoDocumentos")
    public List<MemoriaDocumentoResponse> listarDocumentosDeMemoria(
            @PathVariable Long oidMemoria) {

        return memoriaDocumentoService.listarDocumentosPorMemoriaAdmin(oidMemoria);
    }

    // quitar documento de una memoria
    @DeleteMapping("/memorias/{oidMemoria}/quitarDocumento/{oidDocumento}")
    public void quitarDocumentoDeMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidDocumento) {

        memoriaDocumentoService.quitarDocumentoMemoriaAdmin(oidMemoria, oidDocumento);
    }

    //-----------------------------------MEMORIA PERSONA-----------------------------------

    // agregar persona a una memoria
    @PostMapping("/memorias/{oidMemoria}/agregarPersona/{oidPersona}")
    public MemoriaPersona agregarPersonaAMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidPersona) {

        return memoriaPersonaService.agregarPersonaAMemoriaAdmin(
                oidMemoria,
                oidPersona
        );
    }


    //listar personas de una memoria
    @GetMapping("/memorias/{oidMemoria}/listadoPersonas")
    public List<MemoriaPersona> listarPersonasDeMemoria(
            @PathVariable Long oidMemoria) {

        return memoriaPersonaService.listarPersonasPorMemoriaAdmin(oidMemoria);
    }

    // quitar persona de una memoria
    @DeleteMapping("/memorias/{oidMemoria}/quitarPersona/{oidPersona}")
    public void quitarPersonaDeMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidPersona) {

        memoriaPersonaService.quitarPersonaAMemoriaAdmin(
                oidMemoria,
                oidPersona
        );
    }


}