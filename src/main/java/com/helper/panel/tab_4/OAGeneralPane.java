package com.helper.panel.tab_4;

import com.helper.model.GeneratorCheckBoxLabel;
import com.helper.model.GeneratorPageInfo;
import com.helper.util.CodeGenerateUtils;
import com.helper.util.DBUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

/**
 * <p>
 * <code>DatabasePropertiesPane</code>
 * </p>
 * Description:
 *
 * @author Mcchu
 * @date 2018/3/2 16:41
 */
public class OAGeneralPane extends JPanel {

    private OAGeneralTopPane topPane;
    private OAGeneralCenterPane centerPane;
    private OAGeneralBottomPane bottomPane;
    private OAGeneralRightPane rightPane;


    public OAGeneralPane( ) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.33;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        // 1. 数据源配置
        add((topPane = new OAGeneralTopPane()), gbc);
        JButton testConnBtn = topPane.getTestConn();
        testConnBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 点击测试连接时，打印testConnLabel提示
                String sourceType = (String)topPane.getDatasourceType().getSelectedItem();
                String sourceIP = topPane.getDatasourceIP().getText();
                String sourcePort = topPane.getDatasourcePort().getText();
                String sourceName = topPane.getDatasourceName().getText();
                String sourceUsername = topPane.getDatasourceUsername().getText();
                String sourcePassword = topPane.getDatasourcePassword().getText();

                System.out.println(sourceType);
                Connection conn = DBUtils.getConnection(sourceType,sourceIP,sourcePort,sourceName,sourceUsername,
                        sourcePassword);
                if ( null != conn ){
                    topPane.getTestConnLabel().setText("      Success! ");
                    topPane.getTestConnLabel().setForeground(Color.blue);
                }else {
                    topPane.getTestConnLabel().setText("      Error! ");
                    topPane.getTestConnLabel().setForeground(Color.red);
                }
            }
        });
        gbc.gridy++;

        // 2. 数据库生成配置
        add((centerPane = new OAGeneralCenterPane()), gbc);
        gbc.gridy++;

        // 3. 生成配置
        add((bottomPane = new OAGeneralBottomPane()), gbc);
        JButton btn = bottomPane.getSystemDatabase();
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 点击生成代码时，根据上面数据进行生成
                boolean l1 = bottomPane.getControllerRBtn().isSelected();
                boolean l2 = bottomPane.getEntityRBtn().isSelected();
                boolean l3 = bottomPane.getDTORBtn().isSelected();
                boolean l4 = bottomPane.getServiceRBtn().isSelected();
                boolean l5 = bottomPane.getDAORBtn().isSelected();
                boolean l6 = bottomPane.getMapperRBtn().isSelected();
                GeneratorCheckBoxLabel label = new GeneratorCheckBoxLabel(l1,l2,l3,l4,l5,l6);

                String sourceType = (String)topPane.getDatasourceType().getSelectedItem();
                String sourceIP = topPane.getDatasourceIP().getText();
                String sourcePort = topPane.getDatasourcePort().getText();
                String sourceName = topPane.getDatasourceName().getText();
                String sourceUsername = topPane.getDatasourceUsername().getText();
                String sourcePassword = topPane.getDatasourcePassword().getText();

                String tableName = centerPane.getTableNameField().getText();
                String authorName = centerPane.getAuthorField().getText();
                String tableAnnotation = centerPane.getTableAnnotationField().getText();
                String packageName = centerPane.getPackageField().getText();
                String diskPath = centerPane.getDiskPathField().getText();

                try {
                    System.out.println("======Code Begin Generating=======");
                    GeneratorPageInfo info = new GeneratorPageInfo();
                    info.setDbType(sourceType);
                    info.setIp(sourceIP);
                    info.setPort(sourcePort);
                    info.setDbName(sourceName);
                    info.setDbUserName(sourceUsername);
                    info.setDbPassword(sourcePassword);

                    info.setTableName(tableName);
                    info.setAuthorName(authorName);
                    info.setTableAnnotation(tableAnnotation);
                    info.setPackageName(packageName);
                    info.setDiskPath(diskPath);

                    System.out.println(info.toString());
                    CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils( info );
                    codeGenerateUtils.generate( label );
                    System.out.println("======Code Generator Success=======");
                }catch (Exception ex){
                    System.out.println("======Code Generator failure=======");
                    ex.printStackTrace();
                }
            }
        });

        gbc.gridy = 0;
        gbc.gridx++;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;
        gbc.weightx = 0;
        add((rightPane = new OAGeneralRightPane()), gbc);
        JButton clearBtn = rightPane.getClear();
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                topPane.getDatasourceIP().setText("");
                topPane.getDatasourcePort().setText("");
                topPane.getDatasourceName().setText("");
                topPane.getDatasourceUsername().setText("");
                topPane.getDatasourcePassword().setText("");

                centerPane.getAuthorField().setText("");
                centerPane.getTableNameField().setText("");
                centerPane.getTableAnnotationField().setText("");
                centerPane.getPackageField().setText("");
                centerPane.getDiskPathField().setText("");
            }
        });
    }
}
