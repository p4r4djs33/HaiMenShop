package com.kien.haimenshop.restapi;

import com.kien.haimenshop.entity.Client;
import com.kien.haimenshop.service.ClientService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@RestController
public class ClientRestController {
    @Autowired
    ClientService clientService;
    @GetMapping("/client")
    public Client getClientById(@RequestParam(value = "id", required = false) Long id) {
        return clientService.findClientById(id);
    }

    @GetMapping("/client/take/product")
    public Client clientTakeProduct(@RequestParam(value = "id", required = false) Long id,
                                    @RequestParam(value = "isTaken", required = false) Boolean isTaken) {
        System.out.println("Call clientTakeProduct:" + id + isTaken);
        Client client = clientService.findClientById(id);
        client.setIsTaken(isTaken);
        System.out.println(client);
        clientService.save(client);
        return client;
    }

    @PostMapping("/client/update/{id}")
    public Client clientUpdate(@PathVariable(value = "id", required = false) String id,
                                    @RequestBody(required = false) Client client) {
        System.out.println("Call clientUpdate: " + client + " " + id);
        Client clientOld = clientService.findClientById(Long.parseLong(id));
        clientOld.setName(client.getName());
        clientOld.setTypeFabric(client.getTypeFabric());
        clientOld.setMeasure(client.getMeasure());
        clientOld.setMoneyFabric(client.getMoneyFabric());
        clientOld.setMoreInformation(client.getMoreInformation());
        clientOld.setDeposit(client.getDeposit());
        clientOld.setDateCreation(client.getDateCreation());
//        LocalDate dateEndFormat = LocalDate.parse(dateEnd, inputFormatter);
        clientOld.setDateEnd(client.getDateEnd());
//        clientOld.setIsTaken(isTaken);
//        System.out.println(client);
        clientService.save(clientOld);
        return client;
    }

    @GetMapping("/client/list")
    public List<Client> getClientList() {
        return clientService.findAllClient();
    }

    @GetMapping("/export/excel/online")
    public ResponseEntity<byte[]> exportClientListToExcelOnline() throws IOException {
        List<Client> clients = clientService.findAllClient();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh Sách Khách Hàng");

        // Tạo header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên Khách Hàng");
        headerRow.createCell(2).setCellValue("Loại Vải");
        headerRow.createCell(3).setCellValue("Tiền May");
        headerRow.createCell(4).setCellValue("Số Đo");
        headerRow.createCell(5).setCellValue("Thông Tin Thêm");
        headerRow.createCell(6).setCellValue("Đặt Cọc");
        headerRow.createCell(7).setCellValue("Ngày May");
        headerRow.createCell(8).setCellValue("Ngày Lấy");
        headerRow.createCell(9).setCellValue("Tình trạng");

        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 6000);
        sheet.setColumnWidth(6, 6000);
        sheet.setColumnWidth(7, 6000);
        sheet.setColumnWidth(8, 6000);
        sheet.setColumnWidth(9, 6000);

        // Điền dữ liệu
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(client.getId());
            row.createCell(1).setCellValue(client.getName());
            row.createCell(2).setCellValue(client.getTypeFabric());
            row.createCell(3).setCellValue(client.getMoneyFabric());
            row.createCell(4).setCellValue(client.getMeasure());
            row.createCell(5).setCellValue(client.getMoreInformation());
            row.createCell(6).setCellValue(client.getDeposit());
            row.createCell(7).setCellValue(client.getDateCreation());
            row.createCell(8).setCellValue(client.getDateEnd());
            row.createCell(9).setCellValue(client.getIsTaken() ? "Đã lấy" : "Chưa lấy");
        }

        // Xuất file Excel ra byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        byte[] bytes = outputStream.toByteArray();

        HttpHeaders headers = new HttpHeaders();
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Tạo đối tượng Date hiện tại
        Date dateNow = new Date();
        // Chuyển từ Date sang LocalDateTime
        LocalDateTime localDateTime = dateNow.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        String formattedDate = localDateTime.format(outputFormatter);
        headers.add("Content-Disposition", "attachment; filename=clients-"+formattedDate+".xlsx");
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }


    @GetMapping("/export/excel/offline")
    public String  exportClientListToExcelOffline() throws IOException {
        List<Client> clients = clientService.findAllClient();

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh Sách Khách Hàng");

        // Tạo header
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Tên Khách Hàng");
        headerRow.createCell(2).setCellValue("Loại Vải");
        headerRow.createCell(3).setCellValue("Tiền May");
        headerRow.createCell(4).setCellValue("Số Đo");
        headerRow.createCell(5).setCellValue("Thông Tin Thêm");
        headerRow.createCell(6).setCellValue("Đặt Cọc");
        headerRow.createCell(7).setCellValue("Ngày May");
        headerRow.createCell(8).setCellValue("Ngày Lấy");
        headerRow.createCell(9).setCellValue("Tình trạng");

        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 6000);
        sheet.setColumnWidth(2, 6000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 6000);
        sheet.setColumnWidth(6, 6000);
        sheet.setColumnWidth(7, 6000);
        sheet.setColumnWidth(8, 6000);
        sheet.setColumnWidth(9, 6000);
        // Điền dữ liệu
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(client.getId());
            row.createCell(1).setCellValue(client.getName());
            row.createCell(2).setCellValue(client.getTypeFabric());
            row.createCell(3).setCellValue(client.getMoneyFabric());
            row.createCell(4).setCellValue(client.getMeasure());
            row.createCell(5).setCellValue(client.getMoreInformation());
            row.createCell(6).setCellValue(client.getDeposit());
            row.createCell(7).setCellValue(client.getDateCreation());
            row.createCell(8).setCellValue(client.getDateEnd());
            row.createCell(9).setCellValue(client.getIsTaken() ? "Đã lấy" : "Chưa lấy");
        }

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        // Tạo đối tượng Date hiện tại
        Date dateNow = new Date();
        // Chuyển từ Date sang LocalDateTime
        LocalDateTime localDateTime = dateNow.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
        String formattedDate = localDateTime.format(outputFormatter);
        // Lưu file Excel
        try (FileOutputStream fileOut = new FileOutputStream("F:\\01-Document\\00-HaiMenShop\\clients-"+formattedDate+".xlsx")) {
            workbook.write(fileOut);
        } catch (IOException e) {
            e.printStackTrace();
            return "Lỗi khi lưu file Excel";
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return "File Excel đã được lưu thành công";
    }
}
