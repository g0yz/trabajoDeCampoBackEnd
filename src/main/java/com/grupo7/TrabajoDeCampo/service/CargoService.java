package com.grupo7.TrabajoDeCampo.service;

import com.grupo7.TrabajoDeCampo.model.Cargo;
import com.grupo7.TrabajoDeCampo.model.IntegranteConsejoEducativo;
//import com.grupo7.TrabajoDeCampo.model.Persona;
import com.grupo7.TrabajoDeCampo.repository.CargoRepository;
import com.grupo7.TrabajoDeCampo.repository.tipoPersonaPackageRepository.IntegranteConsejoEducativoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CargoService {

    private final CargoRepository cargoRepository;
    private final IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository;

    public CargoService(CargoRepository cargoRepository,
                        IntegranteConsejoEducativoRepository integranteConsejoEducativoRepository){
        this.cargoRepository = cargoRepository;
        this.integranteConsejoEducativoRepository = integranteConsejoEducativoRepository;
    }

    public List<Cargo> listarCargos(){
        return cargoRepository.findAll();
    }

    public Optional<Cargo> obtenerCargoPorId(Long id){
        return cargoRepository.findById(id);
    }

    public Cargo crearCargo(Cargo cargo, Long idIntegranteConsejoEducativo){

        IntegranteConsejoEducativo integrante = integranteConsejoEducativoRepository.findById(idIntegranteConsejoEducativo).orElseThrow(() -> new RuntimeException("Integrante no encontrado con id: " + idIntegranteConsejoEducativo));

        // Asigno el integrante al cargo
        cargo.setIntegranteConsejoEducativo(integrante);

        // Guardo el cargo en la BD
        return cargoRepository.save(cargo);
    }



}
