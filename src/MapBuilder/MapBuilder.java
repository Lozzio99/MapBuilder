package MapBuilder;
import MapBuilder.gui.DrawingPanel;
import MapBuilder.gui.GridPanel;
import MapBuilder.gui.HelperPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MapBuilder extends JFrame {
    private final JPanel mainPanel;
    public MapBuilder() {
        super("Map building application");
        setLocation(0,0);
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.LIGHT_GRAY);
        setContentPane(mainPanel);

        GroupLayout groupLayout = new GroupLayout(mainPanel);
        mainPanel.setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);


        JScrollPane scroll =new JScrollPane();
        Dimension SCREEN_SIZE = new Dimension(1100, 800);
        scroll.setBackground(Color.LIGHT_GRAY);
        GridPanel drawingPanel = new DrawingPanel();
        scroll.getViewport().add(drawingPanel);


        JScrollPane buttons =new JScrollPane();
        scroll.setBackground(Color.LIGHT_GRAY);
        JPanel helperPanel = new HelperPanel(drawingPanel);
        buttons.getViewport().add(helperPanel);

        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup().addComponent(scroll).addGroup(groupLayout.createSequentialGroup().addComponent(helperPanel)));
        groupLayout.setVerticalGroup(groupLayout.createParallelGroup().addComponent(scroll).addComponent(helperPanel));


        pack();
        final var closed = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                var listen = new WindowEvent(getFrames()[0], 201);
                Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(listen);
                System.out.println("System closed by user");
                System.exit(0);
            }
        };

        super.addWindowListener(closed);
        setVisible(true);
        setResizable(false);
        setPreferredSize(SCREEN_SIZE);
    }



    public static void main(String[] args) {
        new MapBuilder();
    }

}
