import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Введите выражение: \"Строка\"(максимуи 10 символов) операция \"Строка\"(максимуи 10 символов)/число(от 1 до 10 включительно) + Enter ");
        Scanner scn = new Scanner(System.in);
        String exp = scn.nextLine();
        char action;
        String[] data;
        if (exp.contains(" + ")) {
            data = exp.split(" \\+ ");
            action = '+';
        }
        else if (exp.contains(" - ")) {
            data = exp.split(" - ");
            action = '-';
        }
        else if (exp.contains(" * ")) {
            data = exp.split(" \\* ");
            action = '*';
        }
        else if (exp.contains(" / ")) {
            data = exp.split(" / ");
            action = '/';
        }
        else{
            throw new Exception("Некорректный знак действия");
        }
        if(data[0].length()<=12 & data[1].length()<=12) {
            if (action == '*' || action == '/') {
                if (data[1].contains("\"")) throw new Exception("Строчку можно делить или умножать только на число");
            }
            for (int i = 0; i < data.length; i++) {
                data[i] = data[i].replace("\"", "");
            }
            if (action == '+') {
                printInQuotes(data[0] + data[1]);
            } else if (action == '*') {
                int multiplier = Integer.parseInt(data[1]);
                if (multiplier<1 || multiplier>10) throw new Exception("Можно использовать числа от 1 до 10");
                String result = "";
                for (int i = 0; i < multiplier; i++) {
                    result += data[0];
                }
                printInQuotes(result);
            } else if (action == '-') {
                int index = data[0].indexOf(data[1]);
                if (index == -1) {
                    printInQuotes(data[0]);
                } else {
                    String result = data[0].substring(0, index);
                    result += data[0].substring(index + data[1].length());
                    printInQuotes(result);
                }
            } else {
                int newLen = data[0].length() / Integer.parseInt(data[1]);
                int divider = Integer.parseInt(data[1]);
                if (divider<1 || divider>10) throw new Exception("Можно использовать числа от 1 до 10");
                String result = data[0].substring(0, newLen);
                printInQuotes(result);
            }
        }
        else {
            throw new Exception("Можно вводить максимум 10 символов");
        }
    }
    static void printInQuotes(String text){
        if(text.length()>40){
            System.out.print("\""+text.substring(0,40)+"..."+"\"");
        }
        else
            System.out.print("\""+text+"\"");
    }
}