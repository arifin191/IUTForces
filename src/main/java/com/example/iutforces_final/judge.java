package com.example.iutforces_final;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class judge {
    //process builders
    private ProcessBuilder compile;
    private ProcessBuilder run;
    private ProcessBuilder compare;
    //files
    private File inputs;
    private String filename;
    private String pid;
    private String totname;
    private File expectedOutputs;
    private File userOutputs;
    private File submittedFile;
    //final String folderPath;
    private static String folderPath;
    int submissionID;
    private String probid;
    long timeTaken;

    public judge(String f, String p, String pp) {
        submissionID = 714;
        timeTaken = -1L;
        filename = f;
        pid = p;
        probid = pp;
        folderPath = "D://java_proj//IUTForces-main//";
        totname = folderPath + filename;
        //new File(folderPath).mkdir();
    }
    private String readfile(String s) throws IOException {
        File file = new File(folderPath +  "io//"+ pid + s);

        // Note:  Double backquote is to avoid compiler
        // interpret words
        // like \test as \t (ie. as a escape sequence)

        // Creating an object of BufferedReader class
        FileReader fr = new FileReader(file);

        // Declaring a string variable
        // Condition holds true till
        // there is character in a string
        int i;
        String ret = "";
        // Holds true till there is nothing to read
        while ((i = fr.read()) != -1)

            // Print all the content of a file
            ret += (char)i;
        return  ret;
    }
    private int compileCpp() {
        //String temp=folderPath + filename;
        //submittedFile = new File(totname);
        /*try {
            final FileOutputStream fos = new FileOutputStream(submittedFile);
            String s = "#include<iostream>\n" +
                    "using namespace std;\n" +
                    "\n" +
                    "int main() {\n" +
                    "  int a, b; cin >> a >> b;\n" +
                    "  cout << (a > b ? a : b);\n" +
                    "}";
            //File Files;
            byte[] array = Files.readAllBytes(Paths.get(totname));
            //byte[] baeo = s.getBytes();
            fos.write(array);
            fos.close();
        } catch (FileNotFoundException ex) {
            System.out.println("At CompileCPP FOS Error: " + ex.getMessage());
        } catch (IOException ex2) {
            System.out.println("At CompileCPP FWrite Error: " + ex2.getMessage());
        }*/
        //System.out.println(totname);
        //System.out.println(folderPath + pid);
        compile = new ProcessBuilder(new String[]{"g++", totname, "-o", folderPath + pid});
        try {
            final Process p = compile.start();
            try {
                p.waitFor(10L, TimeUnit.SECONDS);
            } catch (InterruptedException ex3) {
                p.destroy();
                System.out.println("Interrupted exception");//("HERE");
                return -1;
            }
            //System.out.println(p.exitValue());
            return p.exitValue();
        } catch (IOException ex2) {
            System.out.println("At CompileCPP CompileProcess Err " + ex2.getMessage());
            return -2;
        }
    }

    private int runCppC() {
        (run = new ProcessBuilder(new String[] { folderPath + pid + ".exe" })).redirectInput(inputs);
        run.redirectOutput(userOutputs);
        try {
            final long startTime = System.nanoTime();
            final Process p = run.start();
            //System.out.println("PANDAAAAAA");
            try {
                //System.out.println("PANDAAAAAA");
                //
                if (!p.waitFor(Integer.parseInt("1000"), TimeUnit.MILLISECONDS)) {
                    final long stopTime = System.nanoTime();
                    p.destroy();
                    timeTaken = (stopTime - startTime) / 1000000L;
                    //System.out.println(timeTaken);
                    //System.out.println(timeTaken);
                    //System.out.println("PANDAAAAAA");
                    //return -1;
                }
            }
            catch (InterruptedException ex) {
                System.out.println("At runCppC Runtime/TLE Error: " + ex.getMessage());
                return 1;
            }
            final long stopTime = System.nanoTime();
            timeTaken = (stopTime - startTime) / 1000000L;
            //System.out.println("HERE" + timeTaken);
            //System.out.println(p.exitValue());
            int x = p.exitValue();
            if (timeTaken > 1000L) {
                return 1;
            } else if (x != 0) {
                return 2;
            } else {
                return 0;
            }
        }
        catch (IOException ex2) {
            System.out.println("At runCppC Runtime Error: " + ex2.getMessage());
            return 2;
        }
    }

    public int run() {
        //System.out.println(this.submission.getLanguage());
        inputs = new File(folderPath + pid + ".in");
        expectedOutputs = new File(folderPath + pid + ".out");
        userOutputs = new File(folderPath + pid + "_u" + ".out");
        int IOFileState;
        try {
            final FileOutputStream fosInp = new FileOutputStream(inputs);
            final FileOutputStream fosOup = new FileOutputStream(expectedOutputs);
            //Path fileName1 = Path.of(folderPath + pid + "given.txt");
            //System.out.println(folderPath+pid+"given.txt");
            String giveninput = readfile( probid + "given.txt");
            //System.out.println(giveninput);
            byte[] bagi = giveninput.getBytes();
            fosInp.write(bagi);
            //Path fileName2 = Path.of(folderPath + pid + "expected.txt");
            String expectedOutput = readfile( probid + "expected.txt");
            //System.out.println(expectedOutput);
            byte[] baeo = expectedOutput.getBytes();
            fosOup.write(baeo);
            fosInp.close();
            fosOup.close();
            IOFileState = 0;
        }
        catch (FileNotFoundException ex) {
            IOFileState = -1;
            System.out.println("At Compiler IO file Error NOT FND: " + ex.getMessage());
        }
        catch (IOException ex2) {
            System.out.println("At Compiler IO file Error: " + ex2.getMessage());
            IOFileState = -1;
        }
        if (IOFileState < 0) {
            return IOFileState;
        }
        boolean ErrBeforeCompareOutput = false;
        //System.out.println("LOL");
        final String language = "C++";//this.submission.getLanguage();
        switch (language) {
            case "C++" -> {
                if (compileCpp() != 0) {
                    ////this.db.updateVerdict(this.submissionID, "Compilation Error", -1);
                    //System.out.println("Compilation Error");
                    ErrBeforeCompareOutput = true;
                    return 0;
                    //break;
                }
                final int verdict = runCppC();
                //System.out.println(verdict);
                if (verdict == 1) {
                    //System.out.println("TLE");
                    //this.db.updateVerdict(this.submissionID, "Time Limit Exceeded", (int)this.timeTaken);
                    ErrBeforeCompareOutput = true;
                    return 1;
                } else if (verdict == 2) {
                    //System.out.println("RTE");
                    //this.db.updateVerdict(this.submissionID, "Run Time Error", -1);
                    ErrBeforeCompareOutput = true;
                    return 2;
                }
                break;
            }
        }
        if (!ErrBeforeCompareOutput) {
            compare = new ProcessBuilder(new String[] { "fc", expectedOutputs.getAbsolutePath(), userOutputs.getAbsolutePath() });
            try {
                final Process p = compare.start();
                //System.out.println("lol");
                p.waitFor(2L, TimeUnit.MINUTES);
                final int comparisonResult = p.exitValue();
                //System.out.println("LMAMMMM" + comparisonResult);
                if (comparisonResult == 0) {
                    //System.out.println("Accepted");
                    return -1;
                }
                else if (comparisonResult == 1) {
                    //System.out.println("Wrong Answer");
                    return 3;
                }
                else {
                    //System.out.println("Output File is Missing!");
                    return 4;
                }
            }
            catch (IOException ex3) {
                System.out.println("Output Compare Process Error: " + ex3.getMessage());
            }
            catch (InterruptedException ex4) {
                System.out.println("Comparing outputs took too long!");
            }
        }
        expectedOutputs.delete();
        inputs.delete();
        userOutputs.delete();
        submittedFile.delete();
        new File(totname + ".exe").delete();
        //new File(Integer.toString(pid)).delete();
        return -1;
    }
    public int getverdict() {
        return run();
    }

    public long gettime() {
        return timeTaken;
    }
    public void main(String[] args) {
        //compileCpp();

    }
}
