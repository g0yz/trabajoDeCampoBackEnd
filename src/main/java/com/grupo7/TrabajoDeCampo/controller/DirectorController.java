package com.grupo7.TrabajoDeCampo.controller;



import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoArchivoResponse;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoRequest;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoResponse;
import com.grupo7.TrabajoDeCampo.dto.equipo.EquipoRequest;
import com.grupo7.TrabajoDeCampo.dto.equipo.EquipoResponse;
import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoRequest;
import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.*;
import com.grupo7.TrabajoDeCampo.dto.persona.PersonaRequest;
import com.grupo7.TrabajoDeCampo.dto.persona.PersonaResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.BecarioResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.IntegranteConsejoEducativoResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.InvestigadorResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.PersonalResponse;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaEquipo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.service.MemoriaExcelExport;
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
import org.springframework.web.multipart.MultipartFile;

import java.sql.SQLException;
import java.util.List;


@RestController
@PreAuthorize("hasRole('DIRECTOR')")
@RequestMapping("/director")
public class DirectorController {


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
    @Autowired
    private MemoriaExcelExport memoriaExcelExport;



    //-----------------------------------GRUPO-----------------------------------
    //visualizar grupo
    @GetMapping("/grupo/ver")
    public Grupo verGrupo(Authentication auth) { Usuario usuario = (Usuario) auth.getPrincipal();
        return grupoService.obtenerGrupoDelDirector(usuario);
    }

