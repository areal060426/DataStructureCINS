package FinalsProject;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.ArrayList;

public class ScholarshipFinderApp extends JFrame {
	
    public static final Color COL_CREAM       = Color.decode("#F1ECCF");
    public static final Color COL_SKY_BLUE    = Color.decode("#8BA8CC");
    public static final Color COL_OFF_WHITE   = Color.decode("#FAF7F0");
    public static final Color COL_NAVY        = Color.decode("#1F3C6D");
    public static final Color COL_YELLOW      = Color.decode("#F9DC85");
    public static final Color COL_BROWN       = Color.decode("#7D6441");


    private java.util.ArrayList<Student> registeredStudents = new java.util.ArrayList<>();
    private java.util.ArrayList<Scholarship> allScholarships = new java.util.ArrayList<>();
    private Student currentUser = null;

 
    private CardLayout mainCardLayout = new CardLayout();
    private JPanel mainContainer = new JPanel(mainCardLayout);
    private JPanel topNavBar;
    private JLabel userGreetingLabel;

    
    private JPanel scholarshipListPanel;
    private JLabel viewTitleLabel;
    private JTextField searchField;
    private JComboBox<String> categoryFilterBox;
    private boolean showingBestMatches = true;

 
    private JTextField profNameField, profGwaField, profIncomeField;
    private JComboBox<String> profFieldBox, profGenderBox;
    private JCheckBox profOfwCheck, profPublicHsCheck, profAgriCheck, profIndigentCheck;

    private JTextField loginUserField;
    private JPasswordField loginPassField;
    private JTextField regName, regUser, regGwa, regIncome;
    private JPasswordField regPass;
    private JComboBox<String> regFieldBox, regGenderBox;
    private JCheckBox chkOfw, chkPublic, chkAgri, chkIndigent;

