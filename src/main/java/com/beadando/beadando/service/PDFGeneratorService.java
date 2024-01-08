package com.beadando.beadando.service;

import com.beadando.beadando.model.Car;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;

@Service
public class PDFGeneratorService {
    private CarService carService;

    public PDFGeneratorService(CarService carService) {
        super();
        this.carService = carService;
    }
    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph = new Paragraph("Currently available cars", fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        Paragraph paragraph2 = new Paragraph("Cars:", fontParagraph);
        paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
        paragraph2.setSpacingBefore(10);

        Table carTable = new Table(5);
        carTable.setBorderWidth(1);
        carTable.setPadding(5);
        carTable.setSpacing(5);

        //Header
        Cell cell = new Cell();
        cell.setHeader(true);
        carTable.addCell("#");
        carTable.addCell("Brand");
        carTable.addCell("Model");
        carTable.addCell("Year");
        carTable.addCell("Rental Price");
        carTable.endHeaders();


        // Cars
        Car[] cars = carService.getAllCars().toArray(new Car[0]);
        Integer carNumber = 1;
        for (Car car : cars) {
            cell = new Cell();
            carTable.addCell(String.valueOf(carNumber));
            carTable.addCell(car.getBrand());
            carTable.addCell(car.getModel());
            carTable.addCell(String.valueOf(car.getYear()));
            carTable.addCell(String.valueOf(car.getRentalPrice()));
            carNumber++;
        }


        document.add(paragraph);
        document.add(paragraph2);
        document.add(carTable);
        document.close();
    }
}
