package Main.java;

import jcomponents.calculateInvestment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calculateInvestmentTest {

    @Test
    void getFinalAmountWith10K(){
        calculateInvestment cc = new calculateInvestment(10000,
                10, 10, 10);
        assertEquals(26096.8, cc.getFinalAmount(0));
    }

    @Test
    void getFinalAmountWith0(){
        calculateInvestment cc = new calculateInvestment(0,
                10, 10, 0);
        assertEquals(0, cc.getFinalAmount(0));
    }

}