package simpledatabase;
import java.util.ArrayList;

public class Join extends Operator{

	private ArrayList<Attribute> newAttributeList;
	private String joinPredicate;
	private String commonAttr;
	ArrayList<Tuple> tuples1;
	private boolean fetch = false;

	
	//Join Constructor, join fill
	public Join(Operator leftChild, Operator rightChild, String joinPredicate){
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.joinPredicate = joinPredicate;
		newAttributeList = new ArrayList<Attribute>();
		tuples1 = new ArrayList<Tuple>();

	}

	
	/**
     * It is used to return a new tuple which is already joined by the common attribute
     * @return the new joined tuple
     */
	//The record after join with two tables
	@Override
	public Tuple next(){
		if (!fetch) {
			Tuple tuple;
			while ((tuple = leftChild.next()) != null) {
				tuples1.add(tuple);		
			}
			fetch = true;
		}
		if (rightChild.next() != null) {
			findCommonAttr();
			for (Attribute attr1: rightChild.getAttributeList()) {
				if (attr1.getAttributeName().equals(commonAttr)) {
					for (Tuple tup: tuples1) {
						for (Attribute attr2: tup.getAttributeList()) {
							if (attr2.getAttributeName().equals(commonAttr) && attr1.getAttributeValue().equals(attr2.getAttributeValue())) {
								newAttributeList = (ArrayList<Attribute>) tup.getAttributeList().clone();
								newAttributeList.remove(attr2);
								newAttributeList.addAll(rightChild.getAttributeList());
								return new Tuple(newAttributeList);
							}
						}	
					}
				}
			}
		}
		return null;
	}
	
	private void findCommonAttr() {
		for (Attribute attr1: rightChild.getAttributeList()) {
			for (Tuple tup: tuples1) {
				for (Attribute attr2: tup.getAttributeList()) {
					if (attr1.getAttributeName().equals(commonAttr = attr2.getAttributeName()))
						return;
				}
			}
		}
	}
	
	
	/**
     * The function is used to get the attribute list of the tuple
     * @return attribute list
     */
	public ArrayList<Attribute> getAttributeList(){
		if(joinPredicate.isEmpty())
			return child.getAttributeList();
		else
			return(newAttributeList);
	}

}