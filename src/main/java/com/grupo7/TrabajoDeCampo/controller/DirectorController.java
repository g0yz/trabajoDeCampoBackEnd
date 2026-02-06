package com.grupo7.TrabajoDeCampo.controller;


import com.grupo7.TrabajoDeCampo.dto.dtoDirector.grupo.GrupoRequestDirector;
import com.grupo7.TrabajoDeCampo.dto.dtoDirector.grupo.GrupoResponseDirector;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@PreAuthorize("hasRole('Director')")
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




    //-----------------------------------GRUPO-----------------------------------
    //visualizar grupo
    @GetMapping("/grupo/ver")
    public Grupo verGrupo(Authentication auth) { Usuario usuario = (Usuario) auth.getPrincipal();
        return grupoService.obtenerGrupoDelDirector(usuario);
    }

    //editar informacion del grupo
    @PutMapping("/grupo/editar")
    public GrupoResponseDirector editarGrupo(Authentication auth, @RequestBody GrupoRequestDirector request) {
        Usuario usuario = (Usuario) auth.getPrincipal();
        return grupoService.editarGrupoDirector(usuario, request);
    }


    //-----------------------------------DOCUMENTOS-----------------------------------

    //agregar documento al grupo
    //@PostMapping("/documentos/agregarDocumento")


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
    //@PostMapping("/equipos/agregarEquipo")


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

    //agregar Memoria al grupo
    //@PostMapping("/memorias/agregarMemoria")


    //listar memorias del grupo
    //@GetMapping("/memorias/listarMemorias")


    //obtener una memoria especifica del grupo
    //@GetMapping("/memorias/obtenerMemoria/{oidMemoria}")

    //editar memoria
    //@





}
