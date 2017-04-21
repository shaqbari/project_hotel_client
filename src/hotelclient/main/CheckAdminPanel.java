package hotelclient.main;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hotelclient.ClientMain;

public class CheckAdminPanel extends JPanel implements ActionListener, ItemListener{
	ClientMain main;
	Connection con;
	
	JPanel p_north, p_input_member, p_south;
	Font font;
	JLabel la_title, la_id, la_pw;
	CheckboxGroup group;
	Checkbox ch_quest, ch_member;		
	JTextField txt_id;
	JPasswordField txt_pw;	
	JButton bt_check, bt_regist;
	
	ArrayList<Hotel_admin>hotel_admins=new ArrayList<Hotel_admin>();
	
	String adminId="admin";
	String adminPw="admin";
	
	public CheckAdminPanel(ClientMain main) {		
		this.main=main;
		con=main.con;
		
		p_north=new JPanel();
		p_input_member=new JPanel();
		p_south=new JPanel();
		la_title=new JLabel("ȣ�ڼ��񽺽ý��� �α���");
		group=new CheckboxGroup();
		ch_quest=new Checkbox("��ȸ��", group, true);
		ch_member=new Checkbox("ȸ��", group, false);
		
		la_id=new JLabel("id");
		la_pw=new JLabel("pw");
		font=new Font("���� ���", font.PLAIN, 20);
		txt_id=new JTextField("admin", 10);
		txt_pw=new JPasswordField("admin", 10);
		bt_check=new JButton("Ȯ��");
		bt_regist=new JButton("���");
		
		this.setLayout(new BorderLayout());
		
		p_input_member.setPreferredSize(new Dimension(400, 100));
		p_input_member.setLayout(new GridLayout(2, 2));
		
		la_title.setFont(new Font("�������", font.BOLD, 25));
		la_id.setFont(font);
		la_pw.setFont(font);
		txt_id.setFont(font);
		txt_pw.setFont(font);
		
		p_north.add(la_title);
		p_north.add(ch_quest);
		p_north.add(ch_member);
		
		p_input_member.add(la_id);
		p_input_member.add(txt_id);
		p_input_member.add(la_pw);
		p_input_member.add(txt_pw);
		
		p_south.add(bt_check);
		p_south.add(bt_regist);
				
		add(p_north, BorderLayout.NORTH);
		add(p_input_member);
		add(p_south, BorderLayout.SOUTH);

		txt_pw.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				int key=e.getKeyCode();
				if (key==KeyEvent.VK_ENTER) {
					check();
				}
			}
		});
		bt_check.addActionListener(this);
		bt_regist.addActionListener(this);
		
		ch_quest.addItemListener(this);
		ch_member.addItemListener(this);
				
		
		setSize(400, 200);
		setVisible(true);		
	}
	
	public void setHotel_admins(){
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select * from hotel_admin";
		
		try {
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			
			hotel_admins.removeAll(hotel_admins);//���� �����.
			
			while (rs.next()) {
				Hotel_admin dto=new Hotel_admin();
				dto.setHotel_admin_id(rs.getString("hotel_admin_id"));
				dto.setHotel_admin_pw(rs.getNString("hotel_admin_pw"));
				dto.setHotel_admin_name(rs.getString("hotel_admin_name"));
				
				hotel_admins.add(dto);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 		
		}		
	}
	
	//���̵� ��� ��ġ�ϸ� hotelMain�� ���̰��Ѵ�.
	public void check(){
		setHotel_admins();
		
		for (int i = 0; i < hotel_admins.size(); i++) {
			if (hotel_admins.get(i).getHotel_admin_id().equals(txt_id.getText())) {//���̵� ��ġ�ϸ�
				if (hotel_admins.get(i).getHotel_admin_pw().equals(new String(txt_pw.getPassword()))) {//��й�ȣ�� ��ġ�ϸ�
					this.setVisible(false);	
					txt_id.setText("");
					txt_pw.setText("");
					txt_id.requestFocus();
					
					String name=hotel_admins.get(i).getHotel_admin_name();
					JOptionPane.showMessageDialog(this, name+"�� �ݰ����ϴ�.");
					main.la_user.setText(name);
					main.setPage(2);//hotelmain�������� ���̰� �Ѵ�.		
					return; //pw�� id��� ��ġ�ϸ� �Ʒ��� ������� �ʴ´�.
				}
			}
		}
		
		txt_id.setText("");
		txt_pw.setText("");
		txt_id.requestFocus();
		JOptionPane.showMessageDialog(this, "�α��������� ��Ȯ���� �ʽ��ϴ�.");
	}

	
	public void actionPerformed(ActionEvent e) {
		Object obj=(Object)e.getSource();
		if (obj==bt_check) {
			check();
		}else if(obj==bt_regist){
			main.setPage(1); //RegAdminPanel���̰� �Ѵ�.
		}
	}

	public void itemStateChanged(ItemEvent e) {
		
	}	
	
}
