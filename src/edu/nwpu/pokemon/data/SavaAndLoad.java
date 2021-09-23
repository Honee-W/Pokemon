package edu.nwpu.pokemon.data;

import edu.nwpu.pokemon.npc.Player;

import static edu.nwpu.pokemon.System.currentFloor;
import static edu.nwpu.pokemon.System.playerBean_1;

import java.io.*;

public class SavaAndLoad {


    public static void save(){
        try {

            PrintWriter save = new PrintWriter("save.txt","UTF-8");
            save.print(  playerBean_1.toString() +
                         currentFloor  + "_" +
                         Player.getLevelUpExp());
            save.close();
            java.lang.System.out.print("save2\n");

        } catch (IOException e) {
            e.toString();
        }
    }

    public static void load(){
        try {
            String temp;

            BufferedReader load = new BufferedReader(new FileReader("save.txt"));
            temp = load.readLine();
            temp.split("_");

            String[] information = temp.split("_");

            playerBean_1.setLevel(Integer.parseInt(information[0]));
            playerBean_1.setHp(Integer.parseInt(information[1]));
            playerBean_1.setAttack(Integer.parseInt(information[2]));
            playerBean_1.setDefend(Integer.parseInt(information[3]));
            playerBean_1.setMoney(Integer.parseInt(information[4]));
            playerBean_1.setExp(Integer.parseInt(information[5]));
            playerBean_1.setYkey(Integer.parseInt(information[6]));
            playerBean_1.setBkey(Integer.parseInt(information[7]));
            playerBean_1.setRkey(Integer.parseInt(information[8]));
            playerBean_1.setToward(Integer.parseInt(information[9]));
            playerBean_1.setPosX(Integer.parseInt(information[10]));
            playerBean_1.setPosY(Integer.parseInt(information[11]));
            currentFloor = (Integer.parseInt(information[12]));
            Player.setLevelUpExp(Integer.parseInt(information[13]));
            
        } catch (IOException e) {
            e.toString();
        }
    }
}
