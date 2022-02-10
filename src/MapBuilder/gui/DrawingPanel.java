package MapBuilder.gui;


import MapBuilder.utils.IVector;
import MapBuilder.utils.Section;
import MapBuilder.utils.Vector2D;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static MapBuilder.gui.HelperPanel.DRAWING_CODE;
public class DrawingPanel extends GridPanel implements MouseMotionListener, MouseListener {

    public DrawingPanel() {
        addMouseMotionListener(this);
        addMouseListener(this);
    }



    @Override
    public void mouseDragged(MouseEvent e) {
        paintSections(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        paintSections(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void paintSections(MouseEvent e) {
        IVector loc = new Vector2D((e.getX()/GRID_WIDTH)*GRID_WIDTH,(e.getY()/GRID_HEIGHT)*GRID_HEIGHT);
        Section selected = sections.get(loc);
        if (selected == null) return;
        selected.setCode(DRAWING_CODE);
        selected.setActive(DRAWING_CODE != -1);
        repaint();
    }

}
