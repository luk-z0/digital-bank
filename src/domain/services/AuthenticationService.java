package domain.services;

import java.util.HashSet;
import java.util.Set;

import domain.entities.Client;
import domain.interfaces.IAuthenticationService;

public class AuthenticationService implements IAuthenticationService {

    private Set<Client> clients = new HashSet<>();
    private Client authenticatedClient = null;

    @Override
    public boolean login(String email, String password) {
        for (Client client : clients) {
            if (client.authenticate(email, password)) {
                this.authenticatedClient = client;
            }
            return true;
        }
        return false;
    }

    @Override
    public void register(Client client) {
        clients.add(client);
    }

    @Override
    public Client getAuthenticatedClient() {
        return this.authenticatedClient;
    }

}
