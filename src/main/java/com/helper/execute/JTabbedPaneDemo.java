package com.helper.execute;

import com.helper.panel.tab_1.MysqlPane;
import com.helper.panel.tab_2.OAPane;
import com.helper.panel.tab_3.TextPane;
import com.helper.panel.tab_4.OAGeneralPane;
import com.helper.util.PropertyUtils;

import javax.swing.*;
import java.awt.*;

public class JTabbedPaneDemo
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch(Exception e){
                    e.printStackTrace();
                }

                JFrame frame = new JFrame (PropertyUtils.getValue("title",PropertyUtils.SYSTEM_PATH));
                frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
                frame.setVisible (true);//默认为false

                int windowWidth = 600; //获得窗口宽
                int windowHeight = 600; //获得窗口高
                Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
                Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
                int screenWidth = screenSize.width; //获取屏幕的宽
                int screenHeight = screenSize.height; //获取屏幕的高
                frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示

                JTabbedPane tp=new JTabbedPane();//创建一个选项卡容器，将之添加到顶层容器内
                frame.setContentPane(tp);
                JPanel panel1 = new OAPane();
                JPanel panel2 = new OAGeneralPane();
                JPanel panel3 = new MysqlPane();
                JPanel panel4 = new TextPane();
                JPanel panel5 = new JPanel ();
                tp.addTab("panel1", panel1); //添加选项卡容器，并且设置其中每个选项卡的标签以及其是否可启用
                tp.setEnabledAt(0,true);
                tp.setTitleAt(0,PropertyUtils.getValue("panel.title.oaGenerator",PropertyUtils.SYSTEM_PATH));

                tp.addTab ("panel2", panel2);
                tp.setEnabledAt (1, true);
                tp.setTitleAt (1,PropertyUtils.getValue("panel.title.oracleGenerator",PropertyUtils.SYSTEM_PATH));

                tp.addTab ("panel3", panel3);
                tp.setEnabledAt (2, true);
                tp.setTitleAt (2,PropertyUtils.getValue("panel.title.mysqlGenerator",PropertyUtils.SYSTEM_PATH));

                tp.addTab ("panel4", panel4);
                tp.setEnabledAt(0,true);
                tp.setTitleAt(3,PropertyUtils.getValue("panel.title.text",PropertyUtils.SYSTEM_PATH));

                tp.addTab ("panel5", panel5);
                tp.setEnabledAt(4,true);
                tp.setTitleAt(4,PropertyUtils.getValue("panel.title.other",PropertyUtils.SYSTEM_PATH));
                ///设置其大小以及其选项卡的位置方向
                tp.setPreferredSize (new Dimension (600,600));
                tp.setTabPlacement (JTabbedPane.TOP);
                ///设置选项卡在容器内的显示形式
                tp.setTabLayoutPolicy (JTabbedPane.SCROLL_TAB_LAYOUT);
                frame.pack();

                //创建标签组件，将五个中间容器设置为流布局，并且将标签组件分别放入到其中
                JLabel l5=new JLabel(PropertyUtils.getValue("panel.title.other",PropertyUtils.SYSTEM_PATH));
                JLabel l6=new JLabel(PropertyUtils.getValue("panel.title.other",PropertyUtils.SYSTEM_PATH));
                JLabel l7=new JLabel(PropertyUtils.getValue("panel.title.other",PropertyUtils.SYSTEM_PATH));
                JLabel l8=new JLabel(PropertyUtils.getValue("panel.title.other",PropertyUtils.SYSTEM_PATH));

                panel5.setLayout(new FlowLayout());

                panel5.add(l7);
                panel5.add(l8);
                frame.pack();
            }
        });
    }
}
