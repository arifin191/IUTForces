package com.example.iutforces_final;


import java.io.*;
import java.sql.*;

//import static jdk.jpackage.internal.WixAppImageFragmentBuilder.ShortcutsFolder.Desktop;

public class pdfs {
    public static void writepdfs(String path) throws SQLException, IOException {
        File pdfFile = new File(path);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", "123581321345589");
        PreparedStatement pst = null;
        ResultSet rs = null;
        byte[] pdfData = new byte[(int) pdfFile.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
        dis.readFully(pdfData);  // read from file into byte[] array
        dis.close();

        try{
            String sql =  "INSERT INTO `tst`.`problems` (pid, pname, tl, pdf) VALUES (?, ?, ?, ?)";
            pst = connection.prepareStatement(sql);

            pst.setString(1, "1");
            pst.setString(2, "easy");
            pst.setString(3, "1000ms");
            pst.setBytes(4, pdfData);  // byte[] array
            pst.executeUpdate();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public static void readpfds() throws SQLException, IOException {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", "123581321345589");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `tst`.`problems`");
            if (rs.next()) {

                //String filename = rs.getString(1);
                Blob blob = rs.getBlob(4);
                InputStream is = blob.getBinaryStream();
                FileOutputStream fos = new FileOutputStream("D:\\downpdf.pdf");

                int b = 0;
                while ((b = is.read()) != -1)
                {
                    fos.write(b);
                }
            }
    } catch (IOException e)
    {
        e.getMessage (); e.printStackTrace();
        System.out.println(e);
    }
    catch (SQLException e)
    {
        e.getMessage (); e.printStackTrace();
        System.out.println(e);
    }
    }

    public static void main(String[] args) throws SQLException, IOException {
        readpfds();
    }
}

