package ch.fhnw.cpib.project.km.syntax.concrete;

import ch.fhnw.cpib.project.km.syntax.abst.Program;

public interface IProgram
{
	Program toAbsSyn();
    void print(String indent);
}
