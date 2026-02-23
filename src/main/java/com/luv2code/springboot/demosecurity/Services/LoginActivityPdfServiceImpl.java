package com.luv2code.springboot.demosecurity.Services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.luv2code.springboot.demosecurity.Entity.LoginActivity;
import com.luv2code.springboot.demosecurity.Repositories.LoginActivityRepository;
import com.luv2code.springboot.demosecurity.ServiceInterfaces.LoginActivityPdfService;
import org.springframework.stereotype.Service;


import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LoginActivityPdfServiceImpl implements LoginActivityPdfService {
    private final LoginActivityRepository repository;

    public LoginActivityPdfServiceImpl(LoginActivityRepository repository) {
        this.repository = repository;
    }

    @Override
    public void exportUserLoginsPdf(String username, OutputStream out) {
        List<LoginActivity> logins = repository.findAllUserLastLogins(username);

        try (Document document = new Document(PageSize.A4)) {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont =
                    FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);

            document.add(new Paragraph(
                    "Login History for User: " + username,
                    titleFont
            ));
            document.add(new Paragraph(
                    "Generated at: " + LocalDateTime.now()
            ));
            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10);

            table.addCell("Username");
            table.addCell("Login Time");

            DateTimeFormatter fmt =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            for (LoginActivity la : logins) {
                table.addCell(la.getUsername());
                table.addCell(la.getLoginTime().format(fmt));
            }

            document.add(table);

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Failed to generate login PDF", e
            );
        }
    }
}
