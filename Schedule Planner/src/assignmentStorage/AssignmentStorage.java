package assignmentStorage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.SortedSet;
import java.util.TreeSet;

import controller.AssignmentEditUI;

//TODO make this implementation faster
//currently designed to be function
public class AssignmentStorage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -296259272907653448L;
	
	private SortedSet<Assignment> assignments;
	
	public AssignmentStorage() {
		assignments = new TreeSet<Assignment>();
	}
	
	public Assignment[] getAssignmentArray() {
		return assignments.toArray(new Assignment[0]);
	}
	
	public void add(Assignment a) {
		assignments.add(a);
	}
	
	public void edit(Assignment a) {
		AssignmentEditUI editor = new AssignmentEditUI(a);
		editor.showAndReturn();
		remove(a);
		add(a);
	}
	
	public void createNewAssignment() {
		AssignmentEditUI editor = new AssignmentEditUI();
		Assignment newAssignment = editor.showAndReturn();
		if(newAssignment != null) {
			add(newAssignment);
		}
	}
	
	public void remove(Assignment a) {
		assignments.remove(a);
	}
	
	public void changeDueDate(Assignment a, LocalDate newDate) {
		assignments.remove(a);
		a.setDueDate(newDate);
		assignments.add(a);
	}
}
