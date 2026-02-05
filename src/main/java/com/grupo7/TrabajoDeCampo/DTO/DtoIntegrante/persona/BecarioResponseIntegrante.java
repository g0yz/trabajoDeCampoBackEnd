package com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.persona;

import com.grupo7.TrabajoDeCampo.model.persona.tipoPersonaPackage.TipoBecario;

public class BecarioResponseIntegrante {

        private Long oidBecario;
        private TipoBecario tipoBecario;
        private String fuenteFinanciamiento;
        private Boolean activo;


        private String nombre;
        private String apellido;
        private Integer horasSemanales;



        public BecarioResponseIntegrante(
                Long oidBecario,
                TipoBecario tipoBecario,
                String fuenteFinanciamiento,
                Boolean activo,
                String nombre,
                String apellido,
                Integer horasSemanales
        ) {
            this.oidBecario = oidBecario;
            this.tipoBecario = tipoBecario;
            this.fuenteFinanciamiento = fuenteFinanciamiento;
            this.activo = activo;
            this.nombre = nombre;
            this.apellido = apellido;
            this.horasSemanales = horasSemanales;
        }

        // getters


        public Long getOidBecario() {
            return oidBecario;
        }

        public TipoBecario getTipoBecario() {
            return tipoBecario;
        }

        public String getFuenteFinanciamiento() {
            return fuenteFinanciamiento;
        }

        public Boolean getActivo() {
            return activo;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public Integer getHorasSemanales() {
            return horasSemanales;
        }



}