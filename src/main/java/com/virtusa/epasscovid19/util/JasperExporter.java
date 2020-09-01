package com.virtusa.epasscovid19.util;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.java.Log;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

@Service
@Log
public class JasperExporter {

    JasperReport jasperReport;
    JasperPrint jasperPrint;
    OutputStream outputStream;
    File file;
    JRXlsExporter xlsExporter;

    @Autowired
    HikariDataSource dataSource;

    public static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            // commons-io
            //IOUtils.copy(inputStream, outputStream);

        }

    }

    public void jasperExporterPDF(HashMap jasperParameter, String jrxmlpath, String fileName, HttpServletResponse response) throws IOException {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            outputStream = response.getOutputStream();
            jasperReport = JasperCompileManager.compileReport(jrxmlpath);
            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);
            file = File.createTempFile("output.", ".pdf");
            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=" + fileName + ".pdf");
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                log.info("about to close db connection");
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }


    }

    public String jasperExporterBASE64(long id, HttpServletRequest request, HashMap jasperParameter, String jrxmlpath, String fileName, HttpServletResponse response) throws IOException {
        Connection connection = null;

        String base64 = "";
        try {
            connection = dataSource.getConnection();
            outputStream = response.getOutputStream();
            jasperReport = JasperCompileManager.compileReport(jrxmlpath);

            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);

            file = File.createTempFile("output.", ".pdf");

            response.setContentType("application/pdf");
            response.setHeader("Content-disposition", "inline; filename=" + fileName + ".pdf");

            System.out.println("=-=-=-=-=-=-=-=-==-=---=>>>>>>>>>>>>>>" + file.getAbsolutePath());


            File destination = new File(request.getServletContext().getRealPath("/") + "pospdf" + System.getProperty("file.separator") + fileName + id + ".pdf");
            JasperExportManager.exportReportToPdfFile(jasperPrint, destination.getAbsolutePath());


//			InputStream targetStream = new FileInputStream(file);
//			copyInputStreamToFile(targetStream, destination);

//            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
//
//            try {
//
//                byte[] buffer = new byte[(int) file.length() + 100];
//                @SuppressWarnings("resource")
//                FileInputStream fileInputStreamReader = new FileInputStream(file);
//                byte[] bytes = new byte[(int)file.length()];
//                fileInputStreamReader.read(bytes);
//                base64 = Base64.getEncoder().encodeToString(bytes);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (connection != null) {
                log.info("about to close db connection");
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }
        return base64;

    }

    public void jasperExporterEXCEL(HashMap jasperParameter, String jrxmlpath, String fileName, HttpServletResponse response) throws IOException {

        Connection connection = null;

        try {
            connection = dataSource.getConnection();
            outputStream = response.getOutputStream();
            jasperReport = JasperCompileManager.compileReport(jrxmlpath);

            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);//DBConnection.getConnection()

            file = File.createTempFile("output.", ".xls");

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition", "inline; filename=" + fileName + ".xls");

            //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            xlsExporter = new JRXlsExporter();
            xlsExporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
            xlsExporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, outputStream);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            xlsExporter.exportReport();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                log.info("about to close db connection");
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }
    }

    public void jasperExporterHTML(HashMap jasperParameter, String jrxmlpath, String fileName, HttpServletResponse response) throws IOException { //java.sql.Connection con=null;

        Connection connection = null;

        try {

            connection = dataSource.getConnection();
            outputStream = response.getOutputStream();
            jasperReport = JasperCompileManager.compileReport(jrxmlpath);
            // con = ((DBConnection) dataSource).getConnection();
            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);


            file = File.createTempFile("output.", ".html");

            response.setContentType("text/html");

            response.setHeader("Content-disposition", "inline; filename=" + fileName + ".html");

            //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            //JasperExportManager.exportReportToHtmlFile(jasperPrint, "output.html");

            /*JRExporter  exporter = new JRHtmlExporter();*/
            HtmlExporter exporter = new HtmlExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);

            exporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                log.info("about to close db connection");
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }


    }

    public JasperPrint jasperPrintMultipalPDF(HashMap jasperParameter, String jrxmlpath, HttpServletResponse response) throws IOException {

        Connection connection = null;

        try {

            connection = dataSource.getConnection();

            jasperReport = JasperCompileManager.compileReport(jrxmlpath);

            jasperPrint = JasperFillManager.fillReport(jasperReport, jasperParameter, connection);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                log.info("about to close db connection");
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                connection = null;
            }
        }
        return jasperPrint;
    }
}
