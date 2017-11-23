package org.ju.cse.gobinda;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Converter extends JFrame {

	private static final long serialVersionUID = 1L;

	private static Vector<String> files = new Vector<String>();
	private static Vector<String> filesName = new Vector<String>();

	private static int picWidth = 0;
	private static int picHeight = 0;

	private static String outputFileType = new String("jpg");
	private static String outputFileLocation = new String("c:");

	private JPanel contentPane;
	private JTextField inputField;
	private JTextField outputField;
	private JLabel info;

	private static String[] types = { "jpg", "png", "bmp", "gif" };

	public Converter() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 385, 509);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		info = new JLabel("");
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setFont(new Font("Tahoma", Font.PLAIN, 18));
		info.setBounds(10, 423, 349, 30);
		contentPane.add(info);

		JLabel lblPhotoConverter = new JLabel("Photo Converter");
		lblPhotoConverter.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhotoConverter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPhotoConverter.setBounds(10, 11, 349, 30);
		contentPane.add(lblPhotoConverter);

		JButton btnConvert = new JButton("CONVERT");
		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int len = files.size();
				if (len == 0) {
					info.setText("NO INPUT FILE IS SELECTED.");
					return;
				}
				for (int i = 0; i < len; i++) {
					try {
						convertFile(files.get(i), filesName.get(i));
					} catch (Exception e) {
					}
				}
			}
		});
		btnConvert.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnConvert.setBounds(55, 373, 266, 39);
		contentPane.add(btnConvert);

		JLabel lblSelectInputFile_2 = new JLabel("Select output File location...");
		lblSelectInputFile_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectInputFile_2.setBounds(55, 283, 266, 24);
		contentPane.add(lblSelectInputFile_2);

		JLabel lblSelectInputFile = new JLabel("Select Output file Width and Height");
		lblSelectInputFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectInputFile.setBounds(55, 52, 266, 24);
		contentPane.add(lblSelectInputFile);

		JLabel lblSelectOutputFile = new JLabel("Select output File Type");
		lblSelectOutputFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectOutputFile.setBounds(55, 128, 266, 24);
		contentPane.add(lblSelectOutputFile);

		final JComboBox<String> comboBox_1 = new JComboBox<String>(types);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outputFileType = types[comboBox_1.getSelectedIndex()];
				info.setText("Selected Output file type.");
			}
		});
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_1.setBounds(55, 154, 266, 39);
		contentPane.add(comboBox_1);

		inputField = new JTextField();
		inputField.setEditable(false);
		inputField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		inputField.setColumns(10);
		inputField.setBounds(55, 249, 233, 30);
		contentPane.add(inputField);

		JLabel lblSelectInputFile_1 = new JLabel("Select input File location...");
		lblSelectInputFile_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSelectInputFile_1.setBounds(55, 224, 266, 24);
		contentPane.add(lblSelectInputFile_1);

		JButton btnNewButton = new JButton("New button");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				info.setText("Selecting picture as input");
				files.removeAllElements();
				filesName.removeAllElements();

				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png", "bmp",
						"gif");
				chooser.setMultiSelectionEnabled(true);
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File[] fi = chooser.getSelectedFiles();
					String ss = new String("");
					for (int i = 0; i < fi.length; i++) {
						files.add(fi[i].getAbsolutePath().toString());
						filesName.add(fi[i].getName());
						ss += fi[i].getName() + " ";
					}
					inputField.setText(ss);
					info.setText("Selected Picture for input");
				}

			}
		});
		btnNewButton.setBounds(287, 249, 34, 30);
		contentPane.add(btnNewButton);

		JButton button = new JButton("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				info.setText("Selected output file location");
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				chooser.setMultiSelectionEnabled(false);
				if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION) {
					outputFileLocation = chooser.getSelectedFile().getAbsolutePath();
					outputField.setText(outputFileLocation);
				}
			}
		});
		button.setBounds(287, 308, 34, 30);
		contentPane.add(button);

		outputField = new JTextField(outputFileLocation);
		outputField.setEditable(false);
		outputField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		outputField.setColumns(10);
		outputField.setBounds(55, 308, 233, 30);
		contentPane.add(outputField);

		String[] em = new String[1500];
		em[0] = new String("As it is");
		for (int i = 1; i < 1500; i++) {
			em[i] = String.valueOf(i);
		}

		final JComboBox<String> comboBox = new JComboBox<String>(em);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				picWidth = comboBox.getSelectedIndex();
				info.setText("Selected Picture Width");
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setBounds(55, 78, 128, 39);
		contentPane.add(comboBox);

		final JComboBox<String> comboBox_2 = new JComboBox<String>(em);
		comboBox_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				picHeight = comboBox_2.getSelectedIndex();
				info.setText("Selected Picture Height");
			}
		});
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox_2.setBounds(193, 78, 128, 39);
		contentPane.add(comboBox_2);
	}

	private void convertFile(String fileLocation, String fileName) {

		fileName = fileName.substring(0, fileName.indexOf('.'));
		fileName = fileName + "." + outputFileType;

		try {

			BufferedImage inputImage = ImageIO.read(new File(fileLocation));

			int type = inputImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : inputImage.getType();

			inputImage = resizeImage(inputImage, type);

			ImageIO.write(inputImage, outputFileType, new File(outputFileLocation + "\\" + fileName));

		} catch (Exception e) {
			// TODO: handle exception
			info.setText("Output Path can't be access");
			e.printStackTrace();
			return;
		}
		info.setText("File successfully Converted..");
	}

	private BufferedImage resizeImage(BufferedImage originalImage, int type) {
		if (picHeight == 0)
			picHeight = originalImage.getHeight();
		if (picWidth == 0)
			picWidth = originalImage.getWidth();

		BufferedImage resizedImage = new BufferedImage(picWidth, picHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, picWidth, picHeight, null);
		g.dispose();

		return resizedImage;
	}

	public static void initialize() {
		new Converter().setVisible(true);
	}

}
