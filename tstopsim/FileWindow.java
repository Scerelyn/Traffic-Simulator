package tstopsim;

import java.awt.Font;
import java.io.File;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tstopsim.Roads.LightCycler;
import tstopsim.Roads.Map;
import tstopsim.Roads.RoadTile;


public class FileWindow extends JFrame{

    private JFrame mainWindow;
    private Map m;
    private LightCycler lc;
    
    public FileWindow(JFrame mainWindow, Map m, LightCycler lc){
        this.mainWindow = mainWindow;
        this.m = m;
        this.lc = lc;
        
        Font use = new Font(Font.SANS_SERIF, Font.PLAIN,20);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        JLabel userText = new JLabel("Load in a map!");
        this.getContentPane().add(userText);
        userText.setFont(use);
        
        JButton loadButton = new JButton("Open a Map...");
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(),BoxLayout.Y_AXIS));
        this.getContentPane().add(loadButton);
        loadButton.setFont(use);
        loadButton.addActionListener(ev -> {
            JFileChooser fc = new JFileChooser();
            fc.setVisible(true);
            if (ev.getSource() == loadButton) {
                int returnVal = fc.showOpenDialog(this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    //This is where a real application would open the file.
                    if(file.getName().endsWith(".txt") || file.getName().endsWith(".traffic")){
                        m.setCurrentMap(file);
                        m.loadNewCity(file);
                        lc.reloadLights(m);
                        userText.setText("Map loaded");
                        if(m.getCity().length >= 1){
                            mainWindow.setBounds(0, 0, m.getCity()[0].length*RoadTile.ROAD_DIMENTION+20, m.getCity().length*RoadTile.ROAD_DIMENTION+60);
                        }
                        this.setBounds(mainWindow.getX() + mainWindow.getWidth(), 0, 0, 0);
                        this.pack();
                    } else {
                        userText.setText("Text (.txt) or .traffic files are the only supported files");
                    }
                } else {
                    //cancel button was pressed, so do nothing
                }
            }
        });
        
        JButton reloadMap = new JButton("Reload current map");
        this.getContentPane().add(reloadMap);
        reloadMap.setFont(use);
        reloadMap.addActionListener(ev -> {
            if(m.getCurrentMap() != null){
                m.wipeCars();
                m.loadNewCity(m.getCurrentMap());
                lc.reloadLights(m);
                userText.setText("Map loaded");
                if (m.getCity().length >= 1) {
                    mainWindow.setBounds(0, 0, m.getCity()[0].length * RoadTile.ROAD_DIMENTION + 20, m.getCity().length * RoadTile.ROAD_DIMENTION + 60);
                }
                this.setBounds(mainWindow.getX() + mainWindow.getWidth(), 0, 0, 0);
                this.pack();
                userText.setText("Map reloaded");
            } else {
                userText.setText("No map currently loaded in");
                this.pack();
            }
        });
        
        JButton loadInCars = new JButton("Load 10 Cars");
        this.getContentPane().add(loadInCars);
        loadInCars.setFont(use);
        loadInCars.addActionListener(ev ->{
            if(m.getCity().length < 3){
                userText.setText("LOAD IN A FULL MAP FIRST, MIN SIZE IS 3x3");
                this.pack();
            } else {
                m.loadInCars(10);
            }
        });
        
        JButton wipeCars = new JButton("Clear Cars");
        wipeCars.setFont(use);
        this.getContentPane().add(wipeCars);
        wipeCars.addActionListener(ev ->{
            m.wipeCars();
            userText.setText("Cars cleared");
        });
        
        this.setBounds(mainWindow.getX()+mainWindow.getWidth(),0,0,0);
        this.pack();
        this.setVisible(true);
    }
}
