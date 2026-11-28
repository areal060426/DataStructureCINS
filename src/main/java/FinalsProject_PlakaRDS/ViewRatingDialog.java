// (7) ViewRatingDialog: rating statistics lang
// ave rate, sum of rate, num of rate, vote distri from 1-5 stars, percentage each rate
package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ViewRatingDialog extends JDialog {

	private final HeaderBar header = new HeaderBar();
	private final Statistics crdAvg = new Statistics();
	private final Statistics crdSor = new Statistics();
	private final Statistics crdNor = new Statistics();
	private final JTable table = new JTable(new Theme.Grid(new Object[0][],
			new String[]{"Rating", "Description", "Count", "Percentage"}));

	public ViewRatingDialog() {
		setTitle(Theme.TITLE);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Theme.W, Theme.H + 40);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(Theme.BG);

		header.setHead("Song");
		header.setSub("Ratings");
		getContentPane().add(header, BorderLayout.NORTH);

		JPanel pnlBody = new JPanel(new BorderLayout(0, 10));
		pnlBody.setBackground(Theme.BG);
		pnlBody.setBorder(new EmptyBorder(14, 20, 14, 20));
		getContentPane().add(pnlBody, BorderLayout.CENTER);

		JPanel pnlCards = new JPanel(new GridLayout(1, 3, 14, 0));
		pnlCards.setOpaque(false);
		crdAvg.setCap("Average Rating");
		crdSor.setCap("Sum of Ratings");
		crdNor.setCap("Number of Ratings");
		pnlCards.add(crdAvg);
		pnlCards.add(crdSor);
		pnlCards.add(crdNor);
		pnlBody.add(pnlCards, BorderLayout.NORTH);

		JPanel pnlMid = new JPanel(new BorderLayout(0, 10));
	pnlMid.setOpaque(false);
	pnlBody.add(pnlMid, BorderLayout.CENTER);

 JScrollPane scroll = new JScrollPane(table);
	Theme.styleScroll(scroll);
	pnlMid.add(scroll, BorderLayout.CENTER);

	JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
	pnlButtons.setBackground(Theme.BG);
	getContentPane().add(pnlButtons, BorderLayout.SOUTH);

	JButton btnBack = new JButton("Back");
	btnBack.addActionListener(e -> dispose());
	pnlButtons.add(btnBack);

	Theme.styleTable(table, new int[]{170, 370, 150, 170}, 
		new int[]{SwingConstants.LEFT, SwingConstants.LEFT, SwingConstants.CENTER, SwingConstants.CENTER}, 46);
	}

	public void setSong(Data.Song s) {
		header.setHead(s.title);
		header.setSub("Ratings  |  " + s.artist);
		crdAvg.setVal(s.numRate == 0 ? "-" : String.format("%.1f / 5", s.avg()));
		crdSor.setVal(String.valueOf(s.sumRate));
		crdNor.setVal(String.valueOf(s.numRate));

		Theme.Grid model = (Theme.Grid) table.getModel();
		model.setRowCount(0);
		for (int i = 5; i >= 1; i--) {
			String percentage = s.numRate == 0 ? "0%" : Math.round(100.0 * s.starCount[i] / s.numRate) + "%";
			model.addRow(new Object[]{Theme.stars(i), Theme.RATE_MEANING[i - 1], s.starCount[i], percentage});
		}
		table.clearSelection();
	}
}