    //editar informacion del grupo
    @PutMapping("/grupo/editar")
    public GrupoResponse editarGrupo(Authentication auth, @RequestBody GrupoRequest request) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        return grupoService.editarGrupoDirector(usuario, request);
    }


    //-----------------------------------DOCUMENTOS-----------------------------------

    //agregar documento al grupo
    @PostMapping(
            value = "/documentos/agregarDocumento",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<DocumentoResponse> agregarDocumento(
            Authentication auth,
            @RequestPart("documento") DocumentoRequest request,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo
    ) {
        Usuario usuario = (Usuario) auth.getPrincipal();

        DocumentoResponse response =
                documentoService.agregarDocumento(usuario, request, archivo);

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
    @PutMapping(
            value = "/documentos/actualizarDocumento/{oidDocumento}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<DocumentoResponse> editarDocumento(
            Authentication auth,
            @PathVariable Long oidDocumento,
            @RequestPart("documento") DocumentoRequest request,
            @RequestPart(value = "archivo", required = false) MultipartFile archivo
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        DocumentoResponse response =
                documentoService.actualizarDocumento(
                        usuario,
                        oidDocumento,
                        request,
                        archivo
                );

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
    public ResponseEntity<byte[]> descargarDocumento(
            @PathVariable("oidDocumento") Long oidDocumento) {

        return documentoService.descargarDocumento(oidDocumento);
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
    @PutMapping("/personas/actualizarPersona/{oidPersona}")
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

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/grupos/personas/listarPersonas")
    public List<PersonaResponse> listarPersonasDelGrupo(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return personaService.listarPersonasPorGrupo(oidGrupo);
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

    //-----------------------------------INVESTIGADOR-----------------------------------

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

    //-----------------------------------INTEGRANTECONSEJOEDUCATIVO-----------------------------------


    //listar todos las integrantes del Consejo Educativo del grupo
    @GetMapping ("/personas/integrantesConsejoEducativo/listarIntegrantesConsejoEducativo")
    public List<IntegranteConsejoEducativoResponse> listarIntegrantesConsejoEducativoDelGrupo(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();
        return integranteConsejoEducativoService.listarIntegrantesConsejoEducativoDelGrupo(oidGrupo);
    }

    //obtener una integranteConsejoEducativo en especifico del grupo
    @GetMapping("/personas/integrantesConsejoEducativo/obtenerIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")
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

    //agregar Memoria al grupo
    @PostMapping("/memorias/agregarMemoria/{anio}")
    public MemoriaResponse agregarMemoria(
            @PathVariable Integer anio,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        return memoriaService.crearMemoriaConPermiso(
                usuario,
                anio
        );
    }


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


    //-----------------------------------MEMORIA EQUIPO-----------------------------------
    // agregar equipo a una memoria
    @PostMapping("/memorias/{oidMemoria}/agregarEquipo/{oidEquipo}")
    public MemoriaEquipo agregarEquipoAMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidEquipo,
            Authentication auth
    ) {

        // Usuario logueado
        Usuario usuario = (Usuario) auth.getPrincipal();

        return memoriaEquipoService.agregarEquipoAMemoriaDirector(
                usuario,
                oidMemoria,
                oidEquipo
        );
    }



    // listar equipos de una memoria
    @GetMapping("/memorias/{oidMemoria}/listadoEquipos")
    public List<MemoriaEquipoResponse> listarEquiposDeMemoria(
            @PathVariable Long oidMemoria,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        return memoriaEquipoService.listarEquipoPorMemoriaDirector(
                usuario,
                oidMemoria
        );
    }


    // quitar equipo de una memoria
    @DeleteMapping("/memorias/{oidMemoria}/quitarEquipo/{oidEquipo}")
    public void quitarEquipoDeMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidEquipo,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        memoriaEquipoService.quitarEquipoAMemoriaDirector(
                usuario,
                oidMemoria,
                oidEquipo
        );
    }

    //-----------------------------------MEMORIA DOCUMENTO-----------------------------------

    // agregar documento a memoria
    @PostMapping("/memorias/{oidMemoria}/agregarDocumento/{oidDocumento}")
    public void agregarDocumentoAMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidDocumento,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        memoriaDocumentoService.agregarDocumentoAMemoriaDirector(
                usuario,
                oidMemoria,
                oidDocumento
        );
    }

    //listar documentos de una memoria
    @GetMapping("/memorias/{oidMemoria}/listadoDocumentos")
    public List<MemoriaDocumentoResponse> listarDocumentosDeMemoria(
            @PathVariable Long oidMemoria,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        return memoriaDocumentoService.listarDocumentosDeMemoriaDirector(
                usuario,
                oidMemoria
        );
    }

    // quitar documento de una memoria
    @DeleteMapping("/memorias/{oidMemoria}/quitarDocumento/{oidDocumento}")
    public void quitarDocumentoDeMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidDocumento,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        memoriaDocumentoService.quitarDocumentoDeMemoriaDirector(
                usuario,
                oidMemoria,
                oidDocumento
        );
    }


    //-----------------------------------MEMORIA PERSONA-----------------------------------

    // agregar persona a una memoria
    @PostMapping("/memorias/{oidMemoria}/agregarPersona/{oidPersona}")
    public void agregarPersonaAMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidPersona,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        memoriaPersonaService.agregarPersonaAMemoriaDirector(
                usuario,
                oidMemoria,
                oidPersona
        );
    }


    //listar personas de una memoria
    @GetMapping("/memorias/{oidMemoria}/listadoPersonas")
    public List<MemoriaPersonaResponse> listarPersonasDeMemoria(
            @PathVariable Long oidMemoria,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        return memoriaPersonaService
                .listarPersonasPorMemoriaDirector(
                        usuario,
                        oidMemoria
                );
    }

    // quitar persona de una memoria
    @DeleteMapping("/memorias/{oidMemoria}/quitarPersona/{oidPersona}")
    public ResponseEntity<?> quitarPersonaDeMemoria(
            @PathVariable Long oidMemoria,
            @PathVariable Long oidPersona,
            Authentication auth
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        memoriaPersonaService
                .quitarPersonaDeMemoriaDirector(
                        usuario,
                        oidMemoria,
                        oidPersona
                );

        return ResponseEntity.ok("Persona quitada de la memoria");
    }




    //exportar memoria en excel
    @GetMapping("/memorias/{oidMemoria}/exportarExcel")
    public ResponseEntity<byte[]> exportarMemoriaExcel(
            @PathVariable Long oidMemoria,
            Authentication auth) {

        MemoriaDetalleResponse memoria =
                memoriaService.obtenerMemoriaEspecificaGrupo(auth,oidMemoria);

        byte[] archivo = memoriaExcelExport.exportarMemoriaCompleta(
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
