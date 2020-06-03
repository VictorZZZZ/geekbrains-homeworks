package ru.gb.lesson4;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TickTacToe {
    private static final char USER_SYMB = 'X';
    private static final char AI_SYMB = '0';
    private static final char EMPTY_SYMB = ' ';
    private static final Random RANDOM = new Random();
    private static char[][] field;
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int winRow;

    private static final Scanner SCANNER = new Scanner(System.in);


    //init
    private static void init(){
        System.out.println("Новая Игра.");
        int fieldSize;
        do {
            System.out.print("Введите размер поля >>> ");
            fieldSize = SCANNER.nextInt();
            if(fieldSize<3) System.out.println("Поле не может быть размером менее чем 3х3.");
        }while(fieldSize<3);
        do{
            System.out.print("Введите сколько фигур в ряд дают победу >>> ");
            winRow = SCANNER.nextInt();
            if(winRow>fieldSize || winRow<2) System.out.println("Это значение не должно превышать " + fieldSize);
        } while(winRow>fieldSize || winRow<2);
        fieldSizeX = fieldSize;
        fieldSizeY = fieldSize;
        field = new char[fieldSizeY][fieldSizeX];
        for(int y=0;y<fieldSizeY;y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = EMPTY_SYMB;
            }
        }
    }
    //drawField
    private static void drawField(){
        for(int y=0;y<fieldSizeY;y++){
            System.out.print("| ");
            for(int x=0;x<fieldSizeX;x++){
                System.out.printf("%1s | ",field[y][x]);
            }
            System.out.println();
        }
    }

    //x and y is not Valid
    private static boolean isNotValid(int x, int y){
        return (x<1 || x>fieldSizeX)
                || (y<1 || y>fieldSizeY);
    }

    //isFieldBusy
    private static boolean isFieldBusy(int x, int y){
        return (field[y-1][x-1]==USER_SYMB) || (field[y-1][x-1]==AI_SYMB);
    }

    //userTurn
    private static void userTurn(){
        int x;
        int y;
        do{
            System.out.println("Ваш ход: Введите координаты клетки куда хотите поставить свой символ в формате x y >>>");
            x = SCANNER.nextInt();
            y = SCANNER.nextInt();
            if(isNotValid(x,y)) {
                System.out.println("Неверно введены координаты.");
            } else {
                if (isFieldBusy(x, y)) System.out.println("Поле (" + x + "," + y + ") занято.");
            }
        }while( isNotValid(x,y) || isFieldBusy(x,y));
        field[y-1][x-1] = USER_SYMB;
    }

    //aiTurn
    private static void aiTurn(){
        //3 1
        int x;
        int y;
        do{
            x = RANDOM.nextInt(fieldSizeX)+1;
            y = RANDOM.nextInt(fieldSizeY)+1;
            System.out.println("AI: "+x+ " "+ y);
        } while ( isFieldBusy(x,y) );
        field[y-1][x-1] = AI_SYMB;
    }

    //isDraw
    private static boolean isDraw(){
        for(int y=0;y<fieldSizeY;y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if(field[y][x] == EMPTY_SYMB) return false;
            }
        }
        return true;
    }


    //Проверка на одинаковые строки
    private static boolean checkSameRows(char[][] matrix){
        for(int x=0;x<matrix.length;x++){
            if(checkIsRowSame(matrix[x])) return true;
        }
        return false;
    }

    //Проверка на одинаковые столбцы
    private static boolean checkSameCols(char[][] matrix){
        for(int y=0; y<matrix.length; y++){
            char[] row = new char[matrix.length];
            for(int x=0; x<matrix.length; x++) {
                row[x] = matrix[x][y];
            }
            if(checkIsRowSame(row)) return true;
        }
        return false;
    }

    private static boolean checkSameDiagonals(char[][] matrix){
        char[] mainDiag = new char[matrix.length];
        char[] sideDiag = new char[matrix.length];
        //Главная диагональ
        for(int x=0; x<matrix.length; x++) {
            mainDiag[x] = matrix[x][x];
            sideDiag[x] = matrix[matrix.length-x-1][x];
        }
        if(checkIsRowSame(mainDiag)) return true;
        if(checkIsRowSame(sideDiag)) return true;

        return false;
    }

    //Проверка все ли элементы массива одинаковые
    private static boolean checkIsRowSame(char[] row){
       char symb = row[0];
       if(symb==EMPTY_SYMB) return false;
       for (int i = 1; i < row.length; i++) {
            if(row[i]!=symb) return false;
       }
       return true;
    }

    //isWinnerExists(Проверка работает только для квадратных fieldSize и fieldsize-1)
    private static boolean isWinnerExists(){
        if(winRow==fieldSizeX-1){
            return isWinnerExistsInSubMatrixes();
        } else {
            return checkSameRows(field) || checkSameCols(field) || checkSameDiagonals(field);
        }
    }

    //Проверка субматриц (например Если поле=5 а победа={4 в ряд} - работает только для квадратных матриц n=n-1)
    private static boolean isWinnerExistsInSubMatrixes(){
        for(int n=0;n<(fieldSizeX-winRow+1);n++) {
            char[][] submatrix = new char[winRow][winRow];
            for (int j = 0; j < winRow; j++) {
                for (int i = 0; i < winRow; i++) {
                    submatrix[j][i] =
                            field[j+n][i];
                }
            }
            if( checkSameRows(submatrix) || checkSameCols(submatrix) || checkSameDiagonals(submatrix) ) return true;

            if(n!=0) {
                submatrix = new char[winRow][winRow];
                for (int j = 0; j < winRow; j++) {
                    for (int i = 0; i < winRow; i++) {
                        submatrix[j][i] =
                                field[j][i + n];
                    }
                }
                if( checkSameRows(submatrix) || checkSameCols(submatrix) || checkSameDiagonals(submatrix) ) return true;

                submatrix = new char[winRow][winRow];
                for (int j = 0; j < winRow; j++) {
                    for (int i = 0; i < winRow; i++) {
                        submatrix[j][i] =
                                field[j + n][i + n];
                    }
                }
                if( checkSameRows(submatrix) || checkSameCols(submatrix) || checkSameDiagonals(submatrix) ) return true;
            }
        }
        return false;
    }

    //main
    public static void main(String[] args){
        System.out.println("Крестики-нолики.");
        while(true) {
            init();
            drawField();
            playOnce();
            System.out.println("Играть сначала (y/n)?");
            if(SCANNER.next().equals("n")){
                break;
            }
        }

    }

    private static void playOnce() {
        while(true) {
            userTurn();
            drawField();
            if(isWinnerExists()) {
                System.out.println("Вы победили!!! Game is Over.");
                break;
            }
            if(isDraw()){
                System.out.println("Ничья. Game is Over.");
                break;
            }
            aiTurn();
            drawField();
            if(isWinnerExists()) {
                System.out.println("Вы проиграли!!! Game is Over.");
                break;
            }
            if(isDraw()){
                System.out.println("Ничья. Game is Over.");
                break;
            }
        }
    }

}
