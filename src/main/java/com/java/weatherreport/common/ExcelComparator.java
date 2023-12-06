import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelComparator {

    public static void main(String[] args) throws IOException {
        String file1Path = "file1.xlsx";
        String file2Path = "file2.xlsx";

        Sheet sheet1 = getSheet(file1Path);
        Sheet sheet2 = getSheet(file2Path);

        compareSheets(sheet1, sheet2);
    }

    private static Sheet getSheet(String filePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(filePath);
        Workbook workbook = null;

        if (filePath.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (filePath.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            throw new IOException("Unsupported file format: " + filePath);
        }

        return workbook.getSheetAt(0); // assuming first sheet
    }

    private static void compareSheets(Sheet sheet1, Sheet sheet2) {
        int rows = Math.max(sheet1.getLastRowNum(), sheet2.getLastRowNum());
        int columns = Math.max(sheet1.getRow(0).getLastCellNum(), sheet2.getRow(0).getLastCellNum());

        for (int i = 0; i <= rows; i++) {
            Row row1 = sheet1.getRow(i);
            Row row2 = sheet2.getRow(i);

            for (int j = 0; j <= columns; j++) {
                Cell cell1 = row1 == null ? null : row1.getCell(j);
                Cell cell2 = row2 == null ? null : row2.getCell(j);

                String value1 = getCellValue(cell1);
                String value2 = getCellValue(cell2);

                if (!value1.equals(value2)) {
                    System.out.println("Difference found at row " + (i + 1) + ", column " + (j + 1) + ": " + value1 + " != " + value2);
                }
            }
        }
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue();
            case Cell.CELL_TYPE_NUMERIC:
                return String.valueOf(cell.getNumericCellValue());
            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case Cell.CELL_TYPE_FORMULA:
                return String.valueOf(cell.getCellFormula());
            default:
                return "";
        }
    }
}
