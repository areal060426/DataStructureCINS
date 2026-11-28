// (12) TopRatedDialog: 'yung top-rated songs lang 'yan
/* sorts songs from highest ave to lowest
 * shows ranking, song title, artist, ave rate
 * may opt na iopen song details and comments
 */
package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TopRatedDialog extends JDialog {

	private final JTable table = new JTable(new Theme.Grid(new Object[0][],
			new String[]{"#", "Song", "Artist", "Rating"}));
	private final JLabel lblEmpty = new JLabel("No songs have been rated yet. Rate a song to see it here!");
	private final JButton btnOpen = new JButton("Song Details");
	private final JButton btnComments = new JButton("View Reviews");
	private List<Data.Song> top = new ArrayList<>();

	public TopRatedDialog() {
		setTitle(Theme.TITLE);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Theme.W, Theme.H + 40);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().setBackground(Theme.BG);

		HeaderBar header = new HeaderBar();
		header.setHead("Top-Rated Songs");
		header.setSub("Choose a song to view its details.");
		getContentPane().add(header, BorderLayout.NORTH);

		JPanel pnlBody = new JPanel(new BorderLayout(0, 10));
		pnlBody.setBackground(Theme.BG);
		pnlBody.setBorder(new EmptyBorder(14, 20, 14, 20));
		getContentPane().add(pnlBody, BorderLayout.CENTER);

		lblEmpty.setFont(Theme.font(Font.PLAIN, 18));
		lblEmpty.setForeground(Theme.MUTED);
		lblEmpty.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBody.add(lblEmpty, BorderLayout.NORTH);

		JScrollPane scroll = new JScrollPane(table);
		Theme.styleScroll(scroll);
		pnlBody.add(scroll, BorderLayout.CENTER);

		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		pnlButtons.setBackground(Theme.BG);
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);

		btnOpen.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (row < 0) {
				Theme.info(this, "No song selected."); 
				return; 
			}
			openSong(row);
		});
		pnlButtons.add(btnOpen);

		btnComments.addActionListener(e -> {
			int row = table.getSelectedRow();
			if (row < 0) { 
				Theme.info(this, "No song selected."); 
				return; 
			}

			FeedbackDialog d = new FeedbackDialog();
			d.setSong(top.get(row));
			d.setLocationRelativeTo(this);
			d.setVisible(true);
		});
		pnlButtons.add(btnComments);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> dispose());
		pnlButtons.add(btnBack);

		Theme.doubleClick(table, this::openSong);
		refresh();
	}

	private void refresh() { // Data.topRated(): kinukuha lang rated songs
		List<Data.Song> all = Data.topRated();
		top = new ArrayList<>(all.subList(0, Math.min(10, all.size())));
		Object[][] data = new Object[top.size()][];
		for (int i = 0; i < data.length; i++) {
			Data.Song s = top.get(i);
			data[i] = new Object[]{i + 1, s.title, s.artist, s.avgText()};
		}
		
		table.setModel(new Theme.Grid(data, new String[]{"#", "Song", "Artist", "Rating"}));
		Theme.styleTable(table, new int[]{50, 330, 270, 210},
				new int[]{SwingConstants.CENTER, SwingConstants.LEFT, SwingConstants.LEFT, SwingConstants.LEFT}, 46);

		lblEmpty.setVisible(top.isEmpty());
		btnOpen.setEnabled(!top.isEmpty());
		btnComments.setEnabled(!top.isEmpty());
	}

	private void openSong(int row) {
		SongDetailDialog d = new SongDetailDialog();
		d.setSong(top.get(row));
		d.setLocationRelativeTo(this);
		d.setVisible(true);
		refresh();
	}
}