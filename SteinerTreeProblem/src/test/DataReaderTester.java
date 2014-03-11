package test;

import java.io.IOException;

import input.DataReader;

public class DataReaderTester {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, IOException {
		DataReader tr = new DataReader();
		tr.readData("src/benchmarks/estein100.txt");
		tr.printpoints(0);
	}

}
