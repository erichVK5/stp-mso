package execute;

import input.STPConfig;

import java.io.IOException;

import pso.FitnessEvaluator;
import pso.SwarmConfig;
import statistics.Analyzer;
import statistics.ProblemSolver;
import statistics.StatData;
import steiner.BottleneckEvaluator;
import steiner.STPSolver;

public class Analyse {
	static STPConfig stpcfg = new STPConfig(
			50,		// Nb of points,
			0,		// index of data
			10		// Nb of Steiner points
			);
	static SwarmConfig swarmcfg = new SwarmConfig(
			20,	// swarmsize
			10,	// cycles
			1,		// w1
			0.25,	// w2
			0.25	// w3
			);
	static int nbOfRuns = 100; // amount of times to solve the problems.
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		//FitnessEvaluator feval = new TreeLengthEvaluator(stpcfg.getSTP());
		FitnessEvaluator feval = new BottleneckEvaluator(stpcfg.getSTP());
		ProblemSolver ps = new STPSolver(stpcfg.getSTP(),swarmcfg,feval);
		Analyzer analyzer = new Analyzer(ps);
		
		long start = System.nanoTime();
		analyzer.run(nbOfRuns);
		long end = System.nanoTime();
		
		StatData statData = analyzer.getStatData();
		System.out.println(statData);
		double duration = (end-start);
		System.out.println("Execution time: " + (duration/1000000000) + " s");
	}
}
