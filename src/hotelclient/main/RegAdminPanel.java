package hotelclient.main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hotelclient.ClientMain;

public class RegAdminPanel extends JPanel implements ActionListener {
	ClientMain main;
	Connection con;
	
	JPanel p_north, p_input, p_south;
	JLabel la_title, la_serial, la_id, la_pw, la_name;
	Font font;
	JTextField txt_serial, txt_id, txt_name;
	JPasswordField txt_pw;
	JButton bt_regist, bt_prev;

	String serial="1111-2222-3333-4444";
	
	public RegAdminPanel(ClientMain main) {
		this.main = main;
		con=main.con;
					
		p_north = new JPanel();
		p_input = new JPanel();
		p_south = new JPanel();

		la_title = new JLabel("������ ���");
		la_serial = new JLabel("SN");
		la_id = new JLabel("ID");
		la_pw = new JLabel("PW");
		la_name = new JLabel("�̸�");

		font = new Font("���� ���", font.PLAIN, 18);

		txt_serial=new JTextField("1111-2222-3333-4444", 10);
		txt_id = new JTextField(10);
		txt_name = new JTextField(10);
		txt_pw = new JPasswordField(10);
		
		txt_serial.setFont(font);
		txt_id.setFont(font);
		txt_name.setFont(font);
		txt_pw.setFont(font);

		bt_regist = new JButton("���");
		bt_prev = new JButton("����");

		setLayout(new BorderLayout());
		p_input.setLayout(new GridLayout(4, 2));
		p_input.setPreferredSize(new Dimension(400, 200));
		
		la_serial.setFont(font);
		la_title.setFont(new Font("�������", font.BOLD, 25));
		la_id.setFont(font);
		la_pw.setFont(font);
		la_name.setFont(font);

		p_north.add(la_title);

		p_input.add(la_serial);
		p_input.add(txt_serial);
		p_input.add(la_id);
		p_input.add(txt_id);
		p_input.add(la_pw);
		p_input.add(txt_pw);
		p_input.add(la_name);
		p_input.add(txt_name);

		p_south.add(bt_regist);
		p_south.add(bt_prev);

		add(p_north, BorderLayout.NORTH);
		add(p_input);
		add(p_south, BorderLayout.SOUTH);

		bt_regist.addActionListener(this);
		bt_prev.addActionListener(this);

		setPreferredSize(new Dimension(400, 300));
		setVisible(false);
	}
	
	//�Է��� �ùٸ��� ������� �Է�ĭ �ʱ�ȭ
	public void initialSet(){
		txt_serial.setText("");
		txt_id.setText("");
		txt_pw.setText("");
		txt_name.setText("");
		txt_serial.requestFocus();
	}
	
	public boolean check() {		
		/*if (txt_serial.getText().equals(serial)) {
			if (txt_id.getText().equals("")!=true) {
				if (txt_id.getText().getBytes().length>5) {
					if (new String(txt_pw.getPassword()).equals("")!=true) {
						if (txt_name.getText().equals("")!=true) {							
							return true;							
						}else{
							JOptionPane.showMessageDialog(this, "�̸��� �Է����ּ���");
							initialSet();							
							return false;
						}						
					}else{
						JOptionPane.showMessageDialog(this, "password�� �Է����ּ���");
						initialSet();
						return false;						
					}					
				}else{
					JOptionPane.showMessageDialog(this, "id�� ���̰� ª���ϴ�.");
					initialSet();
					return false;
				}
			} else {
				JOptionPane.showMessageDialog(this, "id�� �Է����ּ���");
				initialSet();
				return false;
			} 
		}else{
			JOptionPane.showMessageDialog(this, "serial number�� �ùٸ��� �ʽ��ϴ�.");
			initialSet();
			return false;
		}*/
		
		if (txt_serial.getText().equals(serial)==false) {//�̷��� ���� else���� �Ƚᵵ �ȴ�.
			JOptionPane.showMessageDialog(this, "serial number�� �ùٸ��� �ʽ��ϴ�.");
			initialSet();
			return false;
		}		
		if (txt_id.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "id�� �Է����ּ���");
			initialSet();
			return false;
		}		
		if (txt_id.getText().getBytes().length<6) {
			JOptionPane.showMessageDialog(this, "id�� 6���̻����� ���ּ���");
			initialSet();
			return false;
		}
		if (new String(txt_pw.getPassword()).equals("")) {
			JOptionPane.showMessageDialog(this, "password�� �Է����ּ���");
			initialSet();
			return false;
		}
		if (new String(txt_pw.getPassword()).getBytes().length<4) {
			JOptionPane.showMessageDialog(this, "password�� 4���̻����� ���ּ���");
			initialSet();
			return false;
		}
		if(txt_name.getText().equals("")){
			JOptionPane.showMessageDialog(this, "�̸��� �Է����ּ���");
			initialSet();							
			return false;		
		}		
		return true;//���� ������ ��� �ƴҰ�� true�� ��ȯ�ȴ�.
	}

	public void regist() {
		if (check()) {
			StringBuffer sql=new StringBuffer();
			sql.append("insert into hotel_admin(hotel_admin_id, hotel_admin_pw, hotel_admin_name)");
			sql.append("values (?, ?, ?)");
			
			PreparedStatement pstmt=null;
			try {
				pstmt=con.prepareStatement(sql.toString());
				pstmt.setString(1, txt_id.getText());
				pstmt.setString(2, new String(txt_pw.getPassword()));
				pstmt.setString(3, txt_name.getText());				
				int result=pstmt.executeUpdate();
				if (result==1) {
					System.out.println("���Լ���");
					JOptionPane.showMessageDialog(this, "��ϵǾ����ϴ�");
					main.setPage(0);
				}else {
					System.out.println("���Խ���");
					
				}				
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "�ߺ��Ǵ� id�� �ֽ��ϴ�.");
				initialSet();
			} finally {
				if (pstmt!=null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}					
				}
			}			
		}
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = (Object) e.getSource();
		if (obj == bt_prev) {
			main.setPage(0);
		} else if (obj == bt_regist) {
			regist();
		}
	}

}
