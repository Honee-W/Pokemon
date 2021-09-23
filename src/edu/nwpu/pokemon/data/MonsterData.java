package edu.nwpu.pokemon.data;

import java.util.HashMap;
import edu.nwpu.pokemon.npc.Monster;

public class MonsterData {
    public static HashMap<Integer, Monster> monsterMap = new HashMap<>();       //初始化所有怪物

    /**
     * 加载所有怪物数值
     */
    static {
        monsterMap.clear();
        monsterMap.put(40, new Monster(0, 50, 20, 1, 1, 1, "杰尼龟"));
        monsterMap.put(41, new Monster(1, 70, 15, 2, 2, 2, "小火龙"));
        monsterMap.put(42, new Monster(2, 100, 20, 5, 3, 3, "超音蝠"));
        monsterMap.put(43, new Monster(3, 200, 35, 10, 5, 5, "妙蛙种子"));
        monsterMap.put(44, new Monster(4, 110, 25, 5, 5, 4, "地鼠"));
        monsterMap.put(45, new Monster(5, 150, 40, 20, 8, 6, "大地鼠"));
        monsterMap.put(46, new Monster(6, 300, 75, 45, 13, 10, "喵喵"));
        monsterMap.put(47, new Monster(7, 450, 150, 90, 22, 19, "尼多朗"));
        monsterMap.put(48, new Monster(8, 150, 65, 30, 10, 8, "皮皮"));
        monsterMap.put(49, new Monster(9, 550, 160, 90, 25, 20, "皮可西"));
        monsterMap.put(50, new Monster(10, 1300, 300, 150, 40, 35, "臭臭泥"));
        monsterMap.put(51, new Monster(11, 700, 250, 125, 32, 30, "臭泥"));
        monsterMap.put(52, new Monster(12, 500, 400, 260, 47, 45, "阿柏蛇"));
        monsterMap.put(53, new Monster(13, 15000, 1000, 1000, 100, 100, "臭泥Ω"));
        monsterMap.put(54, new Monster(14, 850, 350, 200, 45, 40, "凯西"));
        monsterMap.put(55, new Monster(15, 900, 750, 650, 77, 70, "勇吉拉"));
        monsterMap.put(56, new Monster(16, 400, 90, 50, 15, 12, "三地鼠"));
        monsterMap.put(57, new Monster(17, 1500, 830, 730, 80, 70, "电击怪"));
        monsterMap.put(58, new Monster(18, 1200, 980, 900, 88, 75, "鸭嘴火兽"));
        monsterMap.put(59, new Monster(19, 30000, 1700, 1500, 250, 220, "火箭队"));
        monsterMap.put(60, new Monster(20, 250, 120, 70, 20, 17, "阿柏怪"));
        monsterMap.put(61, new Monster(21, 2000, 680, 590, 70, 65, "胡地"));
        monsterMap.put(62, new Monster(22, 2500, 900, 850, 84, 75, "拉普拉斯"));
        monsterMap.put(63, new Monster(23, 125, 50, 25, 10, 7, "小海狮"));
        monsterMap.put(64, new Monster(24, 100, 200, 110, 30, 25, "白海狮"));
        monsterMap.put(65, new Monster(25, 500, 115, 65, 15, 15, "多刺菊石兽"));
        monsterMap.put(66, new Monster(26, 900, 450, 330, 50, 50, "喵喵Ω"));
        monsterMap.put(67, new Monster(27, 1200, 620, 520, 65, 75, "火爆猴"));
        monsterMap.put(68, new Monster(28, 1250, 500, 400, 55, 55, "尼多力诺"));
        monsterMap.put(69, new Monster(29, 1500, 560, 460, 60, 60, "尼多王"));
        monsterMap.put(70, new Monster(30, 3100, 1150, 1050, 92, 80, "超级大针蜂"));
        //下面是隐藏boss
        monsterMap.put(188, new Monster(31, 99999, 5000, 4000, 0, 0, "血影"));
        monsterMap.put(198, new Monster(32, 99999, 9999, 5000, 0, 0, "魔龙"));
    }
}
