
import java.util.Scanner;

class Calc {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (арабских или римских): ");
        String expression = scanner.nextLine();
        System.out.println(calc(expression));
    }

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRom;
        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        oper = detectOperation(input);
        if (oper == null) throw new Exception("Строка не является математической операцией");
        if (Rom.isRom(operands[0]) && Rom.isRom(operands[1])) {
            num1 = Rom.convertToArabian(operands[0]);
            num2 = Rom.convertToArabian(operands[1]);
            isRom = true;
        }
        else if (!Rom.isRom(operands[0]) && !Rom.isRom(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRom = false;
        }
        else {
            throw new Exception("Используются одновременно разные системы счисления");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRom) {
            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }
            result = Rom.convertToRom(arabian);
        } else {
            result = String.valueOf(arabian);
        }
        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Rom {
    static String[] romArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};


    public static boolean isRom(String val) {
        for (int i = 0; i < romArray.length; i++) {
            if (val.equals(romArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String rom) {
        for (int i = 0; i < romArray.length; i++) {
            if (rom.equals(romArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRom(int arabian) {
        return romArray[arabian];
    }

}