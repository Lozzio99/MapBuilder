package MapBuilder.gui;


import MapBuilder.utils.IVector;
import MapBuilder.utils.Section;
import MapBuilder.utils.Vector2D;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static MapBuilder.enums.ElementType.fromCode;
import static MapBuilder.gui.HelperPanel.DRAWING_CODE;


public class GridPanel extends JPanel
{

    protected Map<IVector, Section> sections;
    public static int WIDTH,GRID_WIDTH,HEIGHT,GRID_HEIGHT;
    public static double scaling;
    public static int rows,columns;
    static {
        WIDTH =(1200);
        HEIGHT = (800);
        GRID_WIDTH = GRID_HEIGHT = 10;
        scaling =  1./GRID_WIDTH;
        rows = HEIGHT / GRID_HEIGHT;
        columns = WIDTH / GRID_WIDTH;
    }

    public GridPanel() {
        super();
        Dimension d = new Dimension(WIDTH,HEIGHT);
        setPreferredSize(d);
        setMaximumSize(d);
        setBackground(Color.WHITE);
        generateSections();
    }

    private void generateSections() {
        sections = new HashMap<>();

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                int x = i * GRID_WIDTH;
                int y = j * GRID_HEIGHT;
                IVector loc = new Vector2D(x,y);
                sections.put(loc,new Section(x,y));
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawSections(g);
        fillActiveSections(g);
    }

    public void selectSquare(int x, int y, int size, int code){
        for (int i = x; i< x+size; i++)
            for (int k = y; k< y+size; k++) {
                int x1 = i*GRID_WIDTH,y1= k*GRID_HEIGHT;
               Section s = getSections().get(new Vector2D(x1,y1));
               if (s == null){
                   System.out.println(x1 + "  "+y1);
                   continue;
               }
               s.setActive(true);
               s.setCode(code);
            }
    }
    public void selectLine(int dim, int index, int code){
        int bound = dim == 0? rows :columns;
        for (int i = 0; i<bound ; i++){
            int x,y;
            if (dim == 0){
                x = index*GRID_WIDTH;
                y = i*GRID_HEIGHT;
            } else {
                x = i*GRID_WIDTH;
                y = index*GRID_HEIGHT;
            }
            Section s = getSections().get(new Vector2D(x,y));
            if (s == null){
                System.out.println(x + " "+y);
                continue;
            }
            s.setActive(true);
            s.setCode(code);
        }
    }
    private void drawSections(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i<= columns; i++){
            int x = i*GRID_WIDTH;
            for (int j = 0; j<= rows; j++){
                int y = j*GRID_HEIGHT;
                g.drawLine(0, y, WIDTH, y);
            }
            g.drawLine(x, 0,x, HEIGHT);
        }
    }

    private void fillActiveSections(Graphics g) {
        for (Section s : sections.values()) {
            if (s.isActive()) {
                g.setColor(fromCode(s.getCode()).getColor());
                g.fillRect(s.getX(), s.getY(), GRID_WIDTH, GRID_HEIGHT);
            }
        }
    }

    public void clear() {
        for (Section s : sections.values()) {
            s.setActive(false);
            s.setCode(DRAWING_CODE);
        }
        repaint();
    }


    public Map<IVector,Section> getSections() {
        return sections;
    }

}