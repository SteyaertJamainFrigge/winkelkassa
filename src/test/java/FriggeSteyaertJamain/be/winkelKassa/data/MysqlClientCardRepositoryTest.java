package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Card;
import FriggeSteyaertJamain.be.winkelKassa.domain.register.Client;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MysqlClientCardRepositoryTest {

    @Test
    public void getClientCard() {
        Card card = Repositories.getInstance().getClientCardRepository().getClientCard(1);
        assertTrue("the category is correctly requested", card.getId() > 0);
    }

    @Test
    public void getAllClientCards() {
        List<Card> categories = Repositories.getInstance().getClientCardRepository().getAllClientCards();
        assertTrue("all categories are requested?", categories.size() > 0);
    }

    @Test
    public void updateClientCards() {
        // some preparation
        Client testClient = new Client(1, "test", "test", "test", "test", "test", "test");
        Card cardToEdit = new Card(1, 20, "123456752", testClient);

        // the actual test
        Repositories.getInstance().getClientCardRepository().updateClientCards(cardToEdit);
        Card editedCard = Repositories.getInstance().getClientCardRepository().getClientCard(1);
        assertEquals("name has been edited?", cardToEdit.getPoints(), editedCard.getPoints());

        // revert update
        Repositories.getInstance().getClientCardRepository().updateClientCards(new Card(1, 0, "123456789", testClient));
    }

    @Test
    public void deleteClientCards() {
        Card cardToDelete = Repositories.getInstance().getClientCardRepository().getClientCard(1);
        Repositories.getInstance().getClientCardRepository().deleteClientCards(cardToDelete);

        //assertNull("the category has been deleted?",Repositories.getInstance().getCategoryRepository().getGategory(1));

        Repositories.getInstance().getClientCardRepository().addClientCard(cardToDelete);
    }
}