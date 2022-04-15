
package com.helper.panel.tab_1;

import com.helper.util.CodeGenerateUtils;
import com.helper.model.GeneratorCheckBoxLabel;
import com.helper.model.GeneratorPageInfo;
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
public class MysqlPane extends JPanel {

    private MysqlTopPane MysqlTopPane;
    private MysqlCenterPane MysqlCenterPane;
    private MysqlBottomPane MysqlBottomPane;
    private MysqlRightPane MysqlRightPane;


    public MysqlPane() {
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
        add((MysqlTopPane = new MysqlTopPane()), gbc);
        JButton testConnBtn = MysqlTopPane.getTestConn();
        testConnBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 点击测试连接时，打印testConnLabel提示
                String sourceType = (String) MysqlTopPane.getDatasourceType().getSelectedItem();
                String sourceIP = MysqlTopPane.getDatasourceIP().getText();
                String sourcePort = MysqlTopPane.getDatasourcePort().getText();
                String sourceName = MysqlTopPane.getDatasourceName().getText();
                String sourceUsername = MysqlTopPane.getDatasourceUsername().getText();
                String sourcePassword = MysqlTopPane.getDatasourcePassword().getText();

                System.out.println(sourceType);
                Connection conn = DBUtils.getConnection(sourceType, sourceIP, sourcePort, sourceName, sourceUsername,
                        sourcePassword);
                if (null != conn) {
                    MysqlTopPane.getTestConnLabel().setText("      Success! ");
                    MysqlTopPane.getTestConnLabel().setForeground(Color.blue);
                } else {
                    MysqlTopPane.getTestConnLabel().setText("      Error! ");
                    MysqlTopPane.getTestConnLabel().setForeground(Color.red);
                }
            }
        });
        gbc.gridy++;

        // 2. 数据库生成配置
        add((MysqlCenterPane = new MysqlCenterPane()), gbc);
        gbc.gridy++;

        // 3. 生成配置
        add((MysqlBottomPane = new MysqlBottomPane()), gbc);
        JButton btn = MysqlBottomPane.getSystemDatabase();
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 点击生成代码时，根据上面数据进行生成
                boolean l1 = MysqlBottomPane.getControllerRBtn().isSelected();
                boolean l2 = MysqlBottomPane.getEntityRBtn().isSelected();
                boolean l3 = MysqlBottomPane.getDTORBtn().isSelected();
                boolean l4 = MysqlBottomPane.getServiceRBtn().isSelected();
                boolean l5 = MysqlBottomPane.getDAORBtn().isSelected();
                boolean l6 = MysqlBottomPane.getMapperRBtn().isSelected();
                GeneratorCheckBoxLabel label = new GeneratorCheckBoxLabel(l1, l2, l3, l4, l5, l6);

                String sourceType = (String) MysqlTopPane.getDatasourceType().getSelectedItem();
                String sourceIP = MysqlTopPane.getDatasourceIP().getText();
                String sourcePort = MysqlTopPane.getDatasourcePort().getText();
                String sourceName = MysqlTopPane.getDatasourceName().getText();
                String sourceUsername = MysqlTopPane.getDatasourceUsername().getText();
                String sourcePassword = MysqlTopPane.getDatasourcePassword().getText();

                String tableName = MysqlCenterPane.getTableNameField().getText();
                String authorName = MysqlCenterPane.getAuthorField().getText();
                String tableAnnotation = MysqlCenterPane.getTableAnnotationField().getText();
                String packageName = MysqlCenterPane.getPackageField().getText();
                String diskPath = MysqlCenterPane.getDiskPathField().getText();

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
                    CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils(info);
                    codeGenerateUtils.generate(label);
                    System.out.println("======Code Generator Success=======");
                } catch (Exception ex) {
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
        add((MysqlRightPane = new MysqlRightPane()), gbc);
        JButton clearBtn = MysqlRightPane.getClear();
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MysqlTopPane.getDatasourceIP().setText("");
                MysqlTopPane.getDatasourcePort().setText("");
                MysqlTopPane.getDatasourceName().setText("");
                MysqlTopPane.getDatasourceUsername().setText("");
                MysqlTopPane.getDatasourcePassword().setText("");

                MysqlCenterPane.getAuthorField().setText("");
                MysqlCenterPane.getTableNameField().setText("");
                MysqlCenterPane.getTableAnnotationField().setText("");
                MysqlCenterPane.getPackageField().setText("");
                MysqlCenterPane.getDiskPathField().setText("");
            }
        });
    }
}
