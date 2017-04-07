package gui.graphic;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import gui.utils.translation.DragTranslation;

public class GraphicWindow extends JFrame {

	private static final long serialVersionUID = -7962589797635660932L;
	private JPanel contentPane;
	private GraphicController gcsAngle;
	private GraphicPanel graphicPanel;
	private GraphicController gcsAngle2;
	private DragTranslation dragMouse;
	private DragTranslation dragSlider1;
	private DragTranslation dragSlider2;
	private GraphicController gcsZoom;

	/**
	 * Create the frame.
	 */
	public GraphicWindow() {
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				System.out.println(arg0.getKeyCode());
				if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE)
					System.exit(0);
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		graphicPanel = new GraphicPanel();
		graphicPanel.setBorder(new LineBorder(new Color(100, 100, 100), 2));
		graphicPanel.setBounds(10, 11, 1034, 660);
		contentPane.add(graphicPanel);
		graphicPanel.setLayout(null);
		
		dragMouse = graphicPanel.newDragTranslation();
		dragSlider1 = graphicPanel.newDragTranslation();
		dragSlider2 = graphicPanel.newDragTranslation();
		
		gcsAngle = new GraphicController.AngleSlider();
		gcsAngle.setBounds(1054, 11, 200, 51);
		gcsAngle.addChangeListener(event -> {
			dragSlider1.translate(gcsAngle.getDoubleValue()*10, 0);
		});
		contentPane.add(gcsAngle);
		
		
		gcsAngle2 = new GraphicController.AngleSlider();
		gcsAngle2.setBounds(1054, 73, 200, 23);
		gcsAngle2.addChangeListener(event -> {
			dragSlider2.translate(0, gcsAngle2.getDoubleValue()*10);
		});
		contentPane.add(gcsAngle2);
		
		gcsZoom = new GraphicController(0, 10);
		gcsZoom.setBounds(1054, 107, 200, 23);
		gcsZoom.addChangeListener(event -> {
			graphicPanel.setZoom(gcsZoom.getDoubleValue());
		});
		contentPane.add(gcsZoom);
		graphicPanel.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent event){
				dragMouse.startTranslation(	event.getPoint().getX()-graphicPanel.getSize().width/2d,
											event.getPoint().getY()-graphicPanel.getSize().height/2d);
			}
			
			public void mouseReleased(MouseEvent event){
				dragMouse.endTranslation();
			}
		});
		graphicPanel.addMouseMotionListener(new MouseAdapter(){
			public void mouseDragged(MouseEvent event) {
				dragMouse.translate(event.getPoint().getX()-graphicPanel.getSize().width/2d,
									event.getPoint().getY()-graphicPanel.getSize().height/2d);
			}
		});
		graphicPanel.addMouseWheelListener(event -> {
			double zoom = graphicPanel.getZoom();
			if(event.getWheelRotation() == -1)
				graphicPanel.setZoom(zoom*2);
			else
				graphicPanel.setZoom(zoom/2);
		});
	}
}
