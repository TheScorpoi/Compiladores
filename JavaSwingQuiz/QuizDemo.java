package JavaSwingQuiz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JOptionPane;

public class QuizDemo extends JFrame {

	JPanel p = new JPanel();
	CardLayout cards = new CardLayout();
	int numQs;
	int wrongs = 0;
	int total = 0;

	public static void main(String[] args) {
		new QuizDemo();
	}

	public QuizDemo() {
		super("QUESTIONÁRIO");
		setResizable(true);
		setSize(650, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		p.setLayout(cards);
		for (int i = 0; i < numQs; i++) {
		}

		cards.show(p, "q");
		add(p);
		setVisible(true);
	}

	public void next() {
			showSummary();


			
		
		cards.show(p, "q");
	}

	

	public void showSummary() {
		JOptionPane.showMessageDialog(null,
				"That's it! Here is your summary:" + "\nYou answered " + wrongs + " questions wrong" + "\nYou answered "
						+ (total - wrongs) + " right" + "\nGiving a correct answer chance: \t\t"
						+ (int) (((float) (total - wrongs) / total) * 100) + "%");
		System.exit(0);
	}

}
