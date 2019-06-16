package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Client;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MySqlClientRepositoryTest {

    private static Client genericClient;
    private static ClientRepository repo;


    @BeforeClass
    public static void setUp(){
        genericClient = new Client(2, "genericName", "genericson", "email@generic.com", "0474123456", "adres", "8000");
        repo = Repositories.getInstance().getClientRepository();
    }

    @Test
    public void addClient() {
        repo.addClient(genericClient);
    }

    @Test
    public void getAllClients() {
        List<Client> clients = repo.getAllClients();
        assertTrue(clients.size() > 0);
    }

    @Test
    public void getClient() {
        repo.getClient(genericClient.getId());
    }

    @Test
    public void updateClient() {
        genericClient.setName("changed by tester");
        repo.updateClient(genericClient);
    }

    @Test
    public void deleteClient() {
        repo.deleteClient(genericClient);
    }
}