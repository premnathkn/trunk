package org.ieee.dummyShop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A frame that shows a n * n grid.
 */
public class GridFrame extends JFrame {
	public GridFrame() {

		// Use helper methods
		createTextField();
		createButton();
		createPanel();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void createTextField() {
		countLabel = new JLabel("Count");

		final int FIELD_WIDTH = 10;
		countField = new JTextField(FIELD_WIDTH);
		countField.setText(10 + "");

	}

	private void createButton() {
		button = new JButton("Draw");

		class GridListener implements ActionListener {
			public void actionPerformed(ActionEvent event) {

				GridComponent component = new GridComponent();
				panel.add(component);
				add(panel);
				createPanel();

			}
		}

		ActionListener listener = new GridListener();
		button.addActionListener(listener);

	}

	private void createPanel() {
		panel = new JPanel();
		panel.add(countLabel);
		panel.add(countField);

		panel.add(button);

		// GridComponent component = new GridComponent();
		// panel.add(component);
		add(panel);
	}

	private JLabel countLabel;
	private JTextField countField;

	private JButton button;

	private JPanel panel;

	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 120;

}
