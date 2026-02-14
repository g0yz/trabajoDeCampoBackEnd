package com.grupo7.TrabajoDeCampo.service.grupo;


import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoRequest;
import com.grupo7.TrabajoDeCampo.dto.grupo.GrupoResponse;
import com.grupo7.TrabajoDeCampo.model.grupo.Grupo;
import com.grupo7.TrabajoDeCampo.model.usuario.Usuario;
import com.grupo7.TrabajoDeCampo.repository.grupo.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GrupoService {

    private final GrupoRepository grupoRepository;

    public GrupoService(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }



    //ADMINISTRADOR

    public List<Grupo> listarGruposAdmin() {
        return grupoRepository.findAll();
    }

    public Optional<Grupo> obtenerGrupoPorIdAdmin(Long id) {
        return grupoRepository.findById(id);
    }

    public Grupo crearGrupoAdmin(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo actualizarGrupoAdmin(Long id, Grupo grupoActualizado) {
        return grupoRepository.findById(id)
                .map(grupo -> {
                    if (grupoActualizado.getFacultadRegional() != null)
                        grupo.setFacultadRegional(grupoActualizado.getFacultadRegional());
                    if (grupoActualizado.getNombreGrupo() != null)
                        grupo.setNombreGrupo(grupoActualizado.getNombreGrupo());
                    if (grupoActualizado.getSigla() != null)
                        grupo.setSigla(grupoActualizado.getSigla());
                    if (grupoActualizado.getEmail() != null)
                        grupo.setEmail(grupoActualizado.getEmail());
                    if (grupoActualizado.getOrganigrama() != null)
                        grupo.setOrganigrama(grupoActualizado.getOrganigrama());
                    if (grupoActualizado.getObjetivoYDesarollo() != null)
                        grupo.setObjetivoYDesarollo(grupoActualizado.getObjetivoYDesarollo());
                    return grupoRepository.save(grupo);
                })
                .orElseThrow(() -> new RuntimeException("Grupo no encontrado con id: " + id));
    }

    public void eliminarGrupoAdmin(Long id) {
        grupoRepository.deleteById(id);
    }



    //INTEGRANTE


    public GrupoResponse obtenerGrupoDelIntegrante(Usuario usuario) {



        if (usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("La persona no pertenece a ningún grupo");
        }

        Grupo grupo = usuario.getPersona().getGrupo();

        return new GrupoResponse(grupo);
    }


    //DIRECTOR

    public Grupo obtenerGrupoDelDirector(Usuario usuario) {

        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("La persona no pertenece a ningún grupo");
        }

        return usuario.getPersona().getGrupo();
    }


    public Grupo obtenerGrupoDelViceDirector(Usuario usuario) {

        if (usuario.getPersona() == null || usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("La persona no pertenece a ningún grupo");
        }

        return usuario.getPersona().getGrupo();
    }


    public GrupoResponse editarGrupoDirector(
            Usuario usuario,
            GrupoRequest request
    ) {
        Grupo grupo = obtenerGrupoDelDirector(usuario);

        grupo.setFacultadRegional(request.getFacultadRegional());
        grupo.setNombreGrupo(request.getNombreGrupo());
        grupo.setSigla(request.getSigla());
        grupo.setEmail(request.getEmail());
        grupo.setOrganigrama(request.getOrganigrama());
        grupo.setObjetivoYDesarollo(request.getObjetivoYDesarollo());

        grupoRepository.save(grupo);

        return new GrupoResponse(grupo);
    }


}
