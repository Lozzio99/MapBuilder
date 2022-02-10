package MapBuilder.gui;

import MapBuilder.GameSettings;
import MapBuilder.MapBuilder;
import MapBuilder.gui.GridPanel;
import MapBuilder.utils.*;
import MapBuilder.enums.AgentType;
import MapBuilder.enums.ElementType;
import MapBuilder.enums.GameMode;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static MapBuilder.enums.ElementType.*;
import static MapBuilder.enums.AgentType.GUARDS;
import static MapBuilder.enums.AgentType.INTRUDERS;
import static MapBuilder.GameSettings.*;
import static MapBuilder.gui.GridPanel.*;


public class HelperPanel extends JPanel {
    public static int DRAWING_CODE = -1;
    private final JButton clearButton = new JButton("Clear"),
            convertButton = new JButton("Convert"),
            runMap = new JButton("Run Map"),
            addDefault = new JButton("Add Default");

    private final JComboBox<ElementType> elementsBox = new JComboBox<>();
    private final JComboBox<GameMode> gameModeBox = new JComboBox<GameMode>();
    private final JSlider guardsCountS = new JSlider(),
            intrudersCountS = new JSlider(),
            guardsSpeedS = new JSlider(),
            intrudersSpeedS = new JSlider(),
            intrudersSprintS = new JSlider();
    private final GridPanel drawingPanel;
    private final GroupLayout groupLayout;
    private int MAP_FILE_INDEX = 0;
    private JLabel gameModeL,
            elementModeL,
            guardsCountL,
            guardsSpeedL,
            intrudersCountL,
            intrudersSpeedL,
            intrudersSprintL;

    public HelperPanel(GridPanel dp) {
        super();
        drawingPanel = dp;
        setPreferredSize(new Dimension(200,800));
        setBackground(Color.WHITE);
        groupLayout = new GroupLayout(this);
        addButtons();
    }

