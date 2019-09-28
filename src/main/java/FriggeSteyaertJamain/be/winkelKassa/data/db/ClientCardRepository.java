package FriggeSteyaertJamain.be.winkelKassa.data.db;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Card;

import java.util.List;

public interface ClientCardRepository {

    void addClientCard(Card c);
    Card getClientCard(int id);
    List<Card> getAllClientCards();
    void updateClientCards(Card c);
    void deleteClientCards(Card c);
}
