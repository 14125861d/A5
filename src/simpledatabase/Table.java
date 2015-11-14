package simpledatabase;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Table extends Operator{
	private BufferedReader br = null;
	private boolean getAttribute=false;
	private String attrLine;
	private String typeLine;
	private Tuple tuple;

	
	public Table(String from){
		this.from = from;
		
		//Create buffer reader
		try{
			br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/datafile/"+from+".csv")));
		}
		catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	
	/**
     * Create a new tuple and return the tuple to its parent.
     * Set the attribute list if you have not prepare the attribute list
     * @return the tuple
     */
	@Override
	public Tuple next(){
		try {
			if (!getAttribute) {
				attrLine = br.readLine();
				typeLine = br.readLine();
				getAttribute = true;
			}
			String data = br.readLine();
			if (data.isEmpty())
				return null;
			tuple = new Tuple(attrLine, typeLine, data);
			tuple.setAttributeName();
			tuple.setAttributeType();
			tuple.setAttributeValue();
			return tuple;
		} catch (Exception e) {}
		return null;
	}
	

	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return tuple.getAttributeList();
	}
	
}