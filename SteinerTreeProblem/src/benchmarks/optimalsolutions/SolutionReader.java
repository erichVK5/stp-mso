package benchmarks.optimalsolutions;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import basic.Point;

public class SolutionReader {
	
	public static String BENCHMARKLOCATION = "src/benchmarks/optimalsolutions/";
	public static String BENCHMARKNAME = "estein";
	public Point[][] data;
	public SolutionReader(int size) {
		try {
			readData(BENCHMARKLOCATION + BENCHMARKNAME + size +"opt.txt");
		} catch (NumberFormatException e) {
			System.err.println("Could not convert to double/int");
		} catch (IOException e) {
			System.err.println("Could not read file");
		}
	}
	private void readData(String file) throws NumberFormatException,IOException {
		FileInputStream fstream = new FileInputStream(file);
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		int nbOfProblems = Integer.valueOf(br.readLine().trim());
		data = new Point[nbOfProblems][];
		for(int i=0;i<nbOfProblems;i++) {
			br.readLine();
			br.readLine();
			int nbOfPoints = Integer.valueOf(br.readLine().trim());
			data[i] = new Point[nbOfPoints];
			for(int j=0;j<nbOfPoints;j++) {	
				String[] coordinates=  br.readLine().trim().split(" ");
				//System.out.println(coordinates[0]);
				double x = Double.parseDouble(coordinates[0]);
				double y = Double.parseDouble(coordinates[1]);
				data[i][j] = new Point(x,y);
			}
		}
		br.close();
	}
	
	public Point[] getSteinerPoints(int index) {
		return data[index];
	}
}
