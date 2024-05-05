package com.algaworks.erp.service;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import com.algaworks.erp.model.Product;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

public class GeneratePdf {

	static String PATH = "C:\\JAVA\\pdf\\GeneretePdf.pdf";

	public static void main(String[] args) throws FileNotFoundException {

		PdfWriter pdfWriter = new PdfWriter(PATH);
		PdfDocument pdfDocument = new PdfDocument(pdfWriter);
		pdfDocument.setDefaultPageSize(PageSize.A4);
		Document document = new Document(pdfDocument);

		/* Definindo duas colunas no cabeçalho */
		float threeCol = 190f;
		float twocol = 285f;
		float twocol150 = twocol + 150f;
		float twoColumnWidth[] = { twocol150, twocol };
		float threeColumnWidth[] = { threeCol, threeCol, threeCol };

		/* Criando uma tabela */
		Table table = new Table(twoColumnWidth);

		table.addCell(newCell("Invoice", true, 20f)
				.setBorder(Border.NO_BORDER));

		Table nextedTable = new Table(new float[] { twocol / 2, twocol / 2 });

		nextedTable.addCell(getHeaderTextCell("Invoice No.:"));
		nextedTable.addCell(getHeaderTextCellValue("RK4262626"));

		nextedTable.addCell(getHeaderTextCell("Invoice Date:"));
		nextedTable.addCell(getHeaderTextCellValue("15/04/2024"));

		table.addCell(new Cell()
				.add(nextedTable)
				.setBorder(Border.NO_BORDER));

		document.add(table);
		document.add(addOneSpace());
		document.add(addDividerSolid(2f));
		document.add(addOneSpace());

		Table twoCollTable = new Table(twoColumnWidth);
		twoCollTable.addCell(getBillingandShippingCell("Billing Information"));
		twoCollTable.addCell(getBillingandShippingCell("Shipping Information"));
		document.add(twoCollTable.setMarginBottom(12f));

		Table twoCollTable2 = new Table(twoColumnWidth);
		twoCollTable2.addCell(getCell10fLeft("Company", true));
		twoCollTable2.addCell(getCell10fLeft("Name", true));
		twoCollTable2.addCell(getCell10fLeft("Coding Error", false));
		twoCollTable2.addCell(getCell10fLeft("Coding", false));
		document.add(twoCollTable2);

		Table twoCollTable3 = new Table(twoColumnWidth);
		twoCollTable3.addCell(getCell10fLeft("Name", true));
		twoCollTable3.addCell(getCell10fLeft("Address", true));
		twoCollTable3.addCell(getCell10fLeft("Arlyn Puttergill", false));
		twoCollTable3.addCell(getCell10fLeft("8570 Gulseth Terra, 3324 Eastwood\nSpringfi, Ma, 01114", false));
		document.add(twoCollTable3);

		float oneColumnWidth[] = { twocol150 };

		Table oneColTable1 = new Table(oneColumnWidth);
		oneColTable1.addCell(getCell10fLeft("Address", true));
		oneColTable1.addCell(getCell10fLeft("8570 Gulseth Terra, 3324 Eastwood\nSpringfi, Ma, 01114", false));
		oneColTable1.addCell(getCell10fLeft("Email", true));
		oneColTable1.addCell(getCell10fLeft("stern@exemple.com", false));
		document.add(oneColTable1.setMarginBottom(10f));

		document.add(addDividerDashedBorder());
		Paragraph producPara = new Paragraph("Products");

		document.add(producPara.setBold());
		Table threeColTable1 = new Table(threeColumnWidth);
		threeColTable1.setBackgroundColor(Color.BLACK, 0.7f);

		threeColTable1
				.addCell(new Cell()
				.add("Description")
				.setBold()
				.setFontColor(Color.WHITE)
				.setBorder(Border.NO_BORDER));
		threeColTable1.addCell(new Cell()
				.add("Quantity")
				.setBold()
				.setFontColor(Color.WHITE)
				.setTextAlignment(TextAlignment.CENTER)
				.setBorder(Border.NO_BORDER));
		threeColTable1.addCell(new Cell()
				.add("Price")
				.setBold()
				.setFontColor(Color.WHITE)
				.setTextAlignment(TextAlignment.RIGHT)
				.setBorder(Border.NO_BORDER));
		
		document.add(threeColTable1);
		
		List<Product> productList = getProductList();
		
		Table threeColTable2 = new Table(threeColumnWidth);
		
		
		float totalSum = 0;
		
		for (Product product : productList) {
			float total = product.getQuantity() * product.getPricePerPeice();
			totalSum += total;
			threeColTable2.addCell(
				new Cell()
				.add(product.getPname())
				.setBorder(Border.NO_BORDER));
			threeColTable2.addCell(
					new Cell()
					.add(String.valueOf(product.getQuantity()))
					.setTextAlignment(TextAlignment.CENTER)
					.setBorder(Border.NO_BORDER));
			threeColTable2.addCell(
					new Cell()
					.add(String.valueOf(total))
					.setTextAlignment(TextAlignment.RIGHT)
					.setBorder(Border.NO_BORDER));
		}
		
		document.add(threeColTable2.setMarginBottom(20f));

		
		float oneTwo[]= {threeCol + 125f, threeCol * 2};
		Table threeColTable4 = new Table(oneTwo);
		threeColTable4.addCell(new Cell().add("")
				.setBorder(Border.NO_BORDER));
		threeColTable4.addCell(addDividerDashedBorder())
				.setBorder(Border.NO_BORDER);
		document.add(threeColTable4);
		
		Table threeColTable3 = new Table(threeColumnWidth);
		threeColTable3.addCell(
				new Cell()
				.add("")
				.setBorder(Border.NO_BORDER));
		threeColTable3.addCell(
				new Cell()
				.add("Total")
				.setTextAlignment(TextAlignment.CENTER)
				.setBorder(Border.NO_BORDER));
		threeColTable3.addCell(
				new Cell()
				.add(String.valueOf(totalSum))
				.setTextAlignment(TextAlignment.RIGHT)
				.setBorder(Border.NO_BORDER));

		document.add(threeColTable3);
		document.add(addDividerDashedBorder()
				.setMarginBottom(15f));
		document.add(addDividerSolid(0.5f));
		
		document.close();
	}

