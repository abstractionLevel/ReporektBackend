package com.example.ReportPlayer.dto.password;


public class ForgotPasswordDto  extends PasswordDto{

    private String token;


    public ForgotPasswordDto() {
        super();
    }

    public ForgotPasswordDto( String password, String confirmPassword,String token) {
       super(password,confirmPassword);
       this.token = token;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
