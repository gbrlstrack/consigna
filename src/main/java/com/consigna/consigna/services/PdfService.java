package com.consigna.consigna.services;

import com.consigna.consigna.models.Lote;
import com.consigna.consigna.models.Peca;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Service
public class PdfService {

    public ByteArrayInputStream gerarRelatorioLote(Lote lote) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, out);
            document.open();

            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font fontSubtitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font fontHeader = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10);
            Font fontCell = FontFactory.getFont(FontFactory.HELVETICA, 9);

            document.add(new Paragraph("Relatório do Lote", fontTitle));
            document.add(Chunk.NEWLINE); // Adiciona um espaço

            document.add(new Paragraph("ID do Lote: " + lote.getId()));
            if (lote.getDataEntrada() != null) {
                document.add(new Paragraph("Data de Entrada: " + lote.getDataEntrada().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
            }
            document.add(new Paragraph("Consignatário: " + lote.getConsignatario().getNome()));
            document.add(new Paragraph("Documento: " + lote.getConsignatario().getDocumento()));
            document.add(Chunk.NEWLINE);

            // --- Início da Tabela de Peças ---
            document.add(new Paragraph("Itens do Lote", fontSubtitle));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(4); // 3 colunas
            table.setWidthPercentage(100);
            table.setWidths(new float[]{1f, 4f, 1f, 2f}); // Proporção das colunas

            // Cabeçalho da tabela
            PdfPCell headerCell1 = new PdfPCell(new Paragraph("ID", fontHeader));
            PdfPCell headerCell2 = new PdfPCell(new Paragraph("Descrição", fontHeader));
            PdfPCell headerCell3 = new PdfPCell(new Paragraph("Quantidade", fontHeader));
            PdfPCell headerCell4 = new PdfPCell(new Paragraph("Valor", fontHeader));
            headerCell3.setHorizontalAlignment(Element.ALIGN_RIGHT);
            headerCell4.setHorizontalAlignment(Element.ALIGN_RIGHT);

            table.addCell(headerCell1);
            table.addCell(headerCell2);
            table.addCell(headerCell3);
            table.addCell(headerCell4);

            // Adiciona as peças na tabela
            for (Peca peca : lote.getPecas()) {
                table.addCell(new Paragraph(String.valueOf(peca.getId()), fontCell));
                table.addCell(new Paragraph(peca.getDescricao(), fontCell));
                PdfPCell qtdCell = new PdfPCell(new Paragraph(peca.getQuantidade().toString(), fontCell));
                qtdCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(qtdCell);
                // Formata o valor como moeda (R$)
                NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                String valorFormatado = peca.getValorMinimo() != null ? currencyFormat.format(peca.getValorMinimo()) : "N/A";
                PdfPCell valorCell = new PdfPCell(new Paragraph(valorFormatado, fontCell));
                valorCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(valorCell);
            }

            document.add(table);
            // --- Fim da Tabela de Peças ---

        } catch (DocumentException e) {
            // Trate a exceção de forma apropriada (ex: log)
            throw new RuntimeException("Erro ao gerar PDF", e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}