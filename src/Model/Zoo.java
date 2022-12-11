package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.print.attribute.HashAttributeSet;

import Exceptions.AnimalsVisitsException;
import Exceptions.DuplicatedValues;
import Exceptions.MaximumCapcityException;
import Utils.MyFileLogWriter;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

public class Zoo implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Zoo zoo = null;

	private HashMap<Integer,ZooEmployee> employees;
	private HashMap<Integer, Visitor> visitors;
	private HashMap<Integer, Mammal> mammals;
	private HashMap<Integer, Reptile> reptiles;
	private HashMap<Integer, Bird> birds;
	private HashMap<Integer, Section> sections;
	private HashMap<Integer,Snack>snacks;
	private HashMap<Integer,Snack>allSnacks;
	private HashMap<Integer, SnackBar> bars;
	private HashMap<ZooEmployee, HashSet<Animal>> animalTreatedByZooEmployee;
	private HashMap<Visitor, HashSet<Animal>> AnimalVisitsByPeople;
	private HashMap<Visitor, HashSet<Snack>> purchasesByVisitors;
    private HashMap<Integer, String> usernamesAndPasswords;
    private HashMap<String, ZooEmployee> usernamesAndEmployees;
	private int empSize = 1;
	private int visSize = 1;
	private int mammalSize = 1;
	private int birdSize = 1;
	private int reptileSize = 1;
	private int sectionSize = 1;
	private int snackBarSize = 1;
	private int snackSize = 1;
    
   /* public ArrayList<ZooEmployee> getEmpSize() {
		return empSize;
	}

	public void setEmpSize(ArrayList<ZooEmployee> empSize) {
		this.empSize = empSize;
	}*/

	public static Zoo getInstance()
	{
		if(zoo==null) {
			zoo = new Zoo();
		
			//readFile();
	
			}
		return zoo;
	}

	private Zoo() {
		employees = new HashMap<>();
		visitors = new HashMap<>();
		mammals =new HashMap<>();
		reptiles = new HashMap<>();
		birds = new HashMap<>();
		sections = new HashMap<>();
		snacks = new HashMap<>();
		bars = new HashMap<>();
		animalTreatedByZooEmployee= new HashMap<>();
		AnimalVisitsByPeople = new HashMap<>();
		purchasesByVisitors = new HashMap<>();
		usernamesAndPasswords = new HashMap<>();
		usernamesAndEmployees = new HashMap<>();
		setAllSnacks(new HashMap<Integer, Snack>());
	}
	
	/*public void serialize() {
		try {
			FileOutputStream fileOut= new FileOutputStream("Zoo.ser")
		}
	}*/
	
	///public static void writeToFile(Zoo zoo) {
	public void writeToFile() {
		
		try {
			FileOutputStream fileOut= new FileOutputStream("Zoo.ser");
			ObjectOutputStream o = new ObjectOutputStream(fileOut);
		
			o.writeObject(zoo);
			//o.flush();
			o.close();
			fileOut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void readFile() {

		try {
			// ObjectInputStream i = new ObjectInputStream(new FileInputStream("Zoo.ser"));
			/*
			 * zoo = (Zoo) i.readObject(); //zoo=(Zoo)i.readObject(); Visitor.idCounter =
			 * (int) i.readObject(); ZooEmployee.idCounter= (int) i.readObject();
			 * Mammal.idCounter = (int) i.readObject(); Bird.idCounter = (int)
			 * i.readObject(); Reptile.idCounter = (int) i.readObject(); Section.idCounter =
			 * (int) i.readObject();
			 * 
			 * SnackBar.idCounter = (int) i.readObject(); Snack.idCounter = (int)
			 * i.readObject(); i.close(); }
			 */
			FileInputStream fileIn = new FileInputStream("Zoo.ser");
			ObjectInputStream i = new ObjectInputStream(fileIn);
			zoo = (Zoo) i.readObject();
			i.close();
			fileIn.close();
			// section
			Section.idCounter = zoo.getSectionSize();
			// snackBar
			SnackBar.idCounter = zoo.getSnackBarSize();
			//snack
			Snack.idCounter = zoo.getSnackSize();
			
			Bird.idCounter = zoo.getBirdSize();
			
			Reptile.idCounter = zoo.getReptileSize();
			
			Visitor.idCounter = zoo.getVisSize();
			
			Mammal.idCounter = zoo.getMammalSize();
			
			ZooEmployee.idCounter = zoo.getEmpSize();
			
		} catch (FileNotFoundException e) {
			// zoo = new Zoo();
			zoo = new Zoo();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException c) {
			c.printStackTrace();
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

	public HashMap<Integer,ZooEmployee> getEmployees() {
		
		return employees;
	}

	public void setEmployees(HashMap<Integer,ZooEmployee> employees) {
		this.employees = employees;
	}

	public HashMap<Integer, Visitor> getVisitors() {
		return visitors;
	}

	public void setVisitorsById(HashMap<Integer, Visitor> visitors) {
		this.visitors = visitors;
	}

	public HashMap<Integer, Mammal> getMammals() {
		return mammals;
	}

	public void setMammalsById(HashMap<Integer, Mammal> mammals) {
		this.mammals= mammals;
	}

	public HashMap<Integer, Reptile> getReptiles() {
		return reptiles;
	}

	public void setReptilesById(HashMap<Integer, Reptile> reptiles) {
		this.reptiles= reptiles;
	}

	public HashMap<Integer, Bird> getBirds() {
		return birds;
	}

	public void setBirdsById(HashMap<Integer, Bird> birds) {
		this.birds = birds;
	}

	public HashMap<Integer, Section> getSections() {
		return sections;
	}

	public void setSections(HashMap<Integer, Section> sections) {
		this.sections = sections;
	}

	public HashMap<Integer, Snack> getSnacks() {
		return snacks;
	}

	public void setSnacksById(HashMap<Integer, Snack> snacks) {
		this.snacks = snacks;
	}

	public HashMap<Integer, SnackBar> getBars() {
		return bars;
	}

	public void setBars(HashMap<Integer, SnackBar> bars) {
		this.bars = bars;
	}



	/**
	 * @return the animalTreatedByZooEmployee
	 */
	public HashMap<ZooEmployee, HashSet<Animal>> getAnimalTreatedByZooEmployee() {
		return animalTreatedByZooEmployee;
	}

	/**
	 * @param animalTreatedByZooEmployee the animalTreatedByZooEmployee to set
	 */
	public void setAnimalTreatedByZooEmployee(HashMap<ZooEmployee, HashSet<Animal>> animalTreatedByZooEmployee) {
		this.animalTreatedByZooEmployee = animalTreatedByZooEmployee;
	}

	/**
	 * @return the animalVisitsByPeople
	 */
	public HashMap<Visitor, HashSet<Animal>> getAnimalVisitsByPeople() {
		return AnimalVisitsByPeople;
	}

	/**
	 * @param animalVisitsByPeople the animalVisitsByPeople to set
	 */
	public void setAnimalVisitsByPeople(HashMap<Visitor, HashSet<Animal>> animalVisitsByPeople) {
		AnimalVisitsByPeople = animalVisitsByPeople;
	}


	public boolean addEmployee(ZooEmployee employee){
		if(employee == null)
			return false;
		
		employee.setId(empSize);
		
		if(!getEmployees().containsKey(employee.getId())) {
			getEmployees().put(employee.getId(),employee); 
			employee.getSection().getEmployees().add(employee);
			empSize++;
		}
		else
			return false;

		return true;
	}

	public boolean addVisitor(Visitor v)  {
		if(v == null ) {
			return false;
		}
		
		v.setId(visSize);
		
		if(!getVisitors().containsKey(v.getId())) {
			
			getVisitors().put(v.getId(),v);
			visSize++;
		}
		else {
		
			return false;
		}
		return true;
	}

	public boolean addMammalById(Mammal m) {
		if(m == null)
			return false;

		//m.setId(mammalSize);
		if(!getMammals().containsKey(m.getId())) {
			getMammals().put(m.getId(),m); 
			m.getSection().getMammals().add(m);
			mammalSize++;
		}
		else
			return false;
		return true;
	}

	public boolean addReptileById(Reptile r) {
		if(r == null)
			return false;

		r.setId(reptileSize);
		if(!getReptiles().containsKey(r.getId())) {
			getReptiles().put(r.getId(),r);
			r.getSection().getReptiles().add(r);
			reptileSize++;
		}
		else
			return false;
		return true;
	}

	public boolean addBirdById(Bird b) {
		if(b == null)
			return false;
		
		b.setId(birdSize);

		if(!getBirds().containsKey(b.getId())) {
			getBirds().put(b.getId(),b) ;

			b.getSection().getBirds().add(b);
			birdSize++;
		}
		else
			return false;
		return true;
	}

	public boolean addSection(Section s) {

		if (s == null) {
			return false;
			
		}

		s.setId(sectionSize);

		if (!getSections().containsKey(s.getId())) {
			getSections().put(s.getId(), s);
			sectionSize++;
			return true;
		}

		return false;

	}

	public boolean addSnack(Snack s) {
		if(s == null)
			return false;
		
		s.setId(snackSize);

		if(!getSnacks().containsKey(s.getId())) {
			getSnacks().put(s.getId(),s);
			snackSize++;
			s.getBar().getSnacks().add(s);
			return true;
		}
		
		return false;
		
	}

	public boolean addSnackBar(SnackBar sb, Section s) {
		if(sb == null || s == null)
			return false;
		
		sb.setId(snackBarSize);

		if(!getBars().containsKey(sb.getId())) {
			getBars().put(sb.getId(),sb);
			s.setBar(sb);
			snackBarSize++;
			return true;
		}

		return false;	
		
	}

	public boolean removeEmployee(ZooEmployee employee) {
		if(employee == null)
			return false;

		getEmployees().remove(employee.getId());
		employee.getSection().getEmployees().remove(employee);
		return true;
	}

	public boolean removeVisitor(Visitor v) {
		if(v == null) {
			return false;
			
		}
	

		getVisitors().remove(v.getId());
		v.getSection().getVisitors().remove(v);
		return true;
	}

	public boolean removeMammal(Mammal m) {
		if(m == null)
			return false;

		getMammals().remove(m.getId());
		m.getSection().getMammals().remove(m);
		return true;
	}

	public boolean removeReptile(Reptile r) {
		if(r == null)
			return false;

		getReptiles().remove(r.getId());
		r.getSection().getReptiles().remove(r);
		return true;
	}

	public boolean removeBird(Bird b) {
		if(b == null)
			return false;

		getBirds().remove(b.getId());
		b.getSection().getBirds().remove(b);
		return true;
	}

	public boolean removeSection(Section oldSection, Section newSection) {
		if(oldSection == null || newSection == null)
			return false;
		//new 
		if(newSection.getBar() == null) {
			showAlert(AlertType.WARNING, "Warning", "The selected section does not have a bar!", null);
			//new
			return false;
		}
		if (oldSection.getBar() != null) {
			//newSection.getBar().setProfit(newSection.getBar().getProfit() + oldSection.getBar().getProfit());
			newSection.getBar().setProfit(newSection.getBar().getProfit() + 0.8*oldSection.getBar().getProfit());
			
			newSection.getBar().getSnacks().addAll(oldSection.getBar().getSnacks());
			
			for(Snack s : oldSection.getBar().getSnacks()) {
				s.setBar(newSection.getBar());
			}
			
		}
			
		for (ZooEmployee ze : oldSection.getEmployees())
			ze.setSection(newSection);
		for (Visitor v : oldSection.getVisitors())
			v.setSection(newSection);
		for (Mammal m : oldSection.getMammals())
			m.setSection(newSection);
		for (Reptile r : oldSection.getReptiles())
			r.setSection(newSection);
		for (Bird b : oldSection.getBirds())
			b.setSection(newSection);
		
//end
		newSection.getEmployees().addAll(oldSection.getEmployees());
		newSection.getVisitors().addAll(oldSection.getVisitors());
		newSection.getMammals().addAll(oldSection.getMammals());
		newSection.getReptiles().addAll(oldSection.getReptiles());
		newSection.getBirds().addAll(oldSection.getBirds());
		newSection.setTodayRevenue(newSection.getTodayRevenue() + oldSection.getTodayRevenue());
				
		//newSection.getBar().setProfit(newSection.getBar().getProfit() + 0.8*oldSection.getBar().getProfit());
		removeSnackBar(oldSection.getBar());
		getSections().remove(oldSection.getId());

		return true;
	}

	public boolean removeSnackBar(SnackBar sb) {
		if(sb == null)
			return false;

		sb.getSection().setBar(null);
		getBars().remove(sb.getId());
		return true;
	}

	public boolean removeSnack(Snack s) {
		if(s == null)
			return false;

		for(SnackBar sb : getBars().values()) {
			sb.getSnacks().remove(s);
		}

		getSnacks().remove(s.getId());
		return true;
	}

	public ZooEmployee getRealEmployee(int id) {
		return getEmployees().get(id);
	}

	public Visitor getRealVisitor(int id) {

		return getVisitors().get(id);
	}

	public Mammal getRealMammal(int id) {
		return getMammals().get(id);
	}

	public Reptile getRealReptile(int id) {

		return getReptiles().get(id);
	}

	public Bird getRealBird(int id) {

		return getBirds().get(id);
	}

	public Section getRealSection(int id) {
		return getSections().get(id);
	}

	public SnackBar getRealSnackBar(int id) {
		return getBars().get(id);
	}

	public Snack getRealSnack(int id) {

		return getSnacks().get(id);
	}

	public double checkTotalRevenue() {
		double revenue = 0.0;
		for(Section s : getSections().values()) {
			revenue += s.getTodayRevenue();
			//System.out.println(s.getTodayRevenue());
			if(s.getBar() != null) {
				System.out.println(s.getBar().getProfit());
				revenue += (s.getBar().getProfit()) * (SnackBar.getZooPercentage());
			}
		}
		return revenue;
	}

	public boolean newVisitorInZoo(Visitor v, Section s)  {
		boolean isValid = true;
		if(v == null || s == null) 
			isValid = false;

		if(!addVisitor(v)) {
			
			isValid = false;
		}
			


		if(isValid && !s.getVisitors().contains(v)) {
			s.getVisitors().add(v);
			v.setSection(s);
			double price = v.checkTicketPrice();
			s.setTodayRevenue(s.getTodayRevenue() + price);
			return true;
		}

		return false;
	}



	public ArrayList<Animal> getAllAnimalsBySectionMaxVisits(Section s) {
		if(s == null) {
			return null;
		}
		ArrayList<Animal> animals= new ArrayList<Animal>();



		for(Animal a : s.getBirds()) {

			animals.add(a);



		}

		for(Animal a : s.getReptiles()) {
			animals.add(a);

		}

		for(Animal a : s.getMammals()) {
			animals.add(a);


		}

		animals.sort(new Comparator<Animal>() {

			@Override
			public int compare(Animal o1, Animal o2) {
				String o1Class = o1.getClass().getSimpleName();
				String o2Class = o2.getClass().getSimpleName();
				if(o1Class.equals(o2Class)) {
					Integer count1= o1.getVisitCounter();
					Integer count2 = o2.getVisitCounter();
					return count1.compareTo(count2);
				}
				else
					return o1Class.compareTo(o2Class);
			}

		});
		return animals;

	}


	public ArrayList<Animal> allAnimalsByWorker(ZooEmployee employee){

		/*ArrayList<Animal> treatsBy = new ArrayList<Animal>();
		if(employee == null) {
			return null;
		}
		if(getEmployees().containsValue(employee)) {
	
			HashSet<Animal> animalsOfEmployee = getAnimalTreatedByZooEmployee().get(employee);

			if(animalsOfEmployee == null || animalsOfEmployee.isEmpty()) {
				return null;
			}
			
			for(Bird b : getBirds().values()) {
				if(!animalsOfEmployee.contains(b) && (!b.isCanFly()))
					treatsBy.add(b);
			}
			for(Mammal m : getMammals().values() ) {
				if(!animalsOfEmployee.contains(m) && (!m.isMeatEater()))
					treatsBy.add(m);
			}
			for(Reptile r : getReptiles().values() ) {
				if(!animalsOfEmployee.contains(r) && (r.isDangerous()))
					treatsBy.add(r);
			}


			treatsBy.sort(null);

		}
		return treatsBy;
		*/
		
		if (employee == null || (!getAnimalTreatedByZooEmployee().containsKey(employee)))
			return null;
		ArrayList<Animal> returnArray = new ArrayList<Animal>();
		HashSet<Animal> animalsTreatedByWorker = getAnimalTreatedByZooEmployee().get(employee);
		TreeSet<Animal> notTreated = new TreeSet<Animal>();
		for (Bird thisBird : getBirds().values()) {
			if (!animalsTreatedByWorker.contains(thisBird)) {// adding all the birds the worker did not treat and can't
																// fly
				if (thisBird.isCanFly() == false)
					notTreated.add(thisBird);
			}
		}
		for (Reptile thisReptile : getReptiles().values()) {
			if (!animalsTreatedByWorker.contains(thisReptile)) {// adding all the dangerous reptiles the worker did not
																// treat
				if (thisReptile.isDangerous())
					notTreated.add(thisReptile);
			}
		}
		for (Mammal thisMammal : getMammals().values()) {
			if (!animalsTreatedByWorker.contains(thisMammal)) {// adding all the meat eater mammals the worker did not
																// treat
				if (thisMammal.isMeatEater() == false)
					notTreated.add(thisMammal);
			}
		}

		returnArray.addAll(notTreated);// adding all the values to the arrayList
		return returnArray;
	}


	public ArrayList<Snack>  findAllSnackByWorker(SnackBar sb){
		ArrayList<Snack> snacks= new ArrayList<Snack>();

		for(Snack s : sb.getSnacks()) {
			if(s instanceof Drink) {
				Drink d = (Drink)s;
				if(!d.isSprinkel()&& d.isIceCube()) {

					snacks.add(d);
				}
			}
			else if(s instanceof Food) {
				Food f= (Food)s;
				if(f.isSpaciy() && !f.isPlate()) {
					snacks.add(f);
				}
			}
		}

		snacks.sort(new Comparator<Snack>() {

			@Override
			public int compare(Snack o1, Snack o2) {

				return o1.getSnackName().compareTo(o2.getSnackName());
			}


		});

		return snacks;


	} 

	public ArrayList<Reptile> reptilesSleepAtSeasson(){
		ArrayList<Reptile> reptiles = new ArrayList<Reptile>();
		if(getReptiles() !=null) {

			for(Reptile r : getReptiles().values() ) {
				if(r.isSeasonSleep() && (!r.isDangerous()) ) {
					reptiles.add(r);
				}
			}
		}

		reptiles.sort(new Comparator<Reptile>() {

			@Override
			public int compare(Reptile o1, Reptile o2) {
				if(o1.getName().equals(o2.getName())) {
					return o1.getGender().compareTo(o2.getGender());
				}
				return o1.getName().compareTo(o2.getName());
			}


		});
		return reptiles;

	}




	public TreeMap<Visitor, Double> geAllDiscountAmount(){


		TreeMap<Visitor, Double> Discount = new TreeMap<Visitor, Double>(new Comparator<Visitor>() {

			@Override
			public int compare(Visitor o1, Visitor o2) {
				Double o1Price = o1.getTicket().getValue()-o1.checkTicketPrice();
				Double o2Price = o2.getTicket().getValue()-o2.checkTicketPrice();
				return o1Price.compareTo(o2Price);
			}
		});

		for(Visitor v : getVisitors().values()) {

			Discount.put(v,  v.getTicket().getValue()-v.checkTicketPrice());

		}

		return Discount;
	}



	public Section getMaxVisitorsVSMaxWorkers() {
		PriorityQueue<Section> pqSection = new PriorityQueue<Section>(new Comparator<Section>() {

			@Override
			public int compare(Section o1, Section o2) {
				int sub1= o1.getVisitors().size()-o1.getEmployees().size();
				int sub2 = o2.getVisitors().size()-o2.getEmployees().size();

				return sub2-sub1;
			}


		});

		for(Section s: getSections().values()) {

			pqSection.add(s);

		}
		return pqSection.poll();
	}

	public HashMap<Visitor, HashSet<Snack>> getPurchasesByVisitors() {
		return purchasesByVisitors;
	}

	public void setPurchasesByVisitors(HashMap<Visitor,HashSet<Snack>> purchasesByVisitors) {
		this.purchasesByVisitors = purchasesByVisitors;
	}
	
	private void showAlert(AlertType type, String title, String header, String text) {
		Alert alert = new Alert(type);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setResizable(true);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(text);
		alert.showAndWait();
	}

	public HashMap<Integer, String> getUsernamesAndPasswords() {
		return usernamesAndPasswords;
	}

	public void setUsernamesAndPasswords(HashMap<Integer, String> usernamesAndPasswords) {
		this.usernamesAndPasswords = usernamesAndPasswords;
	}

	public HashMap<String, ZooEmployee> getUsernamesAndEmployees() {
		return usernamesAndEmployees;
	}

	public void setUsernamesAndEmployees(HashMap<String, ZooEmployee> usernamesAndEmployees) {
		this.usernamesAndEmployees = usernamesAndEmployees;
	}

	public HashMap<Integer,Snack> getAllSnacks() {
		return allSnacks;
	}

	public void setAllSnacks(HashMap<Integer,Snack> allSnacks) {
		this.allSnacks = allSnacks;
	}

	public int getEmpSize() {
		return empSize;
	}

	public void setEmpSize(int empSize) {
		this.empSize = empSize;
	}

	public int getVisSize() {
		return visSize;
	}

	public void setVisSize(int visSize) {
		this.visSize = visSize;
	}

	public int getMammalSize() {
		return mammalSize;
	}

	public void setMammalSize(int mammalSize) {
		this.mammalSize = mammalSize;
	}

	public int getBirdSize() {
		return birdSize;
	}

	public void setBirdSize(int birdSize) {
		this.birdSize = birdSize;
	}

	public int getSectionSize() {
		return sectionSize;
	}

	public void setSectionSize(int sectionSize) {
		this.sectionSize = sectionSize;
	}

	public int getReptileSize() {
		return reptileSize;
	}

	public void setReptileSize(int reptileSize) {
		this.reptileSize = reptileSize;
	}

	public int getSnackSize() {
		return snackSize;
	}

	public void setSnackSize(int snackSize) {
		this.snackSize = snackSize;
	}

	public int getSnackBarSize() {
		return snackBarSize;
	}

	public void setSnackBarSize(int snackBarSize) {
		this.snackBarSize = snackBarSize;
	}
	
	

}
