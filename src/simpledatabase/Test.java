package simpledatabase;

public class Test {
    private String selectText;
    private String fromText;
    private String joinText;
    private String whereText;
    private String orderText;
    FormRAtree tree;
    Operator root;
    Tuple tuple;
	boolean next = true;
	
	/*SQL Statement: SELECT Name 
	*				 FROM Student 
	*				 JOIN CourseEnroll 
	*				 WHERE CourseEnroll.courseID="COMP2021" 
	*				 ORDER BY Name;*/
	
	public void testLevel3(){
	   int cnt = 0;
	   selectText = "courseName";
       fromText = "Student";
       joinText = "CourseEnroll";
       whereText = "CourseEnroll.courseID=\"COMP2021\"";
       orderText = "Name";
       
       tree = new FormRAtree(selectText,fromText,joinText,whereText,orderText);
       root = tree.structureTree();
       
       tuple = root.next();
       System.out.println(tuple.getAttributeValue(0));       
       tuple = root.next();
       System.out.println(tuple.getAttributeValue(0));       
       tuple = root.next();
       System.out.println(tuple.getAttributeValue(0));       
	}
	public static void main(String args[]) {
		new Test().testLevel3();
	}
}
