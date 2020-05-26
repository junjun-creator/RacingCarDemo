package RacingcarDemo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class RacingcarDemo {
	public static int Generation() {
		int randNum;
		int go = 1;
		int stop = 2;
		
		randNum = (int)(Math.random()*10-1);
		if(randNum >= 4) {
			return go;
		}else {
			return stop;
		}
	}

	public static void main(String[] args) {
		LinkedHashMap<String, Integer> carName = new LinkedHashMap<String,Integer>();// Arraylist 사용해서 이름 넣기.
		int check=0;
		while(check == 0) {
			carName.clear();
			System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분, 5자 미만)");
			Scanner sc = new Scanner(System.in);
			String name1;
			sc = new Scanner(sc.next()).useDelimiter("\\s*,\\s*"); //구분자 설정.
			do {
				name1 = sc.next();
				carName.put(name1,0);
				if(name1.length()>=6) {
					System.out.println("자동차 이름은 5자 이하입니다.");
					check =0;
					break;
				}else {check++;}	
			}while (sc.hasNext());
		}
		System.out.println("시도할 횟수는 몇회인가요 ?");
		Scanner count = new Scanner(System.in);
		int c1 = count.nextInt();
		System.out.println();
		
		for(int i=0;i<c1;i++) {
			for(Entry<String, Integer> entry : carName.entrySet()) {
				int operator = (int)entry.getValue();
				if(Generation() == 1) {
					operator++;
					carName.put(entry.getKey(),operator);
				}
				System.out.print(entry.getKey()+" : ");
				for(int j=0;j<operator;j++) {
					System.out.print("*");
				}System.out.println();	
			}System.out.println("\n");
		}
		
		Map.Entry<String, Integer> maxEntry = null;
		LinkedHashMap<String, Integer> compareList = new LinkedHashMap<String,Integer>();
		for (Map.Entry<String, Integer> entry : carName.entrySet())
		{
		    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) >= 0)
		    {
		    	maxEntry = entry;
		    	compareList.put(maxEntry.getKey(), maxEntry.getValue());
		    }  
		}

		Map<String,Integer> sorted =
			    compareList.entrySet().stream()
			       .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
			       .limit(c1)
			       .collect(Collectors.toMap(
			          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
		
		String firstKey = sorted.keySet().stream().findFirst().get();
		int firstValue = sorted.values().stream().findFirst().get();
		
		LinkedHashMap<String,Integer> winner1 = new LinkedHashMap<String,Integer>();
		winner1.putAll(sorted);
		ArrayList<String> remove = new ArrayList<String>();
		for (Map.Entry<String, Integer> entry : winner1.entrySet())
		{
		    if(entry.getValue() != firstValue) {
		    	remove.add(entry.getKey());
		    }
		}
		
		Iterator<String> it = remove.iterator();
		while(it.hasNext()) {
			winner1.remove(it.next());
		}
		System.out.println(winner1.keySet()+"가 최종우승 하였습니다.");
	}

}
