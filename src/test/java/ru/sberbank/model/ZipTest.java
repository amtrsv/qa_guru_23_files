package ru.sberbank.model;
import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class ZipTest {
    private ClassLoader cl = ZipTest.class.getClassLoader();

    @DisplayName("CSV в ZIP")
    @Test
    void checkCvsFileInZipTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                cl.getResourceAsStream("zip.zip"))) {
            ZipEntry zentry;
            while ((zentry = zis.getNextEntry()) != null) {
                if (zentry.getName().contains(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> csvContent = csvReader.readAll();
                    Assertions.assertArrayEquals(new String[]{"CSV;File"}, csvContent.get(1));
                    break;

                }
            }
        }
    }



        @DisplayName("XLSX в ZIP")
        @Test
        void checkXlsxFileInZipTest() throws Exception {
            try (ZipInputStream zis = new ZipInputStream(
                    cl.getResourceAsStream("my_zipzip.zip"))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().contains(".xlsx")) {
                        XLS xls = new XLS(zis);
                        Assertions.assertEquals(xls.excel.getSheetAt(0).getRow(0).getCell(0)
                                .getStringCellValue(), "This is XLSX");
                        break;
                    }
                }
            }
        }

        @DisplayName("PDF в ZIP")
        @Test
        void checkPdfFileInZipTest() throws Exception {
            try (ZipInputStream zis = new ZipInputStream(
                    cl.getResourceAsStream("my_zipzip.zip"))) {
                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    if (entry.getName().contains(".pdf")) {
                        PDF pdf = new PDF(zis);
                        assertThat(pdf.text).contains("This is PDF");
                        break;
                    }
                }
            }
        }
    }

