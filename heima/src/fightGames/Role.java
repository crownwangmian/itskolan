package fightGames;

import java.util.Random;

public class Role {

    Random random = new Random();

    String[] boyfaces = {"风流俊雅", "气宇轩昂", "相貌英俊", "五官端正", "相貌平平", "一塌糊涂", "面目狰狞"};
    String[] girlfaces = {"美奂绝伦", "沉鱼落雁", "婷婷玉立", "身材娇好", "相貌平平", "相貌简陋", "惨不忍睹"};

//=========================================================================================================
    // attack 攻击描述：
    String[] attacks_desc = {
            "%s使出了一招【背心钉】，转到对方的身后，一掌向%s背心的灵台穴拍去。",
            "%s使出了一招【游空探爪】，飞起身形自半空中变掌为抓锁向%s。",
            "%s大喝一声，身形下伏，一招【劈雷坠地】，捶向%s双腿。",
            "%s运气于掌，一瞬间掌心变得血红，一式【掌心雷】，推向%s。",
            "%s阴手翻起阳手跟进，一招【没遮拦】，结结实实的捶向%s。",
            "%s上步抢身，招中套招，一招【劈挂连环】，连环攻向%s。"
    };

    // injured 受伤描述：
    String[] injureds_desc = {
            "结果%s退了半步，毫发无损",
            "结果给%s造成一处瘀伤",
            "结果一击命中，%s痛得弯下腰",
            "结果%s痛苦地闷哼了一声，显然受了点内伤",
            "结果%s摇摇晃晃，一跤摔倒在地",
            "结果%s脸色一下变得惨白，连退了好几步",
            "结果『轰』的一声，%s口中鲜血狂喷而出",
            "结果%s一声惨叫，像滩软泥般塌了下去"
    };


    private String name;
    private int blood;
    private char gender;
    private String face;

    public Role() {
    }


    public Role(String name, int blood, char gender) {
        this.name = name;
        this.blood = blood;
        setGender(gender);
    }

    public String getFace() {
        return face;
    }

    public void setFace() {
        if (gender == '男') {
            int index = random.nextInt(boyfaces.length);
            this.face = boyfaces[index];
        } else if (gender == '女') {
            int index = random.nextInt(girlfaces.length);
            this.face = girlfaces[index];
        } else {
            this.face = "面目狰狞";
        }
    }

    public void show() {
        System.out.println("***********************");
        System.out.println("姓名：" + getName());
        System.out.println("血量：" + getBlood());
        System.out.println("性别：" + getGender());
        System.out.println("面部：" + getFace());
        System.out.println("***********************");
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
        this.setFace();
    }

    public void attack(Role role) {
        int index = random.nextInt(attacks_desc.length);
        String kongFu = attacks_desc[index];
        System.out.printf(kongFu, this.getName(), role.getName());
        System.out.println(" ");
        int hurt = random.nextInt(20) + 1;
        int remainBlood = role.getBlood() - hurt;
        remainBlood = Math.max(remainBlood, 0);
        role.setBlood(remainBlood);
        if (remainBlood > 90) {
            System.out.printf(injureds_desc[0], role.getName());
            System.out.println(" ");
        } else if (remainBlood > 80) {
            System.out.printf(injureds_desc[1], role.getName());
            System.out.println(" ");
        } else if (remainBlood > 70) {
            System.out.printf(injureds_desc[2], role.getName());
            System.out.println(" ");
        } else if (remainBlood > 60) {
            System.out.printf(injureds_desc[3], role.getName());
            System.out.println(" ");
        } else if (remainBlood > 40) {
            System.out.printf(injureds_desc[4], role.getName());
            System.out.println(" ");
        } else if (remainBlood > 20) {
            System.out.printf(injureds_desc[5], role.getName());
            System.out.println(" ");
        } else if (remainBlood >= 1) {
            System.out.printf(injureds_desc[6], role.getName());
            System.out.println(" ");
        } else {
            System.out.printf(injureds_desc[7], role.getName());
            System.out.println(" ");
        }


    }

    /**
     * 获取
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     *
     * @return blood
     */
    public int getBlood() {
        return blood;
    }

    /**
     * 设置
     *
     * @param blood
     */
    public void setBlood(int blood) {
        this.blood = blood;
    }

    public String toString() {
        return "Role{name = " + name + ", blood = " + blood + "}";
    }
}
