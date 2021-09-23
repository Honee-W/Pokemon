package edu.nwpu.pokemon.npc;

/**
 * 怪物类
 * @author 老毛桃
 * @version v1.0.0
 */
public class Monster {

    private int id;         // 怪物标号
    private int hp;         // 生命值
    private int attack;     // 攻击力
    private int defend;     // 防御力
    private int money;      // 金钱
    private int exp;        // 经验
    private String name;    // 怪物名

    /**
     * Instantiates a new Monster.
     *
     * @param id     the id
     * @param hp     the hp
     * @param attack the attack
     * @param defend the defend
     * @param money  the money
     * @param exp    the exp
     * @param name   the name
     */
    public Monster(int id, int hp, int attack, int defend, int money, int exp, String name) {
        this.id = id;
        this.hp = hp;
        this.attack = attack;
        this.defend = defend;
        this.money = money;
        this.exp = exp;
        this.name = name;
    }

    /**
     * 获取 hp.
     * @return the hp
     */
    public int getHp() {
        return hp;
    }

    /**
     * 获取攻击力
     * @return
     */
    public int getAttack() {
        return attack;
    }

    /**
     * 获取防御力
     * @return the defend
     */
    public int getDefend() {
        return defend;
    }

    /**
     * 获取金币数
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * 获取击败后经验值
     * @return the exp
     */
    public int getExp() {
        return exp;
    }

    /**
     * 获取怪物姓名
     * @return the name
     */
    public String getName() {
        return name;
    }

}
