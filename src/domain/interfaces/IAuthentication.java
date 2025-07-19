package domain.interfaces;

public interface IAuthentication {

    boolean authenticate(String email, String password);
}
