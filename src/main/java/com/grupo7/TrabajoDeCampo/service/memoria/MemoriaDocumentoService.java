package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.dto.memoria.MemoriaDocumentoResponse;
import com.grupo7.TrabajoDeCampo.model.documento.Documento;
import com.grupo7.TrabajoDeCampo.model.memoria.Memoria;
import com.grupo7.TrabajoDeCampo.model.memoria.MemoriaDocumento;
import com.grupo7.TrabajoDeCampo.repository.documento.DocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaDocumentoRepository;
import com.grupo7.TrabajoDeCampo.repository.memoria.MemoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoriaDocumentoService {

    private final MemoriaDocumentoRepository memoriaDocumentoRepository;
    private final MemoriaRepository memoriaRepository;
    private final DocumentoRepository documentoRepository;

    public MemoriaDocumentoService(
            MemoriaDocumentoRepository memoriaDocumentoRepository,
            MemoriaRepository memoriaRepository,
            DocumentoRepository documentoRepository) {
        this.memoriaDocumentoRepository = memoriaDocumentoRepository;
        this.memoriaRepository = memoriaRepository;
        this.documentoRepository = documentoRepository;
    }


    //ADMINISTRADOR
    // agregar documento a memoria
    public void agregarDocumentoMemoriaAdmin(Long oidMemoria, Long oidDocumento) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Documento documento = documentoRepository.findById(oidDocumento)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        if (memoriaDocumentoRepository
                .findByMemoriaAndOidDocumento(memoria, oidDocumento)
                .isPresent()) {

            throw new RuntimeException("El documento ya está en la memoria");
        }

        MemoriaDocumento md = new MemoriaDocumento(memoria, documento);
        memoriaDocumentoRepository.save(md);
    }


    // listar documentos de una memoria
    public List<MemoriaDocumentoResponse> listarDocumentosPorMemoriaAdmin(Long oidMemoria) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        return memoriaDocumentoRepository.findByMemoria(memoria)
                .stream()
                .map(md -> new MemoriaDocumentoResponse(
                        md.getOidDocumento(),
                        md.getTitulo(),
                        md.getAutores(),
                        md.getEditorial(),
                        md.getAnio()
                ))
                .toList();
    }

    public void quitarDocumentoMemoriaAdmin(Long oidMemoria, Long oidDocumento) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        MemoriaDocumento md = memoriaDocumentoRepository
                .findByMemoriaAndOidDocumento(memoria, oidDocumento)
                .orElseThrow(() ->
                        new RuntimeException("El documento no está asociado a la memoria"));

        memoriaDocumentoRepository.delete(md);
    }





}
