
package com.helper.panel.tab_1;

import com.helper.util.PropertyUtils;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * <p>
 * <code>DatabasePane</code>
 * </p>
 * Description: 左中配置
 *
 * @author Mcchu
 * @date 2018/3/2 16:40
 */
public class MysqlCenterPane extends JPanel {

    @Getter
    private JTextField tableNameField, authorField, tableAnnotationField, packageField, diskPathField;

    public MysqlCenterPane() {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder(PropertyUtils.getValue("label.generatorConfig",PropertyUtils.GENERATOR_PATH)), new EmptyBorder(12, 0, 0, 0)));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel(PropertyUtils.getValue("label.tableNameField",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(PropertyUtils.getValue("label.authorField",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(PropertyUtils.getValue("label.tableAnnotationField",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(PropertyUtils.getValue("label.packageField",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(PropertyUtils.getValue("label.diskPathField",PropertyUtils.GENERATOR_PATH)), gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add((tableNameField = new JTextField("user",10)), gbc);
        tableNameField.setFont(new Font("标楷体",Font.TRUETYPE_FONT|Font.ITALIC,12));

        gbc.gridy++;
        add((authorField = new JTextField("mcchu",10)), gbc);
        authorField.setFont(new Font("标楷体",Font.TRUETYPE_FONT|Font.ITALIC,12));

        gbc.gridy++;
        add((tableAnnotationField = new JTextField("the table description",10)), gbc);
        tableAnnotationField.setFont(new Font("标楷体",Font.TRUETYPE_FONT|Font.ITALIC,12));

        gbc.gridy++;
        add((packageField = new JTextField("com.demo",10)), gbc);
        packageField.setFont(new Font("标楷体",Font.TRUETYPE_FONT|Font.ITALIC,12));

        gbc.gridy++;
        add(diskPathField = new JTextField("D://",10), gbc);
        diskPathField.setFont(new Font("标楷体",Font.TRUETYPE_FONT|Font.ITALIC,12));
    }

}
