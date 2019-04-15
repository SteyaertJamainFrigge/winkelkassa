package FriggeSteyaertJamain.be.winkelKassa.data;

import FriggeSteyaertJamain.be.winkelKassa.domain.register.Btw;

import java.util.List;

public interface BtwRepository {
    
    /**
     * returns the Btw that has the given id.
     *
     * @param id{String} id
     * @return {Btw}  Btw
     */
    int getBtw(int id);

    /**
     * adds a new Btw to the data layer.
     *
     * @param btw {Btw} product Btw
     */
    void addBtw(Btw btw);

    /**
     * returns all Btw's found in data layer as a List
     *
     * @return {List<Btw>} list of categories
     */
    List<Btw> getAllBtw();


    /**
     * updates the given Btw in the datalayer.
     *
     * @param btw{Btw} product Btw
     */
    void updateBtw(Btw btw);


    /**
     * deletes the given Btw in the data layer
     *
     * @param id{Btw} product Btw
     */
    void deleteBtw(int id);

}