    public ScholarshipFinderApp() {
        setTitle("IskoLARP - College Scholarship Finder");
        setSize(1250, 720);
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
		
		allScholarships.add(new Scholarship(
				"GSIS Scholarship Program",
				"Government Service Insurance System (GSIS)",
				"Government",
				"PHP 15,000  per academic year and latin honors bonus around PHP 20,000-50,000",
				"Parents must be GSIS member, SHS graduate, enrolled in a STEM bachelor’s degree program and have a GWA of at least 90% with no failing grade in SHS",
				"(02) 8847-4747 Metro Manila 1-800-8-847-4747, (Globe/TM) or 1-800-10-847-4747 (Smart/Sun/TNT)",
				90.0, 500000, "STEM", false, false, false, false
		));
	
		allScholarships.add(new Scholarship(
				"San Miguel Foundation Scholarship",
				"San Miguel Foundation, Inc. (SMFI)",
				"Private",
				"Full or partial coverage of tuition and miscellaneous fees, book, and living stipend.",
				"Filipino SHS graduate with a GWA of at least 88% and an annual family income below PHP 400,000",
				"(02) 8632-3000, www.sanmiguel.com.ph/csr",
				88.0, 400000, "Any", false, false, false, false
		));

		allScholarships.add(new Scholarship(
				"CLFI Scholarship",
				"Cebuana Lhuillier Foundation Inc.",
				"Private",
				"Fixed Stipend: Up to 50,000 to help subsidize school-related and living expenses.",
				"Must be a Filipino citizen and an incoming freshman or currently enrolled 1st to 4th year student at a public college or state university. \n"
				+ "Your total annual family income must not exceed 350,000. Additionally, either you or an immediate relative must be an active Cebuana Lhuillier client, \n"
				+ "and you are required to open a Cebuana Lhuillier Microsavings account to receive the grant.",
				"clfi@pjlhuillier.com 0917 812 2737 (Globe) 0918 812 2737 (Smart)",
				85.0, 350000, "Any", false, false, false, false
		));

		allScholarships.add(new Scholarship(
				"JPL-SHL President's Scholarship",
				"Lyceum Of The Philippines Manila Campus",
				"Private",
				"Free tuition and miscellaneous fees",
				"Must be currently Grade 12 or a qualified candidate for graduation. Must belong to at least one of the following: Top 10% of the graduating batch of at \n"
				+ "least 35 students (Grade 11 Ranking), or a Science High School student. Must have at least GPA/GWA of 90%. Must be of good character as certified by the \n"
				+ "High School Principal. Must be a Filipino Citizen. Must pass the qualifying examination and interview given by the Scholarship Committee.",
				"Not specified",
				90.0, 0, "Any", false, false, false, false
		));

		allScholarships.add(new Scholarship(
				"Achiever Scholarship",
				"Centro Escolar University",
				"Private",
				"100% discount on tuition and miscellaneous fees for one (1) regular School Year. Provision of tablet computer (to be given once)",
				"Must not be more than 25 years of age, Filipino citizen, possess good moral character as certified by the senior high school principal,\n"
				+ " annual family income of not more than P 500,000.00, Grade 12 weighted average of 85% or higher with no lower than 85% in Science, Math, and English subjects",
				"88889-7491; 8843-0300 | 8893-2461; 8893-2464 | ceumakatiadmission@ceu.edu.ph",
				85.0, 500000, "Any", false, false, false, false
		));

		allScholarships.add(new Scholarship(
				"Quezon City Scholarship",
				"Quezon City Government - QC Youth Development Office (QCSP)",
				"Public..",
				"For tertiary students: QC Excel Scholarship, tuition fee grant per School year (max amount) P 110,000.00 and Stipend per School year P 50,000.00",
				"Resident of Quezon City. Not a scholar of another Local Government Unit. Be enrolled/registered/accepted in any of the priority courses or specializations \n"
				+ "identified by the Quezon City Government. Must pass interviews and aptitude/psychological tests to be administered by the Quezon City Government. \n"
				+ "Must show proof of leadership/volunteer work/socio-civic engagements. Must have a GWA of at least 1.75 or its equivalent.",
				"QCYDOSubmission@quezoncity.gov.ph, qcusubmission.qcydo@quezoncity.gov.ph, scholarshipstatus.qcydo@quezoncity.gov.ph",
				88.0, 0, "Any", false, false, false, false
		));

		allScholarships.add(new Scholarship(
				"Tzu Chi Educational Assistance Program",
				"Tzu Chi Philippines",
				"Public",
				"Helps first year college students from low income families stay in school, with financial aid and a class on values and life skills",
				"First year college students from low-income families in Metro Manila and nearby Rizal, enrolled in one of nine partner state universities and colleges,\n"
				+ " with a general weighted average of 2.25 (82%) or better. Pass interview, and agree to a home visit by Tzu Chi's social workers, staff and volunteers. \n"
				+ "Partner Schools: UP, UDM, PNU, PLM, PUP, TUP, University of Caloocan, QCU, Pamantasan ng Lungsod ng Marikina",
				"https://tzuchi.org.ph/apply/",
				82.0, 0, "Any", false, false, false, false
		));

		allScholarships.add(new Scholarship(
				"Cebu City Government College Scholarship Program",
				"Cebu City LGU",
				"Government",
				"Private Universities/Colleges: 10,000 tuition subsidy per semester + 1,000 school supplies allowance per semester. State Universities/Colleges (SUCs): P2,500 school supplies allowance per semester. Mountain Barangay Residents: Additional P5,000 transportation allowance per semester",
				"Must be a bonafide resident of Cebu City whose parents/guardians (or the applicant, if over 21/25) are registered Cebu City voters who voted in the last two \n"
				+ "local/national elections. Must be a graduate of a Cebu City public high school, ALS/PEPT, or a Cebu City private high school under a scholarship/voucher grant \n"
				+ " (EVS/ESC), and must enroll in an accredited partner school.",
				"Phone: (032) 266-1036/ (032) 231-1766/ (032) 503-30241 | Email: cebucityscholarshipprogram@gmail.com",
				0.0, 400000, "Any", false, false, false, false
		));

		allScholarships.add(new Scholarship(
				"Argao Tertiary Education Scholarship Assistance Program",
				"Argao, Cebu LGU",
				"Government",
				"Not Specified",
				"Visayas - Argao, Cebu; detailed eligibility not yet verified",
				"https://crs.upv.edu.ph/cebu/views/scholarships.jsp",
				0.0, 0, "Any", false, false, false, false
		));

		allScholarships.add(new Scholarship(
				"Lapu-Lapu City Government Scholarship",
				"Lapu-Lapu City LGU",
				"Government",
				"Not Specified",
				"Visayas - Lapu-Lapu City; detailed eligibility not yet verified",
				"https://crs.upv.edu.ph/cebu/views/scholarships.jsp",
				0.0, 0, "Any", false, false, false, false
		));
		
    	}
    
    
	private void seedDemoStudent() {
			registeredStudents.add(new Student(
			"student", "1234", "Juan Dela Cruz",
			93.5, 240000, "STEM", "Male",
			false, true, false, true
			));
	}

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
				showingBestMatches = true;
				searchField.setText("");
				categoryFilterBox.setSelectedItem("All Categories");
				mainCardLayout.show(mainContainer, "DASHBOARD");
				refreshScholarshipCards(true);
			});
			
			btnAllList.addActionListener(e -> {
				showingBestMatches = false;
				mainCardLayout.show(mainContainer, "DASHBOARD");
				searchField.setText("");
				categoryFilterBox.setSelectedItem("All Categories");
				refreshScholarshipCards(false);
			});
			
			btnProfile.addActionListener(e -> {
				loadCurrentUserIntoProfileForm();
				mainCardLayout.show(mainContainer, "PROFILE");
			});
			
			btnLogout.addActionListener(e -> {
				currentUser = null;
				loginUserField.setText("");
				loginPassField.setText("");
				regName.setText("");
				regUser.setText("");
				regPass.setText("");
				regGwa.setText("90.0");
				regIncome.setText("250000");
				regFieldBox.setSelectedIndex(0);
				regGenderBox.setSelectedIndex(0);
				chkOfw.setSelected(false);
				chkPublic.setSelected(false);
				chkAgri.setSelected(false);
				chkIndigent.setSelected(false);
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
			loginUserField = new JTextField(18);
			loginPassField = new JPasswordField(18);
			JButton loginBtn = createStyledButton("Login & Find Scholarships", COL_NAVY, COL_YELLOW);		
			getRootPane().setDefaultButton(loginBtn);
			
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
			regName = new JTextField();
			regUser = new JTextField();
			regPass = new JPasswordField();
			regGwa = new JTextField("90.0");
			regIncome = new JTextField("250000");
			String[] fields = {"Any", "STEM", "Business/IT/Eng/Educ", "Arts/Sports/Humanities"};
			regFieldBox = new JComboBox<>(fields);
			String[] genders = {"Female", "Male", "Prefer not to say"};
			regGenderBox = new JComboBox<>(genders);
			chkOfw = new JCheckBox("OFW Dependent (OWWA)");
			chkPublic = new JCheckBox("Public High School Grad");
			chkAgri = new JCheckBox("Farmer/Fisherfolk Child");
			chkIndigent = new JCheckBox("4Ps / Listahanan");
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
			showingBestMatches = true;
			userGreetingLabel.setText("Student: " + currentUser.fullName + " | GWA: " + currentUser.gwa + "% | Income: ₱" + String.format("%,.0f", currentUser.annualFamilyIncome));
			topNavBar.setVisible(true);
			mainCardLayout.show(mainContainer, "DASHBOARD");
			refreshScholarshipCards(true);
			}

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
			String[] cats = {"All Categories", "Government", "Foundation", "University", "Private"};
			categoryFilterBox = new JComboBox<>(cats);
			JButton searchBtn = createStyledButton("Filter", COL_SKY_BLUE, COL_NAVY);
			searchBtn.addActionListener(e -> refreshScholarshipCards(showingBestMatches));
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
			showingBestMatches = onlyBestMatches;
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