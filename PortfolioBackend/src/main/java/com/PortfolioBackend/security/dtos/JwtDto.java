package com.PortfolioBackend.security.dtos;

// esta clase la vamos a utilizar cuando hagamos un login
// nos va a devolver el responseEntity del controlador, un Json Web Token
public class JwtDto {
    private String token;
    
    public JwtDto() {
		
	}

	public JwtDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}