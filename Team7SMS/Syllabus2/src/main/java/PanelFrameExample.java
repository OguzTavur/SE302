import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFrameExample {


    public static void openNewFrameInsidePanel() {
        JFrame innerFrame = new JFrame("İç Frame");
        innerFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel innerPanel = new JPanel();
        JLabel label = new JLabel("İç Frame");
        innerPanel.add(label);

        innerFrame.add(innerPanel);
        innerFrame.setSize(300, 200);
        innerFrame.setVisible(true);
    }
}
