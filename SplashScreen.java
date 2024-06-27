import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SplashScreen extends JWindow {
    private static final int SPLASH_WIDTH = 500;
    private static final int SPLASH_HEIGHT = 400;

    public SplashScreen(String imagePath, int duration) {
        JPanel content = (JPanel) getContentPane();
        content.setBackground(Color.white);

        // Set the layout and add the image
        content.setLayout(new BorderLayout());
        JLabel splashLabel = new JLabel(new ImageIcon(imagePath));
        content.add(splashLabel, BorderLayout.CENTER);

        // Add application name label
        JLabel appNameLabel = new JLabel("Enrollify APP", JLabel.CENTER);
        appNameLabel.setFont(new Font("Serif", Font.BOLD, 32));
        appNameLabel.setForeground(Color.BLACK);
        content.add(appNameLabel, BorderLayout.NORTH);

        // Add a footer label
        JLabel footerLabel = new JLabel("Loading, please wait...", JLabel.CENTER);
        footerLabel.setFont(new Font("Serif", Font.ITALIC, 14));
        footerLabel.setForeground(Color.DARK_GRAY);
        content.add(footerLabel, BorderLayout.SOUTH);

        pack();

        // Center the splash screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - SPLASH_WIDTH) / 2;
        int y = (screenSize.height - SPLASH_HEIGHT) / 2 - SPLASH_HEIGHT / 4; // Lowered the splash screen by a quarter of its height
        setBounds(x, y, SPLASH_WIDTH, SPLASH_HEIGHT);

        // Timer to change the footer text after half the duration
        int halfDuration = duration / 2;
        Timer timer = new Timer(halfDuration, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                footerLabel.setText("Created by Benny using Java Swing");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void showSplash(int duration) {
        setVisible(true);
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setVisible(false);
    }
}
