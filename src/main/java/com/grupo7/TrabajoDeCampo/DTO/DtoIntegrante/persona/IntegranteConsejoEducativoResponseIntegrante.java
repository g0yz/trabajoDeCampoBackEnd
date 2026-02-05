package com.grupo7.TrabajoDeCampo.DTO.DtoIntegrante.persona;

public class IntegranteConsejoEducativoResponseIntegrante {

        private Long oidIntegranteConsejoEducativo;
        private String cargo;
        private Boolean activo;

        private String nombre;
        private String apellido;
        private Integer horasSemanales;


        public IntegranteConsejoEducativoResponseIntegrante(
                Long oidIntegranteConsejoEducativo,
                String cargo,
                Boolean activo,
                String nombre,
                String apellido,
                Integer horasSemanales) {

            this.oidIntegranteConsejoEducativo = oidIntegranteConsejoEducativo;
            this.cargo = cargo;
            this.activo = activo;
            this.nombre = nombre;
            this.apellido = apellido;
            this.horasSemanales = horasSemanales;
        }

        public Long getOidIntegranteConsejoEducativo() { return oidIntegranteConsejoEducativo; }
        public String getCargo() { return cargo; }
        public Boolean getActivo() { return activo; }
        public String getNombre() { return nombre; }
        public String getApellido() { return apellido; }
        public Integer getHorasSemanales() { return horasSemanales; }
    }
