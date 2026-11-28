// (8) FeedbackDialog: show user comments
/* show comments & corresponding star ratings
 * sort comments from newest to oldest
 * numbering sa feedback list
 */
package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FeedbackDialog extends JDialog {

	private final HeaderBar header = new HeaderBar();
	private final JTable table = new JTable();

	public FeedbackDialog() {
		setTitle(Theme.TITLE);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Theme.W, Theme.H + 40);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(Theme.BG);

		header.setHead("Song");
		header.setSub("User Review");
		getContentPane().add(header, BorderLayout.NORTH);

		JPanel pnlBody = new JPanel(new BorderLayout(0, 10));
		pnlBody.setBackground(Theme.BG);
		pnlBody.setBorder(new EmptyBorder(14, 20, 14, 20));
		getContentPane().add(pnlBody, BorderLayout.CENTER);

		JScrollPane scroll = new JScrollPane(table);
		Theme.styleScroll(scroll);
		pnlBody.add(scroll, BorderLayout.CENTER);

		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		pnlButtons.setBackground(Theme.BG);
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> dispose());
		pnlButtons.add(btnBack);
	}

	public void setSong(Data.Song s) {
	header.setHead(s.title);
	header.setSub("User Review  |  " + s.artist + "  |  " + s.comments.size() + " review(s)");
	Theme.fillFeedback(table, s.comments, true, Integer.MAX_VALUE);
	}
}