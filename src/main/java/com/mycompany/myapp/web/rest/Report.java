package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.ReportRepository;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class Report {

    @Autowired
    ApplicationContext context;

    @Autowired
    ReportRepository reportRepository;

    @GetMapping(path = "/api/reportsi}")
    @ResponseBody
    public void getPdfReport(HttpServletResponse response) throws Exception {
        Resource resource = context.getResource("classpath:reports/ReportProba.jrxml");
        InputStream inputStream = resource.getInputStream();
        JasperReport report = JasperCompileManager.compileReport(inputStream);
        Map<String, Object> params = new HashMap<>();
        List<com.mycompany.myapp.domain.Report> reports = reportRepository.findAll();
        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(reports);
        params.put("datasource", dataSource);
        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        //Media Type
        response.setContentType(MediaType.APPLICATION_PDF_VALUE);
        //Export PDF Stream
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
    }
}
