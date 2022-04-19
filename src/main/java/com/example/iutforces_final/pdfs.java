package com.example.iutforces_final;


import java.io.*;
import java.sql.*;


//import static jdk.jpackage.internal.WixAppImageFragmentBuilder.ShortcutsFolder.Desktop;

public class pdfs {
private String passw="123581321345589";
private String dname = "";
private String pname = "";
private int pid, tl, ml;
    public pdfs(String dname_, String pname_, int pid_, int tl_, int ml_) {
        dname = dname_;
        pname = pname_;
        pid = pid_;
        tl = tl_;
        ml = ml_;
    }
    public void writepdfs() throws SQLException, IOException {
        System.out.println(dname);
        System.out.println(pname);
        File pdfFile = new File(dname);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
        PreparedStatement pst = null;
        ResultSet rs = null;
        byte[] pdfData = new byte[(int) pdfFile.length()];
        DataInputStream dis = new DataInputStream(new FileInputStream(pdfFile));
        dis.readFully(pdfData);  // read from file into byte[] array
        dis.close();

        try{
            String sql =  "INSERT INTO `tst`.`problemset` (problemID, problem_name, time_limit, memory_limit, pdf) VALUES (?, ?, ?, ?, ?)";
            pst = connection.prepareStatement(sql);

            pst.setInt(1, pid);
            pst.setString(2, pname);
            pst.setInt(3, tl);
            pst.setInt(4, ml);
            pst.setBytes(5, pdfData);  // byte[] array
            pst.executeUpdate();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void readpdfs() throws SQLException, IOException {
       // System.out.println("PANDAAA");
        //System.out.println(pid);
        try {
            System.out.println(pid);
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tst", "root", passw);
            Statement statement = connection.createStatement();
            //System.out.println("C:\\Users\\ASUS\\IdeaProjects\\IUTFORCES_Final\\problemset" + "\\" + pid + "_" + pname + ".pdf");
            ResultSet rs = statement.executeQuery("SELECT * FROM `tst`.`problemset` where problemID ="+ pid);

            int it = 1;
            if (rs.next() && it > 0) {
                --it;
                //String filename = rs.getString(1);
                Blob blob = rs.getBlob(5);
                InputStream is = blob.getBinaryStream();

                FileOutputStream fos = new FileOutputStream("D:\\java_proj\\IUTForces-main\\downprobs" + "\\"  + pid + "_" + pname + ".pdf");

               // System.out.println("Done");
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

   /* public static void main(String[] args) throws SQLException, IOException {
        readpfds();
    }*/
}
