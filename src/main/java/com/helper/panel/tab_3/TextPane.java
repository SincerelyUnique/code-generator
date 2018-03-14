
package com.helper.panel.tab_3;

import com.helper.util.PropertyUtils;
import com.helper.util.TextUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * <p>
 * <code>DatabasePropertiesPane</code>
 * </p>
 * Description:
 *
 * @author Mcchu
 * @date 2018/3/2 16:41
 */
public class TextPane extends JPanel {

    private TextTopPane topPane;
    private TextCenterPane centerPane;
    private TextBottomPane bottomPane;
    private TextRightPane rightPane;


    public TextPane( ) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.33;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(4, 4, 4, 4);

        // 1. 文本
        add((topPane = new TextTopPane()), gbc);
        gbc.gridy++;

        // 2. 文本
        add((centerPane = new TextCenterPane()), gbc);
        gbc.gridy++;

        // 3. 操作
        add((bottomPane = new TextBottomPane()), gbc);
        JButton upperToLowerBtn = bottomPane.getUpperToLowerBtn();
        upperToLowerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = topPane.getTextInputArea().getText();
                String deal = TextUtils.upperToLower(text);
                centerPane.getTextOutputArea().setText(deal);
            }
        });
        JButton lowerToUpperBtn = bottomPane.getLowerToUpperBtn();
        lowerToUpperBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = topPane.getTextInputArea().getText();
                String deal = TextUtils.lowerToUpper(text);
                centerPane.getTextOutputArea().setText(deal);
            }
        });
        JButton stringToUnicodeBtn = bottomPane.getStringToUnicodeBtn();
        stringToUnicodeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = topPane.getTextInputArea().getText();
                String deal = TextUtils.stringToUnicode(text);
                centerPane.getTextOutputArea().setText(deal);
            }
        });
        JButton unicodeToStringBtn = bottomPane.getUnicodeToStringBtn();
        unicodeToStringBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = topPane.getTextInputArea().getText();
                String deal = TextUtils.unicodeToString(text);
                centerPane.getTextOutputArea().setText(deal);
            }
        });
        JButton countByteBtn = bottomPane.getCountByteBtn();
        countByteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = topPane.getTextInputArea().getText();
                String deal = TextUtils.countByte(text);
                String englishLetterLength = String.valueOf(TextUtils.countLetter(text));
                String chineseLength = String.valueOf(TextUtils.countChinese(text));
                String spaceLength = String.valueOf(TextUtils.countSpace(text));

                // label
                String byteLen = PropertyUtils.getValue("content.byteLength",PropertyUtils.GENERATOR_PATH);
                String letterLen = PropertyUtils.getValue("content.letterLength",PropertyUtils.GENERATOR_PATH);
                String chineseLen =PropertyUtils.getValue("content.chineseLength",PropertyUtils.GENERATOR_PATH);
                String spaceLen = PropertyUtils.getValue("content.spaceLength",PropertyUtils.GENERATOR_PATH);
                centerPane.getTextOutputArea().setText( byteLen+deal+"\r\n"+letterLen+
                        englishLetterLength+"\r\n"+chineseLen+chineseLength+"\r\n"+spaceLen+
                        spaceLength);
            }
        });


        gbc.gridy = 0;
        gbc.gridx++;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.weighty = 1;
        gbc.weightx = 0;
        add((rightPane = new TextRightPane()), gbc);
        JButton clearBtn = rightPane.getClear();
        clearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                topPane.getTextInputArea().setText("");
                centerPane.getTextOutputArea().setText("");
            }
        });
    }
}
