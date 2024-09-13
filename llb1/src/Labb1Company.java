import java.util.Arrays;
import java.util.Scanner;

public class Labb1Company {

    private static final Scanner sc = new Scanner(System.in);
    private static final String[] menuChoices = {"1. Ägare (visa alla, lägg till, ändra, ta bort)",
            "2. Anställd (visa alla, lägg till, ändra, ta bort)",
            "3. Skriv ut ammanställning", "0. Avsluta programmet"};


    public static void main(String[] args) {

// Ange restaurangens namn
        String restaurangens_name;
        while (true) {
            System.out.println("Ange restaurangens namn (minst 10 tecken)");
            restaurangens_name = sc.next();
            if (restaurangens_name.length() <= 10) {
                System.out.println("För kort namn.Prova igen");
                continue;
            }
            break;
        }
        int numberOfOwner;
        // dela ägarandel till ägare
        while (true) {
            System.out.println("Antal ägare?");
            numberOfOwner = sc.nextInt();
            if (numberOfOwner <= 0) {
                System.out.println("Det måste finnas minst en ägare");
                continue;
            }
            break;
        }
        int[] owner = new int[numberOfOwner];
        if (owner.length == 1) {
            owner[0] = 100;
            System.out.println("ägare 1 ägarandel 100");
        } else {
            for (int i = 0; i < owner.length - 1; ) {
                System.out.println("ange ägare" + (i + 1) + "ägarandel");
                int ownerPercentage = Integer.parseInt(sc.nextLine());
                if (ownerPercentage <= 0) {
                    System.out.println("en ägare måste äga något...");
                    continue;
                }
                owner[i] = ownerPercentage;
                i++;
            }
            int count = 0;
            for (int j : owner) {
                count += j;
            }
            owner[numberOfOwner - 1] = 100 - count;
        }
        for (int j : owner) {
            System.out.println(j);
        }
// employees
        System.out.println("Välkommen till" + restaurangens_name + "!");
        int[] employees = new int[]{};
        System.out.println("Välj ett av dessa ment-alternativ:");
        for (String menuChoice : menuChoices) {
            System.out.println(menuChoice);
        }
        System.out.println("Ange siffran för menyval");
        sc.nextLine();
        int choice = Integer.parseInt(sc.nextLine());


        while (true) {
            switch (choice) {
                case 1: // 处理所有者相关
                    owner = subChoice(owner, "ägare");
                    break;
                case 2: // 处理员工相关的操作
                    employees = subChoice(employees, "anställd");
                    break;
                case 3: // 打印摘要
                    printSummary(owner, employees);
                    break;
                case 0: // 退出程序
                    System.out.println("Avslutar programmet.");
                    System.exit(0);
                default:
                    System.out.println("Ogiltigt val. Försök igen.");
            }
        }
    }


    //更改股东股份
    public static int[] correctOwnership(int[] array, int wantedOwnership, Boolean giveOwnership) {

        String giveOrTake = giveOwnership ? "fördelas ut" : "tas fram";
        String infoGiverOrTake = giveOwnership ? "ge till" : "ta ifrån";
        System.out.println("Det är " + wantedOwnership + "procentenheter som behöver" + giveOrTake + ".");
        System.out.println(giveOrTake.equals("fördelas ut") ? "vilken ägare vill du ge ägarandelar till ?" : "vilken ägare vill du ta ägarandelar av ? ");
        for (int i = 0; i < array.length; i++) {//show every owner ownership
            System.out.println("ägare" + (i + 1) + ":" + array[i] + "%");
        }
        while (true) {
            System.out.println("Ange siffran för vilken ägare du vill " + infoGiverOrTake);
            int index = sc.nextInt();
            if (index < 0 || index > array.length) {
                System.out.println("Felaktigt val. Prova igen");
                continue;
            }
            if ((array[index - 1] == 1) && (!giveOrTake.equals("fördelas ut"))) {
                System.out.println("Ägare" + index + " har bara 1% kvar. Du kan inte ta bort det sista ägarandelar ");
                continue;
            }
            System.out.println("Hur många preocetenheter vill du " + giveOrTake + " ägare" + index + "?");
            int ownership = sc.nextInt();
            //判断 移动的股份 小于 要求的股份数
            if (ownership > wantedOwnership) {
                System.out.println("du kan inte ta mer än " + wantedOwnership);
                continue;
            }
            // 判断 移动的股份大于0
            if (!(ownership > 0)) {
                System.out.println("du kan endast ta 1-" + wantedOwnership + "procentenheter från ägare");
                continue;
            }

            index--;


            if (giveOwnership) {
                array[index] += ownership;

            } else {
                array[index] -= ownership;
            }

            wantedOwnership -= ownership;

            if (wantedOwnership != 0) {
                System.out.println("Det är " + wantedOwnership + "procentenheter som behöver" + giveOrTake + ".");
                continue;
            }
            System.out.println(Arrays.toString(array));
            return array;
        }
    }


