package it.polito.tdp.dizionariograph.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.dizionariograph.db.WordDAO;

public class Model {
	Graph<String,DefaultEdge> graph;
	List<String> parole;

	public void createGraph(int numeroLettere) {
		WordDAO wDao = new WordDAO();
		parole=wDao.getAllWordsFixedLength(numeroLettere);  //LISTA PAROLE
		graph = new SimpleGraph<String,DefaultEdge>(DefaultEdge.class);  //CREO GRAFO
		Graphs.addAllVertices(graph, parole);
		this.aggiungiVertici(numeroLettere);
		return;
	}
	
	public void aggiungiVertici(int numL) {
		int cont=0;
		for(String p:parole)		
			for(String a:parole)
				if(!a.equals(p)) {
					
					for(int i=0;i<numL;i++) {
						if(cont>1){
							cont=0;
							break;
						}
						if(a.charAt(i)!=p.charAt(i))
							cont++;
					}
					if(cont==1) {
						graph.addEdge(p, a);
					}
						
				}

		
		return;
		
	}

	public List<String> displayNeighbours(String parolaInserita) {
		List<String> neighbour=new ArrayList<String>();
		for(String p:parole)
			if(graph.containsEdge(parolaInserita, p))
				neighbour.add(p);

		
		return neighbour;
	}

	public String findMaxDegree() {
		int grado=0;
		String sol="Non trovata";
		for(String p:parole) {
			if(grado<graph.outDegreeOf(p)) {
				grado=graph.outDegreeOf(p);
				sol=p;
			}
				
		}
		
		return "Parola: "+sol+"\nGrado: "+grado+"\nVicini: "+this.displayNeighbours(sol);
	}
}
