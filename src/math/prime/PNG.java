package math.prime;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

class PNG {

	
	public Storage primes;
	
	public long getNext() {
		primes.iterator += 2;
		return primes.iterator;
	}
	
	public class PNGThread implements Runnable {

		private static final int THREAD_COUNT = 4;
		
		@Override
		public void run() {
			
		}
		
	}
	
	
	public class Storage {
		
		ArrayList<Long> list = new ArrayList<Long>();
		long iterator;
		
		public synchronized void save(String file) {
			try {
				DataOutputStream output = new DataOutputStream(new FileOutputStream(file));
				output.writeLong(iterator);
				for(int j = 0; j < list.size(); j++) {
					output.writeLong(list.get(j));
					if(j % (1 << 10) == 0)
						output.flush();
				}
				output.flush();
				output.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public synchronized void load(String file) {
			try {
				list = new ArrayList<Long>();
				DataInputStream input = new DataInputStream(new FileInputStream(file));
				iterator = input.readLong();
				while(true)
					try {
						list.add(input.readLong());
					} catch(EOFException e) {
						break;
					}
				input.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/*public class Storage implements Iterable<Long> {

		private static final int ELEMENT_PER_FILE = 1 << 10;
		public long size;
		public long[][] array;
		
		@Override
		public Iterator<Long> iterator() {
			return new Iterator<Long>() {
				
				private long index = 0;

				@Override
				public boolean hasNext() {
					return size < index;
				}

				@Override
				public Long next() {
					return array[(int)(index / ELEMENT_PER_FILE)][(int)(index++ % ELEMENT_PER_FILE)];
				}
				
			};
		}
		
		
		public void save(String filePattern) {
			Thread[] threads = new Thread[(int)(size / ELEMENT_PER_FILE)];
			for(int i = 0; i < threads.length; i++) {
				int index = i;
				threads[i] = new Thread(() -> {
					try {
						DataOutputStream output = new DataOutputStream(new FileOutputStream(String.format(filePattern, index)));
						for(int j = 0; j < ELEMENT_PER_FILE; j++)
							output.writeLong(array[index][j]);
						output.flush();
						output.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				threads[i].start();
			}
			for(int i = 0; i < threads.length; i++)
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
		private int flag;
		
		public void load(String filePattern) {
			int count = 0;
			while(Files.exists(new File(String.format(filePattern, count++)).toPath()));
			Thread[] threads = new Thread[count];
			for(int i = 0; i < count; i++) {
				int index = i;
				threads[i] = new Thread(() -> {
					try {
						long[] array = new long[ELEMENT_PER_FILE];
						DataInputStream input = new DataInputStream(new FileInputStream(String.format(filePattern, index)));
						for(int j = 0; j < ELEMENT_PER_FILE; j++)
							try {
								array[j] = input.readLong();
							} catch(EOFException e) {
								flag = j;
							}
						input.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
				threads[i].start();
			}
			for(int i = 0; i < threads.length; i++)
				try {
					threads[i].join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}*/
}
