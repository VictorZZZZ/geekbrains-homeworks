public class Lesson1 {

    byte b = 100;
    short s = 100;
    int i = 1000;
    long l = 1000000L;
    float f = 12.23f;
    double d = -123.258;
    char c = 'C';
    boolean bool = true;


    public static void main(String[] args) {
        System.out.println("Hello world \n");
        calculation(15,2,1,4.3);
        checkSum(15,18);
        checkSum(8,4);
        checkSign(2);
        checkSign(-18);
        sayHello("Victor");


        leapYear(4);
        leapYear(5);
        leapYear(100);
        leapYear(200);
        leapYear(400);
        leapYear(800);
        leapYear(803);
        leapYear(804);
        leapYear(2020);
        leapYear(2021);
        leapYear(2024);
    }

    //задание 3
    private static double calculation(int a,int b, int c, double d){
        double result = a*(b+(c/d));
        System.out.println(result+"\n");
        return result;
    }

    //задание 4
    private static boolean checkSum(int a, int b){
        int sum = a+b;
        System.out.printf("Сумма чисел: %d + %d = %d \n",a,b,sum);
        if(sum>10 && sum<20){
            System.out.println("Сумма попадает в интервал 10<x<20 \n");
            return true;
        } else {
            System.out.println("Сумма не попадает в интервал 10<x<20 \n");
            return false;
        }
    }

    //задание 5
    private static void checkSign(int a){
        if(a<0){
            System.out.println("Число отрицательное \n");
        } else {
            System.out.println("Число положительное \n");
        }
    }

    //задание 6
    private static boolean checkNegative(int a){
        if(a<0) {
            return true;
        } else return false;
    }

    //Задание 7
    private static void sayHello(String name){
        System.out.printf("Привет, %s!",name);
    }

    //Задание 8*
    private static void leapYear(int year){
        if(year % 4 == 0 || year % 400 ==0){
            if((year % 100 == 0) && (year % 400!=0) ) {
                System.out.printf("Год %d не является високосным! \n", year);
            } else {
                System.out.printf("Год %d является високосным! \n", year);
            }

        } else {
            System.out.printf("Год %d не является високосным! \n", year);
        }
    }

}
