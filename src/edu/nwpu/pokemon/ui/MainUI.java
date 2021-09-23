package edu.nwpu.pokemon.ui;

import edu.nwpu.pokemon.Algorithm;
import edu.nwpu.pokemon.bgm.MainBgm;
import edu.nwpu.pokemon.bgm.StartBgm;
import sun.net.ftp.FtpClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static edu.nwpu.pokemon.System.*;
import static edu.nwpu.pokemon.data.SavaAndLoad.load;
import static edu.nwpu.pokemon.ui.PrintFrame.printFrame;

public class MainUI extends JFrame {

    /**BGM*/
    public static MainBgm mainBgm=new MainBgm();
    private StartBgm startBgm=new StartBgm();
    //*************************

    private ImageIcon backGround;
    private JButton beginB;
    private JButton readFileB;
    private JButton cheatB;
    private JButton exitB;
    private JButton aboutUsB;
    private BeginListener beginCL;
    private ReadFileListener readFileCL;
    private CheatListener cheatCL;
    private ExitListener exitCL;
    private AboutUsListener aboutUsCL;


    public MainUI(){
        //初始框架
        startBgm.start();//播放开始界面背景音乐
        this.setTitle("精灵宝可梦");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        //实例化JLayeredPane容器
        JLayeredPane LayeredPane = new JLayeredPane();
        //加载背景图片
        backGround = new ImageIcon("res/1.jpg");
        //加载图片至标签，设置容器大小和位置
        JLabel jl =new JLabel(backGround);
        jl.setBounds(0,0,backGround.getIconWidth(),backGround.getIconHeight());

        //初始化按钮，设置按钮大小和位置,设置透明按钮,注册监听器
        //开始游戏按钮
        beginB =new JButton("开始游戏");
        beginB.setBounds(50,450,100,30);
        beginB.setContentAreaFilled(false);
        beginB.setFont(new Font("楷体",Font.BOLD,16));
        beginB.setForeground(Color.WHITE);
        beginCL =new BeginListener();
        beginB.addActionListener(beginCL);
        //读取进度按钮
        readFileB=new JButton("读取进度");
        readFileB.setBounds(200,450,100,30);
        readFileB.setContentAreaFilled(false);
        readFileB.setFont(new Font("楷体",Font.BOLD,16));
        readFileB.setForeground(Color.BLACK);
        readFileCL=new ReadFileListener();
        readFileB.addActionListener(readFileCL);
        //作弊按钮
        cheatB =new JButton("变得更强");
        cheatB.setBounds(350,450,100,30);
        cheatB.setContentAreaFilled(false);
        cheatB.setFont(new Font("楷体",Font.BOLD,16));
        cheatB.setForeground(Color.WHITE);
        cheatCL =new CheatListener();
        cheatB.addActionListener(cheatCL);
        //退出按钮
        exitB =new JButton("退出游戏");
        exitB.setBounds(130,500,100,30);
        exitB.setContentAreaFilled(false);
        exitB.setFont(new Font("楷体",Font.BOLD,16));
        exitB.setForeground(Color.BLUE);
        exitCL=new ExitListener();
        exitB.addActionListener(exitCL);
        //职员表按钮
        aboutUsB =new JButton("关于我们");
        aboutUsB.setBounds(260,500,100,30);
        aboutUsB.setContentAreaFilled(false);
        aboutUsB.setFont(new Font("楷体",Font.BOLD,16));
        aboutUsB.setForeground(Color.RED);
        aboutUsCL=new AboutUsListener();
        aboutUsB.addActionListener(aboutUsCL);

        LayeredPane.add(jl,JLayeredPane.DEFAULT_LAYER);//背景放置最底层
        LayeredPane.add(beginB,JLayeredPane.MODAL_LAYER);//按钮放置高一层
        LayeredPane.add(readFileB,JLayeredPane.MODAL_LAYER);
        LayeredPane.add(cheatB,JLayeredPane.MODAL_LAYER);
        LayeredPane.add(exitB,JLayeredPane.MODAL_LAYER);
        LayeredPane.add(aboutUsB,JLayeredPane.MODAL_LAYER);

        //窗体设置为JLayeredPane容器
        this.setLayeredPane(LayeredPane);
        this.setSize(backGround.getIconWidth(),backGround.getIconHeight());
        //窗体居中
        this.setLocationRelativeTo(null);

    }

