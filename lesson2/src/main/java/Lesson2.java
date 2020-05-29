import java.sql.SQLOutput;
import java.util.Arrays;

public class Lesson2 {
    public static void main(String[] args) {
//       reverseArray();
//        fillArray();
//        arrayHandling();
//        diagonalArray(30);
//        findMaxMin();
//        System.out.println(checkBalance(new int[]{10, 5, 3, 3, 8, 1}));
//        System.out.println();
//        System.out.println(checkBalance(new int[]{10, 5, 3, 3, 8, 2}));

            moveArray(new int[]{1,2,3,4,5,6,7,8},59);

    }

//1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
    private static void reverseArray(){
        int[] arr = { 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 };
        System.out.println("Исходный массив " + Arrays.toString(arr));
        for(int i=0;i<arr.length;i++){
            if(arr[i]==0){
                arr[i]=1;
            } else {
                arr[i]=0;
            }
        }
        System.out.println("Выходной массив " + Arrays.toString(arr));
    }

//2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    private static void fillArray(){
        int[] arr=new int[8];
        for(int i=0;i<arr.length;i++){
            arr[i]=i*3;
        }
        System.out.println("Массив заполнен: " + Arrays.toString(arr));
    }

//3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    private static void arrayHandling(){
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println("Исходный массив " + Arrays.toString(arr));
        for(int i=0;i<arr.length;i++){
            if(arr[i]<6) arr[i]*=2;
        }
        System.out.println("Выходной массив " + Arrays.toString(arr));
    }

//4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое), и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    private static void diagonalArray(int n){
        int[][] matrix = new int[n][n];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if((i==j) || (i+j)==(n-1) ) {
                    matrix[i][j] = 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
//5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
    private static void findMaxMin(){
        int[] arr = { 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 };
        System.out.println(Arrays.toString(arr));
        int max =arr[0];
        int min = arr[0];
        for (int a:arr) {
            if(a>max) max=a;
            if(a<min) min=a;
        }
        System.out.printf(" max=%d \n min=%d",max,min);

    }

//6. ** Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
// если в массиве есть место, в котором сумма левой и правой части массива равны.
// Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true, граница показана символами ||, эти символы в массив не входят.
    private static boolean checkBalance(int [] arr){
        System.out.println(Arrays.toString(arr));
        int leftSum = arr[0];
        int rightSum = arr[arr.length-1];
        int leftIndex=0;
        int rightIndex=arr.length-1;
        for(int i=0;i<arr.length;i++){
            if(leftSum<rightSum){
                leftSum+=arr[leftIndex+1];
                leftIndex++;
            } else {
                rightSum+=arr[rightIndex-1];
                rightIndex--;
            }
            if(leftIndex+1==rightIndex) break;
        }

        if(leftSum==rightSum) {
            System.out.println("Сумма слева " + leftSum);
            System.out.println("Сумма справа " + rightSum);
            //вывод разделенного массива
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
                if (i == leftIndex) System.out.print("|| ");
            }
            return true;
        } else return false;
    }

//7. **** Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
// при этом метод должен сместить все элементымассива на n позиций.
// Для усложнения задачи нельзя пользоваться вспомогательными массивами.
    private static void moveArray(int [] arr,int n){
        //ОПТИМИЗИРУЕМ. Если n в разы больше размерности массива, то достаточно провести сдвиг массива n % arr.length раз.
        if(arr.length < Math.abs(n)) n = n % arr.length;

        System.out.println(Arrays.toString(arr));
        if(n>0) {
            for (int i = 1; i <= n; i++) {
                int buffer = arr[arr.length - 1];
                for (int j = arr.length - 1; j > 0; j--) {
                    arr[j] = arr[j - 1];
                }
                arr[0] = buffer;
                System.out.println("step " + i + ":" + Arrays.toString(arr));
            }
        }
        if(n<0){
            for (int i = -1; i >= n; i--) {
                int buffer = arr[0];
                for (int j = 0; j < arr.length-1; j++) {
                    arr[j] = arr[j + 1];
                }
                arr[arr.length-1] = buffer;
                System.out.println("step " + -i + ":" + Arrays.toString(arr));
            }
        }
    }

}


