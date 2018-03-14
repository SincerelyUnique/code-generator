package com.helper.panel.tab_2;

import com.helper.model.GeneratorCheckBoxLabel;
import com.helper.model.GeneratorPageInfo;
import com.helper.util.DBUtils;
import com.helper.util.OACodeGenerateUtils;

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
public class OAPane extends JPanel {

    private OASourcePane sourcePane;
    private OADatabasePane databasePane;
    private OASystemDatabasePane systemDatabasePane;
    private OAActionPane actionPane;


    public OAPane( ) {
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
        add((sourcePane = new OASourcePane()), gbc);
        JButton testConnBtn = sourcePane.getTestConn();
        testConnBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 点击测试连接时，打印testConnLabel提示
                String sourceType = (String)sourcePane.getDatasourceType().getSelectedItem();
                String sourceIP = sourcePane.getDatasourceIP().getText();
                String sourcePort = sourcePane.getDatasourcePort().getText();
                String sourceName = sourcePane.getDatasourceName().getText();
                String sourceUsername = sourcePane.getDatasourceUsername().getText();
                String sourcePassword = sourcePane.getDatasourcePassword().getText();

                System.out.println(sourceType);
                Connection conn = DBUtils.getConnection(sourceType,sourceIP,sourcePort,sourceName,sourceUsername,
                        sourcePassword);
                if ( null != conn ){
                    sourcePane.getTestConnLabel().setText("      Success! ");
                    sourcePane.getTestConnLabel().setForeground(Color.blue);
                }else {
                    sourcePane.getTestConnLabel().setText("      Error! ");
                    sourcePane.getTestConnLabel().setForeground(Color.red);
                }
            }
        });
        gbc.gridy++;

        // 2. 数据库生成配置
        add((databasePane = new OADatabasePane()), gbc);
        gbc.gridy++;

        // 3. 生成配置
        add((systemDatabasePane = new OASystemDatabasePane()), gbc);
        JButton btn = systemDatabasePane.getSystemDatabase();
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 点击生成代码时，根据上面数据进行生成
                boolean l2 = systemDatabasePane.getEntityRBtn().isSelected();
                boolean l5 = systemDatabasePane.getDAORBtn().isSelected();
                GeneratorCheckBoxLabel label = new GeneratorCheckBoxLabel(false,l2,false,false,l5,false);

                String sourceType = (String)sourcePane.getDatasourceType().getSelectedItem();
                String sourceIP = sourcePane.getDatasourceIP().getText();
                String sourcePort = sourcePane.getDatasourcePort().getText();
                String sourceName = sourcePane.getDatasourceName().getText();
                String sourceUsername = sourcePane.getDatasourceUsername().getText();
                String sourcePassword = sourcePane.getDatasourcePassword().getText();

                String tableName = databasePane.getTableNameField().getText();
                String authorName = databasePane.getAuthorField().getText();
                String tableAnnotation = databasePane.getTableAnnotationField().getText();
                String packageName = databasePane.getPackageField().getText();
                String diskPath = databasePane.getDiskPathField().getText();
                String sql = databasePane.getSqlField().getText();

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
                    info.setSql(sql.toUpperCase());

                    System.out.println(info.toString());
                    OACodeGenerateUtils codeGenerateUtils = new OACodeGenerateUtils( info );
                    codeGenerateUtils.generate( label, tableName );
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
        add((actionPane = new OAActionPane()), gbc);
        JButton clearBtn = actionPane.getClear();
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sourcePane.getDatasourceIP().setText("");
                sourcePane.getDatasourcePort().setText("");
                sourcePane.getDatasourceName().setText("");
                sourcePane.getDatasourceUsername().setText("");
                sourcePane.getDatasourcePassword().setText("");

                databasePane.getAuthorField().setText("");
                databasePane.getTableNameField().setText("");
                databasePane.getTableAnnotationField().setText("");
                databasePane.getPackageField().setText("");
                databasePane.getDiskPathField().setText("");
                databasePane.getSqlField().setText("");
            }
        });
    }
}
