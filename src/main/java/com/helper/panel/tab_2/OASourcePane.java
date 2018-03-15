
package com.helper.panel.tab_2;

import com.helper.util.PropertyUtils;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * <code>SourcePane</code>
 * </p>
 * Description: 左上配置
 *
 * @author Mcchu
 * @date 2018/3/2 16:39
 */
public class OASourcePane extends JPanel{

    @Getter
    private JTextField datasourceIP,datasourcePort,datasourceName;

    @Getter
    private JTextField datasourceUsername,datasourcePassword;

    @Getter
    private JButton testConn;

    @Getter
    @Setter
    private JLabel testConnLabel;

    @Getter
    private JComboBox datasourceType;

    @SuppressWarnings("unchecked")
    public OASourcePane() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;

        add(new JLabel(PropertyUtils.getValue("label.datasourceType",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(PropertyUtils.getValue("label.datasourceIP",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(PropertyUtils.getValue("label.datasourcePort",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(PropertyUtils.getValue("label.datasourceName",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(PropertyUtils.getValue("label.datasourceUsername",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(PropertyUtils.getValue("label.datasourcePassword",PropertyUtils.GENERATOR_PATH)), gbc);
        gbc.gridy++;
        add(new JLabel(" "), gbc);
        gbc.gridy++;
        add(testConn = new JButton(PropertyUtils.getValue("label.testConn",PropertyUtils.GENERATOR_PATH)), gbc);

        gbc.gridx++;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        datasourceType = new JComboBox();
        datasourceType.addItem("oracle");
        add(datasourceType, gbc);
        gbc.gridy++;
        add((datasourceIP = new JTextField("172.31.0.43",10)), gbc);
        gbc.gridy++;
        add((datasourcePort = new JTextField("1521",10)), gbc);
        gbc.gridy++;
        add((datasourceName = new JTextField("",10)), gbc);
        gbc.gridy++;
        add((datasourceUsername = new JTextField("",10)), gbc);
        gbc.gridy++;
        add((datasourcePassword = new JPasswordField("",10)), gbc);
        gbc.gridy++;
        add(new JLabel(" "), gbc);
        gbc.gridy++;
        add(testConnLabel = new JLabel(""), gbc);
    }

}
