package com.grupo7.TrabajoDeCampo.service.grupo;

import com.grupo7.TrabajoDeCampo.DTO.DtoAdministrador.grupo.GrupoResponseAdministrador;
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

    public List<Grupo> listarGrupos() {
        return grupoRepository.findAll();
    }

    public Optional<Grupo> obtenerGrupoPorId(Long id) {
        return grupoRepository.findById(id);
    }

    public Grupo crearGrupo(Grupo grupo) {
        return grupoRepository.save(grupo);
    }

    public Grupo actualizarGrupo(Long id, Grupo grupoActualizado) {
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

    public void eliminarGrupo(Long id) {
        grupoRepository.deleteById(id);
    }


    public GrupoResponseAdministrador obtenerGrupoDelUsuario(Usuario usuario) {

        if (usuario.getPersona() == null) {
            throw new RuntimeException("El usuario no tiene persona asociada");
        }

        if (usuario.getPersona().getGrupo() == null) {
            throw new RuntimeException("La persona no pertenece a ningún grupo");
        }

        Grupo grupo = usuario.getPersona().getGrupo();

        return new GrupoResponseAdministrador(grupo);
    }




}
