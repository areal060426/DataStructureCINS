// (4) TopBanner: display ng album cover, artist, song, rating
// album cover & name, song & artist name, description & subtitle, ratings

package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

public class TopBanner extends JPanel {
	
	private final JLabel lblPhoto = new JLabel();
	private final JLabel lblAlbum = new JLabel();
	private final JLabel lblTitle = new JLabel();
	private final JLabel lblSub = new JLabel();
	private final JLabel lblRating = new JLabel();
	
	public TopBanner() {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Theme.DEEP);
		setBorder(new EmptyBorder(10, 20, 10, 20));
		
		lblPhoto.setBorder(new LineBorder(Color.WHITE, 3));
		lblAlbum.setFont(Theme.font(Font.PLAIN, 14));
		lblAlbum.setForeground(Theme.SKY);
		lblTitle.setFont(Theme.font(Font.BOLD, 24));
		lblTitle.setForeground(Color.WHITE);
		lblSub.setFont(Theme.font(Font.PLAIN, 16));
		lblSub.setForeground(new Color(232, 243, 249));
		lblRating.setFont(Theme.font(Font.BOLD, 15));
		lblRating.setForeground(Theme.SKY);
		
		for (JLabel l : new JLabel[] {
				lblPhoto, lblAlbum, lblTitle, lblSub, lblRating
		});
		
		add(lblPhoto);
		add(Box.createVerticalStrut(4));
		add(lblAlbum);
		add(Box.createVerticalStrut(4));
		add(lblTitle);
		add(lblSub);
		add(Box.createVerticalStrut(2));
		add(lblRating);
	}
			// content(): ina-update and img, album info, title & subtitle, rating
	public void content(Icon album, String albumLn, String title, String sub, String rating) {
		lblPhoto.setIcon(album);
		lblAlbum.setText(albumLn);
		lblTitle.setText(title);
		lblSub.setText(sub == null ? "" : sub);
		lblSub.setVisible(sub != null);
		lblRating.setText(rating);
	}
}