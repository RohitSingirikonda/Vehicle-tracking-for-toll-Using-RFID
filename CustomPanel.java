package vehicle;
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.MultipleGradientPaint;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;
public class CustomPanel extends JPanel {
	String title;
public CustomPanel() {
	super();
}
public void paintComponent(Graphics g2){
	Graphics2D g = (Graphics2D) g2;
	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	Shape shape = null;
	Paint paint = null;
	Stroke stroke = null;
	int width = getWidth();
	int height = getHeight();
	paint = new Color(0, 0, 0, 255);
	shape = new RoundRectangle2D.Double(0, 0, width, height, 26, 26);
	g.setPaint(paint);
	g.fill(shape);
	paint = new Color(0, 0, 0, 255);
	shape = new RoundRectangle2D.Double(1, 1, width - 2, height - 2, 15.0,15.0);
	g.setPaint(paint);
	paint = new Color(136, 136, 136, 136);
	stroke = new BasicStroke(1.0f, 0, 0, 4.0f, null, 0.0f);
	shape = new RoundRectangle2D.Double(1, 1, width - 2, height - 2, 15.0,15.0);
	g.setPaint(paint);
	g.setStroke(stroke);
	g.draw(shape);
	paint = new LinearGradientPaint(new Point2D.Double(321.125,205.1853790283203),new Point2D.Double(321.125, 300.3125),new float[] { 0.0f, 1.0f }, new Color[] {new Color(255, 255, 255, 255), new Color(0, 0, 0, 0) },MultipleGradientPaint.CycleMethod.NO_CYCLE,MultipleGradientPaint.ColorSpaceType.SRGB, new AffineTransform(1.0f, 0.0f, 0.0f, 1.0f, -189.4317169189453f,-220.40145874023438f));
	shape = new GeneralPath();
	((GeneralPath) shape).moveTo(15.1308, 1.8172914);
	((GeneralPath) shape).curveTo(9.820799, 1.8172914, 2.1307993, 4.507291,2.1307993, 13.817291);
	((GeneralPath) shape).lineTo(2.1307993, 31.78604);
	((GeneralPath) shape).curveTo(39.09004, 37.30802, 86.78241, 40.69229,width / 2, 40.69229);
	((GeneralPath) shape).curveTo(width / 2, 40.69229, width / 2 + 30,38.130333, width - 1, 33.84854);
	((GeneralPath) shape).lineTo(width - 1, 11.817291);
	((GeneralPath) shape).curveTo(width - 1, 6.507291, width - 5,2.8172914, width - 13, 1.8172914);
	((GeneralPath) shape).lineTo(18.1308, 1.8172914);
	((GeneralPath) shape).closePath();
	g.setPaint(paint);
	g.fill(shape);
	g.setFont(new Font("Microsoft Sanserif",Font.BOLD,20));
	g.setColor(new Color(0,0,0));
	g.drawString(title, width/4+1, 31);
	g.setColor(new Color(0,255,0));
	g.drawString(title, width/4, 30);
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
}