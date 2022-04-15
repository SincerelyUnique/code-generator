
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
 * <code>DatabasePane</code>
 * </p>
 * Description: 左中配置
 *
 * @author Mcchu
 * @date 2018/3/2 16:40
 */
public class TextTopPane extends JPanel {

    @Getter
    private TextArea textInputArea;

    public TextTopPane() {
        setLayout(new GridBagLayout());
        setBorder(new CompoundBorder(new TitledBorder(PropertyUtils.getValue("label.input", PropertyUtils.GENERATOR_PATH)), new EmptyBorder(12, 0, 0, 0)));

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel(PropertyUtils.getValue("label.input.content", PropertyUtils.GENERATOR_PATH)), gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        add((textInputArea = new TextArea("", 10, 43, TextArea.SCROLLBARS_VERTICAL_ONLY)), gbc);

    }

}
