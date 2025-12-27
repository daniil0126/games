package Entity;

import com.donii.GameCenter.casino.model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    @Test
    void testDeposit(){
        Player player = new Player(0, "test", "test_pass");
        player.deposit(500);
        assertEquals(500, player.getBalance(), "The balance must be equals to 500");
    }

}