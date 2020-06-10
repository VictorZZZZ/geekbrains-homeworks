package ru.gb.lesson4;


import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
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
    //массив ходов пользователя
    private static int[][] userTurnsArray;
    //массив выигрышных линий
    private static int[][][] winLines;
    //счетчик ходов пользователя
    private static int userTurnCounter=-1;

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
        fieldSizeX = fieldSize;
        fieldSizeY = fieldSize;
        field = new char[fieldSizeY][fieldSizeX];
        for(int y=0;y<fieldSizeY;y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                field[y][x] = EMPTY_SYMB;
            }
        }

        //инициализируем массив контроля пользовательских шагов.
        // Максимально возможное количество ходов каждого пользователя ([размер поля]^2)/2 + 1;
        int maximumTurnsByUser = fieldSize*fieldSize/2+1;
        userTurnsArray = new int[maximumTurnsByUser][2];

        //инициализируем количество линий по которым можно выиграть ([размер поля]*2+2)
        winLines = new int[fieldSize*2+2][fieldSize][2];
        //заполняем всевозможные победные варианты
        fillWinLines();

        do{
            System.out.print("Введите сколько фигур в ряд дают победу >>> ");
            winRow = SCANNER.nextInt();
            if(winRow>fieldSize || winRow<2) System.out.println("Это значение не должно превышать " + fieldSize);
        } while(winRow>fieldSize || winRow<2);

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
        return (x<0 || x>fieldSizeX-1)
                || (y<0 || y>fieldSizeY-1);
    }

    //isFieldBusy
    private static boolean isFieldBusy(int x, int y){
        return (field[y][x]==USER_SYMB)
                || (field[y][x]==AI_SYMB);
    }

    //userTurn
    private static int[] userTurn(){
        int x;
        int y;
        do{
            System.out.println("Ваш ход: Введите координаты клетки куда хотите поставить свой символ в формате x y >>>");
            x = SCANNER.nextInt()-1;
            y = SCANNER.nextInt()-1;
            if(isNotValid(x,y)) {
                System.out.println("Неверно введены координаты.");
            } else {
                if (isFieldBusy(x, y)) System.out.println("Поле занято.");
            }
        }while( isNotValid(x,y) || isFieldBusy(x,y));
        field[y][x] = USER_SYMB;

        //добавляем ход пользователя в Массив
        userTurnCounter++;
        System.out.println("Ход: "+ (userTurnCounter+1));
        userTurnsArray[userTurnCounter][0]=y;
        userTurnsArray[userTurnCounter][1]=x;

        int[] point=new int[]{y,x};
        return point;
    }

    //aiTurn
    private static void aiTurn(int[] point){
        //3 1
        int x;
        int y;
        int[] blockPoint = findBlockPoint(point);
        if(blockPoint[0]!=-1 && blockPoint[1]!=-1){
            y=blockPoint[0];
            x=blockPoint[1];
            System.out.println("AI: " + (x+1) + " " + (y+1));
            field[y][x] = AI_SYMB;
        } else {
            do {
                x = RANDOM.nextInt(fieldSizeX);
                y = RANDOM.nextInt(fieldSizeY);
            } while (isFieldBusy(x, y));
            System.out.println("AI: " + (x+1) + " " + (y+1));
            field[y][x] = AI_SYMB;
        }

    }

    //поиск точки блокировки
    private static int[] findBlockPoint(int[] point){
        int[] blockPoint = new int[]{-1,-1};

        int[] maxRateIndex = getCriticalPointLineIndexes();
        blockPoint = findFreeFieldInWinLines(maxRateIndex);
        if(blockPoint[0]!=-1 && blockPoint[1]!=-1) {
                return blockPoint;
        } else {
            //просто поиск свободной клетки вокруг point
                int y = point[0];
                int x = point[1];

                int[][] possiblePoints = {
                        {y, x + 1},
                        {y, x - 1},
                        {y + 1, x},
                        {y - 1, x},

                        {y - 1, x - 1},
                        {y - 1, x + 1},

                        {y + 1, x - 1},
                        {y + 1, x + 1},
                };
                for (int i = 0; i < possiblePoints.length; i++) {
                    y = possiblePoints[i][0];
                    x = possiblePoints[i][1];
                    //проверка боковых
                    if (!isNotValid(x, y)) {
                        if (!isFieldBusy(x, y)) {
                            blockPoint[0] = y;
                            blockPoint[1] = x;
                            return blockPoint;
                        }
                    }
                }
            }
        return blockPoint;
    }

    private static int[] findFreeFieldInWinLines(int[] maxRateIndex) {

        int[] freePoint=new int[]{-1,-1};
        int counter=0;
        do{
            int[][] winLine = winLines[maxRateIndex[counter]];
            for(int i=0;i<winLine.length;i++){
                int y=winLine[i][0];
                int x=winLine[i][1];
                if(!isFieldBusy(x,y)){
                    freePoint[0]=y;
                    freePoint[1]=x;
                    break;
                }
            }
            counter++;
        }while(freePoint[0]<-1);
        return freePoint;
    }

    //поиск наиболее длинной собраной линии в ходах пользователя, чтобы её заблокировать
    private static int[] getCriticalPointLineIndexes(){
        int[] rating = new int[winLines.length];
        //проходим все победные линии
        for(int n=0;n<winLines.length;n++){
            //Проходим все ходы юзера
            for(int i = 0; i<userTurnCounter+1; i++){
                for(int j=0;j<fieldSizeX;j++){
                    if(userTurnsArray[i][0]==winLines[n][j][0] && userTurnsArray[i][1]==winLines[n][j][1]){
                        rating[n]+=1;
                    }
                }
            }
        }
        //Берём индекс максимального рейтинга
        int max = rating[0];
        for (int i=1;i<rating.length;i++) {
            if(rating[i]>max) {
                max=rating[i];
            }
        }
        int[] maxIndexArray = new int[rating.length];
        for (int i=1;i<rating.length;i++) {
            if(rating[i]==max) maxIndexArray[i]=i;
        }

        Arrays.sort(maxIndexArray);
        ArrayUtils.reverse(maxIndexArray);



        //для проверки рейтингов
        for(int i=0;i<winLines.length;i++)
            System.out.println(Arrays.deepToString(winLines[i])+" - "+rating[i]);
        System.out.println("Максимальные индексы - " + Arrays.toString(maxIndexArray));

        return maxIndexArray;
    }

    //Заполнение массива winLines всевозможными победными вариантами
    private static void fillWinLines(){
        int localCounter =0;
        //строки
        for(int y=0;y<fieldSizeY;y++){
            for(int x=0;x<fieldSizeX;x++){
                //System.out.print(" (" +y + " " + x +")");
                winLines[localCounter][x][0]=y;
                winLines[localCounter][x][1]=x;

            }
            //System.out.println();
            localCounter++;
        }
        //столбцы
        for(int x=0;x<fieldSizeX;x++){
            for(int y=0;y<fieldSizeY;y++){
                winLines[localCounter][y][0]=y;
                winLines[localCounter][y][1]=x;
            }
            localCounter++;
        }
        //диагональ 1
        for(int x=0;x<fieldSizeX;x++){
            //System.out.print(" (" +x + " " + x +")");
            winLines[localCounter][x][0]=x;
            winLines[localCounter][x][1]=x;

        }
        localCounter++;
        //диагональ 2
            for(int x=0;x<fieldSizeX;x++){
                //System.out.print(" (" +(x) + " " + (fieldSizeX-x) +")");
                winLines[localCounter][x][0]=x;
                winLines[localCounter][x][1]=fieldSizeX-x-1;
            }
        localCounter++;
        //просмотр координат всех в вариантов
//        for(int i=0;i<localCounter;i++)
//            System.out.println(Arrays.deepToString(winLines[i]));
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

    //Проверка субматриц на победу (например Если поле=5 а победа={4 в ряд} - работает только для квадратных матриц n=n-1)
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
        userTurnCounter=-1;
        while(true) {
            int[] point = userTurn();
            drawField();
            if(isWinnerExists()) {
                System.out.println("Вы победили!!! Game is Over.");
                break;
            }
            if(isDraw()){
                System.out.println("Ничья. Game is Over.");
                break;
            }
            aiTurn(point);
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
