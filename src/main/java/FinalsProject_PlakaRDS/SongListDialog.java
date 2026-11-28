// (10) SongListDialog: display artist's songs
/* songs ng artist, ave rate each songs, search function, sorting opts
 * nagoopen ng selected song deets
 */
package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SongListDialog extends JDialog {
	
	private final TopBanner hero = new TopBanner();
	private final JComboBox<String> cmbSort = new JComboBox<>(Theme.SORTS);
	private final JTextField txtSearch = new JTextField(16);
	private final JTable table = new JTable(new Theme.Grid(new Object[0][], new String[] {"Song", "Rating"}));
	private final Theme.Grid model = (Theme.Grid) table.getModel();
	private final List<Data.Song> shown = new ArrayList<>();
	private String artist;
	
	public SongListDialog() {
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

		JPanel pnlControls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
		pnlControls.setOpaque(false);
		pnlBody.add(pnlControls, BorderLayout.NORTH);

		JLabel lblSort = new JLabel("Sort by:");
		lblSort.setFont(Theme.font(Font.BOLD, 14));
		lblSort.setForeground(Theme.DEEP);
		pnlControls.add(lblSort);
		pnlControls.add(cmbSort);

		JLabel lblSearch = new JLabel("   Search song:");
		lblSearch.setFont(Theme.font(Font.BOLD, 14));
		lblSearch.setForeground(Theme.DEEP);
		pnlControls.add(lblSearch);
		pnlControls.add(txtSearch);

		JScrollPane scroll = new JScrollPane(table);
		Theme.styleScroll(scroll);
		pnlBody.add(scroll, BorderLayout.CENTER);

		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		pnlButtons.setBackground(Theme.BG);
		getContentPane().add(pnlButtons, BorderLayout.SOUTH);

		JButton btnOpen = new JButton("Song Details");
		btnOpen.addActionListener(e -> openSelected());
		pnlButtons.add(btnOpen);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> dispose());
		pnlButtons.add(btnBack);

        Theme.styleTable(table, new int[]{520, 340},
        		new int[]{SwingConstants.LEFT, SwingConstants.LEFT}, 28);

        cmbSort.setSelectedIndex(Data.songSort);
        cmbSort.addActionListener(e -> refresh());
        txtSearch.getDocument().addDocumentListener(new Theme.Change(this::refresh));
        Theme.doubleClick(table, this::open);
	}
			// setArtist(): kung aling artist gusto makita
	public void setArtist(String artist) {
		this.artist = artist;
		refresh();
	}
			// refresh(): ina-update song list depends sa searching or sorting
	private void refresh() {
		if (artist == null) 
			return;
		String album = Data.album.get(artist);
		hero.content(Theme.img(Data.albumPic.get(artist), Theme.pxInch(Theme.ALBUM)),
				"Album: " + (album == null ? "-" : album), artist, null,
				"Artist rating:  " + Data.artistAvgText(artist));

		Data.songSort = cmbSort.getSelectedIndex();
		String q = txtSearch.getText().trim().toLowerCase();
		shown.clear();
		for (Data.Song s : Data.catalog.get(artist)) 
			if (s.title.toLowerCase().contains(q)) 
				shown.add(s);
		shown.sort(Data.songOrder(Data.songSort));
		model.setRowCount(0);
		for (Data.Song s : shown) 
			model.addRow(new Object[]{s.title, s.avgText()});
		if (!shown.isEmpty()) 
			table.setRowSelectionInterval(0, 0);
	}
	
	private void openSelected() {
		int row = table.getSelectedRow();
		if (row < 0) {
			Theme.info(this, "No song selected.");
			return;
		}
		open(row);
	}
	
	private void open(int row) {
		SongDetailDialog d = new SongDetailDialog();
		d.setSong(shown.get(row));
		d.setLocationRelativeTo(this);
		d.setVisible(true);
		refresh();
	}
}