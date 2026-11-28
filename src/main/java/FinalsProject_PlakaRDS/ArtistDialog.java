// (11) ArtistDialog: displays and searches artists
/* artist imgs & names & ave rates, album info
 * may search functionality and sorting opts
 * inoopen din SongListDialog
 */
package FinalsProject_PlakaRDS;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDialog extends JDialog {
	
	private final JComboBox<String> cmbSort = new JComboBox<>(Theme.SORTS);
	private final JTextField txtSearch = new JTextField(16);
	private final JTable table = new JTable(new Theme.Grid(new Object[0][], new String[] {
			" ", "Artist", "Album", "Rating"
	}));
	private final Theme.Grid model = (Theme.Grid) table.getModel();
	private final List<String> shown = new ArrayList<>();
	
	public ArtistDialog() {
		setTitle(Theme.TITLE);
		setModal(true);
		setResizable(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(Theme.W, Theme.H + 40);
		getContentPane().setLayout(new BorderLayout(9, 0));
		getContentPane().setBackground(Theme.BG);
		
		HeaderBar header = new HeaderBar();
		header.setHead("Artists");
		header.setSub("Select an artist to view their album and songs.");
		getContentPane().add(header, BorderLayout.NORTH);
		
		JPanel pnlBody = new JPanel(new BorderLayout(0, 10));
		pnlBody.setBackground(Theme.BG);
		pnlBody.setBorder(new EmptyBorder(14, 20, 14, 20));
		getContentPane().add(pnlBody, BorderLayout.CENTER);
		
		JPanel pnlControls = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
		pnlControls.setOpaque(false);
		pnlBody.add(pnlControls, BorderLayout.NORTH);
		
		JLabel lblSort = new JLabel("Sort by:");
		lblSort.setFont(Theme.font(Font.BOLD, 14));
		lblSort.setForeground(Theme.DEEP);
		pnlControls.add(lblSort);
		pnlControls.add(cmbSort);
		
		JLabel lblSearch = new JLabel("   Search artist:");
		lblSearch.setFont(Theme.font(Font.BOLD, 14));
		lblSearch.setForeground(Theme.DEEP);
		pnlControls.add(lblSearch);
		pnlControls.add(txtSearch);
		
		JScrollPane scroll = new JScrollPane(table);
		Theme.styleScroll(scroll);
		pnlBody.add(scroll, BorderLayout.CENTER);
		
		JPanel pnlButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 8));
		pnlButton.setBackground(Theme.BG);
		getContentPane().add(pnlButton, BorderLayout.SOUTH);
		
		JButton btnOpen = new JButton("View Artist");
		btnOpen.addActionListener(e -> openSelected());
		pnlButton.add(btnOpen);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(e -> dispose());
		pnlButton.add(btnBack);
		
		Theme.styleTable(table, new int[] {90, 260, 290, 220},
				new int[] {SwingConstants.CENTER, SwingConstants.LEFT, SwingConstants.LEFT, SwingConstants.LEFT}, 72);
		
		cmbSort.setSelectedIndex(Data.artistSort);
		cmbSort.addActionListener(e -> refresh());
		txtSearch.getDocument().addDocumentListener(new Theme.Change(this::refresh));
		Theme.doubleClick(table, this::open);
		refresh();
	}
	
	private void refresh() {
		Data.artistSort = cmbSort.getSelectedIndex();
		String q = txtSearch.getText().trim().toLowerCase();
		shown.clear();
		for (String a : Data.catalog.keySet())
			if (a.toLowerCase().contains(q))
				shown.add(a);
		shown.sort(Data.artistOrder(Data.artistSort));
		model.setRowCount(0);
		for (String a : shown) {
			String album = Data.album.get(a);
			model.addRow(new Object[] {Theme.img(Data.artistPic.get(a), 58), a,
					album == null ? "-" : album, Data.artistAvgText(a)
			});
		}
		if (!shown.isEmpty())
			table.setRowSelectionInterval(0, 0);
	}
	
	private void openSelected() {
		int row = table.getSelectedRow();
		if (row < 0) {
			Theme.info(this, "No artist selected.");
			return;
		}
		open(row);
	}
	private void open(int row) {
		SongListDialog d = new SongListDialog();
		d.setArtist(shown.get(row));
		d.setLocationRelativeTo(this);
		d.setVisible(true);
		refresh();
	}
}