package main.java.com.qcm;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class WrappingJRadioButton extends JRadioButton {
    public WrappingJRadioButton(String text) {
        super(text);
    }

    @Override
    protected void paintComponent(Graphics g) {
        FontMetrics metrics = g.getFontMetrics(getFont());
        String text = getText();
        int width = getWidth();

        // Calculate wrapped lines
        String[] lines = wrapText(text, metrics, width);

        // Draw each line
        for (int i = 0; i < lines.length; i++) {
            int yOffset = metrics.getAscent() + i * metrics.getHeight();
            g.drawString(lines[i], 0, yOffset);
        }
    }

    private String[] wrapText(String text, FontMetrics metrics, int width) {
        StringBuilder wrappedText = new StringBuilder();
        String[] words = text.split(" ");
        int lineWidth = 0;

        for (String word : words) {
            int wordWidth = metrics.stringWidth(word);

            if (lineWidth + wordWidth <= width) {
                wrappedText.append(word).append(" ");
                lineWidth += wordWidth + metrics.stringWidth(" ");
            } else {
                wrappedText.append("\n").append(word).append(" ");
                lineWidth = wordWidth + metrics.stringWidth(" ");
            }
        }

        return wrappedText.toString().split("\n");
    }


}
