package com.grupo7.TrabajoDeCampo.controller;


import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoArchivoResponse;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoRequest;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoResponse;
import com.grupo7.TrabajoDeCampo.dto.equipo.EquipoRequest;
import com.grupo7.TrabajoDeCampo.dto.equipo.EquipoResponse;
import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoRequest;
import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaDetalleResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaResponse;
import com.grupo7.TrabajoDeCampo.dto.persona.PersonaRequest;
import com.grupo7.TrabajoDeCampo.dto.persona.PersonaResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.BecarioResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.IntegranteConsejoEducativoResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.InvestigadorResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.PersonalResponse;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.service.MemoriaExcelExportIntegrante;
import com.grupo7.TrabajoDeCampo.service.documento.DocumentoService;
import com.grupo7.TrabajoDeCampo.service.equipo.EquipoService;
import com.grupo7.TrabajoDeCampo.service.grupo.GrupoService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaDocumentoService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaEquipoService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaPersonaService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaService;
import com.grupo7.TrabajoDeCampo.service.persona.PersonaService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.BecarioService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.IntegranteConsejoEducativoService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.InvestigadorService;
import com.grupo7.TrabajoDeCampo.service.persona.tipoPersona.PersonalService;
import com.grupo7.TrabajoDeCampo.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@PreAuthorize("hasRole('VICEDIRECTOR')")
@RequestMapping("/vicedirector")
public class ViceDirectorController {

    @Autowired
    GrupoService grupoService;


    @Autowired
    private MemoriaService memoriaService;

    @Autowired
    private MemoriaExcelExportIntegrante memoriaExcelExportIntegrante;

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
    private MemoriaPersonaService memoriaPersonaService;
    @Autowired
    private MemoriaDocumentoService memoriaDocumentoService;
    @Autowired
    private MemoriaEquipoService memoriaEquipoService;
    @Autowired
    private UsuarioService usuarioService;




    //-----------------------------------GRUPO-----------------------------------
    //visualizar grupo
    @GetMapping("/grupo/ver")
    public Grupo verGrupo(Authentication auth) { Usuario usuario = (Usuario) auth.getPrincipal();
        return grupoService.obtenerGrupoDelViceDirector(usuario);
    }


    //-----------------------------------DOCUMENTOS-----------------------------------

    //agregar documento al grupo
    @PostMapping("/documentos/agregarDocumento")
    public ResponseEntity<DocumentoResponse> agregarDocumento(
            Authentication auth,
            @RequestBody DocumentoRequest request
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        DocumentoResponse response =
                documentoService.agregarDocumento(usuario, request);

        return ResponseEntity.ok(response);
    }

    //listar documentos del grupo
    @GetMapping("/documentos/listarDocumentos")
    public ResponseEntity<List<DocumentoResponse>> listarDocumentos(
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        List<DocumentoResponse> lista =
                documentoService.listarDocumentos(usuario);

        return ResponseEntity.ok(lista);
    }

    //obtener documento especifico del grupo
    @GetMapping("/documentos/obtenerDocumento/{oidDocumento}")
    public ResponseEntity<DocumentoResponse> obtenerDocumento(
            Authentication auth,
            @PathVariable Long oidDocumento
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        DocumentoResponse response =
                documentoService.obtenerDocumento(oidDocumento, usuario);

        return ResponseEntity.ok(response);
    }

    //editar documento del grupo
    @PutMapping("/documentos/editarDocumento/{oidDocumento}")
    public ResponseEntity<DocumentoResponse> editarDocumento(
            Authentication auth,
            @PathVariable Long oidDocumento,
            @RequestBody DocumentoRequest request
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        DocumentoResponse response =
                documentoService.editarDocumento(usuario, oidDocumento, request);

        return ResponseEntity.ok(response);
    }

    //quitar documento del grupo SOFT
    @PutMapping("/documentos/quitarDocumento/{oidDocumento}")
    public ResponseEntity<String> quitarDocumento(
            Authentication auth,
            @PathVariable Long oidDocumento
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        documentoService.eliminarDocumento(usuario, oidDocumento);

        return ResponseEntity.ok("Documento eliminado correctamente");
    }