	private static List<Product> getProductList() {
		List<Product> productList = new ArrayList<>();
		productList.add(new Product("apple", 2, 159));
		productList.add(new Product("mango", 4, 205));
		productList.add(new Product("banana", 2, 90));
		productList.add(new Product("grapes", 3, 10));
		productList.add(new Product("coconut", 2, 61));
		productList.add(new Product("cherry", 1, 1000));
		productList.add(new Product("kiwi", 3, 30));
		return productList;
	}

	private static Cell newCell(String texto, boolean bold, float fontSize) {
		Cell cell = new Cell().add(texto);
		if (bold)
			cell.setBold();
		if (fontSize > 0)
			cell.setFontSize(fontSize);
		return cell;
	}

	private static Table addDividerSolid(float width) {
		Border grayBorder = new SolidBorder(Color.GRAY, width);
		Table divider = new Table(new float[] { 570f });
		divider.setBorder(grayBorder);
		return divider;
	}

	private static Table addDividerDashedBorder() {
		Border grayBorder = new DashedBorder(Color.GRAY, 0.5f);
		Table table = new Table(new float[] { 570f });
		return table.setBorder(grayBorder);
	}

	private static Paragraph addOneSpace() {
		return new Paragraph("\n");
	}

	private static Cell getHeaderTextCell(String textValue) {
		Cell cell = new Cell().add(textValue);
		cell.setBold();
		cell.setBorder(Border.NO_BORDER);
		cell.setTextAlignment(TextAlignment.RIGHT);
		return cell;
	}

	private static Cell getHeaderTextCellValue(String textValue) {
		Cell cell = new Cell().add(textValue);
		cell.setBorder(Border.NO_BORDER);
		cell.setTextAlignment(TextAlignment.LEFT);
		return cell;
	}

	private static Cell getBillingandShippingCell(String textValue) {
		Cell cell = new Cell().add(textValue);
		cell.setFontSize(12f);
		cell.setBold();
		cell.setBorder(Border.NO_BORDER);
		cell.setTextAlignment(TextAlignment.LEFT);
		return cell;
	}

	private static Cell getCell10fLeft(String textValue, boolean isBold) {
		Cell cell = new Cell().add(textValue);
		cell.setFontSize(10f);
		cell.setBorder(Border.NO_BORDER);
		cell.setTextAlignment(TextAlignment.LEFT);
		return isBold ? cell.setBold() : cell;
	}

}
