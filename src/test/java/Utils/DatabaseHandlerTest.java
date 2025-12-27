package Utils;

import org.junit.jupiter.api.Test;
import com.donii.GameCenter.casino.repository.DatabaseHandler;


class DatabaseHandlerTest {
    DatabaseHandler dbHandler = new DatabaseHandler();

    @Test
    void createNewTable() {
        dbHandler.createNewTable();
    }
}