package persistencia;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static persistencia.FicheroUtils.obtenerMap;

public class CustomBarChartToPDF {

    public static void main(String[] args) {
        // Crear el conjunto de datos
        DefaultCategoryDataset dataset;
        List<JFreeChart> charts = new ArrayList<>();

        for (int i = 8; i <= 1024;) {
            dataset = new DefaultCategoryDataset();
            DefaultCategoryDataset finalDataset = dataset;
            obtenerMap().get(i).forEach((nombre, tiempo) -> {
                finalDataset.addValue((Number) tiempo, (Comparable) "Category 1", (Comparable) nombre);
            });

            // Crear el gráfico de barras 2D
            JFreeChart barChart = ChartFactory.createBarChart(
                    "Matriz [" + i + "x" + i + "]" ,
                    "Categoría",
                    "Tiempo milisegundos",
                    dataset
            );

            // Personalizar el gráfico
            customizeChart(barChart);
            charts.add(barChart);
            i *= 2;
        }

        // Guardar el gráfico en un archivo PDF
        saveChartAsPDF(charts, "CustomBarChart.pdf", 800, 600);
    }

    public static void customizeChart(JFreeChart chart) {
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);

        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                Color[] colors = new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.ORANGE, Color.CYAN, Color.MAGENTA};
                return colors[column % colors.length];
            }
        };

        plot.setRenderer(renderer);

        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotar las etiquetas en 45 grados si es necesario
    }

    public static void saveChartAsPDF(List<JFreeChart> charts, String fileName, int width, int height) {
        Document document = new Document(PageSize.A4.rotate());
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            for (JFreeChart chart : charts) {
                BufferedImage bufferedImage = chart.createBufferedImage(width, height);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ChartUtils.writeBufferedImageAsPNG(byteArrayOutputStream, bufferedImage);
                com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(byteArrayOutputStream.toByteArray());
                document.add(image);
                document.newPage(); // Agregar una nueva página para el siguiente gráfico
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
