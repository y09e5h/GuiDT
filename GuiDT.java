import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
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
		obj.ta=obj.ta+"\n"+line+"\nadd("+obj.nametf.getText()+");";
	}
	public void fullCodeWriter()
	{
		String code = "import java.awt.*;\nimport javax.swing.*;\nclass MyWindow extends JFrame{\n"+obj.varString+"\nMyWindow(){\n"+obj.ta+"\nsetSize(500,500);\nsetLayout(null);\nsetVisible(true);\n}\n}\npublic class "+obj.nametf.getText()+"{\npublic static void main(String[] args){\nnew MyWindow();\n}\n}";
	try{
	FileWriter fw=new FileWriter(obj.nametf.getText()+".java");
	fw.write(code);
	JOptionPane.showMessageDialog(null,"please check the code in "+obj.nametf.getText()+".java file or\n to compile and run program \n type cmd :\njavac "+obj.nametf.getText()+".java\njava "+obj.nametf.getText()+"\n OR click Run button","Code is submited",JOptionPane.WARNING_MESSAGE);
	fw.close(); 
	}
	catch(Exception e)
	{
		System.out.println("here exception occer ---"+e);
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
		Graphics g = obj.draw.getGraphics();
		int j=0;
		for(int i=0;i<1900;i+=20){
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
		for(int i=0;i<1200;i+=20){
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
			obj.itm.setText("Label Selected");
			obj.opr=1;
		}
		else if(e.getSource()==obj.cb)
		{
			obj.itm.setText("Button selected");
			
			obj.opr=2;
		}
		else if(e.getSource()==obj.tbx)
		{
			obj.itm.setText("TextField selected");
			obj.opr=3;
		}
		else if(e.getSource()==obj.submit)
		{
			obj.nametf.setEditable(false);
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
		if(obj.txttf.getText().isEmpty()|| obj.nametf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(obj,"Empty name and Text is not allowed !!","Empty!!",JOptionPane.WARNING_MESSAGE);  
		}
		else if(obj.nmlist.contains(obj.nametf.getText()))
		{
			JOptionPane.showMessageDialog(obj,"Name is used please try other name","Name is used!!",JOptionPane.WARNING_MESSAGE);
		}
		else{
		switch(obj.opr)
		{
			case 1:
			{
			//g.setColor(Color.BLACK);
			//g.drawString("some text :",x,y);
				obj.nmlist.add(obj.nametf.getText());
				Label l = new Label(obj.txttf.getText());
				l.setBounds(x,y,width,hight);
				obj.varString = obj.varString+"JLabel "+obj.nametf.getText()+"= new JLabel(\""+obj.txttf.getText()+"\");\n";
				obj.draw.add(l);
			break;
			}
			case 2:
			{
			//g.setColor(Color.BLUE);
			//g.fillRect(x,y,width,hight);
				obj.nmlist.add(obj.nametf.getText());
				Button b = new Button(obj.txttf.getText());
				b.setBounds(x,y,width,hight);
				obj.varString = obj.varString+"JButton "+obj.nametf.getText()+"= new JButton(\""+obj.txttf.getText()+"\");\n";
				obj.draw.add(b);
				break;
			}
			case 3:
			{
				obj.nmlist.add(obj.nametf.getText());
				TextField t = new TextField();
				t.setBounds(x,y,width,hight);
				obj.varString = obj.varString+"JTextField "+obj.nametf.getText()+"=new JTextField();\n";
				obj.draw.add(t);
				break;
			}
			default:
			{

				break;
			}
			
		}
		wr.writeCode(obj.nametf.getText()+".setBounds("+x+","+y+","+width+","+hight+");");
	}
		
}
	
	public void mouseEntered(MouseEvent e) {}  
    	public void mouseExited(MouseEvent e) {}  
	public void mouseClicked(MouseEvent e) {}
	
}
class RunProgram implements ActionListener
{
	GuiWindow obj;
	RunProgram(GuiWindow obj)
	{
		this.obj = obj;
	}
	public void actionPerformed(ActionEvent e)
	{
		String s = null;
		try
		{
			String cmd="cd",os=System.getProperty("os.name").toLowerCase();
			
			if(os.contains("linux")||os.contains("mac os x")||os.contains("win"))
			{
			/*	cmd="pwd";
				Process p = Runtime.getRuntime().exec(cmd);
				BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
				s= stdInput.readLine();
			}
			else if(os.contains("win"))
			{
				 s = System.getProperty("user.dir");*/
			}
			else
			{
				throw new RuntimeException(" unsupported os!!");
			}			
			//Process p = Runtime.getRuntime().exec(cmd);
			//BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
			//s= stdInput.readLine();
			
			Runtime.getRuntime().exec("javac "+obj.nametf.getText()+".java");
			Runtime.getRuntime().exec("java "+obj.nametf.getText());
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(obj,""+ex,"\nError while running",JOptionPane.WARNING_MESSAGE);
		}
	}
}
/*class CreadteComponentEvents implements ActionListenre
{
	
}*/
class GuiWindow extends Frame
{
	ArrayList<String> nmlist = new ArrayList<String>();
	TextField tf,nametf,txttf;
	String ta="";
	int opr=1;
	Button tb;
	Button cb,submit;
	Label itm,namel,txtl,complistlbl;
	Button tbx,run;
	String varString="";
	
	Frame draw;
	GuiWindow()
	{
		draw = new Frame("Draw");
		//ta = new TextArea();
		nametf = new TextField();
		tf = new TextField();
		itm = new Label();
		namel= new Label("Name");
		tb =  new Button("Lable");
		submit = new Button("submit");
		cb = new Button("Button");
		tbx = new Button("TextField");
		txtl = new Label("Text");
		complistlbl = new Label("Components :");
		txttf = new TextField();
		run = new Button("Run");
		submit.setBounds(10,532,202,32);
		run.setBounds(10,620,202,32);
		//ta.setBounds(781,53,489,557);
		complistlbl.setBounds(10,80,150,30);
		tbx.setBounds(10,120,100,30);
		itm.setBounds(220,15,180,40);
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
		add(run);
		//add(ta);
		add(namel);
		add(nametf);
		add(tbx);
		add(itm);
		add(tf);
		add(tb);
		add(cb);
		add(complistlbl);		
		//setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
		RunProgram pr  = new RunProgram(this);
		MouseEvents me = new MouseEvents(this);
		ButtonEvent be = new ButtonEvent(this);
		run.addActionListener(pr);
		draw.addMouseListener(me);
		tb.addActionListener(be);
		cb.addActionListener(be);
		tbx.addActionListener(be);
		submit.addActionListener(be);
		draw.setSize(1050,800);
		draw.setLocation(300,0);
		draw.setLayout(null);
		draw.setVisible(true);
		setSize(300,800);
		setLayout(null);
		setLocation(0,0);
		setVisible(true);
		JOptionPane.showMessageDialog(this,"First select the component");  
		JOptionPane.showMessageDialog(this,"then type name and text for component:");
		JOptionPane.showMessageDialog(draw,"then draw the component");
		JOptionPane.showMessageDialog(this,"after complition submit the code"); 
	}
	
}
class GuiDT
{
	public static void main(String[] args)
	{	
		new GuiWindow();
		
	}
}