    /**
     * 内部类监听选项按钮
     */
    class BeginListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            java.lang.System.out.println("开始游戏");
            startBgm.stopBgm();//停止播放开始界面音乐
            startBgm.interrupt();//安全关闭线程
            mainBgm.start();//播放游戏界面音乐
            printFrame();
            dispose();
        }
    }

    class ReadFileListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            java.lang.System.out.println("读取文档");
            load();
            printFrame();
            dispose();
        }
    }
    /**
     * 内部类监听挂机，按X键自动战斗
     */
    class HangUpListener implements KeyListener {


        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_X) {
                Algorithm algorithm = new Algorithm();
                repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    class CheatListener implements ActionListener{


        JFrame CLframe =new JFrame();
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            java.lang.System.out.println("变得更强");
            //弹出充值界面，界面置中

            CLframe.setVisible(true);
            CLframe.setTitle("充值界面");
            CLframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
            CLframe.setBounds(500,200,500,500);

            //初始化LayeredPane
            JLayeredPane layeredPane =new JLayeredPane();

            //添加充值图片并添加至标签
            ImageIcon imageIcon =new ImageIcon("res/2.jpg");
            JLabel label=new JLabel(imageIcon);
            label.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
            //添加标签至最底层
            layeredPane.add(label,JLayeredPane.DEFAULT_LAYER);

            //添加按钮，改变角色属性
            JButton button =new JButton("老毛桃最帅");
            button.setBounds(200,400,100,30);
            button.setContentAreaFilled(false);

            //注册并添加监听器
            ChangeCharacterListener characterListener =new ChangeCharacterListener();
            button.addActionListener(characterListener);

            //添加按钮至上层，改变属性
            layeredPane.add(button,JLayeredPane.MODAL_LAYER);
            //设置主页面为LayeredPane
            CLframe.setLayeredPane(layeredPane);

        }

        /**
         * 改变角色属性
         */
        class ChangeCharacterListener implements ActionListener{

            private JFrame frame;  //界面框架
            private JTextField levelTF; //改变信息文本框
            private JTextField hpTF;
            private JTextField attackTF;
            private JTextField defendTF;
            private JTextField moneyTF;
            private JTextField expTF;
            private JTextField YkeyTF;
            private JTextField BkeyTF;
            private JTextField RkeyTF;
            @Override
            public void actionPerformed(ActionEvent e) {
                CLframe.dispose();
                //主框架初始化
                frame =new JFrame();
                frame.setTitle("走向巅峰");
                frame.setVisible(true);
                frame.setBackground(Color.BLACK);
                frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                //实例化LayeredPane容器
                JLayeredPane layeredPane =new JLayeredPane();
                //加载改变属性图片并添加至标签,设置标签大小
                ImageIcon imageIcon =new ImageIcon("res/3.png");
                JLabel label=new JLabel(imageIcon);
                label.setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
                //添加标签至最底层
                layeredPane.add(label,JLayeredPane.DEFAULT_LAYER);

                //添加标签改变属性,设置字体颜色
                JLabel level =new JLabel("角色等级");
                level.setForeground(Color.RED);
                level.setBounds(200,200,100,30);

                JLabel hp =new JLabel("角色生命值");
                hp.setForeground(Color.RED);
                hp.setBounds(200,250,100,30);

                JLabel attack =new JLabel("角色攻击力");
                attack.setForeground(Color.RED);
                attack.setBounds(200,300,100,30);

                JLabel defend =new JLabel("角色防御力");
                defend.setForeground(Color.RED);
                defend.setBounds(200,350,100,30);

                JLabel money =new JLabel("金钱");
                money.setForeground(Color.RED);
                money.setBounds(200,400,50,30);

                JLabel exp =new JLabel("经验");
                exp.setForeground(Color.RED);
                exp.setBounds(200,450,50,30);

                JLabel Ykey =new JLabel("黄色钥匙");
                Ykey.setForeground(Color.RED);
                Ykey.setBounds(200,500,50,30);

                JLabel Bkey =new JLabel("蓝色钥匙");
                Bkey.setForeground(Color.RED);
                Bkey.setBounds(200,550,50,30);

                JLabel Rkey =new JLabel("红色钥匙");
                Rkey.setForeground(Color.RED);
                Rkey.setBounds(200,600,50,30);

                //添加标签，输出原有数据
                JLabel originLevel =new JLabel(String.valueOf(playerBean_1.getLevel()));
                originLevel.setBounds(330,200,30,30);
                originLevel.setForeground(Color.RED);

                JLabel originHP =new JLabel((String.valueOf(playerBean_1.getHp())));
                originHP.setBounds(330,250,30,30);
                originHP.setForeground(Color.RED);

                JLabel originAttack =new JLabel(String.valueOf(playerBean_1.getAttack()));
                originAttack.setBounds(330,300,30,30);
                originAttack.setForeground(Color.RED);

                JLabel originDefend =new JLabel(String.valueOf(playerBean_1.getDefend()));
                originDefend.setBounds(330,350,30,30);
                originDefend.setForeground(Color.RED);

                JLabel originMoney =new JLabel(String.valueOf(playerBean_1.getMoney()));
                originMoney.setBounds(330,400,30,30);
                originMoney.setForeground(Color.RED);

                JLabel originExp =new JLabel(String.valueOf(playerBean_1.getExp()));
                originExp.setBounds(330,450,30,30);
                originExp.setForeground(Color.RED);

                JLabel originYkey =new JLabel(String.valueOf(playerBean_1.getYkey()));
                originYkey.setBounds(330,500,30,30);
                originYkey.setForeground(Color.RED);

                JLabel originBkey =new JLabel(String.valueOf(playerBean_1.getBkey()));
                originBkey.setBounds(330,550,30,30);
                originBkey.setForeground(Color.RED);

                JLabel originRkey =new JLabel(String.valueOf(playerBean_1.getRkey()));
                originRkey.setBounds(330,600,30,30);
                originRkey.setForeground(Color.RED);
                //添加文本框，输入要改变的数值
                levelTF = new JTextField("请输入想要成为的等级");
                levelTF.setForeground(Color.RED);
                levelTF.setBackground(Color.BLACK);
                levelTF.setBounds(460,200,150,30);

                hpTF =new JTextField("请输入想要的血量");
                hpTF.setForeground(Color.RED);
                hpTF.setBackground(Color.BLACK);
                hpTF.setBounds(460,250,150,30);

                attackTF =new JTextField("请输入想要的攻击力");
                attackTF.setForeground(Color.RED);
                attackTF.setBackground(Color.BLACK);
                attackTF.setBounds(460,300,150,30);

                defendTF =new JTextField("请输入想要的防御力");
                defendTF.setForeground(Color.RED);
                defendTF.setBackground(Color.BLACK);
                defendTF.setBounds(460,350,150,30);

                moneyTF=new JTextField("请输入想要的金钱");
                moneyTF.setForeground(Color.RED);
                moneyTF.setBackground(Color.BLACK);
                moneyTF.setBounds(460,400,150,30);

                expTF =new JTextField("请输入想要的经验值");
                expTF.setForeground(Color.RED);
                expTF.setBackground(Color.BLACK);
                expTF.setBounds(460,450,150,30);

                YkeyTF =new JTextField("请输入想要的黄色钥匙");
                YkeyTF.setForeground(Color.RED);
                YkeyTF.setBackground(Color.BLACK);
                YkeyTF.setBounds(460,500,150,30);

                BkeyTF =new JTextField("请输入想要的蓝色钥匙");
                BkeyTF.setForeground(Color.RED);
                BkeyTF.setBackground(Color.BLACK);
                BkeyTF.setBounds(460,550,150,30);

                RkeyTF =new JTextField("请输入想要的红色钥匙");
                RkeyTF.setForeground(Color.RED);
                RkeyTF.setBackground(Color.BLACK);
                RkeyTF.setBounds(460,600,150,30);

                //添加确定键及相应的监听器，触发事件改变属性
                JButton confirmButton = new JButton("确定");
                confirmButton.setBounds(650,400,100,30);
                ConfirmButtonListener confirmButtonListener =new ConfirmButtonListener();
                confirmButton.addActionListener(confirmButtonListener);
                //添加标签及文本框至上层
                layeredPane.add(level,JLayeredPane.MODAL_LAYER);
                layeredPane.add(hp,JLayeredPane.MODAL_LAYER);
                layeredPane.add(attack,JLayeredPane.MODAL_LAYER);
                layeredPane.add(defend,JLayeredPane.MODAL_LAYER);
                layeredPane.add(money,JLayeredPane.MODAL_LAYER);
                layeredPane.add(exp,JLayeredPane.MODAL_LAYER);
                layeredPane.add(Ykey,JLayeredPane.MODAL_LAYER);
                layeredPane.add(Bkey,JLayeredPane.MODAL_LAYER);
                layeredPane.add(Rkey,JLayeredPane.MODAL_LAYER);



                layeredPane.add(originLevel,JLayeredPane.MODAL_LAYER);
                layeredPane.add(originHP,JLayeredPane.MODAL_LAYER);
                layeredPane.add(originAttack,JLayeredPane.MODAL_LAYER);
                layeredPane.add(originDefend,JLayeredPane.MODAL_LAYER);
                layeredPane.add(originMoney,JLayeredPane.MODAL_LAYER);
                layeredPane.add(originExp,JLayeredPane.MODAL_LAYER);
                layeredPane.add(originYkey,JLayeredPane.MODAL_LAYER);
                layeredPane.add(originBkey,JLayeredPane.MODAL_LAYER);
                layeredPane.add(originRkey,JLayeredPane.MODAL_LAYER);

                layeredPane.add(levelTF,JLayeredPane.MODAL_LAYER);
                layeredPane.add(hpTF,JLayeredPane.MODAL_LAYER);
                layeredPane.add(attackTF,JLayeredPane.MODAL_LAYER);
                layeredPane.add(defendTF,JLayeredPane.MODAL_LAYER);
                layeredPane.add(moneyTF,JLayeredPane.MODAL_LAYER);
                layeredPane.add(expTF,JLayeredPane.MODAL_LAYER);
                layeredPane.add(YkeyTF,JLayeredPane.MODAL_LAYER);
                layeredPane.add(BkeyTF,JLayeredPane.MODAL_LAYER);
                layeredPane.add(RkeyTF,JLayeredPane.MODAL_LAYER);

                layeredPane.add(confirmButton,JLayeredPane.MODAL_LAYER);

                //设置主页面为LayeredPane,窗体居中
                frame.setLayeredPane(layeredPane);
                frame.setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
                frame.setLocationRelativeTo(null);

            }

            /**
             * 监听确认按钮事件
             */
            class ConfirmButtonListener implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
                    File file =new File("res/cheatData.txt");
                    try {
                        //存储要改变的数值
                        PrintWriter writer = new PrintWriter("cheatData.txt", "UTF-8");
                        String data = levelTF.getText()+"_"+hpTF.getText()+"_"+attackTF.getText()+"_"+defendTF.getText()+
                                "_"+moneyTF.getText()+"_"+expTF.getText()+"_"+YkeyTF.getText()+"_"+BkeyTF.getText()+"_"+RkeyTF.getText();
                        writer.println(data);
                        writer.close();
                        //改变属性
                        playerBean_1.setLevel(Integer.parseInt(levelTF.getText()));
                        playerBean_1.setHp(Integer.parseInt(hpTF.getText()));
                        playerBean_1.setAttack(Integer.parseInt(attackTF.getText()));
                        playerBean_1.setDefend(Integer.parseInt(defendTF.getText()));
                        playerBean_1.setMoney(Integer.parseInt(moneyTF.getText()));
                        playerBean_1.setExp(Integer.parseInt(expTF.getText()));
                        playerBean_1.setYkey(Integer.parseInt(YkeyTF.getText()));
                        playerBean_1.setBkey(Integer.parseInt(BkeyTF.getText()));
                        playerBean_1.setRkey(Integer.parseInt(RkeyTF.getText()));
                        JOptionPane.showMessageDialog(null,"您已变强，重新进入游戏体验一下吧");
                        //重新开始游戏
                        printFrame();
                        frame.dispose();

                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                        java.lang.System.err.println("数据文件未找到!");
                    } catch (UnsupportedEncodingException unsupportedEncodingException) {
                        unsupportedEncodingException.printStackTrace();
                    }

                }
            }
        }
    }

    class ExitListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            java.lang.System.out.println("退出游戏");
            java.lang.System.exit(1);
        }
    }

    class AboutUsListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            java.lang.System.out.println("关于我们");
            //主页框架初始化
            JFrame frame =new JFrame("关于我们");
            frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            frame.setVisible(true);
            frame.setBounds(400,0,754,800);
            frame.setLayout(null);

            //加载图片至标签,并设置大小
            ImageIcon logo =new ImageIcon("res/logo.png");
            JLabel label =new JLabel(logo);
            label.setBounds(00,0,logo.getIconWidth(),logo.getIconHeight());

            //文本框输入简介
            JTextArea textArea =new JTextArea();
            String intro ="老毛桃组"+"\n"+"组员："+"\n"+"李晶  "+"钟天祥 "+"颜纪伟 "+"袁昊 "+"杨钊 "+"徐林 "+"赫悦翔 "+"王子谦";
            textArea.setText(intro);
            textArea.setBounds(180,630,400,100);

            //添加控件至主页面
            Container container =frame.getContentPane();
            container.add(label);
            container.add(textArea);
        }
    }
}
