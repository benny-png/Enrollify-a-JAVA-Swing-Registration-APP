import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.time.Year;

public class Registration extends JFrame {
    private JTextField fullnameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JTextField birthYearField;
    private JComboBox<String> birthMonthComboBox;
    private JComboBox<String> genderComboBox;
    private JComboBox<String> countryComboBox;

    public Registration() {
        setTitle("Registration Form");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);  // Prevent resizing

        // Create and set up the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Create and set up the form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 10, 10, 10);

        // Full Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Full Name:"), gbc);

        fullnameField = new JTextField(30);  // Make the full name field longer
        gbc.gridx = 1;
        gbc.gridwidth = 3;  // Extend to 3 columns
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(fullnameField, gbc);

        // Password
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Password:"), gbc);

        passwordField = new JPasswordField(10);
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(passwordField, gbc);

        // Confirm Password
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Conf. Pwd:"), gbc);

        confirmPasswordField = new JPasswordField(10);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(confirmPasswordField, gbc);

        // Birth Month
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Birth Month:"), gbc);

        birthMonthComboBox = new JComboBox<>(new String[]{
            "NotSelected", "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        });
        birthMonthComboBox.setMaximumRowCount(6);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(birthMonthComboBox, gbc);

        // Birth Year
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Birth Year:"), gbc);

        birthYearField = new JTextField(6);
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(birthYearField, gbc);

        // Gender
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Gender:"), gbc);

        genderComboBox = new JComboBox<>(new String[]{"NotSelected", "Male", "Female"});
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 10, 40, 10); 
        formPanel.add(genderComboBox, gbc);

        //reset the gbc.insets back o original 
        gbc.insets = new Insets(10, 10, 30, 10); 

        // Country
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        formPanel.add(new JLabel("Country:"), gbc);

        countryComboBox = new JComboBox<>(new String[]{
            "NotSelected", "Burundi", "Kenya", "Rwanda", "South Sudan", "Tanzania", "Uganda"
        });
        gbc.gridx = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(countryComboBox, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save Info");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                saveAction();
            }
        });
        buttonPanel.add(saveButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetAction();
            }
        });
        buttonPanel.add(resetButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(exitButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(buttonPanel, gbc);

        // Add form panel to main panel
        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Add a footer label
        JLabel footerLabel = new JLabel("Created by Benny using Java Swing" , JLabel.CENTER);
        footerLabel.setFont(new Font("Serif", Font.ITALIC, 14));
        footerLabel.setForeground(Color.DARK_GRAY);
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void saveAction() {
        String fullname = fullnameField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        String birthYearText = birthYearField.getText();
        String birthMonth = (String) birthMonthComboBox.getSelectedItem();
        String gender = (String) genderComboBox.getSelectedItem();
        String country = (String) countryComboBox.getSelectedItem();

        // Validation
        if (!fullname.matches("\\b\\w+\\s+\\w+\\s+\\w+\\b")) {
            showError("Fullname must have three names.");
            return;
        }

        if (password.length() < 8) {
            showError("Password must have at least 8 characters.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match.");
            return;
        }

        int birthYear;
        try {
            birthYear = Integer.parseInt(birthYearText);
        } catch (NumberFormatException e) {
            showError("Birth Year must be a number.");
            return;
        }

        if (birthYear < 2005 || birthYear > 2010) {
            showError("Birth Year must be between 2005 and 2010.");
            return;
        }

        if ("NotSelected".equals(birthMonth) || "NotSelected".equals(gender) || "NotSelected".equals(country)) {
            showError("Please select valid options for Birth Month, Gender, and Country.");
            return;
        }

        // Check if the fullname already exists
        if (isFullnameExists(fullname)) {
            showError("Fullname already exists.");
            return;
        }

        // Save to file
        try (FileWriter writer = new FileWriter("RegInfo.txt", true)) {
            writer.write("Fullname: " + fullname + "\n");
            writer.write("Password: " + password + "\n");
            writer.write("Birth Month: " + birthMonth + "\n");
            writer.write("Birth Year: " + birthYear + "\n");
            writer.write("Age (yrs): " + (Year.now().getValue() - birthYear) + "\n");
            writer.write("Gender: " + gender + "\n");
            writer.write("Country: " + country + "\n");
            writer.write("-----\n");
        } catch (IOException e) {
            showError("An error occurred while saving the information.");
            return;
        }

        // Show registered information
        JTextArea textArea = new JTextArea();
        textArea.setText(
            "Fullname: " + fullname + "\n" +
            "Password: " + password + "\n" +
            "Birth Month: " + birthMonth + "\n" +
            "Birth Year: " + birthYear + "\n" +
            "Age (yrs): " + (Year.now().getValue() - birthYear) + "\n" +
            "Gender: " + gender + "\n" +
            "Country: " + country + "\n"
        );
        textArea.setFont(new Font("Serif", Font.BOLD, 28));
        JOptionPane.showMessageDialog(this, new JScrollPane(textArea), "Registered Information", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean isFullnameExists(String fullname) {
        try (BufferedReader reader = new BufferedReader(new FileReader("RegInfo.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Fullname: ") && line.substring(10).equals(fullname)) {
                    return true;
                }
            }
        } catch (IOException e) {
            showError("An error occurred while checking existing records.");
        }
        return false;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void resetAction() {
        fullnameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        birthYearField.setText("");
        birthMonthComboBox.setSelectedIndex(0);
        genderComboBox.setSelectedIndex(0);
        countryComboBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        // Show splash screen
        SplashScreen splash = new SplashScreen("loader.gif", 3000);
        splash.showSplash(5000);

        // Show registration form
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Registration().setVisible(true);
            }
        });
    }
}
