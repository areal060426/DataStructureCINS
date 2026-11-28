// (1) THEME: colors, fonts, images, table styles
/* set ng colors, fonts, and overall theme
 * load ng artist imgs, album covers, logo
 * create ng star ratings
 * set ng table appearance, fonts, row heigh
 * nalagay din placehoolder kapag wala pa images na uploaded sa "covers" folder
 */

package FinalsProject_PlakaRDS;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import java.util.List;
import java.util.function.IntConsumer;

public class Theme {
	
	public static final String TITLE = "PlakaRDS";
	public static final String CATALOG = "catalog.txt";
	public static final String RATINGS = "ratings.txt";
	public static final String LOGO = "PlakaRDSLogo.png";
	public static final int W = 900, H = 640; // consistent window size yan sa lahat ocd kicking in
	public static final double ALBUM = 2.0; // album photo size inches
	public static final double ARTIST = 10; // artist photo size millimeter
	public static final String STAR = "\u2605", STAR_EMPTY = "\u2606";
	public static final String[] SORTS = {"A-Z", "Highest rated", "Lowest rated"};
	public static final String[] RATE_MEANING = {
			"I don't like it.", "It's not for me.", "It's okay.", "I like it.", "I absolutely love it."
			};
	
	public static final Color DEEP = new Color(31, 90, 122);
	public static final Color SKY = new Color(147, 199, 221);
	public static final Color BG = new Color(240, 247, 251);
	public static final Color ZEBRA = new Color(227, 240, 248);
	public static final Color SELECT = new Color(178, 217, 236);
	public static final Color TEXT = new Color(24, 48, 64);
	public static final Color MUTED = new Color(90, 122, 142);
	public static final Color LINE = new Color(176, 204, 220);
	public static final String FONT = pickFont();

	static final Map<String, Icon> iconCache = new HashMap<>();
	
	static String pickFont() {
		Set<String> have = new HashSet<>(Arrays.asList( // nasa isang set fonts and naka-list yung array
				GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) // kinukuha available fonts
				);
		for (String n : new String[] {"Segoe UI Symbol", "Helvetica Neue", "DejaVu Sans", "Arial Unicode MS"}) { // iteration: 1, 2, 3, 4
			if (!have.contains(n)) // pag wala sa set yung font nisskip tapos lilipat sa next
				continue;
				Font f = new Font(n, Font.PLAIN, 14);
			if (f.canDisplay('\u2605') && f.canDisplay('\u2606')) // if kaya ma-display yung stars
				return n;
		}
		return Font.DIALOG;	
	}
			// font(): font configurationg lang
	public static Font font(int style, int size) {
		return new Font(FONT, style, size);
	}
	
