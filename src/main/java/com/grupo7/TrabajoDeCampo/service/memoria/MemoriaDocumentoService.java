package com.grupo7.TrabajoDeCampo.service.memoria;

import com.grupo7.TrabajoDeCampo.DTO.Memoria.MemoriaDocumentoResponse;
import com.grupo7.TrabajoDeCampo.model.Documento;
import com.grupo7.TrabajoDeCampo.model.Memoria;
import com.grupo7.TrabajoDeCampo.model.MemoriaDocumento;
import com.grupo7.TrabajoDeCampo.repository.DocumentoRepository;
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

    // agregar documento a memoria
    public MemoriaDocumento agregarDocumento(Long oidMemoria, Long oidDocumento) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Documento documento = documentoRepository.findById(oidDocumento)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        memoriaDocumentoRepository.findByMemoriaAndDocumento(memoria, documento)
                .ifPresent(md -> {
                    throw new RuntimeException("El documento ya está asociado a la memoria");
                });

        MemoriaDocumento md = new MemoriaDocumento(memoria, documento);
        return memoriaDocumentoRepository.save(md);
    }

    // listar documentos de una memoria
    public List<MemoriaDocumentoResponse> listarPorMemoria(Long oidMemoria) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        return memoriaDocumentoRepository.findByMemoria(memoria)
                .stream()
                .map(md -> new MemoriaDocumentoResponse(
                        md.getDocumento().getOidDocumento(),
                        md.getDocumento().getTitulo(),
                        md.getDocumento().getAutores(),
                        md.getDocumento().getEditorial(),
                        md.getDocumento().getAnio(),
                        md.getDocumento().getNombreArchivo(),
                        md.getDocumento().getTipoArchivo(),
                        md.getDocumento().getActivo()
                ))
                .toList();
    }



    // quitar documento de una memoria
    public void quitarDocumento(Long oidMemoria, Long oidDocumento) {

        Memoria memoria = memoriaRepository.findById(oidMemoria)
                .orElseThrow(() -> new RuntimeException("Memoria no encontrada"));

        Documento documento = documentoRepository.findById(oidDocumento)
                .orElseThrow(() -> new RuntimeException("Documento no encontrado"));

        MemoriaDocumento md = memoriaDocumentoRepository
                .findByMemoriaAndDocumento(memoria, documento)
                .orElseThrow(() ->
                        new RuntimeException("El documento no está asociado a la memoria"));

        memoriaDocumentoRepository.delete(md);
    }
}
