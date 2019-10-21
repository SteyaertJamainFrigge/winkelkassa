package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.data.db.ClientRepository;
import FriggeSteyaertJamain.be.winkelKassa.data.db.Repositories;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Client;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MySqlClientRepositoryTest {
/*
    private static Client genericClient;
    private static ClientRepository repo;


    @BeforeClass
    public static void setUp(){
        genericClient = new Client(1, "genericName", "genericson", "email@generic.com", "0474126456", "adres1", "8000");
        repo = Repositories.getInstance().getClientRepository();
    }

    @Test
    public void getAllClients() {
        List<Client> clients = repo.getAllClients();
        assertTrue(clients.size() > 0);
    }

    @Test
    public void getClient() {
        assertNotNull("heeft succesvol een client kunnen aanvragen?", repo.getClient(genericClient.getId()));
    }


    @Test
    public void updateClient() {
        // preparation
        genericClient.setName("changed by tester");
        repo.addClient(genericClient);

        //actual test
        assertTrue("client was edited?", repo.updateClient(genericClient));

        // cleanup
        repo.deleteClient(genericClient);
    }

    @Test
    public void addAndDeleteClient() {
        assertTrue("client was added?", repo.addClient(genericClient));
        assertTrue("client has been beleted?", repo.deleteClient(genericClient));
    }

 */
}