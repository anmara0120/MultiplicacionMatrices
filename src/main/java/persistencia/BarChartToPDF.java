package persistencia;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BarChartToPDF {

    public static void main(String[] args) {
        // Crear el conjunto de datos
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(1.0, "Category 1", "Bar 1");
        dataset.addValue(4.0, "Category 1", "Bar 2");
        dataset.addValue(3.0, "Category 1", "Bar 3");
        dataset.addValue(5.0, "Category 1", "Bar 4");
        dataset.addValue(5.0, "Category 2", "Bar 1");
        dataset.addValue(7.0, "Category 2", "Bar 2");
        dataset.addValue(6.0, "Category 2", "Bar 3");
        dataset.addValue(8.0, "Category 2", "Bar 4");

        // Crear el gráfico de barras
        JFreeChart barChart = ChartFactory.createBarChart(
                "Diagrama de Barras", // Título del gráfico
                "Categoría", // Etiqueta del eje X
                "Valor", // Etiqueta del eje Y
                dataset, // Conjunto de datos
                PlotOrientation.VERTICAL, // Orientación del gráfico
                true, // Incluye leyenda
                true, // Incluye tooltips
                false // No se generan URLs
        );

        // Guardar el gráfico en un archivo PDF
        saveChartAsPDF(barChart, "BarChartExample.pdf", 800, 600);
    }

    public static void saveChartAsPDF(JFreeChart chart, String fileName, int width, int height) {
        Document document = new Document(PageSize.A4.rotate());
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();
            BufferedImage bufferedImage = chart.createBufferedImage(width, height);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ChartUtils.writeBufferedImageAsPNG(byteArrayOutputStream, bufferedImage);
            com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(byteArrayOutputStream.toByteArray());
            document.add(image);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }
}
