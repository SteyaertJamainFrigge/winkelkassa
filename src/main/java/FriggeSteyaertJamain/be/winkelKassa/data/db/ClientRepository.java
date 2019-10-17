package FriggeSteyaertJamain.be.winkelKassa.data.db;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Client;

import java.util.List;

public interface ClientRepository {

    boolean addClient(Client client);
    Client getClient (int id);
    List<Client> getAllClients();
    boolean updateClient(Client client);
    boolean deleteClient(Client client);
}
