package com.project.loteria.exceptions.handler;

import org.springframework.http.HttpStatus;

public class RestErrorMensage {
    private HttpStatus status;
    private String mensage;

    public RestErrorMensage(HttpStatus status, String mensage) {
        this.status = status;
        this.mensage = mensage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMensage() {
        return mensage;
    }

    public void setMensage(String mensage) {
        this.mensage = mensage;
    }
}
