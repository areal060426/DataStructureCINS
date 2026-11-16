
package DATASTRUFINALS;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class ScholarshipFinderApp extends JFrame {

    // --- CUSTOM COLOR PALETTE FROM IMAGE ---
    public static final Color COL_CREAM       = Color.decode("#F1ECCF");
    public static final Color COL_SKY_BLUE    = Color.decode("#8BA8CC");
    public static final Color COL_OFF_WHITE   = Color.decode("#FAF7F0");
    public static final Color COL_NAVY        = Color.decode("#1F3C6D");
    public static final Color COL_YELLOW      = Color.decode("#F9DC85");
    public static final Color COL_BROWN       = Color.decode("#7D6441");

    // --- DATA STRUCTURES (EXPLICIT java.util.ArrayList) ---
    private java.util.ArrayList<Student> registeredStudents = new java.util.ArrayList<>();
    private java.util.ArrayList<Scholarship> allScholarships = new java.util.ArrayList<>();
    private Student currentUser = null;

    // --- UI COMPONENTS ---
    private CardLayout mainCardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(mainCardLayout);
    private JPanel topNavBar;
    private JLabel userGreetingLabel;

    // Dashboard Panels
    private JPanel scholarshipListPanel;
    private JLabel viewTitleLabel;
    private JTextField searchField;
    private JComboBox<String> categoryFilterBox;

    // Profile Edit Fields
    private JTextField profNameField, profGwaField, profIncomeField;
    private JComboBox<String> profFieldBox, profGenderBox;
    private JCheckBox profOfwCheck, profPublicHsCheck, profAgriCheck, profIndigentCheck;

    public ScholarshipFinderApp() {
        setTitle("IskoLARP - College Scholarship Finder");
        setSize(1050, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        loadScholarshipsFromGuide();
        seedDemoStudent();

        buildTopNavBar();
        add(topNavBar, BorderLayout.NORTH);
        topNavBar.setVisible(false);

        mainContainer.add(buildAuthScreen(), "AUTH");
        mainContainer.add(buildDashboardScreen(), "DASHBOARD");
        mainContainer.add(buildProfileScreen(), "PROFILE");

        add(mainContainer, BorderLayout.CENTER);
        mainCardLayout.show(mainContainer, "AUTH");
    }

    // =========================================================================
    // 1. DATA MODELS
    // =========================================================================

    static class Student {
        String username;
        String password;
        String fullName;
        double gwa;
        double annualFamilyIncome;
        String preferredField;
        String gender;
        boolean isOfwDependent;
        boolean isPublicHsGrad;
        boolean isFarmerFisherChild;
        boolean isLowIncomeListahanan;

        public Student(String username, String password, String fullName, double gwa,
                       double annualFamilyIncome, String preferredField, String gender,
                       boolean isOfwDependent, boolean isPublicHsGrad,
                       boolean isFarmerFisherChild, boolean isLowIncomeListahanan) {
            this.username = username;
            this.password = password;
            this.fullName = fullName;
            this.gwa = gwa;
            this.annualFamilyIncome = annualFamilyIncome;
            this.preferredField = preferredField;
            this.gender = gender;
            this.isOfwDependent = isOfwDependent;
            this.isPublicHsGrad = isPublicHsGrad;
            this.isFarmerFisherChild = isFarmerFisherChild;
            this.isLowIncomeListahanan = isLowIncomeListahanan;
        }
    }

    static class Scholarship {
        String name;
        String provider;
        String category;
        String benefits;
        String qualificationsSummary;
        String contactInfo;

        double minGwa;
        double maxIncome;
        String requiredField;
        boolean femaleOnly;
        boolean ofwOnly;
        boolean publicHsPreferred;
        boolean agriChildOnly;

        public Scholarship(String name, String provider, String category, String benefits,
                           String qualificationsSummary, String contactInfo,
                           double minGwa, double maxIncome, String requiredField,
                           boolean femaleOnly, boolean ofwOnly, boolean publicHsPreferred, boolean agriChildOnly) {
            this.name = name;
            this.provider = provider;
            this.category = category;
            this.benefits = benefits;
            this.qualificationsSummary = qualificationsSummary;
            this.contactInfo = contactInfo;
            this.minGwa = minGwa;
            this.maxIncome = maxIncome;
            this.requiredField = requiredField;
            this.femaleOnly = femaleOnly;
            this.ofwOnly = ofwOnly;
            this.publicHsPreferred = publicHsPreferred;
            this.agriChildOnly = agriChildOnly;
        }
    }

    // =========================================================================
    // 2. LOAD SCHOLARSHIPS FROM GLOBE TELECOM REFERENCE GUIDE
    // =========================================================================

    private void loadScholarshipsFromGuide() {
        allScholarships.add(new Scholarship(
                "CHED Merit Scholarship Program (CMSP)",
                "Commission on Higher Education (CHED)",
                "Government",
                "Covers tuition and other school fees, book/connectivity allowance, and living stipend.",
                "Filipino SHS graduate; GWA of at least 93%; Combined annual family gross income not exceeding ₱500,000; National priority courses.",
                "Contact your regional CHED office for application portals.",
                93.0, 500000, "Any", false, false, false, false
        ));

        allScholarships.add(new Scholarship(
                "Tertiary Education Subsidy (TES)",
                "CHED / UniFAST",
                "Government",
                "Need-based tuition subsidies for private universities, state universities, or local colleges.",
                "Prioritizes Listahanan 2.0 students, 4Ps beneficiaries, and low-income households in areas without SUCs/LUCs.",
                "Apply through your university's Financial Aid / UniFAST coordinator.",
                75.0, 300000, "Any", false, false, false, false
        ));

        allScholarships.add(new Scholarship(
                "Tulong Dunong Program (TDP)",
                "CHED",
                "Government",
                "Provides ₱7,500 per semester or ₱15,000 per year for tuition costs.",
                "Filipino SHS graduate enrolled in a CHED-recognized program; Annual family income not exceeding ₱500,000; Not a current TES/CSP grantee.",
                "Visit your regional CHED office or school scholarship desk.",
                75.0, 500000, "Any", false, false, false, false
        ));

        allScholarships.add(new Scholarship(
                "DOST-SEI Undergraduate Science Scholarship",
                "Department of Science and Technology (DOST-SEI)",
                "Government",
                "Up to ₱40,000/year tuition grant plus ₱8,000 monthly stipend.",
                "Open to all STEM strand students, or non-STEM students in the top 5% of their graduating class pursuing Science, Tech, Engineering, or Math.",
                "SEI E-Scholarship Portal | Call: (02) 8330-8876 / 8330-8826",
                88.0, 0, "STEM", false, false, false, false
        ));

        allScholarships.add(new Scholarship(
                "OWWA Education for Development Scholarship (EDSP)",
                "Overseas Workers Welfare Administration (OWWA)",
                "Government",
                "Maximum of ₱60,000 per year financial assistance.",
                "Dependent of an active OWWA member; Must qualify in the top 1,000 of the DOST national exam.",
                "OWWA Website or OWWA Hotline 1348",
                85.0, 0, "Any", false, true, false, false
        ));

        allScholarships.add(new Scholarship(
                "OWWA OFW Dependent Scholarship Program (ODSP)",
                "Overseas Workers Welfare Administration (OWWA)",
                "Government",
                "Provides ₱20,000 per year in college financial aid.",
                "Qualified dependent of an active OWWA member earning within OWWA income guidelines.",
                "OWWA Website or OWWA Hotline 1348",
                80.0, 400000, "Any", false, true, false, false
        ));

        allScholarships.add(new Scholarship(
                "Congressional Migrant Workers Scholarship (CMWSP)",
                "OWWA / PCSO",
                "Government",
                "Up to ₱60,000 per year for college tuition and expenses.",
                "Migrant workers or their descendants pursuing Science and Technology courses.",
                "OWWA Website or OWWA Hotline 1348",
                80.0, 0, "STEM", false, true, false, false
        ));

        allScholarships.add(new Scholarship(
                "OWWA ELAP (For Deceased/Distressed OFW Dependents)",
                "Overseas Workers Welfare Administration (OWWA)",
                "Government",
                "Up to ₱10,000 per year in college assistance plus livelihood aid.",
                "Dependent of a deceased active OWWA member or OFW sentenced abroad.",
                "OWWA Website or OWWA Hotline 1348",
                75.0, 0, "Any", false, true, false, false
        ));

        allScholarships.add(new Scholarship(
                "Ayala Foundation College Scholarship (U-Go)",
                "Ayala Foundation",
                "Foundation",
                "₱40,000 per year, renewable upon maintaining good academic standing.",
"Female Filipino student enrolled in a public college/university; GWA of at least 85% with no failing grades; Demonstrates financial need.",
"Call: (02) 7759-8288 | Facebook: Ayala Foundation",
85.0, 400000, "Any", true, false, false, false
));
allScholarships.add(new Scholarship(
"SM Foundation College Scholarship",
"SM Foundation",
"Foundation",
"Full tuition in partner schools, monthly allowance, and paid part-time jobs during breaks.",
"Grade 12 public school graduate (or private with DepEd voucher); GWA >= 92%; Family income <= ₱250,000; Courses in Accounting, IT, Engineering, or Education.",
"SM Foundation Application Portal | Call: (02) 8857-0100 loc. 1678",
92.0, 250000, "Business/IT/Eng/Educ", false, false, true, false
));
allScholarships.add(new Scholarship(
"Aboitiz Foundation College Scholarship",
"Aboitiz Foundation",
"Foundation",
"Full tuition, monthly allowance, board exam review stipend, and Latin honors incentives.",
"Incoming sophomore in a partner university; First-year college GWA of at least 88%; No dropped/failing grades or disciplinary record.",
"Call: (02) 8886-2800 loc. 12666",
88.0, 0, "Any", false, false, false, false
));
allScholarships.add(new Scholarship(
"Megaworld Foundation Scholarship",
"Megaworld Foundation",
"Foundation",
"Full tuition, monthly allowance via ATM card, and career opportunities in Megaworld affiliates.",
"Incoming freshman/sophomore/junior with at least 85% GWA; Combined annual family income not exceeding ₱400,000.",
"Call: (02) 8894-6437 to 38",
85.0, 400000, "Any", false, false, false, false
));
allScholarships.add(new Scholarship(
"Iskolar ng LANDBANK Program",
"LANDBANK of the Philippines",
"Foundation",
"Full tuition coverage and monthly living/book allowance.",
"Child/grandchild of an agrarian reform beneficiary, small farmer, or fisherfolk in priority provinces; Family income <= ₱300,000.",
"Call: (02) 8405-7000",
85.0, 300000, "Any", false, false, false, true
));
allScholarships.add(new Scholarship(
"Ateneo de Manila: Merit, Financial Aid & Magis Grants",
"Ateneo de Manila University (ADMU)",
"University",
"25% to 100% tuition/fees discount; Top ACET scorers & Magis scholars receive ₱50k–₱100k allowance, dorm, and book support.",
"Strong ACET performance, high academic standing, and/or demonstrated financial need (also includes Fr. Nebres & Jose P. Rizal public HS valedictorian grants).",
"ADMU Office of Admissions and Aid: (02) 8426-6001 loc. 5154",
90.0, 0, "Any", false, false, false, false
));
allScholarships.add(new Scholarship(
"DLSU: Archer Achiever, Star & Vaugirard Grants",
"De La Salle University (DLSU)",
"University",
"Full tuition and fees waiver; Vaugirard and Gokongwei Engineering scholars receive monthly stipends.",
"Top DCAT examinees, public science high school graduates, engineering applicants, or students with financial need (St. La Salle Grant).",
"DLSU Office for Admissions and Scholarships: (02) 8524-4611 loc. 162",
90.0, 0, "Any", false, false, false, false
));
allScholarships.add(new Scholarship(
"UST: Santo Tomas, San Lorenzo Ruiz & Equity Grants",
"University of Santo Tomas (UST)",
"University",
"Full or partial tuition discounts for academic achievers, athletes/artists, and student assistants.",
"Valedictorians/Salutatorians (Santo Tomas), students in financial need rendering 20–30 hrs/wk assistance (San Lorenzo Ruiz), or varsity/arts talents.",
"UST Office for Student Affairs: (02) 8786-1611 loc. 8276",
88.0, 0, "Any", false, false, false, false
));
allScholarships.add(new Scholarship(
"UA&P: Merit, Dr. Placido Mapa & Financial Aid Grants",
"University of Asia and the Pacific (UA&P)",
"University",
"Up to 100% tuition coverage; Top endowment scholars receive ₱8,000/month stipend, laptop, and internet allowance.",
"Merit: >= 90% HS average & UAPEE; Financial Aid: >= 85% HS average & family income below ₱800,000.",
"UA&P Admissions: (02) 8637-0912 to 26",
85.0, 800000, "Any", false, false, false, false
));
allScholarships.add(new Scholarship(
"Mapúa: E.T. Yuchengco & Don Tomas Mapúa Grants",
"Mapúa University",
"University",
"40% to 100% tuition discount plus up to ₱40,000 annual stipend and ₱12,000 book allowance.",
"Graduated With Highest Honors (Don Tomas), top MPASS exam scorers (E.T. Yuchengco), or Public Science HS graduates under IBFAP.",
"Mapúa Admissions: (02) 8247-5000 loc. 51230",
88.0, 0, "STEM", false, false, false, false
));
}
private void seedDemoStudent() {
registeredStudents.add(new Student(
"student", "1234", "Juan Dela Cruz",
93.5, 240000, "STEM", "Male",
false, true, false, true
));
}
// =========================================================================
// 3. TOP NAVIGATION BAR
// =========================================================================
private void buildTopNavBar() {
topNavBar = new JPanel(new BorderLayout());
topNavBar.setBackground(COL_NAVY);
topNavBar.setBorder(new EmptyBorder(12, 20, 12, 20));
JPanel brandPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 0));
brandPanel.setOpaque(false);
JLabel logoLabel = new JLabel("IskoLARP");
logoLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
logoLabel.setForeground(COL_YELLOW);
userGreetingLabel = new JLabel("Welcome, Student!");
userGreetingLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
userGreetingLabel.setForeground(COL_OFF_WHITE);
brandPanel.add(logoLabel);
brandPanel.add(Box.createHorizontalStrut(10));
brandPanel.add(userGreetingLabel);
JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
buttonPanel.setOpaque(false);
JButton btnBestMatch = createNavButton("Best Match For Me", COL_YELLOW, COL_NAVY);
JButton btnAllList   = createNavButton("All Scholarships", COL_SKY_BLUE, COL_NAVY);
JButton btnProfile   = createNavButton("My Details", COL_CREAM, COL_NAVY);
JButton btnLogout    = createNavButton("Logout", COL_BROWN, COL_OFF_WHITE);
btnBestMatch.addActionListener(e -> {
mainCardLayout.show(mainContainer, "DASHBOARD");
refreshScholarshipCards(true);
});
btnAllList.addActionListener(e -> {
mainCardLayout.show(mainContainer, "DASHBOARD");
refreshScholarshipCards(false);
});
btnProfile.addActionListener(e -> {
loadCurrentUserIntoProfileForm();
mainCardLayout.show(mainContainer, "PROFILE");
});
btnLogout.addActionListener(e -> {
currentUser = null;
topNavBar.setVisible(false);
mainCardLayout.show(mainContainer, "AUTH");
});
buttonPanel.add(btnBestMatch);
buttonPanel.add(btnAllList);
buttonPanel.add(btnProfile);
buttonPanel.add(btnLogout);
topNavBar.add(brandPanel, BorderLayout.WEST);
topNavBar.add(buttonPanel, BorderLayout.EAST);
}
private JButton createNavButton(String text, Color bg, Color fg) {
JButton btn = new JButton(text);
btn.setBackground(bg);
btn.setForeground(fg);
btn.setFont(new Font("SansSerif", Font.BOLD, 13));
btn.setFocusPainted(false);
btn.setBorder(new CompoundBorder(
new LineBorder(COL_OFF_WHITE, 1, true),
new EmptyBorder(7, 14, 7, 14)
));
btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
return btn;
}
// =========================================================================
// 4. LOGIN & REGISTRATION SCREEN
// =========================================================================
private JPanel buildAuthScreen() {
JPanel wrapper = new JPanel(new GridBagLayout());
wrapper.setBackground(COL_OFF_WHITE);
JPanel authCard = new JPanel(new BorderLayout(0, 15));
authCard.setBackground(COL_CREAM);
authCard.setPreferredSize(new Dimension(540, 580));
authCard.setBorder(new CompoundBorder(
new LineBorder(COL_BROWN, 2, true),
new EmptyBorder(25, 30, 25, 30)
));
JPanel header = new JPanel(new GridLayout(2, 1));
header.setOpaque(false);
JLabel title = new JLabel("IskoLARP College Scholarship Portal", SwingConstants.CENTER);
title.setFont(new Font("SansSerif", Font.BOLD, 22));
title.setForeground(COL_NAVY);
JLabel subtitle = new JLabel("Login or Register to find scholarships tailored to your profile", SwingConstants.CENTER);
subtitle.setFont(new Font("SansSerif", Font.PLAIN, 13));
subtitle.setForeground(COL_BROWN);
header.add(title);
header.add(subtitle);
JTabbedPane tabs = new JTabbedPane();
tabs.setFont(new Font("SansSerif", Font.BOLD, 14));
tabs.setBackground(COL_SKY_BLUE);
tabs.setForeground(COL_NAVY);
// --- TAB 1: LOGIN ---
JPanel loginPanel = new JPanel(new GridBagLayout());
loginPanel.setBackground(COL_OFF_WHITE);
loginPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
GridBagConstraints gbc = new GridBagConstraints();
gbc.fill = GridBagConstraints.HORIZONTAL;
gbc.insets = new Insets(8, 5, 8, 5);
JTextField loginUserField = new JTextField(18);
JPasswordField loginPassField = new JPasswordField(18);
JButton loginBtn = createStyledButton("Login & Find Scholarships", COL_NAVY, COL_YELLOW);
JLabel demoHint = new JLabel("Demo Account: Username = student | Password = 1234");
demoHint.setForeground(COL_BROWN);
gbc.gridx = 0; gbc.gridy = 0; loginPanel.add(new JLabel("Username:"), gbc);
gbc.gridx = 0; gbc.gridy = 1; loginPanel.add(loginUserField, gbc);
gbc.gridx = 0; gbc.gridy = 2; loginPanel.add(new JLabel("Password:"), gbc);
gbc.gridx = 0; gbc.gridy = 3; loginPanel.add(loginPassField, gbc);
gbc.gridx = 0; gbc.gridy = 4; loginPanel.add(loginBtn, gbc);
gbc.gridx = 0; gbc.gridy = 5; loginPanel.add(demoHint, gbc);
loginBtn.addActionListener(e -> {
String u = loginUserField.getText().trim();
String p = new String(loginPassField.getPassword()).trim();
for (Student s : registeredStudents) {
if (s.username.equalsIgnoreCase(u) && s.password.equals(p)) {
currentUser = s;
enterApp();
return;
}
}
JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
});
// --- TAB 2: REGISTER ---
JPanel regPanel = new JPanel(new GridLayout(11, 2, 8, 8));
regPanel.setBackground(COL_OFF_WHITE);
regPanel.setBorder(new EmptyBorder(15, 20, 15, 20));
JTextField regName = new JTextField();
JTextField regUser = new JTextField();
JPasswordField regPass = new JPasswordField();
JTextField regGwa = new JTextField("90.0");
JTextField regIncome = new JTextField("250000");
String[] fields = {"Any", "STEM", "Business/IT/Eng/Educ", "Arts/Sports/Humanities"};
JComboBox regFieldBox = new JComboBox<>(fields);
String[] genders = {"Female", "Male", "Prefer not to say"};
JComboBox regGenderBox = new JComboBox<>(genders);
JCheckBox chkOfw = new JCheckBox("OFW Dependent (OWWA)");
JCheckBox chkPublic = new JCheckBox("Public High School Grad");
JCheckBox chkAgri = new JCheckBox("Farmer/Fisherfolk Child");
JCheckBox chkIndigent = new JCheckBox("4Ps / Listahanan");
chkOfw.setOpaque(false); chkPublic.setOpaque(false);
chkAgri.setOpaque(false); chkIndigent.setOpaque(false);
regPanel.add(new JLabel("Full Name:")); regPanel.add(regName);
regPanel.add(new JLabel("Username:")); regPanel.add(regUser);
regPanel.add(new JLabel("Password:")); regPanel.add(regPass);
regPanel.add(new JLabel("GWA (e.g., 92.5):")); regPanel.add(regGwa);
regPanel.add(new JLabel("Annual Family Income (₱):")); regPanel.add(regIncome);
regPanel.add(new JLabel("Preferred Course Field:")); regPanel.add(regFieldBox);
regPanel.add(new JLabel("Gender:")); regPanel.add(regGenderBox);
regPanel.add(chkOfw); regPanel.add(chkPublic);
regPanel.add(chkAgri); regPanel.add(chkIndigent);
JButton regSubmitBtn = createStyledButton("Create Account & Match", COL_NAVY, COL_YELLOW);
regPanel.add(new JLabel("")); regPanel.add(regSubmitBtn);
regSubmitBtn.addActionListener(e -> {
try {
String name = regName.getText().trim();
String user = regUser.getText().trim();
String pass = new String(regPass.getPassword()).trim();
double gwa = Double.parseDouble(regGwa.getText().trim());
double income = Double.parseDouble(regIncome.getText().trim());
if (name.isEmpty() || user.isEmpty() || pass.isEmpty()) {
JOptionPane.showMessageDialog(this, "Please fill in Name, Username, and Password.");
return;
}
for (Student existing : registeredStudents) {
if (existing.username.equalsIgnoreCase(user)) {
JOptionPane.showMessageDialog(this, "Username already exists! Choose another.");
return;
}
}
Student newStudent = new Student(
user, pass, name, gwa, income,
(String) regFieldBox.getSelectedItem(),
(String) regGenderBox.getSelectedItem(),
chkOfw.isSelected(), chkPublic.isSelected(),
chkAgri.isSelected(), chkIndigent.isSelected()
);
registeredStudents.add(newStudent);
currentUser = newStudent;
JOptionPane.showMessageDialog(this, "Registration successful! Finding your best scholarship matches...");
enterApp();
} catch (NumberFormatException ex) {
JOptionPane.showMessageDialog(this, "Please enter valid numbers for GWA and Family Income.", "Input Error", JOptionPane.ERROR_MESSAGE);
}
});
tabs.addTab("Login", loginPanel);
tabs.addTab("Register Student", new JScrollPane(regPanel));
authCard.add(header, BorderLayout.NORTH);
authCard.add(tabs, BorderLayout.CENTER);
wrapper.add(authCard);
return wrapper;
}
private void enterApp() {
userGreetingLabel.setText("Student: " + currentUser.fullName + " | GWA: " + currentUser.gwa + "% | Income: ₱" + String.format("%,.0f", currentUser.annualFamilyIncome));
topNavBar.setVisible(true);
mainCardLayout.show(mainContainer, "DASHBOARD");
refreshScholarshipCards(true);
}
// =========================================================================
// 5. DASHBOARD & SCHOLARSHIP MATCHING VIEW
// =========================================================================
private JPanel buildDashboardScreen() {
JPanel dashboard = new JPanel(new BorderLayout(10, 10));
dashboard.setBackground(COL_OFF_WHITE);
dashboard.setBorder(new EmptyBorder(15, 20, 15, 20));
JPanel filterBar = new JPanel(new BorderLayout(10, 10));
filterBar.setBackground(COL_CREAM);
filterBar.setBorder(new CompoundBorder(
new LineBorder(COL_SKY_BLUE, 1, true),
new EmptyBorder(12, 15, 12, 15)
));
viewTitleLabel = new JLabel("Recommended Scholarships For You");
viewTitleLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
viewTitleLabel.setForeground(COL_NAVY);
JPanel controls = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
controls.setOpaque(false);
searchField = new JTextField(16);
searchField.setToolTipText("Search by keyword (e.g. DOST, Ateneo, Stipend)");
String[] cats = {"All Categories", "Government", "Foundation", "University"};
categoryFilterBox = new JComboBox<>(cats);
JButton searchBtn = createStyledButton("Filter", COL_SKY_BLUE, COL_NAVY);
searchBtn.addActionListener(e -> refreshScholarshipCards(viewTitleLabel.getText().contains("Recommended")));
controls.add(new JLabel("Keyword:"));
controls.add(searchField);
controls.add(new JLabel("Type:"));
controls.add(categoryFilterBox);
controls.add(searchBtn);
filterBar.add(viewTitleLabel, BorderLayout.WEST);
filterBar.add(controls, BorderLayout.EAST);
scholarshipListPanel = new JPanel(new GridLayout(0, 1, 0, 12));
scholarshipListPanel.setBackground(COL_OFF_WHITE);
JScrollPane scrollPane = new JScrollPane(scholarshipListPanel);
scrollPane.setBorder(null);
scrollPane.getViewport().setBackground(COL_OFF_WHITE);
scrollPane.getVerticalScrollBar().setUnitIncrement(16);
scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
dashboard.add(filterBar, BorderLayout.NORTH);
dashboard.add(scrollPane, BorderLayout.CENTER);
return dashboard;
}
private void refreshScholarshipCards(boolean onlyBestMatches) {
scholarshipListPanel.removeAll();
String keyword = searchField.getText().trim().toLowerCase();
String selectedCat = (String) categoryFilterBox.getSelectedItem();
java.util.ArrayList<Scholarship> displayList = new java.util.ArrayList<>();
for (Scholarship s : allScholarships) {
if (!"All Categories".equals(selectedCat) && !s.category.equalsIgnoreCase(selectedCat)) {
continue;
}
if (!keyword.isEmpty() &&
!s.name.toLowerCase().contains(keyword) &&
!s.provider.toLowerCase().contains(keyword) &&
!s.benefits.toLowerCase().contains(keyword) &&
!s.qualificationsSummary.toLowerCase().contains(keyword)) {
continue;
}
if (onlyBestMatches && currentUser != null) {
if (!isEligibleForStudent(s, currentUser)) {
continue;
}
}
displayList.add(s);
}
if (onlyBestMatches) {
viewTitleLabel.setText("Recommended Scholarships For You (" + displayList.size() + " Matches)");
} else {
viewTitleLabel.setText("Complete Philippine College Scholarship Directory (" + displayList.size() + " Total)");
}
if (displayList.isEmpty()) {
    JLabel emptyMsg = new JLabel("No scholarships matched your current filters. Click 'All Scholarships' at the top.");
    emptyMsg.setFont(new Font("SansSerif", Font.BOLD, 15));
    emptyMsg.setForeground(COL_BROWN);
    scholarshipListPanel.add(emptyMsg);
} else {
    for (Scholarship s : displayList) {
        scholarshipListPanel.add(createScholarshipCard(s));
    }
}
scholarshipListPanel.revalidate();
scholarshipListPanel.repaint();
}
private boolean isEligibleForStudent(Scholarship s, Student st) {
if (st.gwa < s.minGwa) return false;
if (s.maxIncome > 0 && st.annualFamilyIncome > s.maxIncome) return false;
if (s.femaleOnly && !"Female".equalsIgnoreCase(st.gender)) return false;
if (s.ofwOnly && !st.isOfwDependent) return false;
if (s.agriChildOnly && !st.isFarmerFisherChild) return false;
if (s.publicHsPreferred && !st.isPublicHsGrad) return false;
if (!"Any".equalsIgnoreCase(s.requiredField) && !"Any".equalsIgnoreCase(st.preferredField)) {
if (!s.requiredField.toLowerCase().contains(st.preferredField.toLowerCase()) &&
!st.preferredField.toLowerCase().contains(s.requiredField.toLowerCase())) {
return false;
}
}
return true;
}
private JPanel createScholarshipCard(Scholarship s) {
JPanel card = new JPanel(new BorderLayout(10, 8));
card.setBackground(COL_CREAM);
card.setPreferredSize(new Dimension(920, 165));
card.setMinimumSize(new Dimension(600, 165));
card.setBorder(new CompoundBorder(
new LineBorder(COL_BROWN, 1, true),
new EmptyBorder(14, 18, 14, 18)
));
JPanel topRow = new JPanel(new BorderLayout());
topRow.setOpaque(false);
JLabel nameLbl = new JLabel(s.name);
nameLbl.setFont(new Font("SansSerif", Font.BOLD, 17));
nameLbl.setForeground(COL_NAVY);
JLabel badgeLbl = new JLabel("  " + s.category.toUpperCase() + "  ");
badgeLbl.setOpaque(true);
badgeLbl.setFont(new Font("SansSerif", Font.BOLD, 11));
if ("Government".equals(s.category)) {
badgeLbl.setBackground(COL_NAVY);
badgeLbl.setForeground(COL_YELLOW);
} else if ("Foundation".equals(s.category)) {
badgeLbl.setBackground(COL_SKY_BLUE);
badgeLbl.setForeground(COL_NAVY);
} else {
badgeLbl.setBackground(COL_YELLOW);
badgeLbl.setForeground(COL_NAVY);
}
topRow.add(nameLbl, BorderLayout.WEST);
topRow.add(badgeLbl, BorderLayout.EAST);
JPanel centerInfo = new JPanel(new GridLayout(3, 1, 2, 4));
centerInfo.setOpaque(false);
JLabel providerLbl = new JLabel("Provider: " + s.provider);
providerLbl.setFont(new Font("SansSerif", Font.BOLD, 13));
providerLbl.setForeground(COL_BROWN);
JLabel benefitsLbl = new JLabel("Benefits: " + s.benefits);
benefitsLbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
benefitsLbl.setForeground(Color.DARK_GRAY);
JLabel qualLbl = new JLabel("Qualifications: " + s.qualificationsSummary);
qualLbl.setFont(new Font("SansSerif", Font.PLAIN, 13));
qualLbl.setForeground(Color.DARK_GRAY);
centerInfo.add(providerLbl);
centerInfo.add(benefitsLbl);
centerInfo.add(qualLbl);
JPanel bottomRow = new JPanel(new BorderLayout());
bottomRow.setOpaque(false);
JLabel contactLbl = new JLabel("Contact: " + s.contactInfo);
contactLbl.setFont(new Font("SansSerif", Font.ITALIC, 12));
contactLbl.setForeground(COL_NAVY);
JButton detailsBtn = createStyledButton("View Application Checklist", COL_SKY_BLUE, COL_NAVY);
detailsBtn.addActionListener(e -> JOptionPane.showMessageDialog(
this,
"Scholarship: " + s.name + "\n" +
"Provider: " + s.provider + "\n\n" +
"Benefits:\n" + s.benefits + "\n\n" +
"Requirements:\n" + s.qualificationsSummary + "\n\n" +
"How to Apply / Contact:\n" + s.contactInfo,
"Scholarship Details",
JOptionPane.INFORMATION_MESSAGE
));
bottomRow.add(contactLbl, BorderLayout.WEST);
bottomRow.add(detailsBtn, BorderLayout.EAST);
card.add(topRow, BorderLayout.NORTH);
card.add(centerInfo, BorderLayout.CENTER);
card.add(bottomRow, BorderLayout.SOUTH);
return card;
}
// =========================================================================
// 6. STUDENT PROFILE EDIT SCREEN
// =========================================================================
private JPanel buildProfileScreen() {
JPanel wrapper = new JPanel(new GridBagLayout());
wrapper.setBackground(COL_OFF_WHITE);
JPanel formCard = new JPanel(new GridLayout(11, 2, 10, 10));
formCard.setBackground(COL_CREAM);
formCard.setPreferredSize(new Dimension(560, 480));
formCard.setBorder(new CompoundBorder(
new LineBorder(COL_NAVY, 2, true),
new EmptyBorder(25, 30, 25, 30)
));
profNameField = new JTextField();
profGwaField = new JTextField();
profIncomeField = new JTextField();
profFieldBox = new JComboBox<>(new String[]{"Any", "STEM", "Business/IT/Eng/Educ", "Arts/Sports/Humanities"});
profGenderBox = new JComboBox<>(new String[]{"Female", "Male", "Prefer not to say"});
profOfwCheck = new JCheckBox("OFW Dependent (OWWA)");
profPublicHsCheck = new JCheckBox("Public High School Grad");
profAgriCheck = new JCheckBox("Farmer/Fisherfolk Child");
profIndigentCheck = new JCheckBox("4Ps / Listahanan");
profOfwCheck.setOpaque(false); profPublicHsCheck.setOpaque(false);
profAgriCheck.setOpaque(false); profIndigentCheck.setOpaque(false);
formCard.add(new JLabel("Full Name:")); formCard.add(profNameField);
formCard.add(new JLabel("General Weighted Average (GWA %):")); formCard.add(profGwaField);
formCard.add(new JLabel("Annual Family Gross Income (₱):")); formCard.add(profIncomeField);
formCard.add(new JLabel("Preferred Course Field:")); formCard.add(profFieldBox);
formCard.add(new JLabel("Gender:")); formCard.add(profGenderBox);
formCard.add(profOfwCheck); formCard.add(profPublicHsCheck);
formCard.add(profAgriCheck); formCard.add(profIndigentCheck);
JButton saveBtn = createStyledButton("Save & Re-Match Scholarships", COL_NAVY, COL_YELLOW);
saveBtn.addActionListener(e -> {
try {
currentUser.fullName = profNameField.getText().trim();
currentUser.gwa = Double.parseDouble(profGwaField.getText().trim());
currentUser.annualFamilyIncome = Double.parseDouble(profIncomeField.getText().trim());
currentUser.preferredField = (String) profFieldBox.getSelectedItem();
currentUser.gender = (String) profGenderBox.getSelectedItem();
currentUser.isOfwDependent = profOfwCheck.isSelected();
currentUser.isPublicHsGrad = profPublicHsCheck.isSelected();
currentUser.isFarmerFisherChild = profAgriCheck.isSelected();
currentUser.isLowIncomeListahanan = profIndigentCheck.isSelected();
enterApp();
JOptionPane.showMessageDialog(this, "Profile updated! Showing your new best matches.");
} catch (NumberFormatException ex) {
JOptionPane.showMessageDialog(this, "Please enter valid numeric values for GWA and Income.");
}
});
formCard.add(new JLabel(""));
formCard.add(saveBtn);
wrapper.add(formCard);
return wrapper;
}
private void loadCurrentUserIntoProfileForm() {
if (currentUser == null) return;
profNameField.setText(currentUser.fullName);
profGwaField.setText(String.valueOf(currentUser.gwa));
profIncomeField.setText(String.valueOf((long) currentUser.annualFamilyIncome));
profFieldBox.setSelectedItem(currentUser.preferredField);
profGenderBox.setSelectedItem(currentUser.gender);
profOfwCheck.setSelected(currentUser.isOfwDependent);
profPublicHsCheck.setSelected(currentUser.isPublicHsGrad);
profAgriCheck.setSelected(currentUser.isFarmerFisherChild);
profIndigentCheck.setSelected(currentUser.isLowIncomeListahanan);
}
private JButton createStyledButton(String text, Color bg, Color fg) {
JButton btn = new JButton(text);
btn.setBackground(bg);
btn.setForeground(fg);
btn.setFont(new Font("SansSerif", Font.BOLD, 13));
btn.setFocusPainted(false);
btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
return btn;
}
public static void main(String[] args) {
SwingUtilities.invokeLater(() -> {
new ScholarshipFinderApp().setVisible(true);
});
}
}
