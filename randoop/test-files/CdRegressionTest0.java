import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest0 {

    public static boolean debug = false;

    @Test
    public void test001() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test001");
        java.lang.String str0 = sg.edu.nus.comp.cs4218.impl.app.CdApplication.ERR_MULTIPLE_ARGS;
        org.junit.Assert.assertEquals("'" + str0 + "' != '" + "Too many arguments" + "'", str0, "Too many arguments");
    }

    @Test
    public void test002() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test002");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("Too many arguments");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
    }

    @Test
    public void test003() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test003");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray1 = new java.lang.String[] {};
        java.io.InputStream inputStream2 = null;
        java.io.OutputStream outputStream3 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray1, inputStream2, outputStream3);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray1);
    }

    @Test
    public void test004() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test004");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.Class<?> wildcardClass1 = cdApplication0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test005() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test005");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = cdApplication0.getNormalizedAbsolutePath("");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: Insufficient arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
    }

    @Test
    public void test006() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test006");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = cdApplication0.getNormalizedAbsolutePath("hi!");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
    }

    @Test
    public void test007() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test007");
        java.lang.Object obj0 = new java.lang.Object();
        java.lang.Class<?> wildcardClass1 = obj0.getClass();
        org.junit.Assert.assertNotNull(wildcardClass1);
    }

    @Test
    public void test008() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test008");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("hi!");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
    }

    @Test
    public void test009() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test009");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test010() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test010");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!", "", "" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test011() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test011");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "hi!", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test012() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test012");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.changeToDirectory("");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: Insufficient arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
    }

    @Test
    public void test013() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test013");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.String str2 = cdApplication0.getNormalizedAbsolutePath("Too many arguments");
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: No such file or directory");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
    }

    @Test
    public void test014() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test014");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray2 = new java.lang.String[] { "" };
        java.io.InputStream inputStream3 = null;
        java.io.OutputStream outputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray2, inputStream3, outputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
    }

    @Test
    public void test015() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test015");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "hi!", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test016() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test016");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "hi!", "", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test017() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test017");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test018() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test018");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test019() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test019");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "", "hi!" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test020() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test020");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test021() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test021");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray2 = new java.lang.String[] { "hi!" };
        java.io.InputStream inputStream3 = null;
        java.io.OutputStream outputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray2, inputStream3, outputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
    }

    @Test
    public void test022() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test022");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test023() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test023");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray2 = new java.lang.String[] { "Too many arguments" };
        java.io.InputStream inputStream3 = null;
        java.io.OutputStream outputStream4 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray2, inputStream3, outputStream4);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray2);
    }

    @Test
    public void test024() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test024");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "", "" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test025() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test025");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "Too many arguments", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test026() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test026");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test027() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test027");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test028() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test028");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray1 = null;
        java.io.InputStream inputStream2 = null;
        java.io.OutputStream outputStream3 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray1, inputStream2, outputStream3);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: Null arguments");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
    }

    @Test
    public void test029() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test029");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "", "hi!", "hi!" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test030() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test030");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test031() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test031");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test032() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test032");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test033() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test033");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test034() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test034");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test035() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test035");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test036() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test036");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "Too many arguments", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test037() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test037");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test038() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test038");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "hi!", "hi!", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test039() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test039");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test040() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test040");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test041() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test041");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "hi!", "Too many arguments" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test042() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test042");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test043() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test043");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test044() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test044");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test045() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test045");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test046() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test046");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "Too many arguments", "" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test047() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test047");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "hi!", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test048() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test048");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test049() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test049");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test050() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test050");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test051() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test051");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "", "", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test052() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test052");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test053() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test053");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "Too many arguments", "hi!" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test054() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test054");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "hi!", "", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test055() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test055");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "hi!", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test056() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test056");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test057() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test057");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test058() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test058");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test059() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test059");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test060() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test060");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test061() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test061");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test062() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test062");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "Too many arguments", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test063() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test063");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!", "", "hi!" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test064() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test064");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test065() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test065");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test066() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test066");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test067() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test067");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "", "", "" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test068() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test068");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test069() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test069");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test070() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test070");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test071() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test071");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test072() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test072");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test073() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test073");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test074() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test074");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test075() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test075");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test076() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test076");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test077() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test077");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "", "hi!", "" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test078() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test078");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "", "Too many arguments" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test079() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test079");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test080() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test080");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test081() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test081");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test082() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test082");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test083() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test083");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test084() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test084");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "Too many arguments", "", "" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test085() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test085");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test086() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test086");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test087() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test087");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "", "", "hi!" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test088() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test088");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test089() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test089");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test090() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test090");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "", "", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test091() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test091");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test092() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test092");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test093() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test093");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test094() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test094");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test095() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test095");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "hi!", "", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test096() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test096");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test097() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test097");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test098() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test098");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test099() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test099");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test100() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test100");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test101() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test101");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test102() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test102");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "hi!", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test103() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test103");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test104() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test104");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "hi!", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test105() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test105");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test106() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test106");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "hi!", "", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test107() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test107");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test108() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test108");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test109() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test109");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test110() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test110");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test111() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test111");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "hi!", "hi!", "" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test112() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test112");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "Too many arguments", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test113() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test113");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "Too many arguments", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test114() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test114");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test115() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test115");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "hi!", "" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test116() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test116");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test117() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test117");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test118() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test118");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "Too many arguments", "hi!", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test119() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test119");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test120() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test120");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test121() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test121");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test122() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test122");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test123() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test123");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "", "hi!", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test124() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test124");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test125() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test125");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test126() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test126");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "hi!", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test127() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test127");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test128() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test128");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test129() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test129");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test130() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test130");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test131() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test131");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test132() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test132");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test133() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test133");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "hi!", "hi!" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test134() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test134");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test135() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test135");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "hi!", "hi!", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test136() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test136");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test137() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test137");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test138() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test138");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test139() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test139");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test140() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test140");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test141() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test141");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test142() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test142");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "hi!", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test143() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test143");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test144() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test144");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray3 = new java.lang.String[] { "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream4 = null;
        java.io.OutputStream outputStream5 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray3, inputStream4, outputStream5);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray3);
    }

    @Test
    public void test145() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test145");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test146() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test146");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test147() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test147");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "hi!", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test148() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test148");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "Too many arguments", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test149() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test149");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test150() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test150");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "hi!", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test151() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test151");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test152() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test152");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "", "", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test153() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test153");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test154() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test154");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test155() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test155");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test156() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test156");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test157() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test157");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "", "Too many arguments", "" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test158() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test158");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test159() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test159");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "hi!", "", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test160() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test160");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test161() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test161");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test162() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test162");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test163() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test163");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "hi!", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test164() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test164");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test165() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test165");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test166() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test166");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test167() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test167");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test168() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test168");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test169() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test169");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test170() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test170");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test171() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test171");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test172() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test172");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "hi!", "hi!", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test173() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test173");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test174() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test174");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "hi!", "", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test175() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test175");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test176() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test176");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "", "", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test177() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test177");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test178() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test178");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test179() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test179");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test180() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test180");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test181() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test181");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test182() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test182");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test183() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test183");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test184() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test184");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test185() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test185");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "", "hi!", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test186() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test186");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "Too many arguments", "", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test187() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test187");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test188() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test188");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "", "hi!", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test189() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test189");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "", "", "Too many arguments" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test190() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test190");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "hi!", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test191() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test191");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test192() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test192");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test193() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test193");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test194() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test194");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test195() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test195");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "hi!", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test196() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test196");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "", "", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test197() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test197");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray4 = new java.lang.String[] { "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream5 = null;
        java.io.OutputStream outputStream6 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray4, inputStream5, outputStream6);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray4);
    }

    @Test
    public void test198() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test198");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test199() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test199");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test200() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test200");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test201");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test202");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test203");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test204");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test205");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test206");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "", "hi!", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test207");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test208");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test209");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test210");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test211");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test212");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test213");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test214");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test215");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test216");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "hi!", "", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test217");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "hi!", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test218");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "hi!", "", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test219");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test220");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "", "", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test221");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "", "", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test222");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test223");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test224");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test225");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "hi!", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test226");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "hi!", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test227");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "hi!", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test228");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test229");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test230");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test231");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "hi!", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test232");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "hi!", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test233");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test234");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test235");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "hi!", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test236");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "Too many arguments", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test237");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test238");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test239");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "hi!", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test240");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test241");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test242");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test243");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test244");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test245");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test246");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test247");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test248");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test249");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test250");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test251() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test251");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test252() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test252");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test253() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test253");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test254() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test254");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test255() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test255");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test256() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test256");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "Too many arguments", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test257() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test257");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test258() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test258");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "hi!", "", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test259() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test259");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "", "Too many arguments", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test260() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test260");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test261() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test261");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test262() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test262");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test263() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test263");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test264() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test264");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test265() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test265");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test266() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test266");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test267() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test267");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "Too many arguments", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test268() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test268");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test269() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test269");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test270() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test270");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "Too many arguments", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test271() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test271");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test272() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test272");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test273() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test273");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "hi!", "hi!", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test274() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test274");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "hi!", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test275() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test275");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test276() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test276");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test277() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test277");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "hi!", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test278() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test278");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "hi!", "hi!", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test279() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test279");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test280() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test280");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "hi!", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test281() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test281");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test282() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test282");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test283() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test283");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "hi!", "hi!", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test284() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test284");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test285() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test285");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test286() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test286");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test287() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test287");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test288() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test288");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test289() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test289");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test290() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test290");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "hi!", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test291() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test291");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test292() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test292");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test293() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test293");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "", "", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test294() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test294");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test295() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test295");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test296() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test296");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test297() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test297");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "", "Too many arguments", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test298() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test298");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test299() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test299");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test300() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test300");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test301() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test301");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test302() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test302");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test303() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test303");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "hi!", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test304() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test304");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test305() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test305");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test306() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test306");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "hi!", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test307() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test307");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "hi!", "", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test308() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test308");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "hi!", "", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test309() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test309");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test310() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test310");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test311() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test311");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test312() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test312");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test313() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test313");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test314() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test314");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test315() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test315");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test316() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test316");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "hi!", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test317() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test317");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "hi!", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test318() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test318");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test319() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test319");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test320() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test320");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "", "", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test321() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test321");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "Too many arguments", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test322() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test322");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test323() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test323");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "hi!", "Too many arguments", "hi!", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test324() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test324");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "", "hi!", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test325() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test325");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "hi!", "hi!", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test326() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test326");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test327() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test327");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test328() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test328");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test329() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test329");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "Too many arguments", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test330() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test330");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test331() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test331");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "hi!", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test332() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test332");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test333() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test333");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test334() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test334");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test335() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test335");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "hi!", "Too many arguments", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test336() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test336");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "Too many arguments", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test337() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test337");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "", "", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test338() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test338");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test339() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test339");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "hi!", "", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test340() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test340");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test341() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test341");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test342() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test342");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test343() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test343");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test344() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test344");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test345() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test345");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test346() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test346");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test347() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test347");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "Too many arguments", "", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test348() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test348");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test349() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test349");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test350() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test350");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test351() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test351");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test352() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test352");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "Too many arguments", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test353() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test353");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test354() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test354");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test355() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test355");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test356() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test356");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test357() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test357");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test358() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test358");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test359() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test359");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test360() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test360");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "Too many arguments", "" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test361() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test361");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test362() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test362");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test363() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test363");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "hi!", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test364() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test364");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test365() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test365");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "hi!", "Too many arguments", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test366() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test366");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test367() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test367");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test368() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test368");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "hi!", "", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test369() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test369");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "hi!", "", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test370() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test370");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "hi!", "", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test371() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test371");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "", "", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test372() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test372");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test373() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test373");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test374() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test374");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test375() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test375");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "hi!", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test376() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test376");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "hi!", "Too many arguments", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test377() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test377");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test378() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test378");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "Too many arguments", "hi!", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test379() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test379");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "", "hi!", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test380() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test380");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "hi!", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test381() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test381");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test382() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test382");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test383() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test383");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "hi!", "Too many arguments", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test384() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test384");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test385() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test385");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "hi!", "Too many arguments", "Too many arguments", "hi!" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test386() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test386");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "", "", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test387() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test387");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "", "hi!", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test388() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test388");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "hi!", "", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test389() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test389");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "Too many arguments", "", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test390() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test390");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "hi!", "Too many arguments", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test391() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test391");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "", "hi!", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test392() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test392");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test393() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test393");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray5 = new java.lang.String[] { "", "", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream6 = null;
        java.io.OutputStream outputStream7 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray5, inputStream6, outputStream7);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray5);
    }

    @Test
    public void test394() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test394");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "", "", "Too many arguments", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test395() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test395");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test396() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test396");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "hi!", "", "", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test397() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test397");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "Too many arguments", "", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test398() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test398");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "hi!", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test399() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test399");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "", "", "Too many arguments", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test400() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test400");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "hi!", "", "", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test401() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test401");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "Too many arguments", "", "", "hi!", "" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }

    @Test
    public void test402() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test402");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "hi!", "hi!", "hi!", "hi!", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test403() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test403");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "hi!", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test404() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test404");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "hi!", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test405() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test405");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "Too many arguments", "", "hi!", "", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test406() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test406");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "Too many arguments", "hi!", "Too many arguments", "hi!", "hi!" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test407() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test407");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "", "Too many arguments", "Too many arguments", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test408() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test408");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "", "Too many arguments", "", "", "" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test409() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test409");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "Too many arguments", "hi!", "", "hi!", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test410() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test410");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "", "", "hi!", "", "", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test411() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test411");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray7 = new java.lang.String[] { "hi!", "", "", "", "hi!", "Too many arguments" };
        java.io.InputStream inputStream8 = null;
        java.io.OutputStream outputStream9 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray7, inputStream8, outputStream9);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray7);
    }

    @Test
    public void test412() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest0.test412");
        sg.edu.nus.comp.cs4218.impl.app.CdApplication cdApplication0 = new sg.edu.nus.comp.cs4218.impl.app.CdApplication();
        java.lang.String[] strArray6 = new java.lang.String[] { "", "Too many arguments", "Too many arguments", "hi!", "Too many arguments" };
        java.io.InputStream inputStream7 = null;
        java.io.OutputStream outputStream8 = null;
        // The following exception was thrown during execution in test generation
        try {
            cdApplication0.run(strArray6, inputStream7, outputStream8);
            org.junit.Assert.fail("Expected exception of type sg.edu.nus.comp.cs4218.exception.CdException; message: cd: OutputStream not provided");
        } catch (sg.edu.nus.comp.cs4218.exception.CdException e) {
        // Expected exception.
        }
        org.junit.Assert.assertNotNull(strArray6);
    }
}

