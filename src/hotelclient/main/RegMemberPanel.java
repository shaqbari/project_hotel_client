package hotelclient.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hotelclient.ClientMain;
import hotelclient.network.MemberRegistReact;
import hotelclient.network.MemberRegistRequest;

public class RegMemberPanel extends JPanel implements ActionListener {
	ClientMain main;
	
	JPanel p_north, p_input, p_south;
	JLabel la_title;
	JLabel la_id, la_pw, la_name, la_email, la_phone;
	JTextField txt_id, txt_name, txt_email, txt_phone;
	ArrayList<Component> registInfo=new ArrayList<Component>();
	
	Font font;
	JPasswordField txt_pw;
	JButton bt_regist, bt_prev;

	
	public RegMemberPanel(ClientMain main) {
		this.main = main;
					
		p_north = new JPanel();
		p_input = new JPanel();
		p_south = new JPanel();

		la_title = new JLabel("회원 등록");
		la_id = new JLabel("ID");
		la_pw = new JLabel("PW");
		la_name = new JLabel("이름");
		la_email = new JLabel("e-mail");
		la_phone=new JLabel("전화번호");
		txt_id = new JTextField(15);
		txt_name = new JTextField(15);
		txt_pw = new JPasswordField(15);
		txt_email=new JTextField(15);
		txt_phone=new JTextField(15);
		
		font = new Font("맑은 고딕", font.PLAIN, 18);
		txt_id.setFont(font);
		txt_name.setFont(font);
		txt_pw.setFont(font);
		txt_email.setFont(font);
		txt_phone.setFont(font);

		bt_regist = new JButton("등록");
		bt_prev = new JButton("이전");

		setLayout(new BorderLayout());
		p_input.setLayout(new GridLayout(5, 2));
		p_input.setPreferredSize(new Dimension(400, 200));
		
		la_title.setFont(new Font("맑은고딕", font.BOLD, 25));
		la_id.setFont(font);
		la_pw.setFont(font);
		la_name.setFont(font);
		la_email.setFont(font);
		la_phone.setFont(font);

		p_north.add(la_title);

		p_input.add(la_id);
		p_input.add(txt_id);
		p_input.add(la_pw);
		p_input.add(txt_pw);
		p_input.add(la_name);
		p_input.add(txt_name);
		p_input.add(la_email);
		p_input.add(txt_email);
		p_input.add(la_phone);
		p_input.add(txt_phone);

		p_south.add(bt_regist);
		p_south.add(bt_prev);

		add(p_north, BorderLayout.NORTH);
		add(p_input);
		add(p_south, BorderLayout.SOUTH);

		bt_regist.addActionListener(this);
		bt_prev.addActionListener(this);

		setPreferredSize(new Dimension(400, 350));
		setVisible(false);
	}
	
	//입력이 올바르지 않을경우 입력칸 초기화
	public void initialSet(){
		txt_id.setText("");
		txt_pw.setText("");
		txt_name.setText("");
		txt_email.setText("");
		txt_phone.setText("");
		txt_id.requestFocus();
	}
	
	public boolean check() {		
		if (txt_id.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "id를 입력해주세요");
			initialSet();
			return false;
		}		
		if (txt_id.getText().getBytes().length<6) {
			JOptionPane.showMessageDialog(this, "id는 6자이상으로 해주세요");
			initialSet();
			return false;
		}
		if (new String(txt_pw.getPassword()).length()==0) {
			JOptionPane.showMessageDialog(this, "password를 입력해주세요");
			initialSet();
			return false;
		}
		if (new String(txt_pw.getPassword()).getBytes().length<4) {
			JOptionPane.showMessageDialog(this, "password는 4자이상으로 해주세요");
			initialSet();
			return false;
		}
		if(txt_name.getText().length()==0){
			JOptionPane.showMessageDialog(this, "이름를 입력해주세요");
			initialSet();							
			return false;		
		}		
		if(txt_email.getText().length()==0){
			JOptionPane.showMessageDialog(this, "이메일를 입력해주세요");
			initialSet();							
			return false;		
		}		
		if(txt_phone.getText().length()==0){
			JOptionPane.showMessageDialog(this, "전화번호를 입력해주세요");
			initialSet();							
			return false;		
		}		
		return true;//위의 조건이 모두 아닐경우 true가 반환된다.
	}

	public void regist() {
		if (check()) {
			MemberRegistRequest registRequest=new MemberRegistRequest(main);
			registRequest.requestJSON(txt_id.getText(), new String(txt_pw.getPassword()), txt_name.getText(), txt_phone.getText(), txt_email.getText());
		}
		
		/*if (check()) {
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
					System.out.println("삽입성공");
					JOptionPane.showMessageDialog(this, "등록되었습니다");
					main.setPage(0);
				}else {
					System.out.println("삽입실패");
					
				}				
			} catch (SQLException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, "중복되는 id가 있습니다.");
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
		}*/
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
