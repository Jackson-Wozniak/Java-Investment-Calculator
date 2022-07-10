package Main.java;

import jcomponents.SetChart;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class setChartTest {

    @Test
    void checkJPanelSize(){
        int[] years = {1,2,3,4};
        double[] values = {1,2,3,4};

        JPanel panel = new SetChart(years, values);
        assertNotNull(panel);
    }

}