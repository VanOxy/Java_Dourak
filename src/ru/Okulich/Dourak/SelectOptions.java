package ru.Okulich.Dourak;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



@SuppressWarnings("serial")
public class SelectOptions extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public SelectOptions() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setTitle("Options");
		
		setBounds(100, 100, 337, 173);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(0, 139, 139));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblAdverssaires = new JLabel("Adversaires : ");
		lblAdverssaires.setBounds(23, 21, 97, 14);
		contentPanel.add(lblAdverssaires);
		
		final JCheckBox turnOver_chk_box = new JCheckBox("TurnOver");
		turnOver_chk_box.setBackground(new Color(0, 139, 139));
		turnOver_chk_box.setBounds(23, 51, 97, 23);
		contentPanel.add(turnOver_chk_box);
		
		final JComboBox enemy_combo_box = new JComboBox();
		enemy_combo_box.setModel(new DefaultComboBoxModel(new String[] {"1", "2"}));
		enemy_combo_box.setSelectedIndex(0);
		enemy_combo_box.setBounds(130, 18, 43, 20);
		contentPanel.add(enemy_combo_box);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(0, 139, 139));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton selectOptions_btn = new JButton("OK");
		//selectOptions_btn.setActionCommand("OK");
		buttonPane.add(selectOptions_btn);
		getRootPane().setDefaultButton(selectOptions_btn);
		selectOptions_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options.nbrAdversaires = enemy_combo_box.getSelectedIndex() + 1;
				Options.turnOver = turnOver_chk_box.isSelected();
				Options.so.dispose();
				//System.out.println(Options.nbrAdversaires+"\n"+Options.turnOver);
			}
		});
			
		JButton no_selectOptions_btn = new JButton("Cancel");
		no_selectOptions_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Options.so.dispose();
			}
		});
		
		//no_selectOptions_btn.setActionCommand("Cancel");
		buttonPane.add(no_selectOptions_btn);
	}
}
