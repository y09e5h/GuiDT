//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
class Write
{
	GuiWindow obj;
	Write(GuiWindow obj)
	{
		this.obj=obj;
	}
	public void writeCode(String line)
	{
		obj.tf.setText(line);
		obj.ta.setText(obj.ta.getText()+"\n"+line+"\nadd("+obj.nametf.getText()+");");
	}
	public void fullCodeWriter()
	{
		String code = "import java.awt.*;\nimport javax.swing.*;\nclass MyWindow extends JFrame{\n"+obj.varString+"\nMyWindow(){\n"+obj.ta.getText()+"\nsetSize(500,500);\nsetLayout(null);\nsetVisible(true);\n}\n}\npublic class "+obj.nametf.getText()+"{\npublic static void main(String[] args){\nnew MyWindow();\n}\n}";
	try{
	FileWriter fw=new FileWriter(obj.nametf.getText()+".java");
	fw.write(code);
	fw.close(); 
	}
	catch(Exception e)
	{
		System.out.println(e);
	}   
	}
}
class ButtonEvent implements ActionListener
{
	GuiWindow obj;
	Write wr;
	ButtonEvent(GuiWindow obj)
	{
		this.obj = obj;
		
	}
	public void actionPerformed(ActionEvent e)
	{
		Graphics g = obj.getGraphics();
		int j=0;
		for(int i=0;i<1900;i+=10){
			if(j%2==0)
			{
				g.setColor(Color.BLUE);
			}
			else
			{
				g.setColor(Color.BLACK);
			}
			j++;
			g.drawLine(i,0,i,1200);
		}
		j=0;
		for(int i=0;i<1200;i+=10){
			if(j%2==0)
			{
				g.setColor(Color.BLUE);
			}
			else
			{
				g.setColor(Color.BLACK);
			}
			j++;			
			g.drawLine(0,i,1900,i);
		}
		if(e.getSource()==obj.tb)
		{
			obj.itm.setText("Label");
			obj.opr=1;
		}
		else if(e.getSource()==obj.cb)
		{
			obj.itm.setText("Button");
			
			obj.opr=2;
		}
		else if(e.getSource()==obj.tbx)
		{
			obj.itm.setText("TextField");
			obj.opr=3;
		}
		else if(e.getSource()==obj.submit)
		{
			wr = new Write(obj);
			wr.fullCodeWriter();
		}

	}
	
}
class MouseEvents implements MouseListener
{
	int x,y,width,hight;
	GuiWindow obj;
	Write wr;
	MouseEvents(GuiWindow obj)
	{
		this.obj = obj;
		wr= new Write(obj);
	}
	public void mousePressed(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
	}
	public void mouseReleased(MouseEvent e)
	{
		width = x - e.getX();
		hight = e.getY() - y;
		if(width<0)
		{
			width = -width;
		}
		if(hight<0)
		{
			hight = -hight;
		}
		//Graphics g = obj.getGraphics();
		switch(obj.opr)
		{
			case 1:
			{
			//g.setColor(Color.BLACK);
			//g.drawString("some text :",x,y);
			Label l = new Label(obj.txttf.getText());
			l.setBounds(x,y,width,hight);
			obj.varString = obj.varString+"JLabel "+obj.nametf.getText()+"= new JLabel(\""+obj.txttf.getText()+"\");\n";
			obj.add(l);
			break;
			}
			case 2:
			{
			//g.setColor(Color.BLUE);
			//g.fillRect(x,y,width,hight);
			Button b = new Button(obj.txttf.getText());
			b.setBounds(x,y,width,hight);
			obj.varString = obj.varString+"JButton "+obj.nametf.getText()+"= new JButton(\""+obj.txttf.getText()+"\");\n";
			obj.add(b);
			break;
			}
			case 3:
			{
				TextField t = new TextField();
				t.setBounds(x,y,width,hight);
				obj.varString = obj.varString+"JTextField "+obj.nametf.getText()+"=new JTextField();\n";
				obj.add(t);
				break;
			}
			default:
			{

				break;
			}
		}
		wr.writeCode(obj.nametf.getText()+".setBounds("+x+","+y+","+width+","+hight+");");
		
	}
	
	public void mouseEntered(MouseEvent e) {}  
    	public void mouseExited(MouseEvent e) {}  
	public void mouseClicked(MouseEvent e) {}
	
}
class GuiWindow extends Frame 
{
	TextField tf,nametf,txttf;
	TextArea ta;
	int opr=1;
	Button tb;
	Button cb,submit;
	Label itm,namel,txtl;
	Button tbx;
	String varString="";
	GuiWindow()
	{
		ta = new TextArea();
		nametf = new TextField();
		tf = new TextField();
		itm = new Label();
		namel= new Label("Name");
		tb =  new Button("Lable");
		submit = new Button("submit");
		cb = new Button("Button");
		tbx = new Button("TextField");
		txtl = new Label("Text");
		txttf = new TextField();
		submit.setBounds(792,632,202,32);
		ta.setBounds(781,53,489,557);
		tbx.setBounds(10,120,100,30);
		itm.setBounds(10,80,140,40);
		tb.setBounds(10,160,100,30);
		cb.setBounds(10,200,100,30);
		tf.setBounds(10,40,200,30);
		namel.setBounds(10,250,50,30);
		nametf.setBounds(10,282,70,30);
		txtl.setBounds(10,314,70,30);
		txttf.setBounds(10,348,70,30);
		add(txtl);
		add(txttf);
		add(submit);
		add(ta);
		add(namel);
		add(nametf);
		add(tbx);
		add(itm);
		add(tf);
		add(tb);
		add(cb);
		//setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		MouseEvents me = new MouseEvents(this);
		ButtonEvent be = new ButtonEvent(this);
		addMouseListener(me);
		tb.addActionListener(be);
		cb.addActionListener(be);
		tbx.addActionListener(be);
		submit.addActionListener(be);
		setSize(500,500);
		setLayout(null);
		setVisible(true);
		
	}
	
}
class GuiDT
{
	public static void main(String[] args)
	{	
		new GuiWindow();
		
	}
}
