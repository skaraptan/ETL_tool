import com.jaunt.ResponseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Yoga2pro on 23.01.2017.
 */
public class StartWindow extends JFrame{
    private JPanel panel;
    private JButton wholeETLButton;
    private JButton extractButton;
    private JButton transformButton;
    private JTextField textField1;
    private JButton loadButton;
    private JButton clearReviewsButton;
    private JButton exportToCSVButton;
    private JTextField textField2;
    private String url;
    private ETLProcessingService currentEtlProcessingService;
    public StartWindow(){
        super("ETL tool for ceneo.pl data");
        setContentPane(panel);
        setLocationRelativeTo(null);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        wholeETLButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                url = textField1.getText();
                ETLProcessingService etlProcessingService = null;
                try {
                    etlProcessingService = new ETLProcessingService(url);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ResponseException e1) {
                    e1.printStackTrace();
                }
                etlProcessingService.doETL();
            }
        });
        extractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                url = textField1.getText();
                try {
                    currentEtlProcessingService = new ETLProcessingService(url);
                } catch (IOException e1) {
                    e1.printStackTrace();
                } catch (ResponseException e1) {
                    e1.printStackTrace();
                }
                currentEtlProcessingService.extractProduct();
                currentEtlProcessingService.extractReviews();
                JOptionPane.showConfirmDialog(panel, "Extraction completed!\n" + currentEtlProcessingService.getUntransformedReviews().size()
                        + " reviews for product with id: " + currentEtlProcessingService.getProductCode() + "  extracted");
            }
        });
        transformButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentEtlProcessingService.transformProduct();
                currentEtlProcessingService.transformReviews();
                JOptionPane.showConfirmDialog(panel, "Transformation completed!\n" + currentEtlProcessingService.getReviews().size()
                        + " reviews for product with id: " + currentEtlProcessingService.getProduct().getProductId() + "  transformed");
            }
        });
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentEtlProcessingService.loadAll();
                JOptionPane.showConfirmDialog(panel, "Database uploading process completed!\n" + currentEtlProcessingService.getReviews().size()
                        + " reviews for product with id: " + currentEtlProcessingService.getProduct().getProductId() + "  uploaded to database");
            }
        });
        exportToCSVButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CSVExportService csvExportService = new CSVExportServiceImpl();
                try {
                    if (currentEtlProcessingService.getUntransformedReviews().isEmpty()) {
                        JOptionPane.showConfirmDialog(panel, "First make extraction and transformation of your reviews!");
                    }
                    if (currentEtlProcessingService.getReviews().isEmpty() && !currentEtlProcessingService.getUntransformedReviews().isEmpty())
                        JOptionPane.showConfirmDialog(panel, "Now make transformation of your reviews!");
                    else {
                        try {
                            csvExportService.exportToCSV(currentEtlProcessingService.getReviews());
                            JOptionPane.showConfirmDialog(panel, "Location of your csv file is : \n" + csvExportService.getPath());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                catch (NullPointerException npe){
                    JOptionPane.showConfirmDialog(panel, "First make extraction and transformation of your reviews!");
                }
            }
        });
        clearReviewsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ReviewStoringService reviewStoringService = new ReviewStoringServiceImpl();
                reviewStoringService.deleteReviews(textField2.getText());
                JOptionPane.showConfirmDialog(panel, "Reviews for product with id " + textField2.getText() + " deleted");
            }
        });

    }

}
