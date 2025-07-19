package domain.entities;

import domain.interfaces.IAuthentication;
import lombok.Data;

@Data
public class Client implements IAuthentication {

    protected String id;
    protected String name;
    protected String email;
    protected String password;

    public Client(String id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return (this.password.contains(password)) && (this.email.contains(email)) ? true : false;
    }

}
