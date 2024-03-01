package Map;

import javax.swing.*;
import java.awt.*;

public class MapWindow extends JFrame {
    public MapWindow() {
        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("Map.png");
        JLabel label = new JLabel(icon);
        frame.add(label, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

}
