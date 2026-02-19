package com.grupo7.TrabajoDeCampo.dto.documento;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import java.sql.Blob;

public class DocumentoRequest {

        private String titulo;
        private String autores;
        private String editorial;
        private Integer anio;
        private Blob archivoBase64;
        private String nombreArchivo;



        public String getTitulo() { return titulo; }
        public String getAutores() { return autores; }
        public String getEditorial() { return editorial; }
        public Integer getAnio() { return anio; }
        public Blob getArchivoBase64() {return archivoBase64;}
        public void setArchivoBase64(Blob archivoBase64) {this.archivoBase64 = archivoBase64;}
        public String getNombreArchivo() {return nombreArchivo;}
        public void setNombreArchivo(String nombreArchivo) {this.nombreArchivo = nombreArchivo;}
        public void setTitulo(String titulo) { this.titulo = titulo; }
        public void setAutores(String autores) { this.autores = autores; }
        public void setEditorial(String editorial) { this.editorial = editorial; }
        public void setAnio(Integer anio) { this.anio = anio; }
    }



