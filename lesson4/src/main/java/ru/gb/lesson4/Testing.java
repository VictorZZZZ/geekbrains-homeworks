package ru.gb.lesson4;

import java.util.Arrays;

public class Testing {
    public static void main(String[] args) {
        //Проверка выделения подматриц из матрицы

        char[][] field = new char[][]{
                {'1','1','1','1','1'},
                {'1','2','2','2','2'},
                {'1','2','3','3','3'},
                {'1','2','3','4','4'},
                {'1','2','3','4','5'},
        };
        int winRow = 4;

        for(int n=0;n<(field.length-winRow+1);n++) {
            char[][] submatrix = new char[winRow][winRow];
            System.out.println();
            for (int j = 0; j < winRow; j++) {
                for (int i = 0; i < winRow; i++) {
                    submatrix[j][i] =
                            field[j+n][i];
                }

                System.out.println(Arrays.toString(submatrix[j]));
            }


            if(n!=0) {
                submatrix = new char[winRow][winRow];
                System.out.println();
                for (int j = 0; j < winRow; j++) {
                    for (int i = 0; i < winRow; i++) {
                        submatrix[j][i] =
                                field[j][i + n];
                    }
                    System.out.println(Arrays.toString(submatrix[j]));
                }


                submatrix = new char[winRow][winRow];
                System.out.println();
                for (int j = 0; j < winRow; j++) {
                    for (int i = 0; i < winRow; i++) {
                        submatrix[j][i] =
                                field[j + n][i + n];
                    }
                    System.out.println(Arrays.toString(submatrix[j]));
                }

            }
        }

    }
}
