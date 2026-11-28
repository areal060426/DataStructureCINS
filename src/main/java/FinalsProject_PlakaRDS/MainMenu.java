// GROUP MEMBERS:
// MACALLA, ALEXANDRA JANE
// ORILLE, LEANN LOUISE
// INOY, JOHN BERNARD
// PAGULAYAN, NORIELLE STACEY

package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class MainMenu extends JFrame {
	
	private final Statistics crdArtists = new Statistics();
	private final Statistics crdSongs = new Statistics();
	private final Statistics crdRatings = new Statistics();
	private final Statistics crdComments = new Statistics();
					// main(): entry point ni java prog, alam mo na yan
	public static void main(String[] args) {
		Theme.btn();
		Data.loadCatalog();
		if (Data.catalog.isEmpty()) {
			Theme.info(null, "No artists found in " + Data.CATALOG + ".\n\nLooking here:\n"
					+ java.nio.file.Paths.get(Data.CATALOG).toAbsolutePath()
					+ "\n\nPut catalog.txt in the project root.");
			return;
		}
		Data.loadRatings();
		EventQueue.invokeLater(() -> {
			try {
				new MainMenu().setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainMenu() {
		setTitle(Theme.TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Theme.W, Theme.H + 40);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel topBanner = new JPanel();
		topBanner.setBackground(Color.WHITE);
		topBanner.setBorder(new EmptyBorder(20, 30, 20, 30));
		topBanner.setLayout(new BoxLayout(topBanner, BoxLayout.Y_AXIS));
		getContentPane().add(topBanner, BorderLayout.CENTER);
		
		JLabel lblLogo = new JLabel("PlakaRDS");
		lblLogo.setFont(Theme.font(Font.BOLD, 56));
		lblLogo.setForeground(Theme.DEEP);
		lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
		Icon logo = Theme.logoIcon(600);
		if (logo != null) {
			lblLogo.setText("");
			lblLogo.setIcon(logo);
		}
		
		JLabel lblSubtitle = new JLabel("Music Rating and Discovery System");
		lblSubtitle.setFont(Theme.font(Font.BOLD, 24));
		lblSubtitle.setForeground(Theme.DEEP);
		lblSubtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel lblDesc = new JLabel("Explore a collection of Original Pilipino Music (OPM) artists and their popular songs.");
		lblDesc.setFont(Theme.font(Font.PLAIN, 15));
		lblDesc.setForeground(Theme.MUTED);
		lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		topBanner.add(Box.createVerticalGlue());
		topBanner.add(lblLogo);
		topBanner.add(Box.createVerticalStrut(24));
		topBanner.add(lblSubtitle);
		topBanner.add(Box.createVerticalStrut(12));
		topBanner.add(lblDesc);
		topBanner.add(Box.createVerticalGlue());
		
		JPanel south = new JPanel(new BorderLayout(0, 0));
		south.setBackground(Theme.BG);
		getContentPane().add(south, BorderLayout.SOUTH);
		
		JPanel stats = new JPanel(new GridLayout(1, 4, 14, 0));
		stats.setOpaque(false);
		stats.setBorder(new EmptyBorder(22, 24, 12, 24));
		crdArtists.setCap("Artists");
		crdSongs.setCap("Songs");
		crdRatings.setCap("Ratings");
		crdComments.setCap("Reviews");
		
		stats.add(crdArtists);
		stats.add(crdSongs);
		stats.add(crdRatings);
		stats.add(crdComments);
		south.add(stats, BorderLayout.CENTER);
		
		JPanel pnlBtn = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
		pnlBtn.setOpaque(false);
		south.add(pnlBtn, BorderLayout.SOUTH);
									// opens ArtistDialog
		JButton btnBrowse = new JButton("Browse Artists");
		btnBrowse.addActionListener(e -> {
			ArtistDialog d = new ArtistDialog();
			d.setLocationRelativeTo(this);
			d.setVisible(true);
			refreshStats(); // refreshStats(): ina-update lang stats cards
		});
		pnlBtn.add(btnBrowse);
								// opens TopRatedDialog
		JButton btnTop = new JButton("Top-Rated Songs");
		btnTop.addActionListener(e -> {
			TopRatedDialog d = new TopRatedDialog();
			d.setLocationRelativeTo(this);
			d.setVisible(true);
			refreshStats();
		});
		pnlBtn.add(btnTop);
								// closes the app
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(e -> dispose());
		pnlBtn.add(btnExit);
		
		refreshStats();
	}
	
	private void refreshStats() {
		int songs = 0, ratings = 0, comments = 0;
		for (List<Data.Song> l : Data.catalog.values())
			for (Data.Song s : l) {
				songs++; 
				ratings += s.numRate;
				comments += s.comments.size();
			}
		crdArtists.setVal(String.valueOf(Data.catalog.size()));
		crdSongs.setVal(String.valueOf(songs));
		crdRatings.setVal(String.valueOf(ratings));
		crdComments.setVal(String.valueOf(comments));
	}
}