    public static int[] addNew(int[] array, String elementParam) {
        if (elementParam.equalsIgnoreCase("anställd")) {
            int salary;
            while (true) {
                System.out.println("Ange den anställdes timlön > ");
                salary = Integer.parseInt(sc.nextLine());
                if (salary < 0) {
                    System.out.println("Timlönen måste vara minst 0 Kr per timme");
                    continue;
                }
                break;
            }
            if (array.length == 0) {
                return new int[]{salary};
            }
            int[] newArray = Arrays.copyOf(array, array.length + 1);
            newArray[array.length] = salary;
            return newArray;
        }
        int ownership;
        while (true) {
            System.out.println("Ange ägarens ägarandel");
            ownership = Integer.parseInt(sc.nextLine());
            if (ownership < 0 || ownership > 100) {
                System.out.println("Felaktig ägarandel. Det måste vara mer än 0% och imndre än 100%");
                continue;
            }
            array = correctOwnership(array, ownership, false);
            int[] newArray = Arrays.copyOf(array, array.length + 1);
            newArray[array.length] = ownership;
            return newArray;
        }


    }

    public static void printSummary(int[] ownerArray, int[] employeesArray) {
        int totalOwnership = 0;
        for (int i = 0; i < ownerArray.length; i++) {
            totalOwnership += ownerArray[i];
            System.out.println("Ägare" + (i + 1) + ": " + ownerArray[i] + "%");
        }
        System.out.println("Totalt ägarandel: " + totalOwnership + "%");

        int totalHourSalaries = 0;
        for (int i = 0; i < employeesArray.length; i++) {
            totalHourSalaries += employeesArray[i];
            System.out.println("Anställd" + (i + 1) + ": " + employeesArray[i] + " Kr/timme");
        }
        System.out.println("Totala timkostnad anställda: " + totalHourSalaries + " Kr/timme");


    }

    public static void printAll(int[] array, String element) {
        if (array.length == 0) {
            System.out.println("Det finns inga " + element + " inlagda");
            return;
        }
        String printPrefix;
        if (element.equalsIgnoreCase("ägare")) {
            printPrefix = "%";
        } else {
            printPrefix = "kr/h";
        }
        for (int i = 0; i < array.length; i++) {
            System.out.println(element + " " + (i + 1) + ": " + array[i] + printPrefix);
        }
    }

