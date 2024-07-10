package ec.edu.puce.elecciones.formulario;

import javax.swing.JInternalFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import ec.edu.puce.elecciones.dominio.Prefecto;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class BocaDeUrna extends JInternalFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JTable table;
	private DefaultTableModel model;

	private List<Prefecto> prefectos;
	private JPanel panel;
	private JButton btnCancelar;
	private JLabel lblNombres;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JLabel lblCiudad;

	public BocaDeUrna(List<Prefecto> prefectos) {
		this.prefectos = prefectos;
		setTitle("BOCA DE URNA - REGISTRO");
		setBounds(100, 100, 600, 427);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 172, 566, 167);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(model.getValueAt(0, 0));
			}
		});
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nombre", "Votos" }));
		scrollPane.setViewportView(table);

		panel = new JPanel();
		panel.setBounds(12, 76, 566, 84);
		getContentPane().add(panel);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(157, 351, 117, 25);
		getContentPane().add(btnCancelar);
		
		lblNombres = new JLabel("Provincia");
		lblNombres.setBounds(12, 21, 70, 15);
		getContentPane().add(lblNombres);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Azuay", "Cañar", "Pichincha", "Manabí", "Guayas"}));
		comboBox.setBounds(79, 12, 231, 24);
		getContentPane().add(comboBox);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Azogues", "Cañar", "Biblián", "Suscal", "La Troncal"}));
		comboBox_1.setBounds(79, 43, 231, 24);
		getContentPane().add(comboBox_1);
		
		lblCiudad = new JLabel("Ciudad");
		lblCiudad.setBounds(12, 48, 70, 15);
		getContentPane().add(lblCiudad);

		model = (DefaultTableModel) table.getModel();
		cargarCandidatos();
		llenarTabla();
	}

	private void cargarCandidatos() {
		int x = 0;
		for (Prefecto prefecto : prefectos) {
			JButton btnPrefecto = new JButton(prefecto.getNombre());
			btnPrefecto.setBounds(x * 155, 0, 150, 80);
			btnPrefecto.addActionListener(this);
			panel.setLayout(null);
			panel.add(btnPrefecto);
			x++;
		}
	}

	private void llenarTabla() {
		model.setRowCount(0);
		for (Prefecto prefecto : prefectos) {
			Object[] fila = new Object[2];
			fila[0] = prefecto.getNombre();
			fila[1] = prefecto.getVotos();
			model.addRow(fila);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnCancelar) {
			dispose();
		}
		for (Prefecto prefecto : prefectos) {
			if (prefecto.getNombre() == e.getActionCommand()) {
				prefecto.setVotos(prefecto.getVotos() + 1);
				llenarTabla();
			}
		}
	}

	public List<Prefecto> getPrefectos() {
		return prefectos;
	}

	public void setPrefectos(List<Prefecto> prefectos) {
		this.prefectos = prefectos;
	}
}
