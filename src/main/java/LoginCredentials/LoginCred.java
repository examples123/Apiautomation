package LoginCredentials;

public class LoginCred  {

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    private  String password;

    public LoginCred(String email, String password) {

        this.email = email;
        this.password = password;
    }






}
