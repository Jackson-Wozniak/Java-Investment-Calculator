package Main.java;

import jcomponents.CalculateInvestment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class calculateInvestmentTest {

    @Test
    void getFinalAmountWith10K(){
        CalculateInvestment cc = new CalculateInvestment(10000,
                10, 10, 10);
        assertEquals(26096.8, cc.getFinalAmount(0));
    }

    @Test
    void getFinalAmountWith0(){
        CalculateInvestment cc = new CalculateInvestment(0,
                10, 10, 0);
        assertEquals(0, cc.getFinalAmount(0));
    }

}