    public static int[] remove(int[] array, String elementParam) {
        if (elementParam.equalsIgnoreCase("ägare") && array.length == 1) {
            System.out.println("Du kan inte ta bort den enda ägaren i företaget");
            return array;
        }
        System.out.println("Vilken " + elementParam + " vill du ta bort?");
        int inputNumber = 0;
        do {
            printAll(array, elementParam);
            System.out.println("Ange siffran på den " + elementParam + " du vill ta bort");
            inputNumber = Integer.parseInt(sc.nextLine()) - 1;
            if (inputNumber < 0 || inputNumber >= array.length) {
                System.out.println("Ange en giltig siffra");
                continue;
            }
            break;
        } while (true);

        int[] newArray = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i == inputNumber) {
                continue; // 跳过要删除的元素
            }
            newArray[j++] = array[i]; // 将元素复制到新数组
        }
        if (!elementParam.equalsIgnoreCase("ägare")) {
            return newArray;
        }
        return correctOwnership(newArray, array[inputNumber], true);
    }

    public static int[] change(int[] array, String elementParam) {


        int inputNumber = 0;
        Boolean giveAway = true;
        while (true) {
            System.out.println("Vilken" + elementParam + "vill du ändra på");
            printAll(array, elementParam);

            System.out.println("Ange siffran på den du vill ändra på >");
            inputNumber = Integer.parseInt(sc.nextLine()) - 1;
            if (inputNumber < 0 || inputNumber >= array.length) {
                System.out.println("Ange en giltig siffra");
                continue;
            }
            break;
        }

        if (!elementParam.equalsIgnoreCase("ägare")) {
            int salary = 0;
            while (true) {
                System.out.println("Ange den anställdes nya timlön");
                salary = Integer.parseInt(sc.nextLine());
                if (salary < 0) {
                    System.out.println("Timlönen måste vara minst 0 Kr per timme");
                    continue;
                }
                array[inputNumber] = salary;
                return array;
            }

        }

        int ownership = 0;
        while (true) {
            System.out.println("Ange ägarens nys ägarandel > ");
            ownership = Integer.parseInt(sc.nextLine());
            if (ownership < 0 || ownership > 100) {
                System.out.println("Felaktig ägarandel. Det måste vara mer än 0% och imndre än 100%");
                continue;
            }
            break;
        }
        if (ownership > array[inputNumber]) {
            ownership -= array[inputNumber];
            array[inputNumber] += ownership;
            giveAway = false;
        } else {
            int temp = array[inputNumber];
            array[inputNumber] = ownership;
            ownership = temp - ownership;
            giveAway = true;
        }
        int[] tempArr = new int[array.length - 1];
        for (int i = 0, j = 0; i < array.length; i++) {
            if (i == inputNumber) {
                continue; // 跳过要删除的元素
            }
            tempArr[j++] = array[i]; // 将元素复制到新数组
        }
        tempArr = correctOwnership(tempArr, ownership, giveAway);

        for (int i = 0, j = 0; i < array.length; i++) {
            if (i != inputNumber) {
                array[i] = tempArr[j];
                j++;
            }
            array[i] = tempArr[j];

        }
        return array;
    }

    public static int[] subChoice(int[] arrayParam, String elementParam) {
        while (true) {
            // 打印菜单选项
            System.out.println("Vad vill du göra med " + elementParam + "?");
            System.out.println("1. Visa alla " + elementParam + ".");
            System.out.println("2. Lägg till en ny " + elementParam + ".");
            System.out.println("3. Ändra en " + elementParam + ".");
            System.out.println("4. Ta bort en " + elementParam + ".");
            System.out.println("0. Gå tillbaka till huvudmenyn.");
            System.out.print("Ange siffran för menyval > ");

            // 获取用户的选择
            int menuSubChoice = Integer.parseInt(sc.nextLine());

            // 根据选择调用相应的方法
            switch (menuSubChoice) {
                case 1: // 显示所有者或员工
                    printAll(arrayParam, elementParam);
                    break;
                case 2: // 添加新所有者或员工
                    arrayParam = addNew(arrayParam, elementParam);
                    break;
                case 3: // 修改所有者或员工
                    arrayParam = change(arrayParam, elementParam);
                    break;
                case 4: // 删除所有者或员工
                    arrayParam = remove(arrayParam, elementParam);
                    break;
                case 0: // 返回主菜单
                    return arrayParam; // 返回修改后的数组
                default:
                    System.out.println("Ogiltigt val. Försök igen...");
            }
        }
    }

}



