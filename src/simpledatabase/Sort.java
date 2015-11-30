package simpledatabase;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sort extends Operator{
	
	private ArrayList<Attribute> newAttributeList;
	private String orderPredicate;
	private int index = 0;
	ArrayList<Tuple> tuplesResult;
	private boolean fetch = false;

	
	public Sort(Operator child, String orderPredicate){
		this.child = child;
		this.orderPredicate = orderPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuplesResult = new ArrayList<Tuple>();
	}
	
	
	/**
     * The function is used to return the sorted tuple
     * @return tuple
     */
	@Override
	public Tuple next(){
		if (!fetch) {
			Tuple tuple;
			while ((tuple = child.next()) != null) {
				tuplesResult.add(tuple);
			}
			final String order = orderPredicate;
			Collections.sort(tuplesResult, new Comparator<Tuple>() {
		        @Override
		        public int compare(Tuple tuple1, Tuple tuple2) {
		        	Object lv = null;
		        	Object rv = null;
		        	for (Attribute attr: tuple1.getAttributeList()) {
		        		if (attr.getAttributeName().equals(order)) 
		        			lv = attr.getAttributeValue();
		        	}
		        	for (Attribute attr: tuple2.getAttributeList()) {
		        		if (attr.getAttributeName().equals(order))
		        			rv = attr.getAttributeValue();
		        	}
		        	return ((Comparable) lv).compareTo(rv);
		        }
		    });
			fetch = true;
		}
		if (tuplesResult.size() > index) {
			Tuple tuple = tuplesResult.get(index++);
			newAttributeList = tuple.getAttributeList();
			return tuple;
		} else {
			return null;
		}
		
	}
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		return newAttributeList;
	}

}