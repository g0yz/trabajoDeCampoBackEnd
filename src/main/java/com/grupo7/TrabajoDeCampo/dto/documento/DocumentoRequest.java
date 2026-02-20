package com.grupo7.TrabajoDeCampo.dto.documento;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import java.sql.Blob;

public class DocumentoRequest {

        private String titulo;
        private String autores;
        private String editorial;
        private Integer anio;


        public String getTitulo() { return titulo; }
        public String getAutores() { return autores; }
        public String getEditorial() { return editorial; }
        public Integer getAnio() { return anio; }
        public void setTitulo(String titulo) { this.titulo = titulo; }
        public void setAutores(String autores) { this.autores = autores; }
        public void setEditorial(String editorial) { this.editorial = editorial; }
        public void setAnio(Integer anio) { this.anio = anio; }
    }



