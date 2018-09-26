package ch.fhnw.cpib.project.km.token;

public abstract class Base {
	public String toString() {
		return this.getClass().getSimpleName().toUpperCase() + ", ";
	}
}
