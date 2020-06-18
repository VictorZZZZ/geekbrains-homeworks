package ru.gb.xo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XoFieldPanel extends JPanel {

    public static final int GAME_MODE_HVAI = 0;
    public static final int GAME_MODE_HVH = 1;
    public static JButton[][] map;

    public XoFieldPanel(){
        setBackground(Color.LIGHT_GRAY);
    }

    public void startGame(int gameMode, int fieldSize, int winLength) {
        removeAll();
        System.out.printf(" Game mode: %d%n Field size: %d%n Win length: %d%n", gameMode, fieldSize, winLength);
        drawField(fieldSize);//здесь добавляю кнопки

    }

    //* Рачертить панель Map на поле для игры, согласно fieldSize
    public void drawField(int fieldSize) {
        setLayout(new GridLayout(fieldSize, fieldSize));
        map=new JButton[fieldSize][fieldSize];
        for(int i=0;i<fieldSize;i++){
            for(int j=0;j<fieldSize;j++){
                map[i][j]=new JButton();
                map[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((JButton)e.getSource()).setText("X");
                    }
                });
                add(map[i][j]);
            }
        }
        //revalidate();
        updateUI();
    }


}
