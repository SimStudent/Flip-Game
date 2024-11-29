import java.awt.event.*;

import java.awt.*;
import javax.swing.*;

/*
 * 项目介绍
 * 
 * 
 */


class tools {
    // JVM获取类加载器，再getSource获得文件的相对坐标？
    public static ImageIcon getIconFromFile(String iconName) {
        java.net.URL iconURL = FlipGame.class.getClassLoader().getResource("images/" + iconName);
        if (iconURL != null) {
            return new ImageIcon(iconURL);
        } else {
            System.err.println("Icon not found: " + iconName);
            return null;
        }
    }

    //获得【0，x）范围之内的某个随机int数值
    public static int getRandomInt(int x) {
        return (int) (Math.random() * x);
    }

    // 怀旧测试服（bushi  
    public static void test(){
        for(int i = 0; i < 100; i++){
            if(tools.getRandomInt(10)<=9){System.out.println(true);}
            else System.out.println(false);
        }
    }
}


public class FlipGame extends JFrame  {

    FlipGame(){
        // add(new Board());
        int titleBarLength = 30;

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = getWidth();
                int height = getHeight();
                System.out.println("Current Width: " + width + ", Current Height: " + height);
            }
        });


        setTitle("Lighting Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // setSize(128*3,128*3 + titleBarLength);

        setSize(398,420);

        setResizable(false);
        createTable();

        // pack();  // 调整窗口的大小，使其能够恰好包含这些组件以及布局管理器所需的额外空间
        // 那还需不需要设置窗口的长和宽
        setVisible(true);
    }

    private JLabel lights[][];

    public void createTable(){  // 创建游戏棋盘
        setLayout(null);  // 要设置为null，否则默认的布局 BorderLayout会让最后一个按钮异常
        lights = new JLabel[3][3];
        for(int i = 0; i < 3; i++){
            for(int j = 0;j < 3;j++){
                lights[i][j] = new Light(false);
                // lights[i][j].setIcon(tools.getIconFromFile("REDx128.png"));
                lights[i][j].setBounds(j*128, i*128, 128, 128);
                add(lights[i][j]);
            }
        }
        }
    





    public static void main(String[] args) throws Exception {
        new FlipGame();
        System.out.println("Hello, World!");
        tools.test();



    }


    @Override
    public synchronized void addKeyListener(KeyListener l) {
        // TODO Auto-generated method stub
        super.addKeyListener(l);
    }
}


class Light extends JLabel{
    private boolean isBright;

    public boolean isBright(){return isBright;}

    public Light(boolean isBright){
        super();
        this.isBright = isBright;
        updateColor();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub
                // super.mouseClicked(e);
                System.out.println("Clicked");
                changeState();
            }
        });

    }

    public void changeState(){
        isBright = !isBright;
        updateColor();
    }

    private void updateColor(){
        if(isBright)setIcon(tools.getIconFromFile("GREENx128.png"));
        else setIcon(tools.getIconFromFile("REDx128.png"));
    }
}