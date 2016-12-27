package gark.splendo.network;

import java.io.IOException;
import java.util.List;

import gark.splendo.model.Card;

/**
 * Interface that describes network behaviour logic.
 */
public interface NetworkManager {

    /**
     * Method requests data cards from the network.
     */
    List<Card> requestCards() throws IOException;

}
