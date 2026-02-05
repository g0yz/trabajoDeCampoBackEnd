package com.grupo7.TrabajoDeCampo.DTO.DtoAuth;

public class UsuarioRegisterRequest {
        private String email;
        private String password;

        public UsuarioRegisterRequest() {
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
}
