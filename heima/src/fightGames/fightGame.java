package fightGames;

public class fightGame {
    public static void main(String[] args) {

        Role qiao = new Role();
        qiao.setName("乔峰");
        qiao.setBlood(100);
        qiao.setGender('男');

//==================================================================================================================
        Role xiao = new Role("鸠摩智", 100, '女');

        qiao.show();
        xiao.show();

        while (true) {
            qiao.attack(xiao);
            System.out.println(" ");
            if (xiao.getBlood() <= 0) {
                System.out.println(qiao.getName() + "获胜");
                break;
            }
            xiao.attack(qiao);
            System.out.println(" ");
            if (qiao.getBlood() <= 0) {
                System.out.println(xiao.getName() + "获胜");
                break;
            }
        }
    }
}
