// (9) SongDetailDialog: makikita song deets and connects rating/feedback windows
/* song title, artist, album & its cover, ave rating
 * opens rating, ratings stats, feedback windows
 */
package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SongDetailDialog extends JDialog {
	
	private final TopBanner hero = new TopBanner();
	private final JTable tblLatest = new JTable();
	private Data.Song song;
	
	public SongDetailDialog() {
		setTitle(Theme.TITLE);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Theme.W, Theme.H + 40);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(Theme.BG);

		getContentPane().add(hero, BorderLayout.NORTH);

        JPanel pnlBody = new JPanel(new BorderLayout(0, 10));
        pnlBody.setBackground(Theme.BG);
        pnlBody.setBorder(new EmptyBorder(10, 20, 10, 20));
        getContentPane().add(pnlBody, BorderLayout.CENTER);

        JScrollPane scroll = new JScrollPane(tblLatest);
        Theme.styleScroll(scroll);
        pnlBody.add(scroll, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
        pnlButtons.setBackground(Theme.BG);
        getContentPane().add(pnlButtons, BorderLayout.SOUTH);
        						// opens RatingDialog
        JButton btnRate = new JButton("Rate & Review");
        btnRate.addActionListener(e -> {
        	RatingDialog d = new RatingDialog();
        	d.setSong(song);
        	d.setLocationRelativeTo(this);
        	d.setVisible(true);
        	refresh();
        });
        pnlButtons.add(btnRate);
        						// opens ViewRatingsDialog
        JButton btnRatings = new JButton("View Ratings");
        btnRatings.addActionListener(e -> {
        	ViewRatingDialog d = new ViewRatingDialog();
        	d.setSong(song);
        	d.setLocationRelativeTo(this);
        	d.setVisible(true);
        });
        pnlButtons.add(btnRatings);
        						// opens FeedbackDialog
        JButton btnFeedback = new JButton("View Reviews");
        btnFeedback.addActionListener(e -> {
        	FeedbackDialog d = new FeedbackDialog();
        	d.setSong(song);
        	d.setLocationRelativeTo(this);
        	d.setVisible(true);
        });
        pnlButtons.add(btnFeedback);
        						// sinasara/binabalikan prev window
        JButton btnBack = new JButton("Back");
        btnBack.addActionListener(e -> dispose());
        pnlButtons.add(btnBack);
	}
			// setSong(): kung aling song gusto makita
	public void setSong(Data.Song s) {
		this.song = s;
		refresh();
	}
	private void refresh() {
		if (song == null) return;
		String album = Data.album.get(song.artist);
		hero.content(Theme.img(Data.albumPic.get(song.artist), Theme.pxInch(Theme.ALBUM)),
				"Album: " + (album == null ? "-" : album), song.title, "by " + song.artist,
				"Average:  " + song.avgText());
		Theme.fillFeedback(tblLatest, song.comments, false, 6);
	}
}