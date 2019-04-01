package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Client;

import java.util.List;

public interface ClientRepository {

    void addClient(Client client);
    Client getClient (int id);
    List<Client> getAllClients();
    void updateClient(Client client);
    void deleteClient(Client client);
}
