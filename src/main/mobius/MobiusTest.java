package main.mobius;

import java.awt.Toolkit;

import static math.prime.NumberTheory.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

import math.Pair;
import math.prime.NumberTheory;
import utils.Chrono;
import utils.Saveable;
import utils.multithreading.Job;
import utils.ArrayList;

public class MobiusTest {

	private static final String filename = "mobius/info.bin";
	private static final int ayy = 5;
	private static final int max = (1 << ayy) - 1;
	private static final Chrono chrono = new Chrono(true);

	private static ArrayList<Pair<Integer, Boolean>> mobius;
	
	//System.out.printf("\u03BC(%s) = %s%n", i, PrimeUtils.mobius(i));
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException, InterruptedException {
		int i = 0;
		int count = 0;
		while(true) {
			while(true) {
				long a = System.nanoTime();
				long b = System.nanoTime();
				count++;
				if(a == b)
					break;
			}
			i++;
			System.out.println(count);
			if(i == 1000)
				break;
		}
		System.out.println((double)count/i);
	}
	
	
	public static void mobius1() throws FileNotFoundException, ClassNotFoundException, IOException {
		if(Files.exists(new File(filename).toPath()))
			mobius = deltaToList(Saveable.loadFromFile(filename));
		else {
			mobius = generateList();
			listToDelta(mobius).saveToFile(filename);
		}
		
		System.out.println("Post-init " + chrono.elapsedTime());
		
		for(double epsilon = 1; epsilon > 0.00001; epsilon /= 2) {
			System.out.printf("%.10f\t", -(0.5 + epsilon));
			for(int i = 0; i < ayy; i++)
				System.out.printf("% .7f\t", calculate(-(0.5 + epsilon), 1 << i));
			System.out.println();
		}
		
		System.out.print("-----------------");
		for(int i = 0; i < ayy; i++) {
			System.out.print("----------------");
		}

		for(double epsilon = 0.5; epsilon > 0; epsilon -= 0.01) {
			System.out.printf("%.10f\t", -(epsilon));
			for(int i = 0; i < ayy; i++)
				System.out.printf("% .7f\t",calculate(-(epsilon), 1 << i));
			System.out.println();
		}
		
		System.out.printf("% .10f\t", 0d);
		for(int i = 0; i < ayy; i++)
			System.out.printf("% .7f\t",calculate(0, 1 << i));
		System.out.println();
		
		Toolkit.getDefaultToolkit().beep();
	}
	
	public static void mobius2() throws FileNotFoundException, IOException {
		System.out.println("Pre-init " + chrono.elapsedTime());
		
		/*mobius = generateList();
		listToDelta(mobius).saveToFile(filename);*/
		
		System.out.println(1 + (int)(max/primoralPn(7)));
		System.out.println((1 + (int)(max/primoralPn(7))) * primoralPn(7));
		ArrayList<ArrayList<Pair<Integer, Boolean>>> result = new Job<MobiusContext, ArrayList<ArrayList<Pair<Integer, Boolean>>>>(new MobiusContext(1 +(int)(max/primoralPn(7)))).execute();
		
		beep();
		
		System.out.println("Post-gen " + chrono.elapsedTime());
		
		result.sort((a, b) -> a.get(0).t1.compareTo(b.get(0).t1)); 
		ArrayList<Pair<Integer, Boolean>> mobius = new ArrayList<Pair<Integer, Boolean>>();
		result.forEach(list -> mobius.addAll(list));
		
		beep(); beep();
		
		System.out.println("Post-sort " + chrono.elapsedTime());
		
		listToDelta(mobius).saveToFile(filename);
		
		//System.out.println(result);
		
		beep(); beep(); beep();
		System.out.println("Post-save" + chrono.elapsedTime());
	}
	
	public static void mobius3() {
		
	}
	public static void beep() {
		Toolkit.getDefaultToolkit().beep();
	}
	
	public static double calculate(double exponent, int limit) {
		limit = Integer.min(limit, mobius.size());
		double n = 0;
		for(int i = 0; i < limit; i++)
			n += (mobius.get(i).t2 ? 1 : -1) * Math.pow(mobius.get(i).t1, exponent);
		return n;
	}
	
	public static ArrayList<Pair<Integer, Boolean>> generateList() {
		System.out.println("Generating");
		ArrayList<Pair<Integer, Boolean>> list = new ArrayList<Pair<Integer, Boolean>>();
		
		byte j = 0;
		for(int i = 0; i < max; i++) {
			int mobius = NumberTheory.mobius(i);
			if(mobius == 0)
				continue;
			list.add(new Pair<Integer, Boolean>(i, mobius == 1));
			if(i % (max / 100) == 0)
				System.out.printf("%s%%%n", j++);
			
		}
		list.trimToSize();
		
		System.out.println("Generated " + chrono.elapsedTime());
		return list;
	}
	
	public static ArrayList<Pair<Integer, Boolean>> deltaToList(ArrayList<Pair<Byte, Boolean>> delta) {
		ArrayList<Pair<Integer, Boolean>> list = new ArrayList<Pair<Integer, Boolean>>();
		
		int current = 1;
		for(Pair<Byte, Boolean> pair : delta) {
			current += pair.t1;
			list.add(new Pair<Integer, Boolean>(current, pair.t2));
		}
		return list;
	}
	
	public static ArrayList<Pair<Byte, Boolean>> listToDelta(ArrayList<Pair<Integer, Boolean>> list) {
		ArrayList<Pair<Byte, Boolean>> delta =  new ArrayList<Pair<Byte, Boolean>>();
		
		int prev = 1;
		for(int i = 0; i < list.size(); i++) {
			delta.add(new Pair<Byte, Boolean>((byte)(list.get(i).t1 - prev), list.get(i).t2));
			prev = list.get(i).t1;
		}
		delta.trimToSize();
		return delta;
	}
	
	
	public static int[] generateListOfNonSquareFreeIntegersLessThan(int n) {
		int[] array = new int[n];
		int j = 0;
		for(int i = 1; i < n; i++)
			if(NumberTheory.isSquareFree(i))
				array[j++] = i;
		array = Arrays.copyOf(array, j);
		return array;
	}
	
}
