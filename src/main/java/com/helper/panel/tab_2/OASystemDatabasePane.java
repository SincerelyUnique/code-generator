
package com.helper.panel.tab_2;

import com.helper.util.PropertyUtils;
import lombok.Getter;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * <p>
 * <code>SystemDatabasePane</code>
 * </p>
 * Description: 左下
 *
 * @author Mcchu
 * @date 2018/3/2 16:40
 */
public class OASystemDatabasePane extends JPanel {

    @Getter
    private JCheckBox entityRBtn,DAORBtn;

    @Getter
    private JButton systemDatabase;

    public OASystemDatabasePane() {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder(PropertyUtils.getValue("label.generatorType",PropertyUtils.GENERATOR_PATH)), new EmptyBorder(8, 0, 0, 0)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 4);
        gbc.anchor = GridBagConstraints.WEST;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.add((entityRBtn = new JCheckBox("Entity")), gbc);
        gbc.gridy++;

        panel.add((DAORBtn = new JCheckBox("DAO")), gbc);
        gbc.gridy++;

        panel.add((new JLabel(" ")), gbc);
        gbc.gridy++;
        panel.add((new JLabel(" ")), gbc);
        gbc.gridy++;
        panel.add((new JLabel(" ")), gbc);
        gbc.gridy++;
        panel.add((new JLabel(" ")), gbc);
        gbc.gridy++;
        panel.add((new JLabel(" ")), gbc);
        gbc.gridy++;

        gbc.gridx++;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(new JLabel(""), gbc);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(4, 4, 4, 4);
        add(panel, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        add((systemDatabase = new JButton(PropertyUtils.getValue("btn.generator",PropertyUtils.GENERATOR_PATH))), gbc);
        systemDatabase.setEnabled(true);
    }
}
