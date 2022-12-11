package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.PrimitiveIterator;

import Exceptions.MaximumCapcityException;
import Utils.Discount;
import Utils.Gender;
import Utils.MyFileLogWriter;
import Utils.TicketType;
import javafx.scene.control.Alert;
import javafx.scene.control.Toggle;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Region;

public class Visitor extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Zoo zoo = Zoo.getInstance();
	static int idCounter = 1;

	private TicketType ticket;
	private Discount discount;

	public Visitor(String firstName, String lastName, LocalDate date, Gender gender
			, TicketType ticket, Discount discount, Section section) {
		super(idCounter++,firstName, lastName, date, gender, section);
		this.ticket = ticket;
		this.discount = discount;
		idCounter = getId();
	}
	


	public Visitor(String firstName, String lastName) {
		super(firstName, lastName);
	}



	public Visitor(int id) {
		super(id);
	}

	public TicketType getTicket() {
		return ticket;
	}

	public void setTicket(TicketType ticket) {
		this.ticket = ticket;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+" Name: "+getFirstName()+" "+getLastName()+", TicketType: "+ticket;
	}

	public void moveVisitorToSection(Section newSection) throws MaximumCapcityException {
			

		if(newSection == null || 
				(this.getSection().getMaxCapacity()/2) <= this.getSection().getVisitors().size()-1) {
			throw new MaximumCapcityException();
		}

		this.getSection().getVisitors().remove(this);

		this.setSection(newSection);
		newSection.getVisitors().add(this);
		
	}
	
	
	public double checkTicketPrice() {
		double perc = 100-getDiscount().getPercentage();
		double price = perc*ticket.getValue()/100;
		return price;
	}

	public boolean purchaseSnack(Snack s) {
		
		boolean isValid = true;
		HashSet<Snack> snacksByVisitor = zoo.getPurchasesByVisitors().get(this);

		if(s == null)
			isValid = false;
		
	if( s instanceof Drink) {
		
		Drink tmp = (Drink)s;
		 
		if(isValid && tmp.isSprinkel() && this.getSection().getBar().getSnacks().contains(tmp)){
			this.getSection().getBar().setProfit(this.getSection().getBar().getProfit() 
					+ s.getPrice()+5);
			System.out.println("im here");
			boolean res = this.getSection().getBar().getSnacks().remove(tmp);
			System.out.println(res);
			/*if(snacksByVisitor == null) {
				snacksByVisitor = new ArrayList<>();
			}
			snacksByVisitor.add(s);
			zoo.getPurchasesByVisitors().put(this, snacksByVisitor);*/
			if( !zoo.getPurchasesByVisitors().containsKey(this)) {
				snacksByVisitor = new HashSet<Snack>();
				snacksByVisitor.add(tmp);
				zoo.getPurchasesByVisitors().put(this, snacksByVisitor);
			}
			zoo.getPurchasesByVisitors().get(this).add(tmp);
			
			
			return true;
		}
		
		else if(isValid && !tmp.isSprinkel() && this.getSection().getBar().getSnacks().contains(tmp)){
			this.getSection().getBar().setProfit(this.getSection().getBar().getProfit() 
					+ s.getPrice());
			this.getSection().getBar().getSnacks().remove(tmp);
			/*if(snacksByVisitor == null) {
				snacksByVisitor = new ArrayList<>();
			}
			snacksByVisitor.add(s);
			zoo.getPurchasesByVisitors().put(this, snacksByVisitor); */
			
			if( !zoo.getPurchasesByVisitors().containsKey(this)) {
				snacksByVisitor = new HashSet<Snack>();
				snacksByVisitor.add(tmp);
				zoo.getPurchasesByVisitors().put(this, snacksByVisitor);
			}
			zoo.getPurchasesByVisitors().get(this).add(tmp);
			
			return true;
		}
			
		
		else
			return false;
		
	}
	
	else if( s instanceof Food) {
		
		Food tmp = (Food)s;
		 
		if(isValid && tmp.isPlate() && this.getSection().getBar().getSnacks().contains(tmp)){
			this.getSection().getBar().setProfit(this.getSection().getBar().getProfit() 
					+ s.getPrice()+20);
			this.getSection().getBar().getSnacks().remove(tmp);
			
			/*if(snacksByVisitor == null) {
				snacksByVisitor = new ArrayList<>();
			}
			snacksByVisitor.add(s);
			zoo.getPurchasesByVisitors().put(this, snacksByVisitor);*/
			
			if( !zoo.getPurchasesByVisitors().containsKey(this)) {
				snacksByVisitor = new HashSet<Snack>();
				snacksByVisitor.add(tmp);
				zoo.getPurchasesByVisitors().put(this, snacksByVisitor);
			}
			zoo.getPurchasesByVisitors().get(this).add(tmp);
			

			return true;
			
		}
		
		else if(isValid && !tmp.isPlate() && this.getSection().getBar().getSnacks().contains(tmp)){
			this.getSection().getBar().setProfit(this.getSection().getBar().getProfit() 
					+ s.getPrice());
			this.getSection().getBar().getSnacks().remove(tmp);
			//zoo.removeSnack(s);
			/*if(snacksByVisitor == null) {
				snacksByVisitor = new ArrayList<>();
			}
			snacksByVisitor.add(s);
			zoo.getPurchasesByVisitors().put(this, snacksByVisitor);*/
			
			if( !zoo.getPurchasesByVisitors().containsKey(this)) {
				snacksByVisitor = new HashSet<Snack>();
				snacksByVisitor.add(tmp);
				zoo.getPurchasesByVisitors().put(this, snacksByVisitor);
			}
			zoo.getPurchasesByVisitors().get(this).add(tmp);
			

			return true;
			
			
		}
		else {
			return false;
		}
		
	}
	
		else
		{
			return false;
		}
			
	}
	
	


}
