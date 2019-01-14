package ch.fhnw.cpib.project.km.analysis;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	public Context rootContext;
	public Map<Object, Context> contextMapping = new HashMap<>();
}