	public static void btn() {
		try {		// LookAndFeel: apply lang ng system appearance sa Swing
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e) {
		}
		UIManager.put("Button.font", font(Font.BOLD, 14));
		UIManager.put("OptionPane.buttonFont", font(Font.BOLD, 14));
		UIManager.put("ComboBox.font", font(Font.PLAIN, 14));
		UIManager.put("TextField.font", font(Font.PLAIN, 14));
		UIManager.put("RadioButton.font", font(Font.PLAIN, 16));
		UIManager.put("Label.font", font(Font.PLAIN, 14));
	}
				// stars(): shows star rating
	public static String stars(int n) {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= 5; i++)
			sb.append(i <= n ? STAR : STAR_EMPTY); // decides if filled star or empty ang idadagdag
		return sb.toString();
	}
	
	public static void info(Component parent, String msg) {
		JOptionPane.showMessageDialog(parent, msg, TITLE, JOptionPane.PLAIN_MESSAGE);
	}
	
	public static int pxInch(double inches) {
		return (int) Math.round(inches * Toolkit.getDefaultToolkit().getScreenResolution());
	}
	
	public static Border titled(String text) {
		TitledBorder tb = BorderFactory.createTitledBorder(new LineBorder(LINE), " " + text + " ");
		tb.setTitleFont(font(Font.BOLD, 14));
		tb.setTitleColor(DEEP);
		return BorderFactory.createCompoundBorder(tb, new EmptyBorder(6, 12, 8, 12));
	}
	
	public static class Change implements DocumentListener {
		final Runnable r;
		public Change(Runnable r) {
			this.r = r;
		}
		@Override public void insertUpdate(DocumentEvent e) {
			r.run();
		}
		@Override public void removeUpdate(DocumentEvent e) {
			r.run();
		}
		@Override public void changedUpdate(DocumentEvent e) {
			r.run();
		}
	}
		
		public static Icon img(String file, int size) {
			String key = file + "@" + size; //filename@size para may identifier
			Icon ic = iconCache.get(key); // loading ng img lng yan
			if (ic == null)	{
				ic = loadImg(file, size);
				iconCache.put(key, ic);
				/* pag null tatawagin ang loadImg para i-load tapos ilalagay result sa cache, pag may img na sa cache, di na tatawagin loadImg
				 * pag ulit-ulit inoopen ni user artist list, di na need iload ulit sa file kung naka-cache na*/
			}
			return ic;
		}
				// loadImg(): load and adjust ng imgs
		static Icon loadImg(String f, int size) {
			if (f != null && !f.isEmpty()) {
				File file = new File("covers", f);
				if (!file.exists()) 
					file = new File(f);
				if (file.exists()) {
					try {	// ginagawang BufferedImage para ma-process ni java
						BufferedImage bi = ImageIO.read(file); 
						if (bi != null) {
							int s = Math.min(bi.getWidth(), bi.getHeight()); // kinukuha square proportion ng img
							BufferedImage sq = bi.getSubimage((bi.getWidth() - s) / 2, (bi.getHeight() - s) / 2, s, s); // getSub: portion ng original img
							return new ImageIcon(sq.getScaledInstance(size, size, Image.SCALE_SMOOTH)); // getScaled: binabago dimensions
						}
					} catch (Exception e) {
						}
					}
				}
			// ito yung sa placeholder kapag wala pa img
			BufferedImage ph = new BufferedImage(size, size, BufferedImage.TYPE_INT_RGB); // blank img
			Graphics2D g = ph.createGraphics();
			g.setColor(SKY);
			g.fillRect(0, 0, size, size); // fill sky blue yung img
			g.setColor(DEEP); // color to ng music note
			g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Math.max(10, size / 3)));
			FontMetrics fm = g.getFontMetrics();
			String note = "\u266A"; // music note symbol yan
			g.drawString(note, (size - fm.stringWidth(note)) / 2, (size + fm.getAscent() - fm.getDescent()) / 2);
			g.dispose();
			return new ImageIcon(ph);
		}
						// sa pic ng logo lang to
		public static Icon logoIcon(int width) {
			File f = new File("covers", LOGO);
			if (!f.exists()) 
				f = new File(LOGO);
			if (!f.exists()) 
				return null;
			try {
				BufferedImage bi = ImageIO.read(f);
				if (bi == null) return null;
				int h = Math.max(1, (int) Math.round((double) width * bi.getHeight() / bi.getWidth()));
				return new ImageIcon(bi.getScaledInstance(width, h, Image.SCALE_SMOOTH));
			} catch (Exception e) { 
				return null; 
			}
		}
	    
		public static class Grid extends DefaultTableModel {
			public Grid(Object[][] data, String[] cols) { 
				super(data, cols); 
			}
			@Override public boolean isCellEditable(int r, int c) { 
				return false; 
			}
		}
	    
		public static class Cell extends DefaultTableCellRenderer {
			final int align;
	    	
			public Cell(int align) {
				this.align = align;
			}
			@Override public Component getTableCellRendererComponent(JTable t, Object v, boolean sel, 
					boolean foc, int row, int col) {
				super.getTableCellRendererComponent(t, v instanceof Icon ? "" : v, sel, false, row, col);
				setIcon(v instanceof Icon ? (Icon) v : null);
				setHorizontalAlignment(v instanceof Icon ? SwingConstants.CENTER : align);
				setFont(font(Font.PLAIN, 15));
				setForeground(TEXT);
				setBackground(sel ? SELECT : (row % 2 == 0 ? Color.WHITE : ZEBRA));
				setBorder(new EmptyBorder(2, 12, 2, 12));
				return this;
			}
		}
	    
		public static class Wrap extends JTextArea implements TableCellRenderer {
			public Wrap() {
				setLineWrap(true);
				setWrapStyleWord(true);
				setOpaque(true);
				setFont(font(Font.PLAIN, 15));
				setBorder(new EmptyBorder(6, 12, 6, 12));
			}
			@Override public Component getTableCellRendererComponent(JTable t, Object v, boolean sel, 
					boolean foc, int row, int col) {
				setText(v == null ? "" : v.toString());
				setForeground(TEXT);
				setBackground(sel ? SELECT : (row % 2 == 0 ? Color.WHITE : ZEBRA));
				return this;
			}
		}
	    				// styleTable(): style lang ng tables
		public static void styleTable (JTable t, int[] widths, int[] align, int rowH) {
			t.setRowHeight(rowH);
			t.setShowGrid(false);
			t.setIntercellSpacing(new Dimension(0, 0));
			t.setFillsViewportHeight(true);
			t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			t.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
			
			for (int i = 0; i < t.getColumnCount(); i++) {
				t.getColumnModel().getColumn(i).setPreferredWidth(widths[i]);
				t.getColumnModel().getColumn(i).setCellRenderer(new Cell(align[i]));
			}
			
			JTableHeader h = t.getTableHeader();
			h.setReorderingAllowed(false);
			h.setResizingAllowed(false);
			h.setPreferredSize(new Dimension(0, 36));
			h.setDefaultRenderer(new DefaultTableCellRenderer() {
				@Override public Component getTableCellRendererComponent(JTable tb, Object v, boolean sel, boolean foc, int row, int col) {
					super.getTableCellRendererComponent(tb, v, false, false, row, col);
					setBackground(DEEP);
					setForeground(Color.WHITE);
					setFont(font(Font.BOLD, 14));
					setHorizontalAlignment(align[col] == SwingConstants.CENTER ? SwingConstants.CENTER : SwingConstants.LEFT);
					setBorder(new EmptyBorder(0, 12, 0, 12));
					return this;
				}
			});
			if (t.getRowCount() > 0) t.setRowSelectionInterval(0, 0);
		}

		public static void styleScroll(JScrollPane sp) {
			sp.setBorder(new LineBorder(LINE));
			sp.getViewport().setBackground(Color.WHITE);
		}
		
		public static void doubleClick(JTable t, IntConsumer action) {
			t.addMouseListener(new MouseAdapter() {
				@Override public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						int r = t.rowAtPoint(e.getPoint());
						if (r >= 0) action.accept(r);
					}
				}
			});
		}
		
		static void rows(JTable t, int col, int width) {
			Wrap w = new Wrap();
			Insets in = w.getInsets();
			for (int r = 0; r < t.getRowCount(); r++) {
				w.setSize(width, 10);
				
				View v = w.getUI().getRootView(w);
				v.setSize(width - in.left - in.right, 0);
				int h = (int) Math.ceil(v.getPreferredSpan(View.Y_AXIS)) + in.top + in.bottom;
				t.setRowHeight(r, Math.max(36, h));
			}
		}
						// fillFeedback(): shows comments sa table
		public static void fillFeedback(JTable t, List<Data.Review> list, boolean numbered, int max) {
			List<Object[]> rows = new ArrayList<>();
			int n = 0;
			for (int i = list.size() - 1; i >= 0 && n < max; i--, n++) {
				Data.Review r = list.get(i);
				rows.add(numbered ? new Object[]{n + 1, stars(r.stars), r.text} : new Object[]{stars(r.stars), r.text});
			}
			if (rows.isEmpty()) {
				String none = "No comments yet. Be the first to share your thoughts!";
				rows.add(numbered ? new Object[]{"", "", none} : new Object[]{"", none});
			}
			String[] cols = numbered ? new String[]{"#", "Rating", "Reviews"} : new String[]{"Rating", "Latest Reviews"};
			int[] widths = numbered ? new int[]{50, 150, 660} : new int[]{150, 710};
			int[] align = numbered ? new int[]{SwingConstants.CENTER, SwingConstants.LEFT, SwingConstants.LEFT}
									: new int[]{SwingConstants.LEFT, SwingConstants.LEFT};
			t.setModel(new Grid(rows.toArray(new Object[0][]), cols));
			styleTable(t, widths, align, 36);
			int last = cols.length - 1;
			t.getColumnModel().getColumn(last).setCellRenderer(new Wrap());
			rows(t, last, widths[last]);
			t.clearSelection();
		}
	}