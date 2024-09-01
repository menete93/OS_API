package com.menete.ORDEM_SERVICO.domain.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.menete.ORDEM_SERVICO.domain.entity.Custommer;

public class ExcelUploadService {

	public static boolean isValidExcelFile(MultipartFile file) {

		return Objects.equals(file.getContentType(), ".xlsx");
	}

	public static List<Custommer> getCustomerDateFromExcel(InputStream inputStream) {

		List<Custommer> custumer = new ArrayList<>();

		try {
			XSSFWorkbook worKbOOK = new XSSFWorkbook(inputStream);
			XSSFSheet sheet = worKbOOK.getSheet("Customer");

			int rowIndex = 0;
			for (Row row : sheet) {

				if (rowIndex == 0) {

					rowIndex++;

					continue;
				}

				Iterator<Cell> cellIterator = row.iterator();
				int cellIndex = 0;
				Custommer customer = new Custommer();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();

					switch (cellIndex) {
//						   case 0 -> customer.setCustumerId((int) cell.getNumericCellValue());
//	                       case 1 -> customer.setFirstName(cell.getStringCellValue());
//	                       case 2 -> customer.setLastName(cell.getStringCellValue());
//	                       case 3 -> customer.setCountry(cell.getStringCellValue());
//	                       case 4 -> customer.setTelephone((int) cell.getNumericCellValue());
//	                       default -> {
//						 }

					}
					cellIndex++;
				}
				custumer.add(customer);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return custumer;

	}
}
