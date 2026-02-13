package com.grupo7.TrabajoDeCampo.controller;


import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoRequest;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoResponse;
import com.grupo7.TrabajoDeCampo.dto.equipo.EquipoRequest;
import com.grupo7.TrabajoDeCampo.dto.equipo.EquipoResponse;
import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoRequest;
import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaDetalleResponse;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/viceDirector")
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
    @DeleteMapping("/documentos/quitarDocumento/{oidDocumento}")
    public ResponseEntity<String> quitarDocumento(
            Authentication auth,
            @PathVariable Long oidDocumento
    ) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        documentoService.eliminarDocumento(usuario, oidDocumento);

        return ResponseEntity.ok("Documento eliminado correctamente");
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
    @PutMapping("/equipos")
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
    @DeleteMapping("/equipos/quitarEquipo/{oidEquipo}")
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
    //@PostMapping("/personas/agregarPersona")

    //listar personas del grupo
    //@GetMapping("/personas/listarPersonas")

    //obtener una persona especifica del grupo
    //@GetMapping("/personas/obtenerPersona/{oidPersona}")

    //editar una persona del grupo
    //@PutMapping("/personas/editarPersona/{oidPersona}")

    //quitar una persona del grupo SOFT
    //


    //-----------------------------------BECARIOS-----------------------------------

    //listar becarios del grupo
    //@GetMapping("/personas/becarios/listarPersonas")

    //obtener un becario en especifico del grupo
    //@GetMapping("/personas/becarios/obtenerBecario/{oidBecario}")

    //-----------------------------------INVESTIGADOR-----------------------------------

    //listar investigadores del grupo
    //GetMapping("/personas/investigadores/listarInvestigadores")

    //obtener un investigador en especifico del grupo
    //@GetMapping("/personas/investigadores/obtenerInvestigador/{oidInvestigador}")

    //-----------------------------------INTEGRANTECONSEJOEDUCATIVO-----------------------------------

    //listar integrantes del consejo educativo del grupo
    //@GetMapping("/personas/integranteConsejoEducativos/listarIntegrantesConsejoEducativo")


    //obtener un integrante del consejo educativo en especifico del grupo
    //@GetMappint("personas/integranteConsejoEducativos/obtenerIntegranteConsejoEducativo/{oidIntegranteConsejoEducativo}")

    //-----------------------------------PERSONAL-----------------------------------

    //listar personal del grupo
    //@GetMapping("/personas/personal/listarPersonal")

    //obtener un personal en especifico del grupo
    //@GetMapping("/personas/personal/obtenerPersonal/{oidPersonal}")

    //-----------------------------------MEMORIAS-----------------------------------

    //listar memorias del grupo
    //@GetMapping("/memorias/listarMemorias")


    //obtener una memoria especifica del grupo
    //@GetMapping("/memorias/obtenerMemoria/{oidMemoria}")


    //FALTA CORREGIR AUTHORIZACION METODO

    //exportar memoria en excel
    @GetMapping("/memorias/{oidMemoria}/exportarExcel")
    public ResponseEntity<byte[]> exportarMemoriaExcel(
            @PathVariable Long oidMemoria) {

        MemoriaDetalleResponse memoria =
                memoriaService.obtenerMemoriaEspecifica(oidMemoria);

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
