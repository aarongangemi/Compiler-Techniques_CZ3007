package test;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import backend.ProgramCodeGenerator;

import org.junit.Assert;
import lexer.Lexer;
import parser.Parser;
import soot.Printer;
import soot.SootClass;
import soot.jimple.JasminClass;
import soot.util.JasminOutputStream;
import ast.List;
import ast.Module;
import ast.Program;

/**
 * System tests for the compiler: compiles a given program to Java bytecode, then immediately
 * loads it into the running JVM and executes it.
 */
public class CompilerTests {
	// set this flag to true to dump generated Jimple code to standard output
	private static final boolean DEBUG = false;
	
	/**
	 * A simple class loader that allows us to directly load compiled classes.
	 */
	private static class CompiledClassLoader extends URLClassLoader {
		private final Map<String, byte[]> classes = new HashMap<String, byte[]>();
		
		public CompiledClassLoader() {
			super(new URL[0], CompilerTests.class.getClassLoader());
		}
		
		public void addClass(String name, byte[] code) {
			classes.put(name, code);
		}
		
		@Override
		protected Class<?> findClass(String name) throws ClassNotFoundException {
			if(classes.containsKey(name)) {
				byte[] code = classes.get(name);
				return defineClass(name, code, 0, code.length);
			}
			return super.findClass(name);
		}
	}

