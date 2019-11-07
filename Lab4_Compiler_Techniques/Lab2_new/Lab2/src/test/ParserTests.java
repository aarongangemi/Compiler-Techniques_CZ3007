package test;

import static org.junit.Assert.fail;

import java.io.StringReader;

import lexer.Lexer;

import org.junit.Test;

import parser.Parser;

public class ParserTests {
	private void runtest(String src) {
		runtest(src, true);
	}

	private void runtest(String src, boolean succeed) {
		Parser parser = new Parser();
		try {
			parser.parse(new Lexer(new StringReader(src)));
			if(!succeed) {
				fail("Test was supposed to fail, but succeeded");
			}
		} catch (beaver.Parser.Exception e) {
			if(succeed) {
				e.printStackTrace();
				fail(e.getMessage());
			}
		} catch (Throwable e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	@Test
	public void testEmptyModule() {
		runtest("module Test { }");
	}
	
	@Test
	public void testIfStatement()
	{ 
		runtest("module ifTest{public int ifStatementTest(){ if(x>2)return 1;}}");
	}
	
	@Test
	public void testWhileLoop()
	{
		runtest("module whileTest{public void whileLoopTest(){while(x < 2){return;}}}");
	}
	
	@Test
	public void testArithmetic()
	{
		runtest("module arithmetictest{public int arithmeticTest(){int x; int y; int z;"
				+ "x=1; y=2; z = x+y*x+y;"
				+ "return z;"
				+ "}}");
	}
	
	@Test
	public void testifElseStatement()
	{
		runtest("module ifElseTest{public boolean ifElseTesting(){"
				+ "if(x < 2){return true;}"
				+ "else{return false;}}}");
	}
	
	@Test
	public void testArray()
	{
		runtest("module testArray{public void testArray(){int[] myArray;"
				+ "myArray=[1,2,3,4,5]; int x; x = myArray[3];}}");
	}
	
	@Test
	public void multiplicationOperations()
	{
		runtest("module testOperatiors{public int Operators(){int x; int y; x = 2; y = 3;"
				+ "return x * y/x;}}");
	}
	@Test
	public void testEqualsOperator()
	{
		runtest("module testEquals{ public boolean isEqual(){boolean isEqual; "
				+ "if(value == 8)"
				+ "{ isEqual = true;"
				+ "return isEqual;}}}");
	}
}
