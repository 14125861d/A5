package simpledatabase;
import java.util.ArrayList;

public class Selection extends Operator{
	
	ArrayList<Attribute> attributeList;
	String whereTablePredicate;
	String whereAttributePredicate;
	String whereValuePredicate;

	
	public Selection(Operator child, String whereTablePredicate, String whereAttributePredicate, String whereValuePredicate) {
		this.child = child;
		this.whereTablePredicate = whereTablePredicate;
		this.whereAttributePredicate = whereAttributePredicate;
		this.whereValuePredicate = whereValuePredicate;
		attributeList = new ArrayList<Attribute>();

	}
	
	
	/**
     * Get the tuple which match to the where condition
     * @return the tuple
     */
	@Override
	public Tuple next(){
		Tuple tuple;
		if ((tuple = child.next()) != null) {
			if (child.from.equals(whereTablePredicate)) {
				attributeList = new ArrayList<Attribute>();
				for (Attribute attr: getAttributeList()) {
					if (attr.getAttributeName().equals(whereAttributePredicate) && attr.getAttributeValue().equals(whereValuePredicate))
						return tuple;
				}
				return next();
			} else {
				return tuple;
			}
		}
		return null;
			
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return the attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		
		return(child.getAttributeList());
	}

	
}