package ru.Okulich.Dourak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class Dourak {

	private JFrame menu;
	private Game game;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dourak window = new Dourak();
					window.menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Dourak() {
		initialize();
	}

	private void initialize() {
		/*
		 * mise en page // design et tt
		 */
		menu = new JFrame();
		menu.getContentPane().setBackground(new Color(0, 139, 139));

		JPanel main_panel = new JPanel(); // ************************ le main_panel
		main_panel.setBackground(new Color(0, 139, 139));

		JPanel panel_lbl_dou = new JPanel();
		panel_lbl_dou.setBackground(new Color(0, 139, 139));
		
		/*
		 * Vive les Layouts!!!!!!! :)
		 */

		GroupLayout groupLayout = new GroupLayout(menu.getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(90)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																main_panel,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																panel_lbl_dou,
																Alignment.LEADING,
																GroupLayout.DEFAULT_SIZE,
																259,
																Short.MAX_VALUE))
										.addGap(85)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addGroup(
				groupLayout
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(panel_lbl_dou,
								GroupLayout.PREFERRED_SIZE, 39,
								GroupLayout.PREFERRED_SIZE)
						.addGap(31)
						.addComponent(main_panel, GroupLayout.DEFAULT_SIZE,
								132, Short.MAX_VALUE).addGap(49)));

		/*
		 * Le titre --> mise en page, fonts, etc
		 */
		panel_lbl_dou.setLayout(new BorderLayout(0, 0));
		final JLabel lbl_title = new JLabel("DOURAK :)");
		lbl_title.setForeground(Color.ORANGE);
		lbl_title.setFont(new Font("Tempus Sans ITC", Font.BOLD, 22));
		lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
		panel_lbl_dou.add(lbl_title, BorderLayout.CENTER);
		main_panel.setLayout(new GridLayout(4, 0, 0, 6));

		/*
		 * les bouttons
		 */
		JButton btn_play = new JButton("Jouer");
		JButton btn_options = new JButton("Options");
		JButton btn_rules = new JButton("Regles");
		JButton btn_exit = new JButton("Quitter");

		main_panel.add(btn_play);
		main_panel.add(btn_options);
		main_panel.add(btn_rules);
		main_panel.add(btn_exit);

		/*
		 * les listeners
		 */
		btn_play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menu.dispose();
				game = new Game();
				game.init();
			}
		});

		btn_options.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options.launchSelectOptions();
			}
		});

		btn_rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options.launchRegles();
			}
		});

		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(1);
			}
		});

		menu.setLayout(groupLayout);
		menu.setTitle("Dourak");
		menu.setBounds(new Rectangle(450,300));
		menu.setLocationRelativeTo(null);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
