package assignmentStorage;

import java.io.Serializable;
import java.time.LocalDate;

public class Assignment implements Comparable<Assignment>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5825352641623013736L;
	
	private String name;
	// TODO implement different class selection
	private LocalDate dueDate;
	private AssignmentType type;
	private String description;

	public Assignment(String name, LocalDate dueDate, AssignmentType type) {
		this.name = name;
		this.dueDate = dueDate;
		this.type = type;
	}

	@Override
	public int compareTo(Assignment other) {
		//compares the due dates of the assignments
		return dueDate.compareTo(other.dueDate);
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setType(AssignmentType type) {
		this.type = type;
	}
	
	public AssignmentType getType() {
		return type;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return "Assignment[" + name + "," + dueDate + "]";
	}
}
