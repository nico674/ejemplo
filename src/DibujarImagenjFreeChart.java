import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultKeyedValuesDataset;
import org.jfree.data.general.DefaultPieDataset;

public class DibujarImagenjFreeChart {
 private static DibujarImagenjFreeChart instancia;
 public static DibujarImagenjFreeChart getInstancia(){
 if (instancia==null){
     instancia= new DibujarImagenjFreeChart();
 }
     return instancia;
 }
private Conexion c = new Conexion();
public Connection cn=c.conectar();
public String consultar =" ";
public void mostrarproducto() throws SQLException, IOException{
    consultar="select * from pr";
    Statement statement= cn.createStatement();
    ResultSet rs =statement.executeQuery(consultar);
    DefaultPieDataset dataset=new DefaultPieDataset();
    while(rs.next()){
        dataset.setValue(rs.getString("nombre"), Integer.parseInt(rs.getString("stock")));
    } //dibujamos el grafico
    JFreeChart chart = ChartFactory.createPieChart("grafica de stock producto", dataset, true, true, false);
    //tamaño para la imagen
    int ancho= 560;
    int alto= 380;
    //save photo in file
    File  f = new File("Grafico.png");
    ChartUtilities.saveChartAsPNG(f, chart, ancho, alto);
}
}
