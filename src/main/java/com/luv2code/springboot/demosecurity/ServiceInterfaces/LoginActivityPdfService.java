package com.luv2code.springboot.demosecurity.ServiceInterfaces;

import java.io.OutputStream;

public interface LoginActivityPdfService {
    void exportUserLoginsPdf(String username, OutputStream out);
}
