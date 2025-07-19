package domain.interfaces;

import domain.entities.Client;

public interface IAuthenticationService {

    abstract boolean login(String email, String password);

    abstract void register(Client client);

    Client getAuthenticatedClient();
}