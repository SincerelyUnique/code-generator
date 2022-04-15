
package com.helper.panel.tab_3;

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
public class TextBottomPane extends JPanel {

    @Getter
    private JButton upperToLowerBtn, lowerToUpperBtn;

    @Getter
    private JButton stringToUnicodeBtn, unicodeToStringBtn;

    @Getter
    private JButton countByteBtn;

    public TextBottomPane() {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder(PropertyUtils.getValue("label.operate", PropertyUtils.GENERATOR_PATH)), new EmptyBorder(8, 0, 0, 0)));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 0, 4);
        gbc.anchor = GridBagConstraints.WEST;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.add((upperToLowerBtn = new JButton(PropertyUtils.getValue("label.toLower", PropertyUtils.GENERATOR_PATH))), gbc);
        gbc.gridx++;
        panel.add((lowerToUpperBtn = new JButton(PropertyUtils.getValue("label.toUpper", PropertyUtils.GENERATOR_PATH))), gbc);
        gbc.gridx++;
        panel.add((stringToUnicodeBtn = new JButton(PropertyUtils.getValue("label.toUnicode", PropertyUtils.GENERATOR_PATH))), gbc);
        gbc.gridx++;
        panel.add((unicodeToStringBtn = new JButton(PropertyUtils.getValue("label.toString", PropertyUtils.GENERATOR_PATH))), gbc);
        gbc.gridx++;
        panel.add((countByteBtn = new JButton(PropertyUtils.getValue("label.counter", PropertyUtils.GENERATOR_PATH))), gbc);

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

    }
}