    private void addButtons() {
        setButtons();
        setDrawMode();
        setGameMode();
        setCountSlider(guardsCountS,GUARDS);
        setCountSlider(intrudersCountS,INTRUDERS);

        setSpeedSlider(guardsSpeedS,GUARDS,false);
        setSpeedSlider(intrudersSpeedS,INTRUDERS,false);
        setSpeedSlider(intrudersSprintS,INTRUDERS,true);

        gameModeL = new JLabel("Game mode");
        gameModeL.setLabelFor(gameModeBox);
        elementModeL = new JLabel("Element");
        elementModeL.setLabelFor(elementsBox);
        guardsCountL = new JLabel("Num of Guards :"+NUM_GUARDS);
        guardsCountL.setLabelFor(guardsCountS);
        intrudersCountL = new JLabel("Num of Intruders :"+NUM_INTRUDERS);
        intrudersCountL.setLabelFor(intrudersCountS);
        guardsSpeedL = new JLabel("Guards speed :"+BASE_SPEED_GUARDS);
        guardsSpeedL.setLabelFor(guardsSpeedS);
        intrudersSpeedL = new JLabel("Intruders speed :"+BASE_SPEED_INTRUDERS);
        intrudersSpeedL.setLabelFor(intrudersSpeedS);
        intrudersSprintL = new JLabel("Intruders sprint :"+SPRINT_SPEED_INTRUDERS);
        intrudersSprintL.setLabelFor(intrudersSprintS);

        setLayout(groupLayout);
        groupLayout.setAutoCreateGaps(true);
        groupLayout.setAutoCreateContainerGaps(true);

        groupLayout.setHorizontalGroup(
                groupLayout.createSequentialGroup().
                        addGroup(
                                groupLayout.createParallelGroup().
                                        addComponent(elementModeL).
                                        addComponent(gameModeL).
                                        addComponent(guardsCountL).
                                        addComponent(intrudersCountL).
                                        addComponent(guardsSpeedL).
                                        addComponent(intrudersSpeedL).
                                        addComponent(intrudersSprintL)
                        ).
                        addGroup(
                                groupLayout.createParallelGroup().
                                        addComponent(clearButton).
                                        addComponent(convertButton).
                                        addComponent(runMap).
                                        addComponent(addDefault).
                                        addComponent(elementsBox).
                                        addComponent(gameModeBox).
                                        addComponent(guardsCountS).
                                        addComponent(intrudersCountS).
                                        addComponent(guardsSpeedS).
                                        addComponent(intrudersSpeedS).
                                        addComponent(intrudersSprintS)
                        )
        );
        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup().
                        addComponent(clearButton).
                        addComponent(convertButton).
                        addComponent(runMap).
                        addComponent(addDefault).
                        addGroup(
                                groupLayout.createParallelGroup().
                                        addComponent(elementModeL).
                                        addComponent(elementsBox)
                                ).
                        addGroup(
                                groupLayout.createParallelGroup().
                                        addComponent(gameModeL).
                                        addComponent(gameModeBox)
                                ).
                        addGroup(
                                groupLayout.createParallelGroup().
                                        addComponent(guardsCountL).
                                        addComponent(guardsCountS)
                        ).
                        addGroup(
                                groupLayout.createParallelGroup().
                                        addComponent(intrudersCountL).
                                        addComponent(intrudersCountS)
                        ).
                        addGroup(
                                groupLayout.createParallelGroup().
                                        addComponent(guardsSpeedL).
                                        addComponent(guardsSpeedS)
                        ).
                        addGroup(
                                groupLayout.createParallelGroup().
                                        addComponent(intrudersSpeedL).
                                        addComponent(intrudersSpeedS)
                        ).
                        addGroup(
                                groupLayout.createParallelGroup().
                                        addComponent(intrudersSprintL).
                                        addComponent(intrudersSprintS)
                        )
        );

    }

    private void setSpeedSlider(JSlider slider, AgentType type, boolean sprint){
        slider.setPreferredSize(new Dimension(200,50));
        slider.setMinimum(0);
        slider.setMaximum(30);
        boolean guards = type.equals(GUARDS);
        slider.setValue(guards? (int)BASE_SPEED_GUARDS : (sprint? (int)SPRINT_SPEED_INTRUDERS : (int)BASE_SPEED_INTRUDERS));
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.addChangeListener(e -> {
            JLabel label;
            if (guards) {
                BASE_SPEED_GUARDS = slider.getValue();
                label = guardsSpeedL;
                label.setText("Guards speed :"+BASE_SPEED_GUARDS);
            } else {
                if (sprint){
                    SPRINT_SPEED_INTRUDERS = slider.getValue();
                    label = intrudersSprintL;
                    label.setText("Intruders speed :"+SPRINT_SPEED_INTRUDERS);
                } else {
                    BASE_SPEED_INTRUDERS = slider.getValue();
                    label = intrudersSpeedL;
                    label.setText("Num Of Intruders :"+BASE_SPEED_INTRUDERS);
                }
            }
        });
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTrack(true);
        slider.setEnabled(true);
        slider.setFocusable(true);
    }
    private void setCountSlider(JSlider slider, AgentType type) {
        slider.setPreferredSize(new Dimension(200,50));
        slider.setMinimum(0);
        slider.setMaximum(10);
        boolean guards = type.equals(GUARDS);
        slider.setValue(guards? NUM_GUARDS : NUM_INTRUDERS);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.addChangeListener(e -> {
            JLabel label;
            if (guards) {
                NUM_GUARDS = slider.getValue();
                label = guardsCountL;
                label.setText("Num Of Guards :"+NUM_GUARDS);
            } else {
                NUM_INTRUDERS = slider.getValue();
                label = intrudersCountL;
                label.setText("Num Of Intruders :"+NUM_INTRUDERS);
            }
        });
        slider.setSnapToTicks(true);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setPaintTrack(true);
        slider.setEnabled(true);
        slider.setFocusable(true);
    }

    private void setGameMode() {
        gameModeBox.addItem(GameMode.EXPLORATION);
        gameModeBox.addItemListener(e -> GameSettings.GAME_MODE = ((GameMode) gameModeBox.getSelectedItem()));
        gameModeBox.setSelectedIndex(0);
    }





    private void setDrawMode() {
        elementsBox.addItem(EMPTY);
        elementsBox.addItem(WALL);
        elementsBox.addItem(SPAWN_GUARDS);
        elementsBox.addItem(SPAWN_INTRUDERS);
        elementsBox.addItem(SHADED);
        elementsBox.addItem(TARGET);
        elementsBox.addItem(DOOR);
        elementsBox.addItem(TELEPORT);
        elementsBox.addItem(LANDING);

        elementsBox.addItemListener(e -> {
            ElementType element = (ElementType) elementsBox.getSelectedItem();
            if (element!= null) DRAWING_CODE = (element.getCode());
            else DRAWING_CODE = -1;
        });

    }

    private void setButtons(){
        clearButton.addActionListener(e -> {
            DRAWING_CODE = (-1);
            drawingPanel.clear();
        });

        convertButton.addActionListener(e -> {
            logScenario(drawingPanel.getSections());
            buildMapFile(drawingPanel.getSections());
            DRAWING_CODE = (-1);
            drawingPanel.clear();
        });

        runMap.addActionListener(e -> {
            MAP_FILE = buildMapFile(drawingPanel.getSections());
            DRAWING_CODE = (-1);
            drawingPanel.clear();
            MapBuilder.getFrames()[0].dispose();
            throw new UnsupportedOperationException("Now you should run the main class and parse the file map"+MAP_FILE_INDEX);
        });

        addDefault.addActionListener(e-> {
            setDefaultElements();
        });
    }

    private void setDefaultElements() {

        //fill corner walls
        drawingPanel.selectLine(0,0,WALL.getCode());
        drawingPanel.selectLine(1,0,WALL.getCode());
        drawingPanel.selectLine(1,rows-1,WALL.getCode());
        drawingPanel.selectLine(0,columns-1,WALL.getCode());

        drawingPanel.selectSquare(100,40,4,TARGET.getCode());
        drawingPanel.selectSquare(2,50,4, SPAWN_GUARDS.getCode());
        drawingPanel.selectSquare(2,25,4, SPAWN_INTRUDERS.getCode());

        drawingPanel.repaint();
    }


    private String buildMapFile(Map<IVector, Section> sections) {

        try {
            //find first map#.txt file available
            String dir = "maps/";
            var file = new File(dir+"map"+MAP_FILE_INDEX+".txt");
            while(file.exists()){
                MAP_FILE_INDEX++;
                file = new File(dir+"map"+MAP_FILE_INDEX+".txt");
            }
            System.out.println(file);
            PrintWriter pw = new PrintWriter(file);
            pw.println("name = Map "+MAP_FILE_INDEX);
            pw.println("gameMode = "+ GameSettings.GAME_MODE.getCode());
            pw.println("height = "+ (int)(HEIGHT*scaling));
            pw.println("width = "+ (int)(WIDTH*scaling));
            pw.println("scaling = "+(1./GRID_WIDTH));
            pw.println("numGuards = "+NUM_GUARDS);
            pw.println("numIntruders = "+NUM_INTRUDERS);
            writeScenario(drawingPanel.getSections(),pw,file);
            pw.flush();
            pw.close();
            return file.getAbsolutePath();
        } catch (IOException | IllegalMapException e) {
            e.printStackTrace();
        }
        return "src/main/resources/maps/testmap.txt";
    }



    public void logScenario(Map<IVector, Section> scenario) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int x = j * GRID_WIDTH;
                int y = i * GRID_HEIGHT;
                IVector loc = new Vector2D(x,y);
                Section atLocation = scenario.get(loc.gridLocation());
                sb.append(fromCode(atLocation.getCode()).getChar());
            }
            sb.append("\n");
        }
        System.out.println(sb.toString().trim());
    }

    public void writeScenario(Map<IVector,Section> scenario, PrintWriter pw,File f) {

        boolean spawnGuards = false,spawnIntruders = false,target= false,teleport=false,landing = false;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int x = j * GRID_WIDTH;
                int y = i * GRID_HEIGHT;
                IVector loc = new Vector2D(x,y);
                Section atLocation = scenario.get(loc.gridLocation());
                ElementType type = fromCode(atLocation.getCode());
                switch (type) {
                    case TARGET -> target = true;
                    case SPAWN_GUARDS -> spawnGuards = true;
                    case SPAWN_INTRUDERS -> spawnIntruders = true;
                    case TELEPORT -> teleport = true;
                    case LANDING -> landing = true;
                }
                if (!type.equals(EMPTY)) {
                    int x1 = (int) (x*scaling), y1= (int) (y*scaling);
                    int sz = (int)(GRID_WIDTH*scaling);
                    pw.println(type + " = " + x1 + " " + y1 + " " + (x1 + sz) + " " + (y1 + sz));
                }

            }
        }

        if (!spawnGuards || !spawnIntruders ||  !target || (teleport && !landing) ) {
            pw.close();
            f.delete();
            if (f.exists()) throw new IllegalStateException();
            throw new IllegalMapException("Illegal map scenario, file was deleted ");
        }
    }


    private static class IllegalMapException extends IllegalStateException{
        public IllegalMapException(String s) {
            super(s);
        }
    }



}
