// (3) HeaderBar: header component
// heading title, subtitle, background, header design

package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Component;

public class HeaderBar extends JPanel {
	
	private final JLabel heading = new JLabel("Heading");
	private final JLabel sub = new JLabel("Subheading");
	
	public HeaderBar() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBackground(Theme.DEEP);
		setBorder(new EmptyBorder(16, 24, 16, 24));
		heading.setFont(Theme.font(Font.BOLD, 26));
		heading.setForeground(java.awt.Color.WHITE);
		heading.setAlignmentX(Component.LEFT_ALIGNMENT);
		sub.setFont(Theme.font(Font.PLAIN, 14));
		sub.setForeground(Theme.SKY);
		sub.setAlignmentX(Component.LEFT_ALIGNMENT);
		add(heading);
		add(Box.createVerticalStrut(4));
		add(sub);
	}
			// setHead(): change ng heading
	public void setHead(String s) {
		heading.setText(s);
	}
			// setSub(): change ng subtitle
	public void setSub(String s) {
		sub.setText(s);
	}
}