	/**
	 * Test runner class.
	 * 
	 * @param modules_src Array of strings, representing the source code of the program modules
	 * @param main_module the name of the main module
	 * @param main_function the name of the main function
	 * @param parm_types the parameter types of the main function
	 * @param args arguments to pass to the main method
	 * @param expected expected result
	 */
	private void runtest(String[] modules_src, String main_module, String main_function, Class<?>[] parm_types, Object[] args, Object expected) {
		try {
			List<Module> modules = new List<Module>();
			for(String module_src : modules_src) {
				Parser parser = new Parser();
				Module module = (Module)parser.parse(new Lexer(new StringReader(module_src)));
				modules.add(module);
			}
			Program prog = new Program(modules);
			
			prog.namecheck();
			prog.typecheck();
			prog.flowcheck();
			if(prog.hasErrors()) {
				Assert.fail(prog.getErrors().iterator().next().toString());
			}
			
			CompiledClassLoader loader = new CompiledClassLoader();
			try {
				for(SootClass klass : new ProgramCodeGenerator().generate(prog)) {
					if(DEBUG) {
						PrintWriter stdout_pw = new PrintWriter(System.out);
						Printer.v().printTo(klass, stdout_pw);
						stdout_pw.flush();
					}

					String name = klass.getName();
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					PrintWriter pw = new PrintWriter(new JasminOutputStream(baos));
					new JasminClass(klass).print(pw);
					pw.flush();
					loader.addClass(name, baos.toByteArray());
				}

				Class<?> testclass = loader.loadClass(main_module);
				Method method = testclass.getMethod(main_function, parm_types);
				Object actual = method.invoke(null, args);
				if(!method.getReturnType().equals(void.class))
					Assert.assertEquals(expected, actual);
			} finally {
				loader.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		} catch(ClassFormatError e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	/** Convenience wrapper for runtest with only a single module. Other arguments are the same .*/
	private void runtest(String string, String classname, String methodname, Class<?>[] parmTypes, Object[] args, Object expected) {
		runtest(new String[] { string }, classname, methodname, parmTypes, args, expected);
	}

	@Test public void testAddition() {
		runtest("module Test {" +
				"  public int f() {" +
				"    return 23+19;" +
				"  }" +
				"}",
				"Test",
				"f",
				new Class<?>[0],
				new Object[0],
				42);
	}
	
	@Test public void testWhileStmt() {
		runtest("module Test { public int testWhile(int x){"
				+ "x = 1; "
				+ "while(x < 3){"
				+ "x = x + 1;}"
				+ "return x;}}","Test","testWhile", new Class<?>[] {int.class}, new Object[] {0},3);
	}
	
	@Test public void testBreak() {
		runtest("module Test{"
				+ "public int testBreak(int x){"
				+ "while(x < 2){"
				+ "x = x + 1;"
				+ "if(x == 1)"
				+ "{"
				+ "break;}}"
				+ "return x;}}","Test","testBreak", new Class<?>[] {int.class}, new Object[] {0},1);
	}
	@Test public void testNestedLoop() {
		runtest("module Test{"
				+ "int z;"
				+ "public int testNestedLoop(int x, int y)"
				+ "{"
				+ "while(x < 5)"
				+ "{"
				+ "x = x + 1;"
				+ "while(y < 3){"
				+ "y = y + 1;"
				+ "if(y == 2){"
				+ "z = y;"
				+ "}}}"
				+ "return z;}}",
				"Test", "testNestedLoop", new Class<?>[] {int.class, int.class}, new Object[] {0,0}, 2);
	}
	
	@Test public void testNegExpr() {
		runtest("module Test{"
				+ "public int negExprTest(){"
				+ "int x;"
				+ "int b; "
				+ "b = 2;"
				+ "int a;"
				+ "a = 4;"
				+ "x = -b + a;"
				+ "return x;}}", "Test", "negExprTest", new Class<?>[0], new Object[0],2);
	}
	
	@Test public void testAdd() {
		runtest("module Test{"
				+ "public int testAdd(int x, int y){"
				+ "int value;"
				+ "value = x + y;"
				+ "return value;}}", "Test", "testAdd", new Class<?>[] {int.class, int.class}, new Object[] {1,1},2);
	}
	
	@Test public void testSub() {
		runtest("module Test{"
				+ "public int testSubtract(int x, int y){"
				+ "int value;"
				+ "value = 0;"
				+ "value = x - y;"
				+ "return value;}}", "Test", "testSubtract", new Class<?>[] {int.class, int.class}, new Object[] {2,1},1);
}
	@Test public void testMul() {
		runtest("module Test{"
				+ "public int testMultiply(int x, int y){"
				+ "int value;"
				+ "value = x * y;"
				+ "return value;}}", "Test", "testMultiply", new Class<?>[] {int.class, int.class}, new Object[] {2,1},2);
}
	@Test public void testDiv() {
		runtest("module Test{"
				+ "public int testDivide(int x, int y){"
				+ "int value;"
				+ "value = x/y;"
				+ "return value;}}", "Test", "testDivide", new Class<?>[] {int.class, int.class}, new Object[] {2,1},2);
}
	@Test public void testMod() {
		runtest("module Test{"
				+ "public int testMod(int x, int y){"
				+ "int value;"
				+ "value = x % y;"
				+ "return value;}}", "Test", "testMod", new Class<?>[] {int.class, int.class}, new Object[] {2,2},0);
}
	@Test public void testArrayIdx() {
		runtest("module Test{"
				+ "public int testIdx(int i){"
				+ "int[] myArray;"
				+ "myArray =[1];"
				+ "myArray[i] = 10;"
				+ "return myArray[i];}}","Test", "testIdx", new Class<?>[] {int.class}, new Object[] {0},10);
	}
	@Test public void testIntLiteral() {
		runtest("module Test{"
				+ "public int testInt(){"
				+ "int x;"
				+ "x = 1;"
				+ "int y;"
				+ "y = 1;"
				+ "return x + y;}}","Test","testInt",new Class<?>[0], new Object[0],2);
	}
	
	@Test public void testBoolean() {		
		runtest("module Test{"
				+ "public int testBool(){"
				+ "boolean x;"
				+ "int y;"
				+ "y = 0;"
				+ "x = true;"
				+ "if(x){y = 1;}"
				+ "return y;}}", "Test", "testBool", new Class<?>[0], new Object[0],1);
	}
	@Test public void testStringLiteral() {
		runtest("module Test{" +
				"public String testString(){"+
				"String x;"
				+ "x = \"I am a string\";"+
				"return x;}}",
				"Test",
				"testString",
				new Class<?>[0],
				new Object[0],
				"I am a string");
	}
	
	
}
