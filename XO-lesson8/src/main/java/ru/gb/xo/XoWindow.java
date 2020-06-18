package ru.gb.xo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XoWindow extends JFrame {
    private static final int WINDOW_WIDTH = 507;
    private static final int WINDOW_HEIGHT = 555;
    private static final int WINDOW_POS_X = 650;
    private static final int WINDOW_POS_Y = 250;

    private XoSettingsWindow settingsWindow;
    private XoFieldPanel fieldPanel;

    public XoWindow(){
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setLocation(WINDOW_POS_X,WINDOW_POS_Y);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setTitle("Крестики - Нолики");
        setResizable(false);

        this.settingsWindow = new XoSettingsWindow(this);

        JButton buttonStart = createStartButton();
        JButton buttonExit = createExitButton();
        JPanel buttonPan = new JPanel(new GridLayout(1,2));

        buttonPan.add(buttonStart);
        buttonPan.add(buttonExit);
        add(buttonPan,BorderLayout.SOUTH);

        fieldPanel = new XoFieldPanel();
        add(fieldPanel,BorderLayout.CENTER);

        setVisible(true);
    }



    private JButton createExitButton(){
        JButton button = new JButton("Exit");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return button;
    }

    private JButton createStartButton() {
        JButton button = new JButton("Новая игра");

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingsWindow.setVisible(true);
            }
        };
        button.addActionListener(listener);
        return button;
    }

    public void startGame(int gameMode, int fieldSize, int winLength) {
        this.fieldPanel.startGame(gameMode, fieldSize, winLength);
    }
}
