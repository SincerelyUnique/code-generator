
package com.helper.panel.tab_2;

import com.helper.util.PropertyUtils;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>
 * <code>ActionPane</code>
 * </p>
 * Description: 右侧button
 *
 * @author Mcchu
 * @date 2018/3/2 16:41
 */
public class OAActionPane extends JPanel {

    @Getter
    private JButton instruction, clear, help, version;

    private JLabel close;

    public OAActionPane() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        gbc.insets = new Insets(4, 4, 4, 4);

        // 说明按钮
        add((instruction = new JButton(PropertyUtils.getValue("btn.instruction",PropertyUtils.GENERATOR_PATH))), gbc);
        instruction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel jPanel = new JPanel();
                JOptionPane.showMessageDialog(jPanel, PropertyUtils.getValue("btn.instruction.content_tab2",
                        PropertyUtils.GENERATOR_PATH), "INSTRUCTION",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridy++;

        // 清空按钮
        add((clear = new JButton(PropertyUtils.getValue("btn.clear",PropertyUtils.GENERATOR_PATH))), gbc);
        gbc.gridy++;

        // 帮助按钮
        add((help = new JButton(PropertyUtils.getValue("btn.help",PropertyUtils.GENERATOR_PATH))), gbc);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel jPanel = new JPanel();
                JOptionPane.showMessageDialog(jPanel, PropertyUtils.getValue("btn.help.content",PropertyUtils.GENERATOR_PATH), "HELP",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridy++;

        // 版本按钮
        add((version = new JButton(PropertyUtils.getValue("btn.version",PropertyUtils.GENERATOR_PATH))), gbc);
        version.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JPanel jPanel = new JPanel();
                JOptionPane.showMessageDialog(jPanel, "Version 1.0.1", "VERSION",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        gbc.gridy++;

        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTH;

        // 关闭按钮
        add((close = new JLabel(" ")), gbc);
    }

}
