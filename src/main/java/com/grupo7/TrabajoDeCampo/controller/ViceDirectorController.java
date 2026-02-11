package com.grupo7.TrabajoDeCampo.controller;


import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoResponse;
import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaDetalleResponse;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.service.MemoriaExcelExportIntegrante;
import com.grupo7.TrabajoDeCampo.service.grupo.GrupoService;
import com.grupo7.TrabajoDeCampo.service.memoria.MemoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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



    //-----------------------------------GRUPO-----------------------------------
    //visualizar grupo
    @GetMapping("/grupo/ver")
    public Grupo verGrupo(Authentication auth) { Usuario usuario = (Usuario) auth.getPrincipal();
        return grupoService.obtenerGrupoDelViceDirector(usuario);
    }

    //-----------------------------------DOCUMENTOS-----------------------------------

    //agregar documento al grupo
    //@PostMapping("/documentos/agregarDocumento/{oidDocumento}")


    //listar documentos del grupo
    //@GetMapping("/documentos/listarDocumentos")


    //obtener documento especifico del grupo
    //GetMapping("/documentos/obtenerDocumento/{oidDocumento}")


    //editar documento del grupo
    //@PutMapping("/documentos/editarDocumento/{oidDocumento}")


    //quitar documento del grupo SOFT
    //


    //-----------------------------------EQUIPOS-----------------------------------


    //agregar equipo al grupo
    //@PostMapping("/equipos/agregarEquipo/{oidEquipo}")


    //listar equipos del grupo
    //@GetMapping("/equipos/listarEquipos")


    //obtener equipo especifico del grupo
    //@GetMapping("/equipos/obtenerEquipo/{oidEquipo}")


    //editar equipo del grupo
    //@PutMapping("/equipos")


    //quitar documento del grupo SOFT
    //

    //-----------------------------------PERSONAS-----------------------------------

    //agregar una Persona al grupo
    //@PostMapping("/personas/agregarPersona/{oidPersona}")

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
