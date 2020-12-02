package org.kpu.ticketbox.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import org.kpu.ticketbox.payment.Receipt;

public class BackupWriter {
	public void backupFile(String filename, HashMap<Integer, Receipt>map) {
		Set<Integer> keys = map.keySet();
		Iterator<Integer> it = keys.iterator();
		File file = new File(filename);		
		try {
			FileWriter fw = new FileWriter(file, true);
			while(it.hasNext()) {
				int number = it.next();
				Receipt receipt = map.get(number);
				String line = receipt.toBackupString();
				fw.write(line);
				fw.write("\n");
			}
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
