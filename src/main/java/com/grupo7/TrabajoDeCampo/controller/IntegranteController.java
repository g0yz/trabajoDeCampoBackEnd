package com.grupo7.TrabajoDeCampo.controller;


import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoArchivoResponse;
import com.grupo7.TrabajoDeCampo.dto.documento.DocumentoResponse;
import com.grupo7.TrabajoDeCampo.dto.equipo.EquipoResponse;
import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaDetalleResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.BecarioResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.IntegranteConsejoEducativoResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.InvestigadorResponse;
import com.grupo7.TrabajoDeCampo.dto.tipoPersona.PersonalResponse;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.service.MemoriaExcelExportIntegrante;

import com.grupo7.TrabajoDeCampo.service.documento.DocumentoService;
import com.grupo7.TrabajoDeCampo.service.equipo.EquipoService;
import com.grupo7.TrabajoDeCampo.service.grupo.GrupoService;
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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@PreAuthorize("hasRole('INTEGRANTE')")
@RequestMapping("/integrante")
public class IntegranteController {

    @Autowired
    private GrupoService grupoService;


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

    @Autowired
    private MemoriaService memoriaService;

    @Autowired
    private MemoriaExcelExportIntegrante memoriaExcelExportIntegrante;


    //-----------------------------------GRUPO-----------------------------------
    //visualizar grupo del integrante
    @GetMapping("/grupo/ver")
    public GrupoResponse verGrupo(Authentication auth) { Usuario usuario = (Usuario) auth.getPrincipal();
        return grupoService.obtenerGrupoDelIntegrante(usuario);
    }

    //-----------------------------------DOCUMENTOS-----------------------------------
    //listar todos los documentos del grupo
    @GetMapping ("/documentos/listarDocumentos")
    public List<DocumentoResponse> listarDocumentos(Authentication auth) {

        Usuario usuario = (Usuario) auth.getPrincipal();

        return documentoService.listarDocumentos(usuario);
    }

    //obtener un documento en especifico del grupo
    @GetMapping("/documentos/visualizarDocumento/{oidDocumento}")
    public DocumentoResponse obtenerDocumento(@PathVariable Long oidDocumento, Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        return documentoService.obtenerDocumento(oidDocumento, usuario);
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

    //listar todos los equipos del grupo
    @GetMapping("/equipos/listarEquipo")
    public List<EquipoResponse> listarEquipos(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        return equipoService.listarEquipos(usuario);
    }

    //obtener un equipo en especifico del grupo
    @GetMapping("/equipos/obtenerEquipo/{oidEquipo}")
    public EquipoResponse obtenerEquipo( @PathVariable Long oidEquipo,Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        return equipoService.obtenerEquipo(oidEquipo, usuario);
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



    //-----------------------------------MEMORIA-----------------------------------

    //listar todas las memorias del grupo
    @GetMapping("/memorias/listarMemorias")
    public List <MemoriaResponse> listarMemoriasDelGrupo(Authentication auth) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        Long oidGrupo = usuario.getPersona().getGrupo().getOidGrupo();

        return memoriaService.listarMemoriasDelGrupo(oidGrupo);
    }

    //obtener una memoria en especifico
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



