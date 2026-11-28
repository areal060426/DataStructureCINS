// (5) Statistics: summarized statistics
// numerical values, labels o captions, bordered card design sa welcome page
// 'yung total artists & songs, ratings submitted & comments 'to

package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class Statistics extends JPanel {
	
	private final JLabel lblVal = new JLabel("0");
	private final JLabel lblCap = new JLabel("Caption");
	
	public Statistics() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(new LineBorder(Theme.LINE), new EmptyBorder(12, 8, 12, 8)));
		
		lblVal.setFont(Theme.font(Font.BOLD, 28));
		lblVal.setForeground(Theme.DEEP);
		lblVal.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblCap.setFont(Theme.font(Font.PLAIN, 13));
		lblCap.setForeground(Theme.MUTED);
		lblCap.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		add(lblVal);
		add(lblCap);
	}
	
	public void setVal(String v) {
		lblVal.setText(v);
	}
	public void setCap(String c) {
		lblCap.setText(c);
	}
}