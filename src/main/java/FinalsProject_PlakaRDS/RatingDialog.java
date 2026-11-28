// (6) RatingDialog: para p'wede mag-rate and comment user
/* five rating options
 * para p'wede maglagay ng comments
 * ina-update song rating info
 * save rating & comment sa "ratings.txt"
 */

package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RatingDialog extends JDialog{

	private final HeaderBar header = new HeaderBar();
	private final JRadioButton rb1 = new JRadioButton(Theme.stars(1) + "     " + Theme.RATE_MEANING[0]);
	private final JRadioButton rb2 = new JRadioButton(Theme.stars(2) + "     " + Theme.RATE_MEANING[1]);
	private final JRadioButton rb3 = new JRadioButton(Theme.stars(3) + "     " + Theme.RATE_MEANING[2]);
	private final JRadioButton rb4 = new JRadioButton(Theme.stars(4) + "     " + Theme.RATE_MEANING[3]);
	private final JRadioButton rb5 = new JRadioButton(Theme.stars(5) + "     " + Theme.RATE_MEANING[4]);
	private final JTextArea txtComment = new JTextArea(5, 30);
	private Data.Song song;

	public RatingDialog() {
		setTitle(Theme.TITLE);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Theme.W, Theme.H + 40);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(Theme.BG);

		header.setHead("Rate this song");
		header.setSub("Song by Artist");
		getContentPane().add(header, BorderLayout.NORTH);

		JPanel pnlBody = new JPanel(new BorderLayout(0, 10));
		pnlBody.setBackground(Theme.BG);
		pnlBody.setBorder(new EmptyBorder(14, 20, 14, 20));
		getContentPane().add(pnlBody, BorderLayout.CENTER);

		JPanel pnlStars = new JPanel(new GridLayout(5, 1, 0, 6));
		pnlStars.setOpaque(false);
		pnlStars.setBorder(Theme.titled("Rating (required)"));
		pnlBody.add(pnlStars, BorderLayout.NORTH);

		ButtonGroup group = new ButtonGroup();
		JRadioButton[] all = {rb1, rb2, rb3, rb4, rb5};
		for (JRadioButton rb : all) {
			rb.setFont(Theme.font(Font.PLAIN, 16));
			rb.setOpaque(false);
			group.add(rb);
			pnlStars.add(rb);
		}

		txtComment.setFont(Theme.font(Font.PLAIN, 15));
		txtComment.setLineWrap(true);	
		txtComment.setWrapStyleWord(true);
		txtComment.setBorder(new EmptyBorder(6, 8, 6, 8));

		JScrollPane scrollComment = new JScrollPane(txtComment);
		scrollComment.setBorder(Theme.titled("Review (optional)"));
		scrollComment.setOpaque(false);
		scrollComment.getViewport().setOpaque(false);
		pnlBody.add(scrollComment, BorderLayout.CENTER);

		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		pnlButtons.setBackground(Theme.BG);
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(e -> submit());
		pnlButtons.add(btnSubmit);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(e -> dispose());
		pnlButtons.add(btnCancel);
	}

	public void setSong(Data.Song s) {
		this.song = s;
		header.setSub(s.title + "   by   " + s.artist);
	}

	private void submit() {
		JRadioButton[] all = {rb1, rb2, rb3, rb4, rb5};
		int given = 0;
		for (int i = 0; i < 5; i++) 
			if (all[i].isSelected()) given = i + 1;
		if (given == 0) {
			Theme.info(this, "Please choose a star rating first.");
			return;
        }
        String text = txtComment.getText().trim();
        song.addRate(given);
        if (!text.isEmpty()) 
        	song.comments.add(new Data.Review(given, text));
        Data.appendRating(song, given, text);
        Theme.info(this, "Thanks! Your rating was saved.\n\nNew average for \"" + song.title + "\":\n" + song.avgText());
        dispose();
    }
}