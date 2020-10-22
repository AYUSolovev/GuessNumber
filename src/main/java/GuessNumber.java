import java.util.Scanner;

public class GuessNumber {
    private int[] planNumber;
    private int[] userNumber;
    private int attempts;

    public GuessNumber(){
        planNumber = null;
        userNumber = null;
        attempts = 0;
    }

    void setUserNumber(){
        userNumber = new int[planNumber.length];
        for (int i = 0; i < planNumber.length; i++) {
            userNumber[i] = -1;
        }
    }
    void getPlanNumber(){
        System.out.print("Загаданная последовательность: ");
        for (int i:planNumber
             ) {
            System.out.print(i);
        }
    }
    void setPlanNumber(int size){
        if (size>=3 && size<=5) {
            planNumber = new int[size];
            for (int i = 0; i < planNumber.length; i++) {
                planNumber[i] = -1;
            }
            setUserNumber();
        }
        else System.exit(1);
    }

    void setAttempts(){
        attempts++;
    }

    int getAttempts(){
        return attempts;
    }

    void generatePlanNumber(){
        int i = 0;
        while (i < planNumber.length){
            int temp = (int)(Math.random()*10);
            if (!duplicated(temp,planNumber)){
                planNumber[i] = temp;
                i++;
            }
        }
        System.out.println("Случайная последовательность цифр сгенерирована");
    }

    boolean duplicated(int num, int[] arr){
        boolean comp = false;
        for (int i:arr) {
            if(i == num) comp = true;
        }
        return comp;
    }

    void convertStrInInt(String arg){
        if (arg.length() == userNumber.length){
            String[] str = arg.split("");
            for (int i = 0; i < userNumber.length; i++){
                if (!duplicated(Integer.parseInt(str[i]),userNumber)) userNumber[i] = Integer.parseInt(str[i]); // проверяем не дублируются ли цифры в последовательности пользователя
                else System.exit(1);
            }
        }
        else System.exit(1);
    }

    void comparison(){
        int cows = 0;
        int bulls = 0;
        for(int i=0; i < planNumber.length; i++){
            if (userNumber[i] == planNumber[i]) bulls++;
            else if(duplicated(userNumber[i],planNumber)){
                cows++;
            }
        }
        setAttempts();
        if (bulls == planNumber.length){
            System.out.println("Последовательность разгадана!!!");
            getPlanNumber();
            System.out.println("\nКоличество попыток:" + getAttempts());
            System.exit(0);
        }
        else {
            System.out.println("Число угаданных цифр, которые находятся на неправильных позициях: " + cows + "\n" +
                    "число угаданных цифр, которые находятся на правильных позициях: " + bulls);
        }
    }

    void startGame(){
        boolean gameOver = false;
        Scanner scanInt = new Scanner(System.in);
        Scanner scanStr = new Scanner(System.in);
        System.out.println("Введите сложность игры (3,4 или 5): ");
        int temp = scanInt.nextInt();
        setPlanNumber(temp);
        generatePlanNumber();
        String str;
        System.out.println("Введите последовательность длинной " + planNumber.length +" из цифр 0-9");
        str = scanStr.nextLine();
        while (!gameOver){
            if (str.equals("сдаюсь")) {
                gameOver = true;
                getPlanNumber();
            }
            else {
                convertStrInInt(str);
                comparison();
                setUserNumber();
                System.out.println("Напишите <сдаюсь>, если хотите окончить игру, либо введите новую последовательность: ");
                str = scanStr.nextLine();
            }
        }
    }
}