    @GetMapping("/documentos/descargarDocumento/{oidDocumento}")
    public ResponseEntity<InputStreamResource> descargarDocumento(@PathVariable Long oid) throws SQLException {

        DocumentoArchivoResponse docResp = documentoService.descargarDocumento(oid);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + docResp.getNombreArchivo() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(docResp.getInputStream()));
    }

    //-----------------------------------EQUIPOS-----------------------------------


    //agregar equipo al grupo

    @PostMapping("/equipos/agregarEquipo")
    public ResponseEntity<EquipoResponse> agregarEquipo(
            Authentication auth,
            @RequestBody EquipoRequest request
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        EquipoResponse response =
                equipoService.agregarEquipo(usuario, request);

        return ResponseEntity.ok(response);
    }

    //listar equipos del grupo
    @GetMapping("/equipos/listarEquipos")
    public ResponseEntity<List<EquipoResponse>> listarEquipos(
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        List<EquipoResponse> lista =
                equipoService.listarEquipos(usuario);

        return ResponseEntity.ok(lista);
    }

    //obtener equipo especifico del grupo
    @GetMapping("/equipos/obtenerEquipo/{oidEquipo}")
    public ResponseEntity<EquipoResponse> obtenerEquipo(
            Authentication auth,
            @PathVariable Long oidEquipo
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        EquipoResponse response =
                equipoService.obtenerEquipo(oidEquipo, usuario);

        return ResponseEntity.ok(response);
    }

    //editar equipo del grupo
    @PutMapping("/equipos/actualizarEquipo/{oidEquipo}")
    public ResponseEntity<EquipoResponse> editarEquipo(
            Authentication auth,
            @PathVariable Long oidEquipo,
            @RequestBody EquipoRequest request
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        EquipoResponse response =
                equipoService.editarEquipo(usuario, oidEquipo, request);

        return ResponseEntity.ok(response);
    }

    // quitar equipo del grupo (SOFT DELETE)
    @PutMapping("/equipos/quitarEquipo/{oidEquipo}")
    public ResponseEntity<String> quitarEquipo(
            Authentication auth,
            @PathVariable Long oidEquipo
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        equipoService.eliminarEquipo(usuario, oidEquipo);

        return ResponseEntity.ok("Equipo eliminado correctamente");
    }

    //-----------------------------------PERSONAS-----------------------------------

    //agregar una Persona al grupo
    @PostMapping("/personas/agregarPersona")
    public ResponseEntity<PersonaResponse> agregarPersona(
            @RequestBody PersonaRequest request,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        PersonaResponse response =
                personaService.agregarPersonaAGrupo(usuario, request);

        return ResponseEntity.ok(response);
    }


    //editar una persona del grupo
    @PutMapping("/personas/editarPersona/{oidPersona}")
    public PersonaResponse editarPersona(
            @PathVariable Long oidPersona,
            @RequestBody PersonaRequest request,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        return personaService.editarPersonaDelGrupo(
                usuario,
                oidPersona,
                request
        );
    }

    //quitar una persona del grupo SOFT
    @PutMapping("/personas/quitarPersona/{oidPersona}")
    public ResponseEntity<Void> quitarPersona(
            @PathVariable Long oidPersona,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        personaService.quitarPersonaDelGrupo(usuario, oidPersona);

        return ResponseEntity.noContent().build(); // 204
    }

    //-----------------------------------BECARIOS-----------------------------------

    //listar todas las becarios del grupo
    @GetMapping ("/personas/becarios/listarBecarios")
    public List<BecarioResponse> listarBecarios(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        return becarioService.listarBecariosDelGrupo(oidGrupo);
    }

    //obtener una becario en especifico del grupo
    @GetMapping("/personas/becarios/obtenerBecario/{oidBecario}")
    public BecarioResponse obtenerBecario(@PathVariable Long oidBecario, Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return becarioService.obtenerBecarioDelGrupo(oidGrupo, oidBecario);
    }

    //-----------------------------------INVESTIGADORES-----------------------------------

    //listar todos las investigadores del grupo
    @GetMapping ("/personas/investigadores/listarInvestigadores")
    public List<InvestigadorResponse> listarInvestigadoresDelGrupo(
            Authentication auth) {

        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        return investigadorService.listarInvestigadoresDelGrupo(oidGrupo);
    }

    //obtener una investigador en especifico del grupo
    @GetMapping("/personas/investigadores/obtenerInvestigador/{oidInvestigador}")
    public InvestigadorResponse obtenerInvestigadorDelGrupo(@PathVariable Long oidGrupo, @PathVariable Long oidInvestigador) {
        return investigadorService.obtenerInvestigadorDelGrupo(oidGrupo, oidInvestigador);
    }

    //-----------------------------------INTEGRANTES CONSEJO EDUCATIVO-----------------------------------

    //listar todos las integrantes del Consejo Educativo del grupo
    @GetMapping ("/personas/integranteConsejoEducativos/listarIntegrantesConsejoEducativo")
    public List<IntegranteConsejoEducativoResponse> listarIntegrantesConsejoEducativoDelGrupo(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return integranteConsejoEducativoService.listarIntegrantesConsejoEducativoDelGrupo(oidGrupo);
    }

    //obtener una integranteConsejoEducativo en especifico del grupo
    @GetMapping("/personas/integranteConsejoEducativos/obtenerIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")
    public IntegranteConsejoEducativoResponse obtenerIntegranteConsejoEducativoDelGrupo(@PathVariable Long oidGrupo, @PathVariable Long oidIntegranteConsejoEducativo) {
        return integranteConsejoEducativoService
                .obtenerIntegranteConsejoEducativoDelGrupo(
                        oidGrupo,
                        oidIntegranteConsejoEducativo
                );
    }

    //-----------------------------------PERSONAL-----------------------------------

    //listar todo el personal del grupo
    @GetMapping ("/personas/personal/listarPersonal")
    public List<PersonalResponse> listarPersonalDelGrupo(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return personalService.listarPersonalDelGrupo(oidGrupo);
    }

    //obtener un personal en especifico del grupo
    @GetMapping("/personas/personal/obtenerPersonal/{oidPersonal}")
    public PersonalResponse obtenerPersonalDelGrupo(
            @PathVariable Long oidGrupo,
            @PathVariable Long oidPersonal) {
        return personalService.obtenerPersonalDelGrupo(
                oidGrupo,
                oidPersonal
        );
    }

    //-----------------------------------MEMORIAS-----------------------------------

    //listar todas las memorias del grupo
    @GetMapping("/memorias/listarMemorias")
    public List <MemoriaResponse> listarMemoriasDelGrupo(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        return memoriaService.listarMemoriasDelGrupo(oidGrupo);
    }

    //obtener una memoria especifica del grupo
    @GetMapping("/memorias/obtenerMemoria/{oidMemoria}")
    public MemoriaDetalleResponse verMemoria(
            @PathVariable("oidMemoria") Long oidMemoria,
            Authentication auth
    ) {
        return memoriaService.obtenerMemoriaEspecificaGrupo(auth, oidMemoria);
    }

    //exportar memoria en excel
    @GetMapping("/memorias/{oidMemoria}/exportarExcel")
    public ResponseEntity<byte[]> exportarMemoriaExcel(
            @PathVariable Long oidMemoria,
            Authentication auth) {

        MemoriaDetalleResponse memoria =
                memoriaService.obtenerMemoriaEspecificaGrupo(auth,oidMemoria);

        byte[] archivo = memoriaExcelExportIntegrante.exportarMemoriaCompleta(
                new GrupoResponse(memoria.getGrupo()),
                memoria.getPersonas(),
                memoria.getDocumentos(),
                memoria.getEquipos()
        );

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=memoria_" + oidMemoria + ".xlsx")
                .contentType(
                        MediaType.parseMediaType(
                                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
                        )
                )
                .body(archivo);
    }




}
