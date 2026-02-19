package com.grupo7.TrabajoDeCampo.dto.documento;

import java.io.InputStream;

public class DocumentoArchivoResponse {
    private InputStream inputStream;
    private String nombreArchivo;

    public DocumentoArchivoResponse(InputStream inputStream, String nombreArchivo) {
        this.inputStream = inputStream;
        this.nombreArchivo = nombreArchivo;
    }

    public InputStream getInputStream() { return inputStream; }
    public String getNombreArchivo() { return nombreArchivo; }
}