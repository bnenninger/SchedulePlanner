package assignmentStorage;

public enum AssignmentType {
	ASSIGNMENT("Assignment"), STUDY("Study"), EXAM("Exam");
	
	private final String name;
	
	AssignmentType(String name){
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
	public static String[] getTypes() {
		AssignmentType[] values = AssignmentType.values();
		String[] names = new String[values.length];
		for(int i = 0; i < names.length; i++) {
			names[i] = values[i].toString();
		}
		return names;
	}
}
